package co.edu.usb.cali.banco.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false) //

class TestTipoUsuarioRepository {


	
	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository; 
	
	long tiusId = 4L;

	@Test
	@DisplayName("CrearTipoUsuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void aTest() {
		assertNotNull(tipoUsuarioRepository, "el tipoUsuarioRepository es nulo");

		TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(tiusId);
		assertNull(tipoUsuario, "el TipoUsuario ya existe");

		tipoUsuario = new TipoUsuario();
		tipoUsuario.setActivo("S");
		tipoUsuario.setNombre("dasdasda");
		tipoUsuario.setTiusId(tiusId);

		tipoUsuarioRepository.save(tipoUsuario);

	}

	@Test
	@DisplayName("ModificarTipoUsuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void bTest() {

		assertNotNull(tipoUsuarioRepository, "el entityManager es nulo");

		TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(tiusId);
		assertNotNull(tipoUsuario, "el tipoUsuario no existe");

		tipoUsuario = new TipoUsuario();
		tipoUsuario.setActivo("N");
		tipoUsuario.setNombre("dasdasdsa");
		tipoUsuario.setTiusId(tiusId);

		tipoUsuarioRepository.update(tipoUsuario);

	}

	@Test
	@DisplayName("BorrarTipoUsuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void cTest() {
		assertNotNull(tipoUsuarioRepository, "el entityManager es nulo");

		TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(tiusId);
		assertNotNull(tipoUsuario, "el tipoUsuario no existe");

		tipoUsuarioRepository.delete(tipoUsuario);
	}

	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestTipoUsuarioRepository.class);

	@Test
	@DisplayName("ConsultarTodos")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void dTest() {

		assertNotNull(tipoUsuarioRepository, "el entityManager es nulo");

		List<TipoUsuario> losTipoUsuario = tipoUsuarioRepository.findAll();

		for (TipoUsuario tipoUsuario : losTipoUsuario) {
			log.info("id:" + tipoUsuario.getNombre());
			log.info("Nombre:" + tipoUsuario.getTiusId());

		}

		losTipoUsuario.forEach(tipoUsuario -> {
			log.info("id:" + tipoUsuario.getNombre());
			log.info("Nombre:" + tipoUsuario.getTiusId());
		});

	}

}
