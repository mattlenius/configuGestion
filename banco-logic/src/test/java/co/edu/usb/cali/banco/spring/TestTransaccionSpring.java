package co.edu.usb.cali.banco.spring;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
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
import co.edu.usb.cali.banco.domain.Cuenta;
import co.edu.usb.cali.banco.domain.TipoDocumento;
import co.edu.usb.cali.banco.domain.TipoTransaccion;
import co.edu.usb.cali.banco.domain.Transaccion;
import co.edu.usb.cali.banco.domain.Usuario;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false) //

class TestTransaccionSpring {

	@PersistenceContext
	EntityManager entityManager;
	String cuenId = "4640-0341-9387-5781";
	long tranId = 3001L;

	@Test
	@DisplayName("CrearTransaccion")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void aTest() {
		assertNotNull(entityManager, "el entityManager es nulo");

		Transaccion transaccions = entityManager.find(Transaccion.class, tranId);
		assertNull(transaccions, "el transaccions ya existe");

		Cuenta cuenta = entityManager.find(Cuenta.class, cuenId);
		assertNotNull(cuenta, "el cuenta es nulo");

		transaccions = new Transaccion();
		transaccions.setCuenta(cuenta);
		Date fecha = new Date();

		transaccions.setFecha(new Timestamp(fecha.getDate()));

		TipoTransaccion tipoTransaccion = entityManager.find(TipoTransaccion.class, 2L);
		assertNotNull(tipoTransaccion, "el TipoTransaccion es nulo");
		transaccions.setTipoTransaccion(tipoTransaccion);
		transaccions.setTranId(tranId);

		Usuario usuario = entityManager.find(Usuario.class, "callbrook0");
		assertNotNull(usuario, "el TipoTransaccion es nulo");
		transaccions.setUsuario(usuario);
		transaccions.setValor(new BigDecimal(1000000));

		entityManager.persist(transaccions);

	}

	@Test
	@DisplayName("ModificarTransaccion")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void bTest() {

		assertNotNull(entityManager, "el entityManager es nulo");

		Transaccion transaccions = entityManager.find(Transaccion.class, tranId);
		assertNotNull(transaccions, "el transaccions no existe");

		Cuenta cuenta = entityManager.find(Cuenta.class, cuenId);
		assertNotNull(cuenta, "el cuenta es nulo");

		transaccions = new Transaccion();
		transaccions.setCuenta(cuenta);
		Date fecha = new Date();

		transaccions.setFecha(new Timestamp(fecha.getDate()));

		TipoTransaccion tipoTransaccion = entityManager.find(TipoTransaccion.class, 2L);
		assertNotNull(tipoTransaccion, "el TipoTransaccion es nulo");
		transaccions.setTipoTransaccion(tipoTransaccion);
		transaccions.setTranId(tranId);

		Usuario usuario = entityManager.find(Usuario.class, "callbrook0");
		assertNotNull(usuario, "el TipoTransaccion es nulo");
		transaccions.setUsuario(usuario);
		transaccions.setValor(new BigDecimal(1000050));

		entityManager.merge(transaccions);

	}

	@Test
	@DisplayName("BorrarTransaccion")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void cTest() {
		assertNotNull(entityManager, "el entityManager es nulo");

		Transaccion transaccions = entityManager.find(Transaccion.class, tranId);
		assertNotNull(transaccions, "el transaccions no existe");

		entityManager.remove(transaccions);

	}

	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestTransaccionSpring.class);

	@Test
	@DisplayName("ConsultarTodos")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void dTest() {
		assertNotNull(entityManager, "el entityManager es nulo");

		String JPQL = "SELECT cli  FROM Transaccion cli";
		List<Transaccion> losTransaccion = entityManager.createQuery(JPQL).getResultList();

		for (Transaccion transaccion : losTransaccion) {
			log.info("id:" + transaccion.getFecha());
			log.info("Nombre:" + transaccion.getUsuario().getNombre());

		}

		losTransaccion.forEach(transaccion -> {
			log.info("id:" + transaccion.getFecha());
			log.info("Nombre:" + transaccion.getUsuario().getNombre());
		});

	}

}
