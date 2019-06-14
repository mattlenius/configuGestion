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

import co.edu.usb.cali.banco.domain.Cliente;
import co.edu.usb.cali.banco.domain.CuentaRegistrada;
import co.edu.usb.cali.banco.repository.CuentaRegistradaRepository;


@Service
@Scope("singleton")
public class CuentaRegistradaServiceImpl implements CuentaRegistradaService {
	
	
	@Autowired
	private CuentaRegistradaRepository cuentaRegistradaRepository;
	
	@Autowired
	private Validator validator;

	public void validar(CuentaRegistrada cuentaRegistrada) throws Exception {
		try {
			Set<ConstraintViolation<CuentaRegistrada>> constraintViolations = validator.validate(cuentaRegistrada);

			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();

				for (ConstraintViolation<CuentaRegistrada> constraintViolation : constraintViolations) {
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
	public void save(CuentaRegistrada cuentaRegistrada) throws Exception {
		if (cuentaRegistrada == null) {
			throw new Exception("El cuentaRegistrada es nulo");
		}
		validar(cuentaRegistrada);
		CuentaRegistrada entity = cuentaRegistradaRepository.findById(cuentaRegistrada.getCureId());
		if(entity!=null) {
			throw new Exception("El cuentaRegistrada no se puede crear porque ya existe");
		}
		cuentaRegistradaRepository.save(cuentaRegistrada);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(CuentaRegistrada cuentaRegistrada) throws Exception {
		if (cuentaRegistrada == null) {
			throw new Exception("El cuentaRegistrada es nulo");
		}
		validar(cuentaRegistrada);
		CuentaRegistrada entity = cuentaRegistradaRepository.findById(cuentaRegistrada.getCureId());
		if(entity==null) {
			throw new Exception("El cuentaRegistrada no se puede modificar porque no existe");
		}
		cuentaRegistradaRepository.update(cuentaRegistrada);


	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(CuentaRegistrada cuentaRegistrada) throws Exception {
		if (cuentaRegistrada == null) {
			throw new Exception("El cuentaRegistrada es nulo");
		}
		CuentaRegistrada entity = cuentaRegistradaRepository.findById(cuentaRegistrada.getCureId());
		if(entity==null) {
			throw new Exception("El cuentaRegistrada no se puede borrar porque no existe");
		}
		cuentaRegistradaRepository.delete(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public CuentaRegistrada findById(Long id) {
		return cuentaRegistradaRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CuentaRegistrada> findAll() {
		return cuentaRegistradaRepository.findAll();
	}

}
