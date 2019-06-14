package co.edu.usb.cali.banco.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usb.cali.banco.domain.CuentaRegistrada;


@Repository
@Scope("singleton")
public class CuentaRegistradaRepositoryImpl implements CuentaRegistradaRepository {
	
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(CuentaRegistrada cuentaRegistrada) {
		entityManager.persist(cuentaRegistrada);
	}

	@Override
	public void update(CuentaRegistrada cuentaRegistrada) {
		entityManager.merge(cuentaRegistrada);
	}

	@Override
	public void delete(CuentaRegistrada cuentaRegistrada) {
		entityManager.remove(cuentaRegistrada);
	}

	@Override
	public CuentaRegistrada findById(Long id) {
		return entityManager.find(CuentaRegistrada.class, id);
	}

	@Override
	public List<CuentaRegistrada> findAll() {
		return entityManager.createNamedQuery("CuentaRegistrada.findAll").getResultList();
	}

}
