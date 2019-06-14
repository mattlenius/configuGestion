package co.edu.usb.cali.banco.mapper;

import java.util.List;

import co.edu.usb.cali.banco.domain.Cliente;
import co.edu.usb.cali.banco.domain.Cuenta;
import co.edu.usb.cali.banco.dto.CuentaDTO;

public interface CuentaMapper {
	
	 public CuentaDTO cuentaToCuentaDTO(Cuenta cuenta)throws Exception;

	 public Cuenta cuentaDTOToCuenta(CuentaDTO cuentaDTO)throws Exception;
	 
	 public List<CuentaDTO> listCuentaToListCuentaDTO(List<Cuenta> cuentas)throws Exception;

	 public List<Cuenta> listCuentaDTOToListCuenta(List<CuentaDTO> cuentaDTOs) throws Exception;

}
