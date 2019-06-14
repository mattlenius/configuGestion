package co.edu.usb.cali.banco.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.usb.cali.banco.domain.Cuenta;
import co.edu.usb.cali.banco.domain.TipoTransaccion;
import co.edu.usb.cali.banco.dto.CuentaDTO;
import co.edu.usb.cali.banco.dto.TipoTransaccionDTO;

public class TipoTransaccionMapperImpl implements TipoTransaccionMapper {
	
	

	@Override
	 @Transactional(readOnly = true)
	public TipoTransaccionDTO TipoTransaccionToTipoTransaccionDTO(TipoTransaccion TipoTransaccion) throws Exception {
		TipoTransaccionDTO transaccionDTO = new TipoTransaccionDTO();
		transaccionDTO.setActivo(TipoTransaccion.getActivo());
		transaccionDTO.setNombre(TipoTransaccion.getNombre());
		transaccionDTO.setTitrId(TipoTransaccion.getTitrId());
		return transaccionDTO;
	}

	@Override
	 @Transactional(readOnly = true)
	public TipoTransaccion tipoTransaccionDTOToTipoTransaccion(TipoTransaccionDTO tipoTransaccionDTO) throws Exception {
		TipoTransaccion tipoTransaccion = new TipoTransaccion();
		tipoTransaccion.setActivo((tipoTransaccionDTO.getActivo()!=null)?tipoTransaccionDTO.getActivo():null);
		tipoTransaccion.setNombre((tipoTransaccionDTO.getNombre()!=null) ? tipoTransaccionDTO.getNombre():null);
		tipoTransaccion.setTitrId(tipoTransaccionDTO.getTitrId());
		return tipoTransaccion;
	}

	@Override
	 @Transactional(readOnly = true)
	public List<TipoTransaccionDTO> listTipoTransaccionToListTipoTransaccionDTO(
			List<TipoTransaccion> listTipoTransaccion) throws Exception {
		try {
			List<TipoTransaccionDTO> tipoTransaccionDTOs = new ArrayList<TipoTransaccionDTO>();

			for (TipoTransaccion tipoTransaccion : listTipoTransaccion) {
				TipoTransaccionDTO tipoTransaccionDTO = TipoTransaccionToTipoTransaccionDTO(tipoTransaccion);

				tipoTransaccionDTOs.add(tipoTransaccionDTO);
			}

			return tipoTransaccionDTOs;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	 @Transactional(readOnly = true)
	public List<TipoTransaccion> listTipoTransaccionDTOToListTipoTransaccion(
			List<TipoTransaccionDTO> listTipoTransaccionDTOs) throws Exception {
		try {
			List<TipoTransaccion> listTipoTransaccion = new ArrayList<TipoTransaccion>();

			for (TipoTransaccionDTO tipoTransaccionDTO : listTipoTransaccionDTOs) {
				TipoTransaccion tipoTransaccion = tipoTransaccionDTOToTipoTransaccion(tipoTransaccionDTO);

				listTipoTransaccion.add(tipoTransaccion);
			}

			return listTipoTransaccion;
		} catch (Exception e) {
			throw e;
		}
		
	}

}
