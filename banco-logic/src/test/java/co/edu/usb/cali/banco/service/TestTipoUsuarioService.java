package co.edu.usb.cali.banco.service;

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

class TestTipoUsuarioService {


	
	@Autowired
	private TipoUsuarioService tipoUsuarioService; 
	
	long tiusId = 4L;

	@Test
	@DisplayName("CrearTipoUsuario")
	void aTest() throws Exception {
		assertNotNull(tipoUsuarioService, "el tipoUsuarioService es nulo");

		TipoUsuario tipoUsuario = tipoUsuarioService.findById(tiusId);
		assertNull(tipoUsuario, "el TipoUsuario ya existe");

		tipoUsuario = new TipoUsuario();
		tipoUsuario.setActivo("S");
		tipoUsuario.setNombre("dasdasda");
		tipoUsuario.setTiusId(tiusId);

		tipoUsuarioService.save(tipoUsuario);

	}

	@Test
	@DisplayName("ModificarTipoUsuario")
	void bTest() throws Exception {

		assertNotNull(tipoUsuarioService, "el tipoUsuarioService es nulo");

		TipoUsuario tipoUsuario = tipoUsuarioService.findById(tiusId);
		assertNotNull(tipoUsuario, "el tipoUsuario no existe");

		tipoUsuario = new TipoUsuario();
		tipoUsuario.setActivo("N");
		tipoUsuario.setNombre("dasdasdsa");
		tipoUsuario.setTiusId(tiusId);

		tipoUsuarioService.update(tipoUsuario);

	}

	@Test
	@DisplayName("BorrarTipoUsuario")
	void cTest() throws Exception {
		assertNotNull(tipoUsuarioService, "el tipoUsuarioService es nulo");

		TipoUsuario tipoUsuario = tipoUsuarioService.findById(tiusId);
		assertNotNull(tipoUsuario, "el tipoUsuario no existe");

		tipoUsuarioService.delete(tipoUsuario);
	}

	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestTipoUsuarioService.class);

	@Test
	@DisplayName("ConsultarTodos")
	void dTest() {

		assertNotNull(tipoUsuarioService, "el tipoUsuarioService es nulo");

		List<TipoUsuario> losTipoUsuario = tipoUsuarioService.findAll();

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
