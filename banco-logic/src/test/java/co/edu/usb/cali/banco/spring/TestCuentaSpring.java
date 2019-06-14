package co.edu.usb.cali.banco.spring;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
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
import co.edu.usb.cali.banco.domain.Cuenta;
import co.edu.usb.cali.banco.domain.TipoDocumento;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)//

class TestCuentaSpring {
	
	@PersistenceContext
	EntityManager entityManager;
	String cuenId="5842-8348-1804-8569";
	
	@Test
	@DisplayName("CrearCuenta")
	@Transactional(readOnly=false, propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	void aTest() {		

		assertNotNull(entityManager, "el entityManager es nulo");
		
		Cuenta cuenta  = entityManager.find(Cuenta.class,cuenId );
		assertNull(cuenta, "el cuenta ya existe");
		
		cuenta = new Cuenta();
		cuenta.setActiva("S");
		cuenta.setCuenId(cuenId);

		Cliente cliente = entityManager.find(Cliente.class,2L );
		assertNotNull(cliente, "el cliente es nulo");
		
		cuenta.setCliente(cliente);
		cuenta.setSaldo(new BigDecimal(12222));
		cuenta.setClave("jdasdasdas");

		entityManager.persist(cuenta);

	}
	
	@Test
	@DisplayName("ModificarCuenta")
	@Transactional(readOnly=false, propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	void bTest() {

		assertNotNull(entityManager, "el entityManager es nulo");
		
		Cuenta cuenta  = entityManager.find(Cuenta.class,cuenId );
		assertNotNull(cuenta, "el cuenta no existe");
		
		cuenta = new Cuenta();
		cuenta.setActiva("N");
		cuenta.setCuenId(cuenId);

		Cliente cliente = entityManager.find(Cliente.class,2L );
		assertNotNull(cliente, "el cliente es nulo");
		
		cuenta.setCliente(cliente);
		cuenta.setSaldo(new BigDecimal(12222));
		cuenta.setClave("jdasdasdas");
		

		entityManager.merge(cuenta);
		
	}
	
	@Test
	@DisplayName("BorrarCliente")
	@Transactional(readOnly=false, propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	void cTest() {
		assertNotNull(entityManager, "el entityManager es nulo");
		
		Cuenta cuenta  = entityManager.find(Cuenta.class,cuenId );
		assertNotNull(cuenta, "el cuenta no existe");

		entityManager.remove(cuenta);

		
	}
	
	
	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestCuentaSpring.class);
	@Test
	@DisplayName("ConsultarTodos")
	@Transactional(readOnly=false, propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	void dTest() {
		
		
		assertNotNull(entityManager, "el entityManager es nulo");
		
		String JPQL="SELECT cli  FROM Cuenta cli";
		List<Cuenta> losCuenta =  entityManager.createQuery(JPQL).getResultList();
		
		for (Cuenta cuenta : losCuenta) {
			log.info("id:"+cuenta.getCuenId());
			log.info("Nombre:"+cuenta.getSaldo());
			
		}
		
		losCuenta.forEach(cuenta ->{
			log.info("id:"+cuenta.getCuenId());
			log.info("Nombre:"+cuenta.getSaldo());
		});
		
	}

}
