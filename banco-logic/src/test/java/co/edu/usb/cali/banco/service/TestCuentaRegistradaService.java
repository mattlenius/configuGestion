package co.edu.usb.cali.banco.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.usb.cali.banco.domain.Cliente;
import co.edu.usb.cali.banco.domain.Cuenta;
import co.edu.usb.cali.banco.domain.CuentaRegistrada;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)//

class TestCuentaRegistradaService {
	
	
	
	@Autowired
	private CuentaRegistradaService cuentaRegistradaService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private CuentaService cuentaService;
	
	long cureId=1L;
	
	@Test
	@DisplayName("CrearCuentaRegistrada")
	void aTest() throws Exception {		
		assertNotNull(cuentaRegistradaService, "el cuentaRegistradaService es nulo");
		

		CuentaRegistrada cuentaRegistradas = cuentaRegistradaService.findById(cureId);
		assertNull(cuentaRegistradas, "el cuentaRegistrada ya existe");
		
		cuentaRegistradas = new CuentaRegistrada();
		
		Cliente cliente = clienteService.findById(2L);
		assertNotNull(cliente, "el cliente es nulo");
		
		cuentaRegistradas.setCliente(cliente);
		cuentaRegistradas.setCureId(cureId);
		
		Cuenta cuenta = cuentaService.findById("4640-0341-9387-5781");
		assertNotNull(cuenta, "el cuenta es nulo");
		cuentaRegistradas.setCuenta(cuenta);
		


		cuentaRegistradaService.save(cuentaRegistradas);
	}
	
	@Test
	@DisplayName("ModificarCuentaRegistrada")
	void bTest() throws Exception {

		assertNotNull(cuentaRegistradaService, "el cuentaRegistradaService es nulo");
		

		CuentaRegistrada cuentaRegistradas = cuentaRegistradaService.findById(cureId);
		assertNotNull(cuentaRegistradas, "el cuentaRegistrada no existe");
		
		cuentaRegistradas = new CuentaRegistrada();
		
		Cliente cliente = clienteService.findById(3L);
		assertNotNull(cliente, "el cliente es nulo");
		
		cuentaRegistradas.setCliente(cliente);
		cuentaRegistradas.setCureId(cureId);
		
		Cuenta cuenta = cuentaService.findById("4640-0341-9387-5781");
		assertNotNull(cuenta, "el cuenta es nulo");
		cuentaRegistradas.setCuenta(cuenta);
		
		cuentaRegistradaService.update(cuentaRegistradas);
		
	}
	
	@Test
	@DisplayName("BorrarCuentaRegistrada")
	void cTest() throws Exception {
		assertNotNull(cuentaRegistradaService, "el cuentaRegistradaService es nulo");
		
		CuentaRegistrada cuentaRegistradas = cuentaRegistradaService.findById(cureId);
		assertNotNull(cuentaRegistradas, "el cuentaRegistradas no existe");
		
		cuentaRegistradaService.delete(cuentaRegistradas);
	
		
	}
	
	
	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestCuentaRegistradaService.class);
	@Test
	@DisplayName("ConsultarTodos")
	void dTest() {
	
		assertNotNull(cuentaRegistradaService, "el cuentaRegistradaService es nulo");
		List<CuentaRegistrada> losCuentaRegistrada =  cuentaRegistradaService.findAll();
		
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
