package co.edu.usb.cali.banco.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usb.cali.banco.domain.TipoTransaccion;
import co.edu.usb.cali.banco.domain.TipoUsuario;
import co.edu.usb.cali.banco.dto.TipoTransaccionDTO;
import co.edu.usb.cali.banco.dto.TipoUsuarioDTO;


@Component
@Scope("singleton")
public class TipoUsuarioMapperImpl implements TipoUsuarioMapper {

	@Override
	@Transactional(readOnly = true)
	public TipoUsuarioDTO tipoUsuarioToTipoUsuarioDTO(TipoUsuario tipoUsuario) throws Exception {
		 TipoUsuarioDTO tipoUsuarioDTO = new TipoUsuarioDTO();
		 tipoUsuarioDTO.setActivo(tipoUsuario.getActivo());
		 tipoUsuarioDTO.setNombre(tipoUsuario.getNombre());
		 tipoUsuarioDTO.setTiusId(tipoUsuario.getTiusId());
		return tipoUsuarioDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public TipoUsuario tipoUsuarioDTOToTipoUsuario(TipoUsuarioDTO tipoUsuarioDTO) throws Exception {
		TipoUsuario tipoUsuario = new TipoUsuario();
		tipoUsuario.setActivo(tipoUsuarioDTO.getActivo()!= null ? tipoUsuarioDTO.getActivo():null);
		tipoUsuario.setNombre(tipoUsuarioDTO.getNombre()!=null? tipoUsuarioDTO.getNombre():null);
		tipoUsuario.setTiusId(tipoUsuarioDTO.getTiusId());
		return tipoUsuario;
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoUsuarioDTO> listTipoUsuarioToListTipoUsuarioDTO(List<TipoUsuario> listTipoUsuarios)
			throws Exception {
		try {
			List<TipoUsuarioDTO> tipoUsuarioDTOs = new ArrayList<TipoUsuarioDTO>();

			for (TipoUsuario tipoUsuario : listTipoUsuarios) {
				TipoUsuarioDTO tipoUsuarioDTO = tipoUsuarioToTipoUsuarioDTO(tipoUsuario);

				tipoUsuarioDTOs.add(tipoUsuarioDTO);
			}

			return tipoUsuarioDTOs;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoUsuario> listTipoUsuarioDTOToListTipoUsuario(List<TipoUsuarioDTO> listTipoUsuarioDTOs)
			throws Exception {
		try {
			List<TipoUsuario> listTipoUsuario = new ArrayList<TipoUsuario>();

			for (TipoUsuarioDTO tipoUsuarioDTO : listTipoUsuarioDTOs) {
				TipoUsuario tipoUsuario = tipoUsuarioDTOToTipoUsuario(tipoUsuarioDTO);

				listTipoUsuario.add(tipoUsuario);
			}

			return listTipoUsuario;
		} catch (Exception e) {
			throw e;
		}
	}

}
