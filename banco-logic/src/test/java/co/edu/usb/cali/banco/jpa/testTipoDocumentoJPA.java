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

class testTipoDocumentoJPA {

	long tdocId=6L;

	@Test
	@DisplayName("CrearTipoDocumento")
	void aTest() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		TipoDocumento tipoDocumento  = entityManager.find(TipoDocumento.class,tdocId );
		assertNull(tipoDocumento, "el tipoDocumento ya existe");
		
		tipoDocumento = new TipoDocumento();
		
		tipoDocumento.setActivo("S");
		tipoDocumento.setNombre("Cedula Extrajera");
		tipoDocumento.setTdocId(tdocId);
		
		
		
		entityManager.getTransaction().begin();
		entityManager.persist(tipoDocumento);
		entityManager.getTransaction().commit();
		
		
		
	}
	
	
	@Test
	@DisplayName("ModificarTipoDocumento")
	void bTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		TipoDocumento tipoDocumento  = entityManager.find(TipoDocumento.class,tdocId );
		assertNotNull(tipoDocumento, "el tipoDocumento no existe");
		
		tipoDocumento = new TipoDocumento();
		
		tipoDocumento.setActivo("N");
		tipoDocumento.setNombre("Cedula extrajera");
		tipoDocumento.setTdocId(tdocId);
		
		
		
		entityManager.getTransaction().begin();
		entityManager.merge(tipoDocumento);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("BorrarTipoDocumento")
	void cTest() {
		//fail("Not yet implemented"); //muestra los fallos
		//assertNotNull(null, "es nulo");//siga si esta nulo
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");		
		assertNotNull(entityManagerFactory, "el entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es nulo");
		
		TipoDocumento tipoDocumento  = entityManager.find(TipoDocumento.class,tdocId );
		assertNotNull(tipoDocumento, "el tipoDocumento no existe");
		
		entityManager.getTransaction().begin();
			entityManager.remove(tipoDocumento);
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
		
		String JPQL="SELECT cli  FROM TipoDocumento cli";
		List<TipoDocumento> losTipoDocumento =  entityManager.createQuery(JPQL).getResultList();
		
		for (TipoDocumento tipoDocumento : losTipoDocumento) {
			log.info("id:"+tipoDocumento.getNombre());
			log.info("Nombre:"+tipoDocumento.getTdocId());
			
		}
		
		losTipoDocumento.forEach(tipoDocumento ->{
			log.info("id:"+tipoDocumento.getNombre());
			log.info("Nombre:"+tipoDocumento.getTdocId());
		});
		
		
	}

}
