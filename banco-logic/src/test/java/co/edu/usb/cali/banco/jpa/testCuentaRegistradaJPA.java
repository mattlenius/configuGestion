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

class testCuentaRegistradaJPA {
	long cureId=1L;

	@Test
	@DisplayName("CrearCuentaRegistradas")
	void aTest() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		

		CuentaRegistrada cuentaRegistradas = entityManager.find(CuentaRegistrada.class,cureId);
		assertNull(cuentaRegistradas, "el cuentaRegistrada ya existe");
		
		cuentaRegistradas = new CuentaRegistrada();
		
		Cliente cliente = entityManager.find(Cliente.class,2L );
		assertNotNull(cliente, "el cliente es nulo");
		
		cuentaRegistradas.setCliente(cliente);
		cuentaRegistradas.setCureId(cureId);
		
		Cuenta cuenta = entityManager.find(Cuenta.class,"4640-0341-9387-5781");
		assertNotNull(cuenta, "el cuenta es nulo");
		cuentaRegistradas.setCuenta(cuenta);
		

		entityManager.getTransaction().begin();
		entityManager.persist(cuentaRegistradas);
		entityManager.getTransaction().commit();
		
		
		
	}
	
	
	@Test
	@DisplayName("ModificarCuentaRegistradas")
	void bTest() {

		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		

		CuentaRegistrada cuentaRegistradas = entityManager.find(CuentaRegistrada.class,cureId);
		assertNotNull(cuentaRegistradas, "el cuentaRegistrada no existe");
		
		cuentaRegistradas = new CuentaRegistrada();
		
		Cliente cliente = entityManager.find(Cliente.class,3L );
		assertNotNull(cliente, "el cliente es nulo");
		
		cuentaRegistradas.setCliente(cliente);
		cuentaRegistradas.setCureId(cureId);
		
		Cuenta cuenta = entityManager.find(Cuenta.class,"4640-0341-9387-5781");
		assertNotNull(cuenta, "el cuenta es nulo");
		cuentaRegistradas.setCuenta(cuenta);
		

		entityManager.getTransaction().begin();
		entityManager.merge(cuentaRegistradas);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("BorrarCuentaRegistradas")
	void cTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		CuentaRegistrada cuentaRegistradas = entityManager.find(CuentaRegistrada.class,cureId);
		assertNotNull(cuentaRegistradas, "el cuentaRegistradas no existe");
		
		entityManager.getTransaction().begin();
			entityManager.remove(cuentaRegistradas);
		entityManager.getTransaction().commit();
		
		
	}
	
	private final static Logger log = org.slf4j.LoggerFactory.getLogger(testCuentaRegistradaJPA.class);
	@Test
	@DisplayName("ConsultarTodos")
	void dTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		String JPQL="SELECT cli  FROM CuentaRegistrada cli";
		List<CuentaRegistrada> losCuentaRegistrada =  entityManager.createQuery(JPQL).getResultList();
		
		for (CuentaRegistrada cuentaRegistrada : losCuentaRegistrada) {
			log.info("id:"+cuentaRegistrada.getCliente().getNombre());
			log.info("Nombre:"+cuentaRegistrada.getCuenta().getCuenId());
			
		}
		
		losCuentaRegistrada.forEach(cuentaRegistrada ->{
			log.info("id:"+cuentaRegistrada.getCliente().getNombre());
			log.info("Nombre:"+cuentaRegistrada.getCuenta().getCuenId());
		});
		
		
	}

}
