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

import co.edu.usb.cali.banco.domain.Cliente;
import co.edu.usb.cali.banco.domain.TipoDocumento;
import co.edu.usb.cali.banco.domain.TipoUsuario;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false) //

class TestTipoUsuarioSpring {

	@PersistenceContext
	EntityManager entityManager;
	long tiusId = 4L;

	@Test
	@DisplayName("CrearTipoUsuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void aTest() {
		assertNotNull(entityManager, "el entityManager es nulo");

		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, tiusId);
		assertNull(tipoUsuario, "el cliente ya existe");

		tipoUsuario = new TipoUsuario();
		tipoUsuario.setActivo("S");
		tipoUsuario.setNombre("dasdasda");
		tipoUsuario.setTiusId(tiusId);

		entityManager.persist(tipoUsuario);

	}

	@Test
	@DisplayName("ModificarTipoUsuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void bTest() {

		assertNotNull(entityManager, "el entityManager es nulo");

		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, tiusId);
		assertNotNull(tipoUsuario, "el tipoUsuario no existe");

		tipoUsuario = new TipoUsuario();
		tipoUsuario.setActivo("N");
		tipoUsuario.setNombre("dasdasdsa");
		tipoUsuario.setTiusId(tiusId);

		entityManager.merge(tipoUsuario);

	}

	@Test
	@DisplayName("BorrarTipoUsuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void cTest() {
		assertNotNull(entityManager, "el entityManager es nulo");

		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, tiusId);
		assertNotNull(tipoUsuario, "el tipoUsuario no existe");

		entityManager.remove(tipoUsuario);
	}

	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestTipoUsuarioSpring.class);

	@Test
	@DisplayName("ConsultarTodos")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void dTest() {

		assertNotNull(entityManager, "el entityManager es nulo");

		String JPQL = "SELECT cli  FROM TipoUsuario cli";
		List<TipoUsuario> losTipoUsuario = entityManager.createQuery(JPQL).getResultList();

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
