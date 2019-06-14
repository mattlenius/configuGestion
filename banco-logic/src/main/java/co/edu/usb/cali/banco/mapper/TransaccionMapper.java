package co.edu.usb.cali.banco.mapper;

import java.util.List;

import co.edu.usb.cali.banco.domain.Transaccion;
import co.edu.usb.cali.banco.dto.TransaccionDTO;

public interface TransaccionMapper {
	
	 public TransaccionDTO transaccionToTransaccionDTO(Transaccion transaccion)throws Exception;

	 public Transaccion transaccionDTOToTransaccion(TransaccionDTO transaccionDTO)throws Exception;
	 
	 public List<TransaccionDTO> listTransaccionToListTransaccionDTO(List<Transaccion> transaccions)throws Exception;

	 public List<Transaccion> listTransaccionDTOToListTransaccion(List<TransaccionDTO> transaccionDTOs) throws Exception;

}
