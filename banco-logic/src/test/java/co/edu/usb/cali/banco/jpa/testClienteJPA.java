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

class testClienteJPA {
	long clieId=142020L;

	@Test
	@DisplayName("CrearCliente")
	void aTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		Cliente cliente  = entityManager.find(Cliente.class,clieId );
		assertNull(cliente, "el cliente ya existe");
		
		cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("avenida perdida del nunca jamas");
		cliente.setEmail("juandavidarcila18@gmail.com");
		cliente.setNombre("Juan David Arcila");
		cliente.setTelefono("3105220300");
		
		
		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class,2L );
		assertNotNull(tipoDocumento, "el tipoDocumento es nulo");
		
		cliente.setTipoDocumento(tipoDocumento);
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(cliente);
		
		entityManager.getTransaction().commit();
		
		
	}
	
	
	@Test
	@DisplayName("ModificarCliente")
	void bTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		Cliente cliente  = entityManager.find(Cliente.class,clieId );
		assertNotNull(cliente, "el cliente no existe");
		
		cliente = new Cliente();
		cliente.setActivo("N");
		cliente.setDireccion("avenida perdida jamas");
		cliente.setEmail("juandavidarcila18@gmail.com");
		cliente.setNombre("Juan David Arcila Campaz");
		cliente.setTelefono("3105220300");
		
		
		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class,2L );
		assertNotNull(tipoDocumento, "el tipoDocumento es nulo");
		cliente.setTipoDocumento(tipoDocumento);
		
		entityManager.getTransaction().begin();
			entityManager.merge(cliente);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("BorrarCliente")
	void cTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		Cliente cliente  = entityManager.find(Cliente.class,clieId );
		assertNotNull(cliente, "el cliente no existe");
		
		entityManager.getTransaction().begin();
			entityManager.remove(cliente);
		entityManager.getTransaction().commit();
		
		
	}
	
	private final static Logger log = org.slf4j.LoggerFactory.getLogger(testClienteJPA.class);
	@Test
	@DisplayName("ConsultarTodos")
	void dTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		String JPQL="SELECT cli  FROM Cliente cli";
		List<Cliente> losCliente =  entityManager.createQuery(JPQL).getResultList();
		
		for (Cliente cliente : losCliente) {
			log.info("id:"+cliente.getNombre());
			log.info("Nombre:"+cliente.getNombre());
			
		}
		
		losCliente.forEach(cliente ->{
			log.info("id:"+cliente.getNombre());
			log.info("Nombre:"+cliente.getNombre());
		});
		
		
	}

}
