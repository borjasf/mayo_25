package documentos.rest;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import documentos.rest.dto.DocumentoDto;
import documentos.rest.dto.NuevoDocumentoDto;
import documentos.servicio.IServicioDocumentos;
import servicio.FactoriaServicios;

@Path("documentos")
public class ControladorDocumentos {

	private IServicioDocumentos servicio = FactoriaServicios.getServicio(IServicioDocumentos.class);

	@Context
	private UriInfo uriInfo;

	// curl http://localhost:8080/api/usuarios/juan@um.es

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDocumento(@PathParam("id") String id) throws Exception {

		DocumentoDto dto = DocumentoDto.fromEntity(servicio.getDocumento(id));

		return Response.status(Response.Status.OK).entity(dto).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTodosDocumentos() throws Exception {

		List<DocumentoDto> docs = this.servicio.getDocumentos().stream().map(doc -> DocumentoDto.fromEntity(doc))
				.collect(Collectors.toList());

		return Response.status(Response.Status.OK).entity(docs).build();
	}
	// curl -i -X PATCH --data "nombre=Juan González"
	// http://localhost:8080/api/usuarios/juan@um.es

	@PATCH
	@Path("/{id}/colaboradores")
	public Response updateNombre(@PathParam("id") String id, @FormParam("colaborador") String nombre) throws Exception {

		servicio.anadirColaborador(id, nombre);

		return Response.status(Response.Status.NO_CONTENT).build();
	}

	// curl -i -X POST -H "Content-type: application/json" -d "{'email':
	// 'juan@um.es', 'nombre': 'Juan'}" http://localhost:8080/api/usuarios/

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUsuario(NuevoDocumentoDto dto) throws Exception {
		String id = servicio.crearDocumento(dto.getUsuario(), dto.getContenido());

		URI nuevaURL = this.uriInfo.getAbsolutePathBuilder().path(id).build();
		return Response.created(nuevaURL).build();
	}

	@GET
	@Path("/propietario/{propietario}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDocumentoByAutor(@PathParam("propietario") String nombre) throws Exception {

		List<DocumentoDto> docs = this.servicio.recuperarByPropietario(nombre).stream()
				.map(doc -> DocumentoDto.fromEntity(doc)).collect(Collectors.toList());

		return Response.status(Response.Status.OK).entity(docs).build();
	}

	@DELETE
	@Path("/{id}")
	public Response eliminarUsuario(@PathParam("id") String id) throws Exception {
		servicio.eliminarDocumento(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}