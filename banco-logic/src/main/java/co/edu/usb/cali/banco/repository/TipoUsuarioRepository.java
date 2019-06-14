package co.edu.usb.cali.banco.repository;

import java.util.List;

import co.edu.usb.cali.banco.domain.TipoUsuario;

public interface TipoUsuarioRepository {

	public void save (TipoUsuario tipoUsuario);
	public void update (TipoUsuario tipoUsuario);
	public void delete (TipoUsuario tipoUsuario);
	public TipoUsuario findById(Long id);
	public List<TipoUsuario> findAll();

	
}
