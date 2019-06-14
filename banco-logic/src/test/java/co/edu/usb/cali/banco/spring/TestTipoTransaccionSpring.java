package co.edu.usb.cali.banco.spring;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usb.cali.banco.domain.TipoTransaccion;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false) //

class TestTipoTransaccionSpring {

	@PersistenceContext
	EntityManager entityManager;
	long titrId = 4L;

	@Test
	@DisplayName("CrearTipoTransaccion")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void aTest() {
		assertNotNull(entityManager, "el entityManager es nulo");

		TipoTransaccion tipoTransaccion = entityManager.find(TipoTransaccion.class, titrId);
		assertNull(tipoTransaccion, "el tipoTransaccion ya existe");

		tipoTransaccion = new TipoTransaccion();
		tipoTransaccion.setActivo("S");
		tipoTransaccion.setNombre("BIGCOINS");
		tipoTransaccion.setTitrId(titrId);

		entityManager.persist(tipoTransaccion);

	}

	@Test
	@DisplayName("ModificarTipoTransaccion")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void bTest() {

		assertNotNull(entityManager, "el entityManager es nulo");

		TipoTransaccion tipoTransaccion = entityManager.find(TipoTransaccion.class, titrId);
		assertNotNull(tipoTransaccion, "el tipoTransaccion no existe");

		tipoTransaccion = new TipoTransaccion();
		tipoTransaccion.setActivo("N");
		tipoTransaccion.setNombre("BIGCOINsS");
		tipoTransaccion.setTitrId(titrId);

		entityManager.merge(tipoTransaccion);

	}

	@Test
	@DisplayName("BorrarTipoTransaccion")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void cTest() {
		assertNotNull(entityManager, "el entityManager es nulo");

		TipoTransaccion tipoTransaccion = entityManager.find(TipoTransaccion.class, titrId);
		assertNotNull(tipoTransaccion, "el tipoTransaccion no existe");

		entityManager.remove(tipoTransaccion);

	}

	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestTipoTransaccionSpring.class);

	@Test
	@DisplayName("ConsultarTodos")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void dTest() {

		assertNotNull(entityManager, "el entityManager es nulo");

		String JPQL = "SELECT cli  FROM TipoTransaccion cli";
		List<TipoTransaccion> losTipoTransaccion = entityManager.createQuery(JPQL).getResultList();

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
