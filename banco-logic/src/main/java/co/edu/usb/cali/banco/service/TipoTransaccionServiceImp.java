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

import co.edu.usb.cali.banco.domain.CuentaRegistrada;
import co.edu.usb.cali.banco.domain.TipoTransaccion;
import co.edu.usb.cali.banco.repository.TipoTransaccionRepository;


@Service
@Scope("singleton")
public class TipoTransaccionServiceImp implements TipoTransaccionService {
	
	
	@Autowired
	private TipoTransaccionRepository tipoTransaccionRepository;
	
	@Autowired
	private Validator validator;

	public void validar(TipoTransaccion tipoTransaccion) throws Exception {
		try {
			Set<ConstraintViolation<TipoTransaccion>> constraintViolations = validator.validate(tipoTransaccion);

			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();

				for (ConstraintViolation<TipoTransaccion> constraintViolation : constraintViolations) {
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
	public void save(TipoTransaccion tipoTransaccion) throws Exception {
		if (tipoTransaccion == null) {
			throw new Exception("El tipoTransaccion es nulo");
		}
		validar(tipoTransaccion);
		TipoTransaccion entity = tipoTransaccionRepository.findById(tipoTransaccion.getTitrId());
		if(entity!=null) {
			throw new Exception("El tipoTransaccion no se puede crear porque ya existe");
		}
		tipoTransaccionRepository.save(tipoTransaccion);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(TipoTransaccion tipoTransaccion) throws Exception {
		if (tipoTransaccion == null) {
			throw new Exception("El tipoTransaccion es nulo");
		}
		validar(tipoTransaccion);
		TipoTransaccion entity = tipoTransaccionRepository.findById(tipoTransaccion.getTitrId());
		if(entity==null) {
			throw new Exception("El cliente no se puede modificar porque no existe");
		}
		tipoTransaccionRepository.update(tipoTransaccion);


	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(TipoTransaccion tipoTransaccion) throws Exception {
		if (tipoTransaccion == null) {
			throw new Exception("El tipoTransaccion es nulo");
		}
		TipoTransaccion entity = tipoTransaccionRepository.findById(tipoTransaccion.getTitrId());
		if(entity==null) {
			throw new Exception("El tipoTransaccion no se puede borrar porque no existe");
		}
		tipoTransaccionRepository.delete(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public TipoTransaccion findById(Long id) {
		return tipoTransaccionRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoTransaccion> findAll() {
		return tipoTransaccionRepository.findAll();
	}

}
