package co.edu.usb.cali.banco.jpa;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import co.edu.usb.cali.banco.domain.Cliente;
import co.edu.usb.cali.banco.domain.Cuenta;
import co.edu.usb.cali.banco.domain.CuentaRegistrada;
import co.edu.usb.cali.banco.domain.TipoDocumento;
import co.edu.usb.cali.banco.domain.TipoTransaccion;
import co.edu.usb.cali.banco.domain.TipoUsuario;
import co.edu.usb.cali.banco.domain.Transaccion;
import co.edu.usb.cali.banco.domain.Usuario;

class testUsuarioJPA {
	String usuUsuario="callbrooe23";


	@Test
	@DisplayName("CrearUsuario")
	void aTest() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		Usuario usuario  = entityManager.find(Usuario.class,usuUsuario );
		assertNull(usuario, "el cuenta ya existe");
		
		usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setClave("dasdasd");
		usuario.setIdentificacion(new BigDecimal(1143));
		usuario.setNombre("juanda");
		usuario.setUsuUsuario(usuUsuario);
		
		
		TipoUsuario tipoUsuario= entityManager.find(TipoUsuario.class,1L );
		assertNotNull(tipoUsuario, "el cliente no existe");
		
		usuario.setTipoUsuario(tipoUsuario);
		
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		
		
		
	}
	
	
	@Test
	@DisplayName("ModificarUsuario")
	void bTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		Usuario usuario  = entityManager.find(Usuario.class,usuUsuario );
		assertNotNull(usuario, "el cliente no existe");
		
		usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setClave("dasdasd");
		usuario.setIdentificacion(new BigDecimal(1143));
		usuario.setNombre("juanda");
		usuario.setUsuUsuario(usuUsuario);
		
		
		TipoUsuario tipoUsuario= entityManager.find(TipoUsuario.class,1L );
		assertNotNull(tipoUsuario, "el cliente no existe");
		
		usuario.setTipoUsuario(tipoUsuario);
		
		
		entityManager.getTransaction().begin();
			entityManager.merge(tipoUsuario);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("BorrarUsuario")
	void cTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		Usuario usuario  = entityManager.find(Usuario.class,usuUsuario );
		assertNotNull(usuario, "el cliente no existe");
		
		entityManager.getTransaction().begin();
			entityManager.remove(usuario);
		entityManager.getTransaction().commit();
		
		
	}
	
	private final static Logger log = org.slf4j.LoggerFactory.getLogger(testCuentaJPA.class);
	@Test
	@DisplayName("ConsultarTodos")
	void dTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		String JPQL="SELECT cli  FROM Usuario cli";
		List<Usuario> losUsuario =  entityManager.createQuery(JPQL).getResultList();
		
		for (Usuario usuario : losUsuario) {
			log.info("id:"+usuario.getNombre());
			log.info("Nombre:"+usuario.getClave());
			
		}
		
		losUsuario.forEach(usuario ->{
			log.info("id:"+usuario.getNombre());
			log.info("Nombre:"+usuario.getClave());
		});
		
		
	}

}
