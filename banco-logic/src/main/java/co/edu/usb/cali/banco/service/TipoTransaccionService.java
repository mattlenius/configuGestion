package co.edu.usb.cali.banco.service;

import java.util.List;

import co.edu.usb.cali.banco.domain.TipoTransaccion;

public interface TipoTransaccionService {
	
	public void save (TipoTransaccion tipoTransaccion) throws Exception;
	public void update (TipoTransaccion tipoTransaccion) throws Exception;
	public void delete (TipoTransaccion tipoTransaccion) throws Exception;
	public TipoTransaccion findById(Long id);
	public List<TipoTransaccion> findAll();

}
