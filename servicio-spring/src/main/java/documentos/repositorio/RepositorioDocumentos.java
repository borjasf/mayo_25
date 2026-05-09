package documentos.repositorio;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import documentos.modelo.Documento;


//NoRepositoryBean es para que no conecte esta interfaz con la base de datos, es solo para que hereden las otras interfaces.
//PageAndSortingRepository es una interfaz de spring que tiene los metodos .save, .findById, .deleteById, etc. 
//y ademas tiene metodos para paginar y ordenar los resultados, como .findAll(Pageable pageable) o .findAll(Sort sort).
public interface RepositorioDocumentos extends PagingAndSortingRepository<Documento, String> {
	
}
