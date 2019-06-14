package co.edu.usb.cali.banco.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usb.cali.banco.domain.TipoTransaccion;


@Repository
@Scope("singleton")
public class TipoTransaccionRepositoryImpl implements TipoTransaccionRepository {
	
	@PersistenceContext
	private EntityManager entityManager; 
	
	@Override
	public void save(TipoTransaccion tipoTransaccion) {
		entityManager.persist(tipoTransaccion);
	}

	@Override
	public void update(TipoTransaccion tipoTransaccion) {
		entityManager.merge(tipoTransaccion);
	}

	@Override
	public void delete(TipoTransaccion tipoTransaccion) {
		entityManager.remove(tipoTransaccion);
	}

	@Override
	public TipoTransaccion findById(Long id) {
		return entityManager.find(TipoTransaccion.class, id);
	}

	@Override
	public List<TipoTransaccion> findAll() {
		return entityManager.createNamedQuery("TipoTransaccion.findAll").getResultList();
	}

}
