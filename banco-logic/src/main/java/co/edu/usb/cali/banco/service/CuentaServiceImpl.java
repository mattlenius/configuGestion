package co.edu.usb.cali.banco.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usb.cali.banco.domain.Cuenta;
import co.edu.usb.cali.banco.repository.CuentaRepository;

@Service
@Scope("singleton")
public class CuentaServiceImpl implements CuentaService {

	@Autowired
	private CuentaRepository cuentaRepository;

	@Autowired
	private Validator validator;

	public void validar(Cuenta cuenta) throws Exception {
		try {
			Set<ConstraintViolation<Cuenta>> constraintViolations = validator.validate(cuenta);

			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();

				for (ConstraintViolation<Cuenta> constraintViolation : constraintViolations) {
					strMessage.append(constraintViolation.getPropertyPath().toString());
					strMessage.append(" - ");
					strMessage.append(constraintViolation.getMessage());
					strMessage.append(". \n");
				}

				throw new Exception(strMessage.toString());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(Cuenta cuenta) throws Exception {
		if (cuenta == null) {
			throw new Exception("El cuenta es nulo");
		}
		validar(cuenta);
		Cuenta entity = cuentaRepository.findById(cuenta.getCuenId());
		if (entity != null) {
			throw new Exception("El cuenta no se puede crear porque ya existe");
		}
		cuentaRepository.save(cuenta);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Cuenta cuentaRegistrada) throws Exception {
		if (cuentaRegistrada == null) {
			throw new Exception("El cuenta es nulo");
		}
		validar(cuentaRegistrada);
		Cuenta entity = cuentaRepository.findById(cuentaRegistrada.getCuenId());
		if (entity == null) {
			throw new Exception("El cuenta no se puede modificar porque no existe");
		}
		cuentaRepository.update(cuentaRegistrada);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Cuenta cuenta) throws Exception {
		if (cuenta == null) {
			throw new Exception("El cuenta es nulo");
		}
		Cuenta entity = cuentaRepository.findById(cuenta.getCuenId());
		if (entity == null) {
			throw new Exception("El cuenta no se puede borrar porque no existe");
		}
		cuentaRepository.delete(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Cuenta findById(String id) {
		return cuentaRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cuenta> findAll() {
		return cuentaRepository.findAll();
	}

}
