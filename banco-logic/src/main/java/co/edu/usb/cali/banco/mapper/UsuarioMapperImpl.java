package co.edu.usb.cali.banco.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usb.cali.banco.domain.Cliente;
import co.edu.usb.cali.banco.domain.TipoUsuario;
import co.edu.usb.cali.banco.domain.Usuario;
import co.edu.usb.cali.banco.dto.ClienteDTO;
import co.edu.usb.cali.banco.dto.UsuarioDTO;
import co.edu.usb.cali.banco.service.TipoDocumentoService;
import co.edu.usb.cali.banco.service.TipoUsuarioService;
import co.edu.usb.cali.banco.service.UsuarioService;


@Component
@Scope("singleton")
public class UsuarioMapperImpl implements UsuarioMapper {
	
	
	@Autowired
	private TipoUsuarioService tipoUsuarioService;

	@Override
	 @Transactional(readOnly = true)
	public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario) throws Exception {
		try {
		UsuarioDTO usuarioDTO= new UsuarioDTO();
		usuarioDTO.setActivo(usuario.getActivo());
		usuarioDTO.setClave(usuario.getClave());
		usuarioDTO.setIdentificacion(usuario.getIdentificacion());
		usuarioDTO.setNombre(usuario.getNombre());
		usuarioDTO.setUsuUsuario(usuario.getUsuUsuario());
		usuarioDTO.setTiusid(usuario.getTipoUsuario().getTiusId());
		return usuarioDTO;
		
		} catch (Exception e) {
            throw e;
        }
	}

	@Override
	 @Transactional(readOnly = true)
	public Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO) throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario();
		usuario.setActivo(usuarioDTO.getActivo()!=null ? usuarioDTO.getActivo():null);
		usuario.setClave(usuarioDTO.getClave()!=null ? usuarioDTO.getClave():null);
		usuario.setIdentificacion(usuarioDTO.getIdentificacion()!=null? usuarioDTO.getIdentificacion():null);
		usuario.setNombre(usuarioDTO.getNombre()!=null ? usuarioDTO.getNombre():null);
		usuario.setUsuUsuario(usuarioDTO.getUsuUsuario());
		
		TipoUsuario tipoUsuario = new TipoUsuario();
		if(usuarioDTO.getTiusid()!=null) {
			tipoUsuario= tipoUsuarioService.findById(usuarioDTO.getTiusid());
		}
		if(tipoUsuario!=null) {
			usuario.setTipoUsuario(tipoUsuario);
		}
		
		return usuario;
	}

	@Override
	 @Transactional(readOnly = true)
	public List<UsuarioDTO> listUsuarioToListUsuarioDTO(List<Usuario> listUsuarios) throws Exception {
		 try {
	            List<UsuarioDTO> usuarioDTOs = new ArrayList<UsuarioDTO>();

	            for ( Usuario usuario : listUsuarios) {
	            	UsuarioDTO usuarioDTO = usuarioToUsuarioDTO(usuario);

	            	usuarioDTOs.add(usuarioDTO);
	            }

	            return usuarioDTOs;
	        } catch (Exception e) {
	            throw e;
	        }
	}

	@Override
	 @Transactional(readOnly = true)
	public List<Usuario> listUsuarioDTOToListUsuario(List<UsuarioDTO> listUsuarioDTOs) throws Exception {
		try {
            List<Usuario> listUsuario = new ArrayList<Usuario>();

            for (UsuarioDTO usuarioDTO : listUsuarioDTOs) {
            	Usuario usuario = usuarioDTOToUsuario(usuarioDTO);

            	listUsuario.add(usuario);
            }

            return listUsuario;
        } catch (Exception e) {
            throw e;
        }
	}

}
