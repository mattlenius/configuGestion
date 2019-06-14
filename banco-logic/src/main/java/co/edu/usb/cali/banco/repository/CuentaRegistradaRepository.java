package co.edu.usb.cali.banco.repository;

import java.util.List;

import co.edu.usb.cali.banco.domain.CuentaRegistrada;

public interface CuentaRegistradaRepository {
	public void save (CuentaRegistrada cuentaRegistrada);
	public void update (CuentaRegistrada cuentaRegistrada);
	public void delete (CuentaRegistrada cuentaRegistrada);
	public CuentaRegistrada findById(Long id);
	public List<CuentaRegistrada> findAll();

}
