package documentos.rest.dto;

import java.util.List;
import java.util.stream.Collectors;

import documentos.modelo.Documento;



public class DocumentoDto {

  
    private String id;
  
    private String propietario;
  
    private String contenido;
    
    private List <String> colaboradores;

    public DocumentoDto() {
    }

    
    public DocumentoDto(String id, String propietario, String contenido, List <String> colaboradores) {
		super();
		this.id = id;
		this.propietario = propietario;
		this.contenido = contenido;
		this.colaboradores = colaboradores;
	}


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


	public List <String> getColaboradores() {
		return colaboradores;
	}


	public void setColaboradores(List <String> colaboradores) {
		this.colaboradores = colaboradores;
	}


	public static DocumentoDto fromEntity(Documento doc) {
	    return new DocumentoDto(
	        doc.getId(),
	        doc.getPropietario(),
	        doc.getContenido(),
	        doc.getColaboradores().stream()
	            .map(colab -> colab.toString())
	            .collect(Collectors.toList())

	    );
	}
}
