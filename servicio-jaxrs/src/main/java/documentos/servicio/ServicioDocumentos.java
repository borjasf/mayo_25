
package documentos.servicio;

import java.util.List;

import documentos.modelo.Documento;
import documentos.repositorio.RepositorioDocumentosAdHocMongoDB;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.RepositorioException;


public class ServicioDocumentos implements IServicioDocumentos {

	private RepositorioDocumentosAdHocMongoDB repositorio = FactoriaRepositorios.getRepositorio(Documento.class);


	@Override
	public String crearDocumento(String usuario, String contenido) throws RepositorioException {
		// Control de integridad de los datos

				if (usuario == null || usuario.isEmpty())
					throw new IllegalArgumentException("usuario: no debe ser nulo ni vacio");
				if (contenido == null || contenido.isEmpty())
					throw new IllegalArgumentException("contenido: no debe ser nulo ni vacio");

		
		

				Documento doc = new Documento(usuario,contenido);

				String id = repositorio.add(doc);

				return id;
	}
	

	@Override
	public Documento getDocumento(String idDocumento) throws EntidadNoEncontrada, RepositorioException {
		if (idDocumento == null || idDocumento.isEmpty())
			throw new IllegalArgumentException("idDocumento: no debe ser nulo ni vacio");

		 return repositorio.getById(idDocumento);
		
	}

	@Override
	public void eliminarDocumento(String idDocumento) throws EntidadNoEncontrada, RepositorioException {
		if (idDocumento == null || idDocumento.isEmpty())
			throw new IllegalArgumentException("idDocumento: no debe ser nulo ni vacio");

		Documento doc = getDocumento(idDocumento);

		repositorio.delete(doc);
		
	}

	@Override
	public void anadirColaborador(String idDocumento, String usuario) throws EntidadNoEncontrada, RepositorioException {
		if (idDocumento == null || idDocumento.isEmpty())
			throw new IllegalArgumentException("idDocumento: no debe ser nulo ni vacio");
		if (usuario == null || usuario.isEmpty())
			throw new IllegalArgumentException("usuario: no debe ser nulo ni vacio");
		Documento doc = getDocumento(idDocumento);
		if(!doc.getColaboradores().contains(usuario)) {
			doc.getColaboradores().add(usuario);
		}
		repositorio.update(doc);
		
	}

	@Override
	public void eliminarColaborador(String idDocumento, String usuario) throws EntidadNoEncontrada, RepositorioException {
		if (idDocumento == null || idDocumento.isEmpty())
			throw new IllegalArgumentException("idDocumento: no debe ser nulo ni vacio");
		if (usuario == null || usuario.isEmpty())
			throw new IllegalArgumentException("usuario: no debe ser nulo ni vacio");
		Documento doc = getDocumento(idDocumento);
		if(doc.getColaboradores().contains(usuario)) {
			doc.getColaboradores().remove(usuario);
		}
		repositorio.update(doc);
		
	}

	@Override
	public List<Documento> recuperarByPropietario(String nombrePropietario) throws EntidadNoEncontrada, RepositorioException {
		if (nombrePropietario == null || nombrePropietario.isEmpty())
			throw new IllegalArgumentException("nombrePropietario: no debe ser nulo ni vacio");
		return repositorio.recuperarByPropietario(nombrePropietario);
	}



	@Override
	public List<Documento> getDocumentos() throws EntidadNoEncontrada, RepositorioException {
		return repositorio.getAll();
	}



	@Override
	public void eliminarDocumentosPropietario(String nombrePropietario) throws EntidadNoEncontrada, RepositorioException {
		if (nombrePropietario == null || nombrePropietario.isEmpty())
			throw new IllegalArgumentException("nombrePropietario: no debe ser nulo ni vacio");
		List<Documento> docs = recuperarByPropietario(nombrePropietario);
		for(Documento doc : docs) {
			repositorio.delete(doc);
		}
		
	}



}
