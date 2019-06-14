package co.edu.usb.cali.banco.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usb.cali.banco.domain.Cuenta;

@Repository
@Scope("singleton")
public class CuentaRepositoryImpl implements CuentaRepository {
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Cuenta cuenta) {
		entityManager.persist(cuenta);

	}

	@Override
	public void update(Cuenta cuenta) {
		entityManager.merge(cuenta);
	}

	@Override
	public void delete(Cuenta cuenta) {
		entityManager.remove(cuenta);
	}

	@Override
	public Cuenta findById(String id) {
		return entityManager.find(Cuenta.class, id);
	}

	@Override
	public List<Cuenta> findAll() {
		return entityManager.createNamedQuery("Cuenta.findAll").getResultList();
	}

}
