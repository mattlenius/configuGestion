package co.edu.usb.cali.banco.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usb.cali.banco.domain.Cliente;
import co.edu.usb.cali.banco.domain.Cuenta;
import co.edu.usb.cali.banco.domain.CuentaRegistrada;
import co.edu.usb.cali.banco.dto.CuentaDTO;
import co.edu.usb.cali.banco.dto.CuentaRegistradaDTO;
import co.edu.usb.cali.banco.service.ClienteService;
import co.edu.usb.cali.banco.service.CuentaService;

public class CuentaRegistradaMapperImpl implements CuentaRegistradaMapper {

	@Autowired
	private CuentaService cuentaService;

	@Autowired
	private ClienteService clienteService;

	@Override
	@Transactional(readOnly = true)
	public CuentaRegistradaDTO cuentaRegistradaToCuentaRegistradaDTO(CuentaRegistrada cuentaRegistrada)
			throws Exception {

		CuentaRegistradaDTO cuentaRegistradaDTO = new CuentaRegistradaDTO();

		cuentaRegistradaDTO.setCureId(cuentaRegistrada.getCureId());
		cuentaRegistradaDTO.setClieId(cuentaRegistrada.getCliente().getClieId());
		cuentaRegistradaDTO.setCuenId(cuentaRegistrada.getCuenta().getCuenId());

		return cuentaRegistradaDTO;
	}

	@Override
	@Transactional(readOnly = true)
	public CuentaRegistrada cuentaRegistradaDTOToCuentaRegistradaDTO(CuentaRegistradaDTO cuentaRegistradaDTO)
			throws Exception {
		CuentaRegistrada cuentaRegistrada = new CuentaRegistrada();
		cuentaRegistrada.setCureId(cuentaRegistradaDTO.getClieId());

		Cliente cliente = new Cliente();

		if (cuentaRegistradaDTO.getClieId() > 0) {
			cliente = clienteService.findById(cuentaRegistradaDTO.getClieId());
		}

		if (cliente != null) {
			cuentaRegistrada.setCliente(cliente);
		}

		Cuenta cuenta = new Cuenta();

		if (cuentaRegistradaDTO.getCuenId() != null) {
			cuenta = cuentaService.findById(cuentaRegistradaDTO.getCuenId());
		}

		if (cuenta != null) {
			cuentaRegistrada.setCuenta(cuenta);
		}

		return cuentaRegistrada;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CuentaRegistradaDTO> listCuentaRegistradaDTOToListCuentaRegistradaDTODTO(
			List<CuentaRegistrada> listCuentaRegistradas) throws Exception {
		try {
			List<CuentaRegistradaDTO> cuentaRegistradaDTOs = new ArrayList<CuentaRegistradaDTO>();

			for (CuentaRegistrada cuentaRegistrada : listCuentaRegistradas) {
				CuentaRegistradaDTO cuentaRegistradaDTO = cuentaRegistradaToCuentaRegistradaDTO(cuentaRegistrada);

				cuentaRegistradaDTOs.add(cuentaRegistradaDTO);
			}

			return cuentaRegistradaDTOs;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<CuentaRegistrada> listCuentaRegistradaDTODTOToListCuentaRegistradaDTO(
			List<CuentaRegistradaDTO> listCuentaRegistradaDTOs) throws Exception {
		
		List<CuentaRegistrada> listcuentaRegistrada = new ArrayList<CuentaRegistrada>();

		for (CuentaRegistradaDTO cuentaRegistradaDTO : listCuentaRegistradaDTOs) {
			CuentaRegistrada cuentaRegistrada = cuentaRegistradaDTOToCuentaRegistradaDTO(cuentaRegistradaDTO);

			listcuentaRegistrada.add(cuentaRegistrada);
		}

		return listcuentaRegistrada;

	}

}
