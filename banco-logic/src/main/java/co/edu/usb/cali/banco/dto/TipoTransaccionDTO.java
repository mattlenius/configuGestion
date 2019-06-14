package co.edu.usb.cali.banco.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import co.edu.usb.cali.banco.domain.Transaccion;



public class TipoTransaccionDTO {
	private static final long serialVersionUID = 1L;


	private Long titrId;

	private String activo;

	private String nombre;
	
	
	

	public TipoTransaccionDTO() {
		super();
	}




	public TipoTransaccionDTO(Long titrId, String activo, String nombre) {
		super();
		this.titrId = titrId;
		this.activo = activo;
		this.nombre = nombre;
	}




	public Long getTitrId() {
		return titrId;
	}




	public void setTitrId(Long titrId) {
		this.titrId = titrId;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	

}