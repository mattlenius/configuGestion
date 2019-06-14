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
import co.edu.usb.cali.banco.domain.Transaccion;
import co.edu.usb.cali.banco.domain.Usuario;

class testCuentaJPA {
	String cuenId="5842-8348-1804-8569";

	@Test
	@DisplayName("CrearCuenta")
	void aTest() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
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
		
		entityManager.getTransaction().begin();
		entityManager.persist(cuenta);
		entityManager.getTransaction().commit();
		
		
	}
	
	
	@Test
	@DisplayName("ModificarCuenta")
	void bTest() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
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
		
		entityManager.getTransaction().begin();
		entityManager.merge(cuenta);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("BorrarCuenta")
	void cTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		Cuenta cuenta  = entityManager.find(Cuenta.class,cuenId );
		assertNotNull(cuenta, "el cuenta no existe");
		
		entityManager.getTransaction().begin();
			entityManager.remove(cuenta);
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
