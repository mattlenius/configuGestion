package co.edu.usb.cali.banco.service;

import java.util.List;

import co.edu.usb.cali.banco.domain.Transaccion;

public interface TransaccionService {
	
	public void save (Transaccion transaccion) throws Exception;
	public void update (Transaccion transaccion) throws Exception;
	public void delete (Transaccion transaccion) throws Exception;
	public Transaccion findById(Long id);
	public List<Transaccion> findAll();

}
