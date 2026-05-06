package documentos.servicio;

import java.util.List;

import documentos.modelo.Documento;
import repositorio.EntidadNoEncontrada;

public interface IServicioDocumentos {

	String crearDocumento(String usuario, String contenido);

	Documento getDocumento(String idDocumento) throws EntidadNoEncontrada;

	void eliminarDocumento(String idDocumento) throws EntidadNoEncontrada;

	void anadirColaborador(String idDocumento, String usuario) throws EntidadNoEncontrada;

	void eliminarColaborador(String idDocumento, String usuario) throws EntidadNoEncontrada;

	void eliminarDocumentosPropietario(String nombrePropietario) throws EntidadNoEncontrada;

	List<Documento> recuperarByPropietario(String nombrePropietario) throws EntidadNoEncontrada;

	List<Documento> getDocumentos() throws EntidadNoEncontrada;
	// Page<EncuestaResumen> getListadoPaginado(Pageable pageable);
}
