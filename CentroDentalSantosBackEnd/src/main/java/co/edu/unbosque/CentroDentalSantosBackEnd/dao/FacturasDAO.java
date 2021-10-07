package co.edu.unbosque.CentroDentalSantosBackEnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.CentroDentalSantosBackEnd.model.Facturas;

public interface FacturasDAO extends JpaRepository<Facturas, Long>{

}
