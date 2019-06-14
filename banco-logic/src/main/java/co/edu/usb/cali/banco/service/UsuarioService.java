package co.edu.usb.cali.banco.service;

import java.util.List;

import co.edu.usb.cali.banco.domain.Usuario;

public interface UsuarioService {
	
	public void save (Usuario usuario) throws Exception;
	public void update (Usuario usuario) throws Exception;
	public void delete (Usuario usuario) throws Exception;
	public Usuario findById(String id);
	public List<Usuario> findAll();

}
