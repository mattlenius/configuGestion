package co.edu.usb.cali.banco.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usb.cali.banco.domain.Cliente;
import co.edu.usb.cali.banco.domain.Cuenta;
import co.edu.usb.cali.banco.dto.CuentaDTO;
import co.edu.usb.cali.banco.service.ClienteService;

@Component
@Scope("singleton")
public class CuentaMapperImpl implements CuentaMapper {

	@Autowired
	private ClienteService clienteService;

	@Transactional(readOnly = true)
	public CuentaDTO cuentaToCuentaDTO(Cuenta cuenta) throws Exception {
		try {
			CuentaDTO cuentaDTO = new CuentaDTO();
			cuentaDTO.setActiva(cuenta.getActiva());
			cuentaDTO.setClave(cuenta.getClave());
			cuentaDTO.setCuenId(cuenta.getCuenId());
			cuentaDTO.setSaldo(cuenta.getSaldo());
			cuentaDTO.setClieId(cuenta.getCliente().getClieId());
			return cuentaDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public Cuenta cuentaDTOToCuenta(CuentaDTO cuentaDTO) throws Exception {
		try {
			Cuenta cuenta = new Cuenta();
			
			cuenta.setActiva((cuentaDTO.getActiva()!=null)?cuentaDTO.getActiva():null);
			cuenta.setClave((cuentaDTO.getClave()!=null)?cuentaDTO.getClave():null);
			cuenta.setCuenId(cuentaDTO.getCuenId());
			cuenta.setSaldo((cuentaDTO.getSaldo()!=null)?cuentaDTO.getSaldo():null);

			Cliente cliente = new Cliente();

			if (cuentaDTO.getClieId()>0) {
				cliente = clienteService.findById(cuentaDTO.getClieId());
			}

			if (cliente != null) {
				cuenta.setCliente(cliente);
			}

			return cuenta;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public List<CuentaDTO> listCuentaToListCuentaDTO(List<Cuenta> listCuenta) throws Exception {
		try {
			List<CuentaDTO> cuentaDTOs = new ArrayList<CuentaDTO>();

			for (Cuenta cliente : listCuenta) {
				CuentaDTO clienteDTO = cuentaToCuentaDTO(cliente);

				cuentaDTOs.add(clienteDTO);
			}

			return cuentaDTOs;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public List<Cuenta> listCuentaDTOToListCuenta(List<CuentaDTO> listCuentaDTO) throws Exception {
		try {
			List<Cuenta> listCuenta = new ArrayList<Cuenta>();

			for (CuentaDTO cuentaDTO : listCuentaDTO) {
				Cuenta cuenta = cuentaDTOToCuenta(cuentaDTO);

				listCuenta.add(cuenta);
			}

			return listCuenta;
		} catch (Exception e) {
			throw e;
		}
	}

	

}
