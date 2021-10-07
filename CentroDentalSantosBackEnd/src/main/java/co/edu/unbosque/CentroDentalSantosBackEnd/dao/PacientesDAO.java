package co.edu.unbosque.CentroDentalSantosBackEnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.CentroDentalSantosBackEnd.model.Pacientes;

public interface PacientesDAO extends JpaRepository<Pacientes, Long>{

}
