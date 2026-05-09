package documentos.test.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import documentos.servicio.IServicioDocumentos;
import repositorio.EntidadNoEncontrada;

@Component
public class ConsumidorEventos {
	//Se inyecta el servicio correspondiente.
	@Autowired
	private IServicioDocumentos servicio;
	
	//El traductor de JSON a Java.
	private ObjectMapper objectMapper = new ObjectMapper();
	
	// Le dices a Spring: "Ejecuta este método cuando llegue algo a la cola X"
	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
	// Si el método del examen no lanza excepciones chequedas, se debe quitar el "throws EntidadNoEncontrada".
    public void handleEvent(Message mensaje) throws EntidadNoEncontrada {
		System.out.println("Mensaje recibido: "+mensaje);
		String body = new String(mensaje.getBody());
		System.out.println("Evento: "+body);
		
		try {
			//Parsear el JSON del evento
			JsonNode node = objectMapper.readTree(body);
			//Verificar el tipo de mensaje, ESTO ES LO QUE CAMBIA DE UN EXAMEN A OTRO.
			
			//Cambio el nombre del evento esperado.
			if(node.has("tipo")&&"usuario_eliminado".equals(node.get("tipo").asText())) {
				
				//Extraigo los datos del JSON que pida el enunciado.
				String usuarioEliminado = node.get("usuario").asText();
				System.out.println("Elimnando los documentos del usuario "+usuarioEliminado);
				//Eliminar los documentos del usuario, se llama al servicio inyectado arriba.
				this.servicio.eliminarDocumentosPropietario(usuarioEliminado);
				System.out.println("Documentos del usuario "+usuarioEliminado+" eliminados correctamente.");
			}
		} catch(Exception e) {
			System.err.println("Error procesando evento: "+e.getMessage());
			e.printStackTrace();
		}
	}
}
