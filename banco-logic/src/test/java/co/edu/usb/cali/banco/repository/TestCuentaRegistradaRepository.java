package co.edu.usb.cali.banco.repository;

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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usb.cali.banco.domain.Cliente;
import co.edu.usb.cali.banco.domain.Cuenta;
import co.edu.usb.cali.banco.domain.CuentaRegistrada;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)//

class TestCuentaRegistradaRepository {
	
	
	
	@Autowired
	private CuentaRegistradaRepository cuentaRegistradaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CuentaRepository cuentaRepository;
	
	long cureId=1L;
	
	@Test
	@DisplayName("CrearCuentaRegistrada")
	@Transactional(readOnly=false, propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	void aTest() {		
		assertNotNull(cuentaRegistradaRepository, "el cuentaRegistradaRepository es nulo");
		

		CuentaRegistrada cuentaRegistradas = cuentaRegistradaRepository.findById(cureId);
		assertNull(cuentaRegistradas, "el cuentaRegistrada ya existe");
		
		cuentaRegistradas = new CuentaRegistrada();
		
		Cliente cliente = clienteRepository.findById(2L);
		assertNotNull(cliente, "el cliente es nulo");
		
		cuentaRegistradas.setCliente(cliente);
		cuentaRegistradas.setCureId(cureId);
		
		Cuenta cuenta = cuentaRepository.findById("4640-0341-9387-5781");
		assertNotNull(cuenta, "el cuenta es nulo");
		cuentaRegistradas.setCuenta(cuenta);
		


		cuentaRegistradaRepository.save(cuentaRegistradas);
	}
	
	@Test
	@DisplayName("ModificarCuentaRegistrada")
	@Transactional(readOnly=false, propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	void bTest() {

		assertNotNull(cuentaRegistradaRepository, "el cuentaRegistradaRepository es nulo");
		

		CuentaRegistrada cuentaRegistradas = cuentaRegistradaRepository.findById(cureId);
		assertNotNull(cuentaRegistradas, "el cuentaRegistrada no existe");
		
		cuentaRegistradas = new CuentaRegistrada();
		
		Cliente cliente = clienteRepository.findById(3L);
		assertNotNull(cliente, "el cliente es nulo");
		
		cuentaRegistradas.setCliente(cliente);
		cuentaRegistradas.setCureId(cureId);
		
		Cuenta cuenta = cuentaRepository.findById("4640-0341-9387-5781");
		assertNotNull(cuenta, "el cuenta es nulo");
		cuentaRegistradas.setCuenta(cuenta);
		
		cuentaRegistradaRepository.update(cuentaRegistradas);
		
	}
	
	@Test
	@DisplayName("BorrarCuentaRegistrada")
	@Transactional(readOnly=false, propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	void cTest() {
		assertNotNull(cuentaRegistradaRepository, "el cuentaRegistradaRepository es nulo");
		
		CuentaRegistrada cuentaRegistradas = cuentaRegistradaRepository.findById(cureId);
		assertNotNull(cuentaRegistradas, "el cuentaRegistradas no existe");
		
		cuentaRegistradaRepository.delete(cuentaRegistradas);
	
		
	}
	
	
	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestCuentaRegistradaRepository.class);
	@Test
	@DisplayName("ConsultarTodos")
	void dTest() {
	
		assertNotNull(cuentaRegistradaRepository, "el cuentaRegistradaRepository es nulo");
		List<CuentaRegistrada> losCuentaRegistrada =  cuentaRegistradaRepository.findAll();
		
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
