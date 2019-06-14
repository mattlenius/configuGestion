package co.edu.usb.cali.banco.service;

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

class TestUsuarioService {

	
	@Autowired
	private  UsuarioService usuarioService;
	
	@Autowired
	private TipoUsuarioService tipousuarioService; 
	
	String usuUsuario = "callbr23";

	@Test
	@DisplayName("CrearUsuario")
	void aTest() throws Exception {
		assertNotNull(usuarioService, "el usuarioService es nulo");

		Usuario usuario = usuarioService.findById(usuUsuario);;
		assertNull(usuario, "el usurio ya existe");

		usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setClave("dasdasd");
		usuario.setIdentificacion(new BigDecimal(1143));
		usuario.setNombre("juanda");

		TipoUsuario tipoUsuario = tipousuarioService.findById(1L);
		assertNotNull(tipoUsuario, "el tipoUsuario no existe");

		usuario.setTipoUsuario(tipoUsuario);
		usuario.setUsuUsuario(usuUsuario);

		usuarioService.save(usuario);

	}

	@Test
	@DisplayName("ModificarUsuario")
	void bTest() throws Exception {

		assertNotNull(usuarioService, "el entityManager es nulo");

		Usuario usuario = usuarioService.findById(usuUsuario);
		assertNotNull(usuario, "el usuario no existe");

		usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setClave("dasdasd");
		usuario.setIdentificacion(new BigDecimal(1143));
		usuario.setNombre("juanda");
		usuario.setUsuUsuario(usuUsuario);

		TipoUsuario tipoUsuario = tipousuarioService.findById(1L);
		assertNotNull(tipoUsuario, "el tipoUsuario no existe");

		usuario.setTipoUsuario(tipoUsuario);

		usuarioService.update(usuario);

	}

	@Test
	@DisplayName("BorrarUsuario")
	void cTest() throws Exception {
		assertNotNull(usuarioService, "el entityManager es nulo");

		Usuario usuario = usuarioService.findById(usuUsuario);
		assertNotNull(usuario, "el usuario no existe");

		usuarioService.delete(usuario);

	}

	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestUsuarioService.class);

	@Test
	@DisplayName("ConsultarTodos")
	void dTest() {
		assertNotNull(usuarioService, "el usuarioService es nulo");

		String JPQL = "SELECT cli  FROM Usuario cli";
		List<Usuario> losUsuario = usuarioService.findAll();

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
