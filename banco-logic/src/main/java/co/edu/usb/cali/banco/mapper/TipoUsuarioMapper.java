package co.edu.usb.cali.banco.mapper;

import java.util.List;

import co.edu.usb.cali.banco.domain.TipoUsuario;
import co.edu.usb.cali.banco.dto.TipoUsuarioDTO;

public interface TipoUsuarioMapper {
	
	 public TipoUsuarioDTO tipoUsuarioToTipoUsuarioDTO(TipoUsuario tipoUsuario)throws Exception;

	 public TipoUsuario tipoUsuarioDTOToTipoUsuario(TipoUsuarioDTO tipoUsuarioDTO)throws Exception;
	 
	 public List<TipoUsuarioDTO> listTipoUsuarioToListTipoUsuarioDTO(List<TipoUsuario> listTipoUsuarios)throws Exception;

	 public List<TipoUsuario> listTipoUsuarioDTOToListTipoUsuario(List<TipoUsuarioDTO> listTipoUsuarioDTOs) throws Exception;

}
