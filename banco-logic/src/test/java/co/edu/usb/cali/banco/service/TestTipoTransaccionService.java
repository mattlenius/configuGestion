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

import co.edu.usb.cali.banco.domain.TipoTransaccion;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false) //

class TestTipoTransaccionService {

	@Autowired
	private TipoTransaccionService tipoTransaccionService;
	
	long titrId = 4L;

	@Test
	@DisplayName("CrearTipoTransaccion")
	void aTest() throws Exception {
		assertNotNull(tipoTransaccionService, "el entityManager es nulo");

		TipoTransaccion tipoTransaccion = tipoTransaccionService.findById(titrId);
		assertNull(tipoTransaccion, "el tipoTransaccion ya existe");

		tipoTransaccion = new TipoTransaccion();
		tipoTransaccion.setActivo("S");
		tipoTransaccion.setNombre("BIGCOINS");
		tipoTransaccion.setTitrId(titrId);

		tipoTransaccionService.save(tipoTransaccion);

	}

	@Test
	@DisplayName("ModificarTipoTransaccion")
	void bTest() throws Exception {

		assertNotNull(tipoTransaccionService, "el tipoTransaccionService es nulo");

		TipoTransaccion tipoTransaccion = tipoTransaccionService.findById(titrId);
		assertNotNull(tipoTransaccion, "el tipoTransaccion no existe");

		tipoTransaccion = new TipoTransaccion();
		tipoTransaccion.setActivo("N");
		tipoTransaccion.setNombre("BIGCOINsS");
		tipoTransaccion.setTitrId(titrId);

		tipoTransaccionService.update(tipoTransaccion);

	}

	@Test
	@DisplayName("BorrarTipoTransaccion")
	void cTest() throws Exception {
		assertNotNull(tipoTransaccionService, "el entityManager es nulo");

		TipoTransaccion tipoTransaccion = tipoTransaccionService.findById(titrId);
		assertNotNull(tipoTransaccion, "el tipoTransaccion no existe");

		tipoTransaccionService.delete(tipoTransaccion);

	}

	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestTipoTransaccionService.class);

	@Test
	@DisplayName("ConsultarTodos")
	void dTest() {

		assertNotNull(tipoTransaccionService, "el tipoTransaccionService es nulo");

		List<TipoTransaccion> losTipoTransaccion = tipoTransaccionService.findAll();

		for (TipoTransaccion tipoTransaccion : losTipoTransaccion) {
			log.info("id:" + tipoTransaccion.getNombre());
			log.info("Nombre:" + tipoTransaccion.getTitrId());

		}

		losTipoTransaccion.forEach(tipoTransaccion -> {
			log.info("id:" + tipoTransaccion.getNombre());
			log.info("Nombre:" + tipoTransaccion.getTitrId());
		});

	}

}
