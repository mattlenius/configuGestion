package co.edu.usb.cali.banco.repository;

import java.util.List;

import co.edu.usb.cali.banco.domain.TipoTransaccion;

public interface TipoTransaccionRepository {

	public void save (TipoTransaccion tipoTransaccion);
	public void update (TipoTransaccion tipoTransaccion);
	public void delete (TipoTransaccion tipoTransaccion);
	public TipoTransaccion findById(Long id);
	public List<TipoTransaccion> findAll();

	
}
