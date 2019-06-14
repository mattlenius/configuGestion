package co.edu.usb.cali.banco.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usb.cali.banco.domain.Cuenta;
import co.edu.usb.cali.banco.domain.TipoTransaccion;
import co.edu.usb.cali.banco.domain.Transaccion;
import co.edu.usb.cali.banco.domain.Usuario;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false) //

class TestTransaccionRepository {

	@Autowired
	private TransaccionRepository transaccionRepository;

	@Autowired
	private CuentaRepository cuentaRepository;

	@Autowired
	private TipoTransaccionRepository tipoTransaccionRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	String cuenId = "4640-0341-9387-5781";
	long tranId = 3001L;

	@Test
	@DisplayName("CrearTransaccion")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void aTest() {
		assertNotNull(transaccionRepository, "el entityManager es nulo");

		Transaccion transaccions = transaccionRepository.findById(tranId);
		assertNull(transaccions, "el transaccions ya existe");

		Cuenta cuenta = cuentaRepository.findById(cuenId);
		assertNotNull(cuenta, "el cuenta es nulo");

		transaccions = new Transaccion();
		transaccions.setCuenta(cuenta);
		Date fecha = new Date();

		transaccions.setFecha(new Timestamp(fecha.getDate()));

		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(2L);
		assertNotNull(tipoTransaccion, "el TipoTransaccion es nulo");
		transaccions.setTipoTransaccion(tipoTransaccion);
		transaccions.setTranId(tranId);

		Usuario usuario = usuarioRepository.findById("callbrook0");
		assertNotNull(usuario, "el TipoTransaccion es nulo");
		transaccions.setUsuario(usuario);
		transaccions.setValor(new BigDecimal(1000000));

		transaccionRepository.save(transaccions);

	}

	@Test
	@DisplayName("ModificarTransaccion")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void bTest() {

		assertNotNull(transaccionRepository, "el entityManager es nulo");

		Transaccion transaccions = transaccionRepository.findById(tranId);
		assertNotNull(transaccions, "el transaccions no existe");

		Cuenta cuenta = cuentaRepository.findById(cuenId);
		assertNotNull(cuenta, "el cuenta es nulo");

		transaccions = new Transaccion();
		transaccions.setCuenta(cuenta);
		Date fecha = new Date();

		transaccions.setFecha(new Timestamp(fecha.getDate()));

		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(2L);
		assertNotNull(tipoTransaccion, "el TipoTransaccion es nulo");
		transaccions.setTipoTransaccion(tipoTransaccion);
		transaccions.setTranId(tranId);

		Usuario usuario = usuarioRepository.findById("callbrook0");
		assertNotNull(usuario, "el TipoTransaccion es nulo");
		transaccions.setUsuario(usuario);
		transaccions.setValor(new BigDecimal(1000050));

		transaccionRepository.update(transaccions);

	}

	@Test
	@DisplayName("BorrarTransaccion")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void cTest() {
		assertNotNull(transaccionRepository, "el entityManager es nulo");

		Transaccion transaccions = transaccionRepository.findById(tranId);
		assertNotNull(transaccions, "el transaccions no existe");

		transaccionRepository.delete(transaccions);

	}

	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestTransaccionRepository.class);

	@Test
	@DisplayName("ConsultarTodos")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void dTest() {
		assertNotNull(transaccionRepository, "el transaccionRepository es nulo");

		List<Transaccion> losTransaccion = transaccionRepository.findAll();

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
