package co.edu.usb.cali.banco.service;

import java.util.List;

import co.edu.usb.cali.banco.domain.Cliente;
import co.edu.usb.cali.banco.domain.TipoDocumento;

public interface TipoDocumentoService {
	public void save (TipoDocumento tipoDocumento) throws Exception;
	public void update (TipoDocumento tipoDocumento) throws Exception;
	public void delete (TipoDocumento tipoDocumento) throws Exception;
	public TipoDocumento findById(Long id);
	public List<TipoDocumento> findAll();
}
