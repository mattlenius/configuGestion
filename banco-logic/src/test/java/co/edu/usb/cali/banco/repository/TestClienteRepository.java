package co.edu.usb.cali.banco.repository;

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


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)//

class TestClienteRepository {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;
	
	long clieId=142020L;
	
	@Test
	@DisplayName("CrearCliente")
	@Transactional(readOnly=false, propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	void aTest() {		
		assertNotNull(clienteRepository, "el clienteRepository es nulo");
		assertNotNull(tipoDocumentoRepository, "el tipoDocumentoRepository es nulo");
		
		Cliente cliente  = clienteRepository.findById(clieId);
		assertNull(cliente, "el cliente no existe");
		
		cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("avenida perdida jamas");
		cliente.setEmail("juandavidarcila18@gmail.com");
		cliente.setNombre("Juan David Arcila Campaz");
		cliente.setTelefono("3105220300");
		
		
		TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(2L);
		assertNotNull(tipoDocumento, "el tipoDocumento es nulo");
		cliente.setTipoDocumento(tipoDocumento);
		
		clienteRepository.save(cliente);

	}
	
	@Test
	@DisplayName("ModificarCliente")
	@Transactional(readOnly=false, propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	void bTest() {

		assertNotNull(clienteRepository, "el entityManager es nulo");
		
		Cliente cliente  = clienteRepository.findById(clieId);
		assertNotNull(cliente, "el cliente no existe");
		
		cliente.setActivo("N");
		cliente.setDireccion("avenida perdida jamas");
		cliente.setEmail("juandavidarcila18@gmail.com");
		cliente.setNombre("Juan David Arcila Campaz");
		cliente.setTelefono("3105220300");
		
		
		clienteRepository.update(cliente);
	    
		
	}
	
	@Test
	@DisplayName("BorrarCliente")
	@Transactional(readOnly=false, propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	void cTest() {
		assertNotNull(clienteRepository, "el entityManager es nulo");
		
		Cliente cliente  = clienteRepository.findById(clieId);
		assertNotNull(cliente, "el cliente no existe");
		clienteRepository.delete(cliente);
		
	}
	
	
	private final static Logger log = org.slf4j.LoggerFactory.getLogger(TestClienteRepository.class);
	@Test
	@DisplayName("ConsultarTodos")
	@Transactional(readOnly=false, propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
	void dTest() {
		
		assertNotNull(clienteRepository, "el entityManager es nulo");
		List<Cliente> losCliente =  clienteRepository.findAll();
		
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
