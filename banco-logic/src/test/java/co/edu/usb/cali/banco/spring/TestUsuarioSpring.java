package co.edu.usb.cali.banco.spring;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
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
import co.edu.usb.cali.banco.domain.Usuario;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false) 

class TestUsuarioSpring {

	@PersistenceContext
	EntityManager entityManager;
	String usuUsuario = "callbr23";

	@Test
	@DisplayName("CrearUsuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void aTest() {
		assertNotNull(entityManager, "el entityManager es nulo");

		Usuario usuario = entityManager.find(Usuario.class, usuUsuario);
		assertNull(usuario, "el usurio ya existe");

		usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setClave("dasdasd");
		usuario.setIdentificacion(new BigDecimal(1143));
		usuario.setNombre("juanda");

		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, 1L);
		assertNotNull(tipoUsuario, "el tipoUsuario no existe");

		usuario.setTipoUsuario(tipoUsuario);
		usuario.setUsuUsuario(usuUsuario);

		entityManager.persist(usuario);

	}

	@Test
	@DisplayName("ModificarUsuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void bTest() {

		assertNotNull(entityManager, "el entityManager es nulo");

		Usuario usuario = entityManager.find(Usuario.class, usuUsuario);
		assertNotNull(usuario, "el usuario no existe");

		usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setClave("dasdasd");
		usuario.setIdentificacion(new BigDecimal(1143));
		usuario.setNombre("juanda");
		usuario.setUsuUsuario(usuUsuario);

		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, 1L);
		assertNotNull(tipoUsuario, "el cliente no existe");

		usuario.setTipoUsuario(tipoUsuario);

		entityManager.merge(tipoUsuario);

	}

	@Test
	@DisplayName("BorrarUsuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void cTest() {
		assertNotNull(entityManager, "el entityManager es nulo");

		Usuario usuario = entityManager.find(Usuario.class, usuUsuario);
		assertNotNull(usuario, "el usuario no existe");

		entityManager.remove(usuario);

	}

	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestUsuarioSpring.class);

	@Test
	@DisplayName("ConsultarTodos")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void dTest() {
		assertNotNull(entityManager, "el entityManager es nulo");

		String JPQL = "SELECT cli  FROM Usuario cli";
		List<Usuario> losUsuario = entityManager.createQuery(JPQL).getResultList();

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
