package documentos.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import documentos.modelo.Documento;

public interface RepositorioDocumentosMongo 
	extends RepositorioDocumentos, MongoRepository<Documento, String> {

	
	List<Documento> findByPropietario(String propietario); //spring hace la query solo, en jax rs hay q crearla
}