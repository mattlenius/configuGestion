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

import co.edu.usb.cali.banco.domain.Transaccion;
import co.edu.usb.cali.banco.repository.TransaccionRepository;


@Service
@Scope("singleton")
public class TransaccionServiceImpl implements TransaccionService {
	
	
	@Autowired
	private TransaccionRepository transaccionRepository;
	
	@Autowired
	private Validator validator;

	public void validar(Transaccion transaccion) throws Exception {
		try {
			Set<ConstraintViolation<Transaccion>> constraintViolations = validator.validate(transaccion);

			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();

				for (ConstraintViolation<Transaccion> constraintViolation : constraintViolations) {
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
	public void save(Transaccion transaccion) throws Exception {
		if (transaccion == null) {
			throw new Exception("El transaccion es nulo");
		}
		validar(transaccion);
		Transaccion entity = transaccionRepository.findById(transaccion.getTranId());
		if(entity!=null) {
			throw new Exception("El transaccion no se puede crear porque ya existe");
		}
		transaccionRepository.save(transaccion);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Transaccion transaccion) throws Exception {
		if (transaccion == null) {
			throw new Exception("El transaccion es nulo");
		}
		validar(transaccion);
		Transaccion entity = transaccionRepository.findById(transaccion.getTranId());
		if(entity==null) {
			throw new Exception("El transaccion no se puede modificar porque no existe");
		}
		transaccionRepository.update(transaccion);


	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Transaccion transaccion) throws Exception {
		if (transaccion == null) {
			throw new Exception("El transaccion es nulo");
		}
		Transaccion entity = transaccionRepository.findById(transaccion.getTranId());
		if(entity==null) {
			throw new Exception("El transaccion no se puede borrar porque no existe");
		}
		transaccionRepository.delete(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Transaccion findById(Long id) {
		return transaccionRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Transaccion> findAll() {
		return transaccionRepository.findAll();
	}

}
