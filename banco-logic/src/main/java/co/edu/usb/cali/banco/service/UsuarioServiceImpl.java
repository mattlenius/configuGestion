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
import co.edu.usb.cali.banco.domain.Usuario;
import co.edu.usb.cali.banco.repository.UsuarioRepository;


@Service
@Scope("singleton")
public class UsuarioServiceImpl implements UsuarioService {
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private Validator validator;

	public void validar(Usuario usuario) throws Exception {
		try {
			Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);

			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();

				for (ConstraintViolation<Usuario> constraintViolation : constraintViolations) {
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
	public void save(Usuario usuario) throws Exception {
		if (usuario == null) {
			throw new Exception("El usuario es nulo");
		}
		validar(usuario);
		Usuario entity = usuarioRepository.findById(usuario.getUsuUsuario());
		if(entity!=null) {
			throw new Exception("El usuario no se puede crear porque ya existe");
		}
		usuarioRepository.save(usuario);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Usuario usuario) throws Exception {
		if (usuario == null) {
			throw new Exception("El usuario es nulo");
		}
		validar(usuario);
		Usuario entity = usuarioRepository.findById(usuario.getUsuUsuario());
		if(entity==null) {
			throw new Exception("El usuario no se puede modificar porque no existe");
		}
		usuarioRepository.update(usuario);


	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Usuario usuario) throws Exception {
		if (usuario == null) {
			throw new Exception("El usuario es nulo");
		}
		Usuario entity = usuarioRepository.findById(usuario.getUsuUsuario());
		if(entity==null) {
			throw new Exception("El usuario no se puede borrar porque no existe");
		}
		usuarioRepository.delete(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(String id) {
		return usuarioRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

}
