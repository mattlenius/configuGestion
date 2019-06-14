package co.edu.usb.cali.banco.service;

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
import org.springframework.beans.factory.annotation.Autowired;
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
@Rollback(false) //

class TestCuentaService {

	@Autowired
	private CuentaService cuentaService;
	
	@Autowired
	private ClienteService clienteService;
	
	String cuenId = "5842-8348-1804-8569";

	@Test
	@DisplayName("CrearCuenta")
	void aTest() throws Exception {

		assertNotNull(cuentaService, "el cuentaService es nulo");

		Cuenta cuenta = cuentaService.findById( cuenId);
		assertNull(cuenta, "el cuenta ya existe");

		cuenta = new Cuenta();
		cuenta.setActiva("S");
		cuenta.setCuenId(cuenId);

		Cliente cliente = clienteService.findById(2L);
		assertNotNull(cliente, "el cliente es nulo");

		cuenta.setCliente(cliente);
		cuenta.setSaldo(new BigDecimal(12222));
		cuenta.setClave("jdasdasdas");

		cuentaService.save(cuenta);

	}

	@Test
	@DisplayName("ModificarCuenta")
	void bTest() throws Exception {

		assertNotNull(cuentaService, "el cuentaService es nulo");

		Cuenta cuenta = cuentaService.findById( cuenId);
		assertNotNull(cuenta, "el cuenta no existe");

		cuenta = new Cuenta();
		cuenta.setActiva("N");
		cuenta.setCuenId(cuenId);

		Cliente cliente = clienteService.findById(2L);
		assertNotNull(cliente, "el cliente es nulo");

		cuenta.setCliente(cliente);
		cuenta.setSaldo(new BigDecimal(12222));
		cuenta.setClave("jdasdasdas");

		cuentaService.update(cuenta);

	}

	@Test
	@DisplayName("BorrarCliente")
	void cTest() throws Exception {
		assertNotNull(cuentaService, "el cuentaService es nulo");

		Cuenta cuenta = cuentaService.findById( cuenId);
		assertNotNull(cuenta, "el cuenta no existe");

		cuentaService.delete(cuenta);

	}

	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestCuentaService.class);

	@Test
	@DisplayName("ConsultarTodos")
	void dTest() {

		assertNotNull(cuentaService, "el entityManager es nulo");

		List<Cuenta> losCuenta = cuentaService.findAll();

		for (Cuenta cuenta : losCuenta) {
			log.info("id:" + cuenta.getCuenId());
			log.info("Nombre:" + cuenta.getSaldo());

		}

		losCuenta.forEach(cuenta -> {
			log.info("id:" + cuenta.getCuenId());
			log.info("Nombre:" + cuenta.getSaldo());
		});

	}

}
