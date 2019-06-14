package co.edu.usb.cali.banco.mapper;

import java.util.List;

import co.edu.usb.cali.banco.domain.Cliente;
import co.edu.usb.cali.banco.domain.CuentaRegistrada;
import co.edu.usb.cali.banco.dto.CuentaRegistradaDTO;

public interface CuentaRegistradaMapper {
	
	 public CuentaRegistradaDTO cuentaRegistradaToCuentaRegistradaDTO(CuentaRegistrada cuentaRegistrada)throws Exception;

	 public CuentaRegistrada cuentaRegistradaDTOToCuentaRegistradaDTO(CuentaRegistradaDTO cuentaRegistradaDTO)throws Exception;
	 
	 public List<CuentaRegistradaDTO> listCuentaRegistradaDTOToListCuentaRegistradaDTODTO(List<CuentaRegistrada> listCuentaRegistradas)throws Exception;

	 public List<CuentaRegistrada> listCuentaRegistradaDTODTOToListCuentaRegistradaDTO(List<CuentaRegistradaDTO> listCuentaRegistradaDTOs) throws Exception;

}
