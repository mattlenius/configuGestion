package co.edu.usb.cali.banco.service;

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
import org.springframework.beans.factory.annotation.Autowired;
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

class TestTipoDocumentoService {

	@Autowired
	private TipoDocumentoService tipoDocumentoService;

	long tdocId = 6L;

	@Test
	@DisplayName("CrearTipoDocumento")
	
	
	void aTest() throws Exception {
		assertNotNull(tipoDocumentoService, "el tipoDocumentoService es nulo");

		TipoDocumento tipoDocumento = tipoDocumentoService.findById(tdocId);
		assertNull(tipoDocumento, "el tipoDocumento ya existe");

		tipoDocumento = new TipoDocumento();

		tipoDocumento.setActivo("S");
		tipoDocumento.setNombre("Cedula Extrajera");
		tipoDocumento.setTdocId(tdocId);

		tipoDocumentoService.save(tipoDocumento);

	}

	@Test
	@DisplayName("ModificarTipoDocumento")
	void bTest() throws Exception {

		assertNotNull(tipoDocumentoService, "el entityManager es nulo");

		TipoDocumento tipoDocumento = tipoDocumentoService.findById(tdocId);
		assertNotNull(tipoDocumento, "el tipoDocumento no existe");

		tipoDocumento = new TipoDocumento();

		tipoDocumento.setActivo("N");
		tipoDocumento.setNombre("Cedula extrajera");
		tipoDocumento.setTdocId(tdocId);

		tipoDocumentoService.update(tipoDocumento);
	}

	@Test
	@DisplayName("BorrarTipoDocumento")
	void cTest() throws Exception {
		assertNotNull(tipoDocumentoService, "el entityManager es nulo");

		TipoDocumento tipoDocumento = tipoDocumentoService.findById(tdocId);
		assertNotNull(tipoDocumento, "el tipoDocumento no existe");

		tipoDocumentoService.delete(tipoDocumento);

	}

	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestTipoDocumentoService.class);

	@Test
	@DisplayName("ConsultarTodos")
	void dTest() {

		assertNotNull(tipoDocumentoService, "el tipoDocumentoService es nulo");

		List<TipoDocumento> losTipoDocumento = tipoDocumentoService.findAll();

		for (TipoDocumento tipoDocumento : losTipoDocumento) {
			log.info("id:" + tipoDocumento.getNombre());
			log.info("Nombre:" + tipoDocumento.getTdocId());

		}

		losTipoDocumento.forEach(tipoDocumento -> {
			log.info("id:" + tipoDocumento.getNombre());
			log.info("Nombre:" + tipoDocumento.getTdocId());
		});

	}

}
