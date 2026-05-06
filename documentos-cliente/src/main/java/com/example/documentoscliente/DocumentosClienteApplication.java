package com.example.documentoscliente;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.documentoscliente.client.DocumentClient;
import com.example.documentoscliente.model.Documento;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SpringBootApplication
public class DocumentosClienteApplication {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DocumentosClienteApplication.class, args);
	}

	@Bean
	public DocumentClient documentClient() {
		// Ajustar la base URL según el servicio de documentos (ej:
		// http://localhost:8080/api/)
		Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/api/")
				.addConverterFactory(GsonConverterFactory.create()).build();

		return retrofit.create(DocumentClient.class);
	}

	@Bean
	public CommandLineRunner runner(DocumentClient client) {
		return args -> {
			try {
				// Crear documento
				Documento nuevo = new Documento("user1", "Contenido de prueba");
				retrofit2.Response<Void> respCreate = client.createDocumento(nuevo).execute();
				if (respCreate.isSuccessful()) {
					String location = respCreate.headers().get("Location");
					String id = location.substring(location.lastIndexOf("/") + 1);
					System.out.println("Documento creado con id: " + id);

					// Recuperar

					retrofit2.Response<Documento> respGet = client.getDocumento(id).execute();
					if (respGet.isSuccessful()) {
						System.out.println("Recuperado: " + respGet.body());
					} else {
						System.out.println("Error recuperando: " + respGet.code());
					}

					// Eliminar
					retrofit2.Response<Void> respDel = client.deleteDocumento(id).execute();
					System.out.println("Borrado, código: " + respDel.code());
				} else {
					System.out.println("Error creando documento: " + respCreate.code());

				}

				///// Pruebas evento
				System.out.println("------------PRUEBAS DEL EVENTO------------");
				System.out.println("Creando dos documentos del user1");
				Documento n1 = new Documento("user1", "Contenido de prueba");
				retrofit2.Response<Void> res = client.createDocumento(n1).execute();
				Documento n2 = new Documento("user1", "Contenido de prueba");
				retrofit2.Response<Void> res2 = client.createDocumento(n2).execute();
				if (res.isSuccessful() && res2.isSuccessful()) {
					System.out.println("Documentos creados correctamente");
					System.out.println("Recuperando documentos del user1");
					retrofit2.Response<List<Documento>> respGet = client.recuperarDocumentosByPropietario("user1")
							.execute();
					System.out.println("Documentos recuperados: " + respGet.body());
					System.out.println("Evento usuario_eliminado publicado");
					Map<String, String> evento = new HashMap<>();
					evento.put("tipo", "usuario_eliminado");
					evento.put("usuario", "user1");

					rabbitTemplate.convertAndSend("arso-exchange", "arso", evento);
					Thread.sleep(2000); // espera 2 segundos a que el consumidor procese
					System.out.println("Recuperando documentos del user1 despues de borrarlos");
					retrofit2.Response<List<Documento>> respGet2 = client.recuperarDocumentosByPropietario("user1").execute();
					System.out.println("Documentos recuperados: " + respGet2.body());
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}
}
