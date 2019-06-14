package co.edu.usb.cali.banco.dto;

import java.util.List;


public class TipoUsuarioDTO {
	

	private Long tiusId;

	private String activo;

	private String nombre;


	public Long getTiusId() {
		return tiusId;
	}

	public void setTiusId(Long tiusId) {
		this.tiusId = tiusId;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



}