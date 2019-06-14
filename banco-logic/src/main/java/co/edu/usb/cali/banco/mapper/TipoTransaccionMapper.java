package co.edu.usb.cali.banco.mapper;

import java.util.List;

import co.edu.usb.cali.banco.domain.Cliente;
import co.edu.usb.cali.banco.domain.TipoTransaccion;
import co.edu.usb.cali.banco.dto.ClienteDTO;
import co.edu.usb.cali.banco.dto.TipoTransaccionDTO;

public interface TipoTransaccionMapper {
	
	 public TipoTransaccionDTO TipoTransaccionToTipoTransaccionDTO(TipoTransaccion TipoTransaccion)throws Exception;

	 public TipoTransaccion tipoTransaccionDTOToTipoTransaccion(TipoTransaccionDTO tipoTransaccionDTO)throws Exception;
	 
	 public List<TipoTransaccionDTO> listTipoTransaccionToListTipoTransaccionDTO(List<TipoTransaccion> listTipoTransaccion)throws Exception;

	 public List<TipoTransaccion> listTipoTransaccionDTOToListTipoTransaccion(List<TipoTransaccionDTO> listTipoTransaccionDTOs) throws Exception;

}
