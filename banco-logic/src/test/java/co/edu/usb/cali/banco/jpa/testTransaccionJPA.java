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

class testTransaccionJPA {
	String cuenId="4640-0341-9387-5781";
	long tranId=3001L;

	@Test
	@DisplayName("CrearTransaccion")
	void aTest() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");

		Transaccion transaccions = entityManager.find(Transaccion.class,tranId );
		assertNull(transaccions, "el transaccions ya existe");
		
		Cuenta cuenta  = entityManager.find(Cuenta.class,cuenId );
		assertNotNull(cuenta, "el cuenta es nulo");
		
		
		transaccions = new Transaccion();
		transaccions.setCuenta(cuenta);
		Date fecha = new Date();
		
		transaccions.setFecha(new Timestamp(fecha.getDate()));
		
		TipoTransaccion tipoTransaccion = entityManager.find(TipoTransaccion.class,2L );
		assertNotNull(tipoTransaccion, "el TipoTransaccion es nulo");
		transaccions.setTipoTransaccion(tipoTransaccion);
		transaccions.setTranId(tranId);
		
		Usuario usuario = entityManager.find(Usuario.class,"callbrook0" );
		assertNotNull(usuario, "el TipoTransaccion es nulo");
		transaccions.setUsuario(usuario);
		transaccions.setValor(new BigDecimal(1000000));

		
		entityManager.getTransaction().begin();
		entityManager.persist(transaccions);
		entityManager.getTransaction().commit();
		
		
		
	}
	
	
	@Test
	@DisplayName("ModificarTransaccion")
	void bTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		Transaccion transaccions = entityManager.find(Transaccion.class,tranId );
		assertNotNull(transaccions, "el transaccions no existe");
		
		Cuenta cuenta  = entityManager.find(Cuenta.class,cuenId );
		assertNotNull(cuenta, "el cuenta es nulo");
		
		
		transaccions = new Transaccion();
		transaccions.setCuenta(cuenta);
		Date fecha = new Date();
		
		transaccions.setFecha(new Timestamp(fecha.getDate()));
		
		TipoTransaccion tipoTransaccion = entityManager.find(TipoTransaccion.class,2L );
		assertNotNull(tipoTransaccion, "el TipoTransaccion es nulo");
		transaccions.setTipoTransaccion(tipoTransaccion);
		transaccions.setTranId(tranId);
		
		Usuario usuario = entityManager.find(Usuario.class,"callbrook0" );
		assertNotNull(usuario, "el TipoTransaccion es nulo");
		transaccions.setUsuario(usuario);
		transaccions.setValor(new BigDecimal(1000050));
		
		
		entityManager.getTransaction().begin();
			entityManager.merge(transaccions);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("BorrarTransaccion")
	void cTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		Transaccion transaccions = entityManager.find(Transaccion.class,tranId );
		assertNotNull(transaccions, "el transaccions no existe");
		
		entityManager.getTransaction().begin();
		entityManager.remove(transaccions);
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
		
		String JPQL="SELECT cli  FROM Transaccion cli";
		List<Transaccion> losTransaccion =  entityManager.createQuery(JPQL).getResultList();
		
		for (Transaccion transaccion : losTransaccion) {
			log.info("id:"+transaccion.getFecha());
			log.info("Nombre:"+transaccion.getUsuario().getNombre());
			
		}
		
		losTransaccion.forEach(transaccion ->{
			log.info("id:"+transaccion.getFecha());
			log.info("Nombre:"+transaccion.getUsuario().getNombre());
		});
		
		
	}

}
