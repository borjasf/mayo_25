package documentos.modelo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="documentos")
public class Documento {
	@Id
	private String id;
	private String propietario;
	private List <String> colaboradores;
	private String contenido;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	



	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Documento (String propietario, String contenido) {
		super();
		this.propietario = propietario;
		this.setColaboradores(new LinkedList<>());
		this.contenido = contenido;
	}

	public List <String> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List <String> colaboradores) {
		this.colaboradores = colaboradores;
	}

}
