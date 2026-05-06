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
	@Autowired
	private IServicioDocumentos servicio;
	private ObjectMapper objectMapper = new ObjectMapper();
	

	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleEvent(Message mensaje) throws EntidadNoEncontrada {
		System.out.println("Mensaje recibido: "+mensaje);
		String body = new String(mensaje.getBody());
		System.out.println("Evento: "+body);
		
		try {
			//Parsear el JSON del evento
			JsonNode node = objectMapper.readTree(body);
			//Verificar el tipo de mensaje
			if(node.has("tipo")&&"usuario_eliminado".equals(node.get("tipo").asText())) {
				String usuarioEliminado = node.get("usuario").asText();
				System.out.println("Elimnando los documentos del usuario "+usuarioEliminado);
				//Eliminar los documentos del usuario
				this.servicio.eliminarDocumentosPropietario(usuarioEliminado);
				System.out.println("Documentos del usuario "+usuarioEliminado+" eliminados correctamente.");
			}
		} catch(Exception e) {
			System.err.println("Error procesando evento: "+e.getMessage());
			e.printStackTrace();
		}
	}
}
