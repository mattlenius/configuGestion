package co.edu.usb.cali.banco.repository;

import java.util.List;

import co.edu.usb.cali.banco.domain.Cuenta;

public interface CuentaRepository {
	
	public void save (Cuenta cuenta);
	public void update (Cuenta cuenta);
	public void delete (Cuenta cuenta);
	public Cuenta findById(String id);
	public List<Cuenta> findAll();
	
}
