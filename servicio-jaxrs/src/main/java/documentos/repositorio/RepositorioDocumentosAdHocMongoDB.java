package documentos.repositorio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.conversions.Bson;

import com.mongodb.client.model.Filters;

import documentos.modelo.Documento;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public class RepositorioDocumentosAdHocMongoDB extends RepositorioDocumentosMongoDB implements RepositorioDocumentosAdHoc {
	

	
	public RepositorioDocumentosAdHocMongoDB() throws IOException {
		super();
	}




	@Override
	public List<Documento> recuperarByPropietario(String nombreAutor) throws RepositorioException, EntidadNoEncontrada {
		try {
		Bson filtro = Filters.eq("documentos.usuario",nombreAutor);
		return getCollection().find(filtro).into(new ArrayList<>());
		} catch (Exception e) {
			throw new RepositorioException("Error al buscar obras por nota técnica mínima", e);
		}
	}
}