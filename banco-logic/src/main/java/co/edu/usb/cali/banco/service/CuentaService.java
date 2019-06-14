package co.edu.usb.cali.banco.service;

import java.util.List;

import co.edu.usb.cali.banco.domain.Cuenta;

public interface CuentaService {
	
	public void save (Cuenta cuenta) throws Exception;
	public void update (Cuenta cuenta) throws Exception;
	public void delete (Cuenta cuenta) throws Exception;
	public Cuenta findById(String id);
	public List<Cuenta> findAll();

}
