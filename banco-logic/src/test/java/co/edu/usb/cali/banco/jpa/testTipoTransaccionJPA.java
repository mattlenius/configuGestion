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

class testTipoTransaccionJPA {

	long titrId=4L;

	@Test
	@DisplayName("CreartipoTransaccion")
	void aTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		TipoTransaccion tipoTransaccion  = entityManager.find(TipoTransaccion.class,titrId );
		assertNull(tipoTransaccion, "el tipoTransaccion ya existe");
		
		
		tipoTransaccion = new TipoTransaccion();
		tipoTransaccion.setActivo("S");
		tipoTransaccion.setNombre("BIGCOINS");
		tipoTransaccion.setTitrId(titrId);
		
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(tipoTransaccion);
		
		entityManager.getTransaction().commit();
		
		
	}
	
	
	@Test
	@DisplayName("ModificarTipoTransaccion")
	void bTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		TipoTransaccion tipoTransaccion  = entityManager.find(TipoTransaccion.class,titrId );
		assertNotNull(tipoTransaccion, "el tipoTransaccion no existe");
		
		tipoTransaccion = new TipoTransaccion();
		tipoTransaccion.setActivo("N");
		tipoTransaccion.setNombre("BIGCOINsS");
		tipoTransaccion.setTitrId(titrId);
		
		
		
		
		entityManager.getTransaction().begin();
		entityManager.merge(tipoTransaccion);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("BorrarTipoTransaccion")
	void cTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		TipoTransaccion tipoTransaccion  = entityManager.find(TipoTransaccion.class,titrId );
		assertNotNull(tipoTransaccion, "el tipoTransaccion no existe");
		
		entityManager.getTransaction().begin();
			entityManager.remove(tipoTransaccion);
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
		
		String JPQL="SELECT cli  FROM TipoTransaccion cli";
		List<TipoTransaccion> losTipoTransaccion =  entityManager.createQuery(JPQL).getResultList();
		
		for (TipoTransaccion tipoTransaccion : losTipoTransaccion) {
			log.info("id:"+tipoTransaccion.getNombre());
			log.info("Nombre:"+tipoTransaccion.getTitrId());
			
		}
		
		losTipoTransaccion.forEach(tipoTransaccion ->{
			log.info("id:"+tipoTransaccion.getNombre());
			log.info("Nombre:"+tipoTransaccion.getTitrId());
		});
		
		
	}

}
