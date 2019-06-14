package co.edu.usb.cali.banco.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



public class UsuarioDTO {

	
	private String usuUsuario;

	private String activo;

	private String clave;

	private BigDecimal identificacion;

	private String nombre;
	
	private Long tiusid;

	public UsuarioDTO(String usuUsuario, String activo, String clave, BigDecimal identificacion, String nombre,
			Long tiusid) {
		super();
		this.usuUsuario = usuUsuario;
		this.activo = activo;
		this.clave = clave;
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.tiusid = tiusid;
	}

	public UsuarioDTO() {
		super();
	}

	public String getUsuUsuario() {
		return usuUsuario;
	}

	public void setUsuUsuario(String usuUsuario) {
		this.usuUsuario = usuUsuario;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public BigDecimal getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(BigDecimal identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getTiusid() {
		return tiusid;
	}

	public void setTiusid(Long tiusid) {
		this.tiusid = tiusid;
	}
	

	
	
	


}