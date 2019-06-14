package co.edu.usb.cali.banco.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usb.cali.banco.domain.Transaccion;

@Repository
@Scope("singleton")
public class TransaccionRepositoryImpl implements TransaccionRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Transaccion transaccion) {
		entityManager.persist(transaccion);
	}

	@Override
	public void update(Transaccion transaccion) {
		entityManager.merge(transaccion);
	}

	@Override
	public void delete(Transaccion transaccion) {
		entityManager.remove(transaccion);
	}

	@Override
	public Transaccion findById(Long id) {
		return entityManager.find(Transaccion.class, id);
	}

	@Override
	public List<Transaccion> findAll() {
		return entityManager.createNamedQuery("Transaccion.findAll").getResultList();
	}

}
