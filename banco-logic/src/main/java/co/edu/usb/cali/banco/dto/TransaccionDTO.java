package co.edu.usb.cali.banco.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import co.edu.usb.cali.banco.domain.Cuenta;


public class TransaccionDTO  {


	private Long tranId;

	private Timestamp fecha;

	private BigDecimal valor;

	
	private String cuenId;

	private Long titrId;

	private String usuUsuario;
	
	

	public TransaccionDTO() {
		super();
	}



	public TransaccionDTO(Long tranId, Timestamp fecha, BigDecimal valor, String cuenId, Long titrId,
			String usuUsuario) {
		super();
		this.tranId = tranId;
		this.fecha = fecha;
		this.valor = valor;
		this.cuenId = cuenId;
		this.titrId = titrId;
		this.usuUsuario = usuUsuario;
	}



	public Long getTranId() {
		return tranId;
	}



	public void setTranId(Long tranId) {
		this.tranId = tranId;
	}



	public Timestamp getFecha() {
		return fecha;
	}



	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}



	public BigDecimal getValor() {
		return valor;
	}



	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}



	public String getCuenId() {
		return cuenId;
	}



	public void setCuenId(String cuenId) {
		this.cuenId = cuenId;
	}



	public Long getTitrId() {
		return titrId;
	}



	public void setTitrId(Long titrId) {
		this.titrId = titrId;
	}



	public String getUsuUsuario() {
		return usuUsuario;
	}



	public void setUsuUsuario(String usuUsuario) {
		this.usuUsuario = usuUsuario;
	}

	
	

}