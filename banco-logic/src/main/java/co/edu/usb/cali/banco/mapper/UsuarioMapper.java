package co.edu.usb.cali.banco.mapper;

import java.util.List;

import co.edu.usb.cali.banco.domain.Usuario;
import co.edu.usb.cali.banco.dto.UsuarioDTO;

public interface UsuarioMapper {
	
	 public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario)throws Exception;

	 public Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO)throws Exception;
	 
	 public List<UsuarioDTO> listUsuarioToListUsuarioDTO(List<Usuario> listUsuarios)throws Exception;

	 public List<Usuario> listUsuarioDTOToListUsuario(List<UsuarioDTO> listUsuarioDTOs) throws Exception;

}
