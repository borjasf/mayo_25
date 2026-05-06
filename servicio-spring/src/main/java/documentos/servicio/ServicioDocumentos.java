
package documentos.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import documentos.modelo.Documento;
import documentos.repositorio.RepositorioDocumentosMongo;
import repositorio.EntidadNoEncontrada;

@Service
@Transactional
public class ServicioDocumentos implements IServicioDocumentos {

	private RepositorioDocumentosMongo repositorio;

	@Autowired
	public ServicioDocumentos(RepositorioDocumentosMongo repositorio) {
		this.repositorio = repositorio;
	}



	@Override
	public String crearDocumento(String usuario, String contenido) {
		// Control de integridad de los datos

				if (usuario == null || usuario.isEmpty())
					throw new IllegalArgumentException("usuario: no debe ser nulo ni vacio");
				if (contenido == null || contenido.isEmpty())
					throw new IllegalArgumentException("contenido: no debe ser nulo ni vacio");

		
		

				Documento doc = new Documento(usuario,contenido);

				String id = repositorio.save(doc).getId();

				return id;
	}
	

	@Override
	public Documento getDocumento(String idDocumento) throws EntidadNoEncontrada {
		if (idDocumento == null || idDocumento.isEmpty())
			throw new IllegalArgumentException("idDocumento: no debe ser nulo ni vacio");

		Optional<Documento> resultado = repositorio.findById(idDocumento);
		if (resultado.isPresent() == false)
			throw new EntidadNoEncontrada("No existe encuesta con id: " + idDocumento);
		else
			return resultado.get();
	}

	@Override
	public void eliminarDocumento(String idDocumento) throws EntidadNoEncontrada {
		if (idDocumento == null || idDocumento.isEmpty())
			throw new IllegalArgumentException("idDocumento: no debe ser nulo ni vacio");

		Documento doc = getDocumento(idDocumento);

		repositorio.delete(doc);
		
	}

	@Override
	public void anadirColaborador(String idDocumento, String usuario) throws EntidadNoEncontrada {
		if (idDocumento == null || idDocumento.isEmpty())
			throw new IllegalArgumentException("idDocumento: no debe ser nulo ni vacio");
		if (usuario == null || usuario.isEmpty())
			throw new IllegalArgumentException("usuario: no debe ser nulo ni vacio");
		Documento doc = getDocumento(idDocumento);
		if(!doc.getColaboradores().contains(usuario)) {
			doc.getColaboradores().add(usuario);
		}
		repositorio.save(doc);
		
	}

	@Override
	public void eliminarColaborador(String idDocumento, String usuario) throws EntidadNoEncontrada {
		if (idDocumento == null || idDocumento.isEmpty())
			throw new IllegalArgumentException("idDocumento: no debe ser nulo ni vacio");
		if (usuario == null || usuario.isEmpty())
			throw new IllegalArgumentException("usuario: no debe ser nulo ni vacio");
		Documento doc = getDocumento(idDocumento);
		if(doc.getColaboradores().contains(usuario)) {
			doc.getColaboradores().remove(usuario);
		}
		repositorio.save(doc);
		
	}

	@Override
	public List<Documento> recuperarByPropietario(String nombrePropietario) throws EntidadNoEncontrada {
		if (nombrePropietario == null || nombrePropietario.isEmpty())
			throw new IllegalArgumentException("nombrePropietario: no debe ser nulo ni vacio");
		return repositorio.recuperarByPropietario(nombrePropietario);
	}



	@Override
	public List<Documento> getDocumentos() throws EntidadNoEncontrada {
		return repositorio.findAll();
	}



	@Override
	public void eliminarDocumentosPropietario(String nombrePropietario) throws EntidadNoEncontrada {
		if (nombrePropietario == null || nombrePropietario.isEmpty())
			throw new IllegalArgumentException("nombrePropietario: no debe ser nulo ni vacio");
		List<Documento> docs = recuperarByPropietario(nombrePropietario);
		for(Documento doc : docs) {
			repositorio.delete(doc);
		}
		
	}



}
