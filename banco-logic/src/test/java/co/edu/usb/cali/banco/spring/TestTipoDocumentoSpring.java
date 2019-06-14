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

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false) //

class TestTipoDocumentoSpring {

	@PersistenceContext
	EntityManager entityManager;
	long tdocId = 6L;

	@Test
	@DisplayName("CrearTipoDocumento")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void aTest() {
		assertNotNull(entityManager, "el entityManager es nulo");

		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, tdocId);
		assertNull(tipoDocumento, "el tipoDocumento ya existe");

		tipoDocumento = new TipoDocumento();

		tipoDocumento.setActivo("S");
		tipoDocumento.setNombre("Cedula Extrajera");
		tipoDocumento.setTdocId(tdocId);

		entityManager.persist(tipoDocumento);

	}

	@Test
	@DisplayName("ModificarTipoDocumento")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void bTest() {

		assertNotNull(entityManager, "el entityManager es nulo");

		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, tdocId);
		assertNotNull(tipoDocumento, "el tipoDocumento no existe");

		tipoDocumento = new TipoDocumento();

		tipoDocumento.setActivo("N");
		tipoDocumento.setNombre("Cedula extrajera");
		tipoDocumento.setTdocId(tdocId);

		entityManager.merge(tipoDocumento);

	}

	@Test
	@DisplayName("BorrarTipoDocumento")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void cTest() {
		assertNotNull(entityManager, "el entityManager es nulo");

		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, tdocId);
		assertNotNull(tipoDocumento, "el tipoDocumento no existe");

		entityManager.remove(tipoDocumento);

	}

	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestTipoDocumentoSpring.class);

	@Test
	@DisplayName("ConsultarTodos")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void dTest() {

		assertNotNull(entityManager, "el entityManager es nulo");
		
		String JPQL="SELECT cli  FROM TipoDocumento cli";
		List<TipoDocumento> losTipoDocumento =  entityManager.createQuery(JPQL).getResultList();
		
		for (TipoDocumento tipoDocumento : losTipoDocumento) {
			log.info("id:"+tipoDocumento.getNombre());
			log.info("Nombre:"+tipoDocumento.getTdocId());
			
		}
		
		losTipoDocumento.forEach(tipoDocumento ->{
			log.info("id:"+tipoDocumento.getNombre());
			log.info("Nombre:"+tipoDocumento.getTdocId());
		});

	}

}
