package documentos.repositorio;


import java.io.IOException;

import org.bson.BsonValue;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import documentos.modelo.Documento;
import repositorio.RepositorioMongoDB;
import utils.MongoDBConnectionManager;
import utils.PropertiesReader;

public class RepositorioDocumentosMongoDB extends RepositorioMongoDB<Documento> {

	private MongoCollection<Documento> collection;
	protected MongoDatabase database;

	public RepositorioDocumentosMongoDB() throws IOException {

		MongoDBConnectionManager manager = MongoDBConnectionManager.getMongoDBConnectionManager();
		MongoClient mongoClient = manager.getMongoClient();
		PropertiesReader properties = new PropertiesReader("mongo.properties");
		String databaseString = properties.getProperty("mongodatabase");
		
		database = mongoClient.getDatabase(databaseString);
		
		CodecRegistry pojoCodecRegistry = CodecRegistries
				.fromProviders(PojoCodecProvider.builder().automatic(true).build());
		
		CodecRegistry defacultCodecRegistry = CodecRegistries
				.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
		
		// CAMBIAR ESTO
		collection = database.getCollection("documentos",Documento.class)
				.withCodecRegistry(defacultCodecRegistry);
		
		
	}

	@Override
	public MongoCollection<Documento> getCollection() {
		return collection;
	}

	@Override
	public Document getFilterById(String id) {
		return new Document("_id", new ObjectId(id));
		
		//return new Document("_id", id); // si manejáramos los identificadores como tipo String
		
	}

	@Override
	public String getIdValue(BsonValue valor) {		
		return valor.asObjectId().getValue().toString();
		
		//return valor.asString().getValue().toString(); // si manejáramos los identificadores como tipo String
	}

}