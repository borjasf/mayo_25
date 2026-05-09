package documentos.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import documentos.modelo.Documento;


//SI ES MONGO, SE HEREDA DE MONGOREPOSITORY, SI ES SQL SE HEREDA DE JPAREPOSITORY, 
//(SI ES JAX RS NO SE HEREDA DE NADA, HAY Q CREAR LA QUERY MANUALMENTE)
public interface RepositorioDocumentosMongo 
	extends RepositorioDocumentos, MongoRepository<Documento, String> {

	//Query automatica de spring. El nombre del metodo tiene que empezar por findBy, luego el nombre del campo por el que se quiere filtrar, en este caso propietario, y luego el tipo de dato del campo, en este caso String.
	List<Documento> findByPropietario(String propietario); //spring hace la query solo, en jax rs hay q crearla
}