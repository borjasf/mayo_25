package documentos.rest;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import documentos.modelo.Documento;
import documentos.rest.dto.DocumentoDto;
import documentos.rest.dto.NuevoDocumentoDto;
import documentos.servicio.IServicioDocumentos;

@RestController
@RequestMapping("/documentos")
public class DocumentosController{

	private IServicioDocumentos servicio;
	
	//@Autowired
	//private PagedResourcesAssembler<DocumentoResumen> pagedResourcesAssembler;
	

	@Autowired
	public DocumentosController(IServicioDocumentos servicio) {
		this.servicio = servicio;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> createDocument(
			 @Valid @RequestBody NuevoDocumentoDto nuevoDoc) throws Exception {
	 
		String id = this.servicio.crearDocumento(nuevoDoc.getUsuario(),nuevoDoc.getContenido());
	 
		 // Construye la URL completa del nuevo recurso
		 URI nuevaURL = ServletUriComponentsBuilder.fromCurrentRequest()
				 			.path("/{id}").buildAndExpand(id).toUri();
		 
		 return ResponseEntity.created(nuevaURL).build();
	}
	
	
	@GetMapping("/{id}")
	public EntityModel<DocumentoDto> getDocumentoById(@PathVariable String id) throws Exception {
		 
		Documento doc = this.servicio.getDocumento(id);
		DocumentoDto docDTO = DocumentoDto.fromEntity(doc);
		 
		// Envolver el DTO con EntityModel y agregar enlace self
		EntityModel<DocumentoDto> model = EntityModel.of(docDTO);
		model.add(
			 WebMvcLinkBuilder.linkTo(
					 WebMvcLinkBuilder
					 	.methodOn(DocumentosController.class)
					 	.getDocumentoById(id))
				 .withSelfRel());
		return model;	 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarDocumento(@PathVariable String id) throws Exception{
		 
		servicio.eliminarDocumento(id);
		return ResponseEntity.noContent().build();
	}
	@PatchMapping("/{id}/colaboradores")
	public ResponseEntity<Void> añadirColaborador(@PathVariable String id,@RequestBody String colaborador) throws Exception{
		 
		servicio.anadirColaborador(id, colaborador);
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping
	public List<DocumentoDto> getDocumentos() throws Exception {

		List<DocumentoDto> docs = this.servicio.getDocumentos().stream().map(doc->DocumentoDto.fromEntity(doc)).collect(Collectors.toList());

		return docs;
	}
	
	@GetMapping("/propietario/{propietario}")
	public List<DocumentoDto> getDocumentosByPropietario(@PathVariable String propietario) throws Exception {

		List<DocumentoDto> docs = this.servicio.recuperarByPropietario(propietario).stream().map(doc->DocumentoDto.fromEntity(doc)).collect(Collectors.toList());

		return docs;
	}
	

	
	/*@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public PagedModel<EntityModel<DocumentoResumen>> getEncuestasPaginado(Pageable paginacion) throws Exception {
		 
		 Page<DocumentoResumen> resultado = this.servicio.getListadoPaginado(paginacion);
		 
		 return this.pagedResourcesAssembler.toModel(resultado, encuestaResumenAssembler);
	}
	*/
	
	
}