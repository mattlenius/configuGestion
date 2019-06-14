package co.edu.usb.cali.banco.spring;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
import co.edu.usb.cali.banco.domain.CuentaRegistrada;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)//

class TestCuentaRegistradaSpring {
	
	@PersistenceContext
	EntityManager entityManager;
	long cureId=1L;
	
	@Test
	@DisplayName("CrearCuentaRegistrada")
	@Transactional(readOnly=false, propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	void aTest() {		
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
		


		entityManager.persist(cuentaRegistradas);
	}
	
	@Test
	@DisplayName("ModificarCuentaRegistrada")
	@Transactional(readOnly=false, propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	void bTest() {

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
		
		entityManager.merge(cuentaRegistradas);
		
	}
	
	@Test
	@DisplayName("BorrarCuentaRegistrada")
	@Transactional(readOnly=false, propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	void cTest() {
		assertNotNull(entityManager, "el entityManager es nulo");
		
		CuentaRegistrada cuentaRegistradas = entityManager.find(CuentaRegistrada.class,cureId);
		assertNotNull(cuentaRegistradas, "el cuentaRegistradas no existe");
		
		entityManager.remove(cuentaRegistradas);
	
		
	}
	
	
	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestCuentaRegistradaSpring.class);
	@Test
	@DisplayName("ConsultarTodos")
	void dTest() {
	
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
