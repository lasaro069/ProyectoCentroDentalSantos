package co.edu.unbosque.CentroDentalSantosBackEnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.CentroDentalSantosBackEnd.model.Citas;
import co.edu.unbosque.CentroDentalSantosBackEnd.model.OrdenesTrabajo;

public interface CitasDAO extends JpaRepository<Citas, Long>{

}
