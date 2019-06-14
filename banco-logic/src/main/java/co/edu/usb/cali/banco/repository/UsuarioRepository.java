package co.edu.usb.cali.banco.repository;

import java.util.List;

import co.edu.usb.cali.banco.domain.Usuario;

public interface UsuarioRepository {

	public void save (Usuario usuario);
	public void update (Usuario usuario);
	public void delete (Usuario usuario);
	public Usuario findById(String id);
	public List<Usuario> findAll();

	
}
