package co.edu.usb.cali.banco.service;

import java.util.List;

import co.edu.usb.cali.banco.domain.CuentaRegistrada;

public interface CuentaRegistradaService {
	
	public void save (CuentaRegistrada cuentaRegistrada) throws Exception;
	public void update (CuentaRegistrada cuentaRegistrada) throws Exception;
	public void delete (CuentaRegistrada cuentaRegistrada) throws Exception;
	public CuentaRegistrada findById(Long id);
	public List<CuentaRegistrada> findAll();

}
