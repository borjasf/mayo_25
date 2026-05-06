package documentos.servicio;

import java.util.List;

import documentos.modelo.Documento;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public interface IServicioDocumentos {

	String crearDocumento(String usuario, String contenido) throws  RepositorioException;

	Documento getDocumento(String idDocumento) throws EntidadNoEncontrada, RepositorioException;

	void eliminarDocumento(String idDocumento) throws EntidadNoEncontrada,  RepositorioException;

	void anadirColaborador(String idDocumento, String usuario) throws EntidadNoEncontrada,  RepositorioException;

	void eliminarColaborador(String idDocumento, String usuario) throws EntidadNoEncontrada,  RepositorioException;

	void eliminarDocumentosPropietario(String nombrePropietario) throws EntidadNoEncontrada,  RepositorioException;

	List<Documento> recuperarByPropietario(String nombrePropietario) throws EntidadNoEncontrada,  RepositorioException;

	List<Documento> getDocumentos() throws EntidadNoEncontrada,  RepositorioException;
	// Page<EncuestaResumen> getListadoPaginado(Pageable pageable);
}
