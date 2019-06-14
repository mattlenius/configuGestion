package co.edu.usb.cali.banco.dto;

import co.edu.usb.cali.banco.domain.Cliente;
import co.edu.usb.cali.banco.domain.Cuenta;


/**
 * The persistent class for the cuenta_registrada database table.
 * 
 */

public class CuentaRegistradaDTO {

	
	private Long cureId;

	private Long clieId;


	private String cuenId;


	public CuentaRegistradaDTO() {
		super();
	}


	public CuentaRegistradaDTO(Long cureId, Long clieId, String cuenId) {
		super();
		this.cureId = cureId;
		this.clieId = clieId;
		this.cuenId = cuenId;
	}


	public Long getCureId() {
		return cureId;
	}


	public void setCureId(Long cureId) {
		this.cureId = cureId;
	}


	public Long getClieId() {
		return clieId;
	}


	public void setClieId(Long clieId) {
		this.clieId = clieId;
	}


	public String getCuenId() {
		return cuenId;
	}


	public void setCuenId(String cuenId) {
		this.cuenId = cuenId;
	}


	
	

}