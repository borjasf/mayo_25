package documentos.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import documentos.modelo.Documento;

public interface RepositorioDocumentosMongo 
	extends RepositorioDocumentos, MongoRepository<Documento, String> {

	@Query("{'usuario': ?0 }")
	List<Documento> recuperarByPropietario(String propietario);
}