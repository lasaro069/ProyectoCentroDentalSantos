package co.edu.unbosque.CentroDentalSantosBackEnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.CentroDentalSantosBackEnd.model.Usuarios;

public interface UsuariosDAO extends JpaRepository<Usuarios, Long>{

}
