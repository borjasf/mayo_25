package documentos.repositorio;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import documentos.modelo.Documento;

@NoRepositoryBean
public interface RepositorioDocumentos extends PagingAndSortingRepository<Documento, String> {
	
}
