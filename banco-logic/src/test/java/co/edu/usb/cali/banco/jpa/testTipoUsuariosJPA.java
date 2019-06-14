package co.edu.usb.cali.banco.jpa;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import co.edu.usb.cali.banco.domain.Cliente;
import co.edu.usb.cali.banco.domain.TipoDocumento;
import co.edu.usb.cali.banco.domain.TipoUsuario;
import co.edu.usb.cali.banco.domain.Usuario;

class testTipoUsuariosJPA {
	long tiusId=4L;

	@Test
	@DisplayName("CrearTipoUsuario")
	void aTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		TipoUsuario tipoUsuario  = entityManager.find(TipoUsuario.class,tiusId );
		assertNull(tipoUsuario, "el cliente ya existe");
		
		tipoUsuario = new TipoUsuario();
		tipoUsuario.setActivo("S");
		tipoUsuario.setNombre("dasdasda");
		tipoUsuario.setTiusId(tiusId);
		
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(tipoUsuario);
		
		entityManager.getTransaction().commit();
		
		
	}
	
	
	@Test
	@DisplayName("ModificarTipoUsuario")
	void bTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		TipoUsuario tipoUsuario  = entityManager.find(TipoUsuario.class,tiusId );
		assertNotNull(tipoUsuario, "el tipoUsuario no existe");
		
		tipoUsuario = new TipoUsuario();
		tipoUsuario.setActivo("N");
		tipoUsuario.setNombre("dasdasdsa");
		tipoUsuario.setTiusId(tiusId);
		
		entityManager.getTransaction().begin();
			entityManager.merge(tipoUsuario);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("BorrarTipoUsuario")
	void cTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		TipoUsuario tipoUsuario  = entityManager.find(TipoUsuario.class,tiusId );
		assertNotNull(tipoUsuario, "el tipoUsuario no existe");
		
		entityManager.getTransaction().begin();
			entityManager.remove(tipoUsuario);
		entityManager.getTransaction().commit();
		
		
	}
	
	private final static Logger log = org.slf4j.LoggerFactory.getLogger(testTipoUsuariosJPA.class);
	@Test
	@DisplayName("ConsultarTodos")
	void dTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		String JPQL="SELECT cli  FROM TipoUsuario cli";
		List<TipoUsuario> losTipoUsuario =  entityManager.createQuery(JPQL).getResultList();
		
		for (TipoUsuario tipoUsuario : losTipoUsuario) {
			log.info("id:"+tipoUsuario.getNombre());
			log.info("Nombre:"+tipoUsuario.getTiusId());
			
		}
		
		losTipoUsuario.forEach(tipoUsuario ->{
			log.info("id:"+tipoUsuario.getNombre());
			log.info("Nombre:"+tipoUsuario.getTiusId());
		});
		
		
	}

}
