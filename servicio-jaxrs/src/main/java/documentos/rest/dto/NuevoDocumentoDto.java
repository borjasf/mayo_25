package documentos.rest.dto;

import javax.validation.constraints.NotNull;


public class NuevoDocumentoDto {

	@NotNull
    private String usuario;
	@NotNull
    private String contenido;
 
    
    @NotNull
   // @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}", 
    	//message = "Formato de fecha inválido. 'yyyy-MM-ddTHH:mm:ss'")
    //private String apertura;
    

    public NuevoDocumentoDto() { // POJO
    
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


    
  
}

