package co.edu.usb.cali.banco.spring;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)//

class TestClienteSpring {
	
	@PersistenceContext
	EntityManager entityManager;
	long clieId=142020L;
	
	@Test
	@DisplayName("CrearCliente")
	@Transactional(readOnly=false, propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	void aTest() {		
		assertNotNull(entityManager, "el entityManager es nulo");
		
		Cliente cliente  = entityManager.find(Cliente.class,clieId );
		assertNull(cliente, "el cliente no existe");
		
		cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("avenida perdida jamas");
		cliente.setEmail("juandavidarcila18@gmail.com");
		cliente.setNombre("Juan David Arcila Campaz");
		cliente.setTelefono("3105220300");
		
		
		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class,2L );
		assertNotNull(tipoDocumento, "el tipoDocumento es nulo");
		cliente.setTipoDocumento(tipoDocumento);
		
		entityManager.persist(cliente);

	}
	
	@Test
	@DisplayName("ModificarCliente")
	@Transactional(readOnly=false, propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	void bTest() {

		assertNotNull(entityManager, "el entityManager es nulo");
		
		Cliente cliente  = entityManager.find(Cliente.class,clieId );
		assertNotNull(cliente, "el cliente no existe");
		
		cliente.setActivo("N");
		cliente.setDireccion("avenida perdida jamas");
		cliente.setEmail("juandavidarcila18@gmail.com");
		cliente.setNombre("Juan David Arcila Campaz");
		cliente.setTelefono("3105220300");

	    entityManager.merge(cliente);
		
	}
	
	@Test
	@DisplayName("BorrarCliente")
	@Transactional(readOnly=false, propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	void cTest() {
		assertNotNull(entityManager, "el entityManager es nulo");
		
		Cliente cliente  = entityManager.find(Cliente.class,clieId );
		assertNotNull(cliente, "el cliente no existe");
		entityManager.remove(cliente);
		
	}
	
	
	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestClienteSpring.class);
	@Test
	@DisplayName("ConsultarTodos")
	@Transactional(readOnly=false, propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	void dTest() {
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
