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

import co.edu.usb.cali.banco.domain.TipoTransaccion;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false) //

class TestTipoTransaccionRepository {

	@Autowired
	private TipoTransaccionRepository tipoTransaccionRepository;
	
	long titrId = 4L;

	@Test
	@DisplayName("CrearTipoTransaccion")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void aTest() {
		assertNotNull(tipoTransaccionRepository, "el entityManager es nulo");

		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(titrId);
		assertNull(tipoTransaccion, "el tipoTransaccion ya existe");

		tipoTransaccion = new TipoTransaccion();
		tipoTransaccion.setActivo("S");
		tipoTransaccion.setNombre("BIGCOINS");
		tipoTransaccion.setTitrId(titrId);

		tipoTransaccionRepository.save(tipoTransaccion);

	}

	@Test
	@DisplayName("ModificarTipoTransaccion")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void bTest() {

		assertNotNull(tipoTransaccionRepository, "el tipoTransaccionRepository es nulo");

		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(titrId);
		assertNotNull(tipoTransaccion, "el tipoTransaccion no existe");

		tipoTransaccion = new TipoTransaccion();
		tipoTransaccion.setActivo("N");
		tipoTransaccion.setNombre("BIGCOINsS");
		tipoTransaccion.setTitrId(titrId);

		tipoTransaccionRepository.update(tipoTransaccion);

	}

	@Test
	@DisplayName("BorrarTipoTransaccion")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void cTest() {
		assertNotNull(tipoTransaccionRepository, "el entityManager es nulo");

		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(titrId);
		assertNotNull(tipoTransaccion, "el tipoTransaccion no existe");

		tipoTransaccionRepository.delete(tipoTransaccion);

	}

	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestTipoTransaccionRepository.class);

	@Test
	@DisplayName("ConsultarTodos")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void dTest() {

		assertNotNull(tipoTransaccionRepository, "el tipoTransaccionRepository es nulo");

		List<TipoTransaccion> losTipoTransaccion = tipoTransaccionRepository.findAll();

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
