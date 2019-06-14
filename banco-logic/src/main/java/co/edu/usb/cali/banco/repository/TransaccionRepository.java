package co.edu.usb.cali.banco.repository;

import java.util.List;

import co.edu.usb.cali.banco.domain.Transaccion;

public interface TransaccionRepository {

	public void save (Transaccion transaccion);
	public void update (Transaccion transaccion);
	public void delete (Transaccion transaccion);
	public Transaccion findById(Long id);
	public List<Transaccion> findAll();

	
}
