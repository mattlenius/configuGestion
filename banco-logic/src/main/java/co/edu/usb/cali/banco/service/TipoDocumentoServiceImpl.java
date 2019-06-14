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
import co.edu.usb.cali.banco.domain.TipoDocumento;
import co.edu.usb.cali.banco.repository.TipoDocumentoRepository;

@Service
@Scope("singleton")
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

	@Autowired
	private Validator validator;

	public void validar(TipoDocumento tipoDocumento) throws Exception {
		try {
			Set<ConstraintViolation<TipoDocumento>> constraintViolations = validator.validate(tipoDocumento);

			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();

				for (ConstraintViolation<TipoDocumento> constraintViolation : constraintViolations) {
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

	@Autowired
	private TipoDocumentoRepository TipoDocumentoRepository;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void save(TipoDocumento tipoDocumento) throws Exception {
		if (tipoDocumento == null) {
			throw new Exception("El cliente es nulo");
		}
		validar(tipoDocumento);
		TipoDocumento entity = TipoDocumentoRepository.findById(tipoDocumento.getTdocId());
		if (entity != null) {
			throw new Exception("El cliente no se puede crear porque ya existe");
		}
		TipoDocumentoRepository.save(tipoDocumento);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(TipoDocumento tipoDocumento) throws Exception {
		if (tipoDocumento == null) {
			throw new Exception("El cliente es nulo");
		}
		validar(tipoDocumento);
		TipoDocumento entity = TipoDocumentoRepository.findById(tipoDocumento.getTdocId());
		if(entity==null) {
			throw new Exception("El cliente no se puede modificar porque no existe");
		}
		TipoDocumentoRepository.update(tipoDocumento);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(TipoDocumento tipoDocumento) throws Exception {
		if (tipoDocumento == null) {
			throw new Exception("El cliente es nulo");
		}
		TipoDocumento entity = TipoDocumentoRepository.findById(tipoDocumento.getTdocId());
		if(entity==null) {
			throw new Exception("El cliente no se puede borrar porque no existe");
		}
		TipoDocumentoRepository.delete(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public TipoDocumento findById(Long id) {
		// TODO Auto-generated method stub
		return TipoDocumentoRepository.findById(id);
	}

	@Override
	public List<TipoDocumento> findAll() {
		return TipoDocumentoRepository.findAll();
	}

}
