package documentos.rest.dto;

import java.util.List;
import java.util.stream.Collectors;

import documentos.modelo.Documento;



public class DocumentoDto {

  
    private String id;
  
    private String usuario;
  
    private String contenido;
    
    private List <String> colaboradores;

    public DocumentoDto() {
    }

    
    public DocumentoDto(String id, String usuario, String contenido, List <String> colaboradores) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.contenido = contenido;
		this.colaboradores = colaboradores;
	}


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


	public List <String> getColaboradores() {
		return colaboradores;
	}


	public void setColaboradores(List <String> colaboradores) {
		this.colaboradores = colaboradores;
	}


	public static DocumentoDto fromEntity(Documento doc) {
	    return new DocumentoDto(
	        doc.getId(),
	        doc.getUsuario(),
	        doc.getContenido(),
	        doc.getColaboradores().stream()
	            .map(colab -> colab.toString())
	            .collect(Collectors.toList())

	    );
	}
}
