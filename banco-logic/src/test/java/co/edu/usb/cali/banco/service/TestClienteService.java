package co.edu.usb.cali.banco.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
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
import co.edu.usb.cali.banco.domain.TipoDocumento;
import co.edu.usb.cali.banco.repository.TipoDocumentoRepository;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)//

class TestClienteService {
	
	@Autowired
	private ClienteService clienteservice;
	
	@Autowired
	private TipoDocumentoService tipoDocumentoService;
	
	long clieId=142020L;
	
	@Test
	@DisplayName("CrearCliente")
	void aTest() throws Exception {		
		assertNotNull(clienteservice, "el clienteRepository es nulo");
		assertNotNull(tipoDocumentoService, "el tipoDocumentoRepository es nulo");
		
		Cliente cliente  = clienteservice.findById(clieId);
		assertNull(cliente, "el cliente no existe");
		
		cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("avenida perdida jamas");
		cliente.setEmail("juandavidarcila18@gmail.com");
		cliente.setNombre("Juan David Arcila Campaz");
		cliente.setTelefono("3105220300");
		
		
		TipoDocumento tipoDocumento = tipoDocumentoService.findById(2L);
		assertNotNull(tipoDocumento, "el tipoDocumento es nulo");
		cliente.setTipoDocumento(tipoDocumento);
		
		clienteservice.save(cliente);

	}
	
	@Test
	@DisplayName("ModificarCliente")
	void bTest() throws Exception {

		assertNotNull(clienteservice, "el clienteservice es nulo");
		
		Cliente cliente  = clienteservice.findById(clieId);
		assertNotNull(cliente, "el cliente no existe");
		
		cliente.setActivo("N");
		cliente.setDireccion("avenida perdida jamas");
		cliente.setEmail("juandavidarcila18@gmail.com");
		cliente.setNombre("Juan David Arcila Campaz");
		cliente.setTelefono("3105220300");
		
		
		clienteservice.update(cliente);
	    
		
	}
	
	@Test
	@DisplayName("BorrarCliente")
	void cTest() throws Exception {
		assertNotNull(clienteservice, "el clienteservice es nulo");
		
		Cliente cliente  = clienteservice.findById(clieId);
		assertNotNull(cliente, "el cliente no existe");
		clienteservice.delete(cliente);
		
	}
	
	
	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestClienteService.class);
	@Test
	@DisplayName("ConsultarTodos")
	@Transactional(readOnly=false, propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	void dTest() {
		
		assertNotNull(clienteservice, "el entityManager es nulo");
		List<Cliente> losCliente =  clienteservice.findAll();
		
		for (Cliente cliente : losCliente) {
			log.info("id:"+cliente.getNombre());
			log.info("Nombre:"+cliente.getNombre());
			
		}
		
		losCliente.forEach(cliente ->{
			log.info("id:"+cliente.getNombre());
			log.info("Nombre:"+cliente.getNombre());
		});
		
		
	}

}
