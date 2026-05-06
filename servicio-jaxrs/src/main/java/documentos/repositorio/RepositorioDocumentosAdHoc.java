package documentos.repositorio;

import java.util.List;

import documentos.modelo.Documento;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import repositorio.RepositorioString;

/*
 * Esta interfaz extiende la definición genérica con operaciones *ad hoc* de consulta.
 * 
 * Es una interfaz concreta para una entidad (Obra).
 */
public interface RepositorioDocumentosAdHoc extends RepositorioString<Documento> {

	public List<Documento> recuperarByPropietario(String nombreAutor) throws RepositorioException, EntidadNoEncontrada;	
	
}