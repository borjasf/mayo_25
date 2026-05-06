package documentos.modelo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="documentos")
public class Documento {
	@Id
	private String id;
	private String usuario;
	private List <String> colaboradores;
	private String contenido;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	



	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Documento (String usuario, String contenido) {
		super();
		this.usuario = usuario;
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
