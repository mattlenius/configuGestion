package co.edu.usb.cali.banco.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usb.cali.banco.domain.TipoUsuario;
import co.edu.usb.cali.banco.domain.Usuario;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false) 

class TestUsuarioRepository {

	
	@Autowired
	private  UsuarioRepository usuarioRepository;
	
	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository; 
	
	String usuUsuario = "callbr23";

	@Test
	@DisplayName("CrearUsuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void aTest() {
		assertNotNull(usuarioRepository, "el usuarioRepository es nulo");

		Usuario usuario = usuarioRepository.findById(usuUsuario);;
		assertNull(usuario, "el usurio ya existe");

		usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setClave("dasdasd");
		usuario.setIdentificacion(new BigDecimal(1143));
		usuario.setNombre("juanda");

		TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(1L);
		assertNotNull(tipoUsuario, "el tipoUsuario no existe");

		usuario.setTipoUsuario(tipoUsuario);
		usuario.setUsuUsuario(usuUsuario);

		usuarioRepository.save(usuario);

	}

	@Test
	@DisplayName("ModificarUsuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void bTest() {

		assertNotNull(usuarioRepository, "el entityManager es nulo");

		Usuario usuario = usuarioRepository.findById(usuUsuario);
		assertNotNull(usuario, "el usuario no existe");

		usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setClave("dasdasd");
		usuario.setIdentificacion(new BigDecimal(1143));
		usuario.setNombre("juanda");
		usuario.setUsuUsuario(usuUsuario);

		TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(1L);
		assertNotNull(tipoUsuario, "el tipoUsuario no existe");

		usuario.setTipoUsuario(tipoUsuario);

		usuarioRepository.update(usuario);

	}

	@Test
	@DisplayName("BorrarUsuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void cTest() {
		assertNotNull(usuarioRepository, "el entityManager es nulo");

		Usuario usuario = usuarioRepository.findById(usuUsuario);
		assertNotNull(usuario, "el usuario no existe");

		usuarioRepository.delete(usuario);

	}

	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestUsuarioRepository.class);

	@Test
	@DisplayName("ConsultarTodos")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void dTest() {
		assertNotNull(usuarioRepository, "el usuarioRepository es nulo");

		String JPQL = "SELECT cli  FROM Usuario cli";
		List<Usuario> losUsuario = usuarioRepository.findAll();

		for (Usuario usuario : losUsuario) {
			log.info("id:" + usuario.getNombre());
			log.info("Nombre:" + usuario.getClave());

		}

		losUsuario.forEach(usuario -> {
			log.info("id:" + usuario.getNombre());
			log.info("Nombre:" + usuario.getClave());
		});

	}

}
