package com.example.documentoscliente.model;

import java.util.ArrayList;
import java.util.List;


// EL MODELO AQUÍ NO LLEVA NINGUN @ DE SPRINGDATA, ES SOLO UN POJO NORMAL Y CORRIENTE.
public class Documento {

    private String id;
    private String usuario;
    private String contenido;
    private List <String> colaboradores;
    public Documento() {
    }

    public Documento(String propietario, String contenido) {
        this.usuario = propietario;

        this.contenido = contenido;
    }

   

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String propietario) {
        this.usuario = propietario;
    }

 



    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
	public String toString() {
		return "Documento [id=" + id + ", usuario=" + usuario + ", contenido=" + contenido + ", colaboradores="
				+ colaboradores + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List <String> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List <String> colaboradores) {
		this.colaboradores = colaboradores;
	}
}
