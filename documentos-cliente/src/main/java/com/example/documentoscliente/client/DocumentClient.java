package com.example.documentoscliente.client;

import java.util.List;

import com.example.documentoscliente.model.Documento;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DocumentClient {

    @POST("documentos")
    Call<Void> createDocumento(@Body Documento documento);

    @GET("documentos/{id}")
    Call<Documento> getDocumento(@Path("id") String id);

    @DELETE("documentos/{id}")
    Call<Void> deleteDocumento(@Path("id") String id);
    @GET("documentos/propietario/{id}")
    Call<List<Documento>> recuperarDocumentosByPropietario(@Path("id")String id);
}
