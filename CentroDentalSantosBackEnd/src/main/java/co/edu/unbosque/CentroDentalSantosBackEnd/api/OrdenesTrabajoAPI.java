package co.edu.unbosque.CentroDentalSantosBackEnd.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.CentroDentalSantosBackEnd.dao.OrdenesTrabajoDAO;
import co.edu.unbosque.CentroDentalSantosBackEnd.model.OrdenesTrabajo;


@RestController
@RequestMapping("usuarios")
public class OrdenesTrabajoAPI {


	@Autowired
	private OrdenesTrabajoDAO ordenesTrabajoDAO;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody OrdenesTrabajo ordenesTrabajo) {
		ordenesTrabajoDAO.save(ordenesTrabajo);
	}
	
	@GetMapping("/listar")
	public List<OrdenesTrabajo> listar(){
		return ordenesTrabajoDAO.findAll();
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id")Long id) {
		ordenesTrabajoDAO.deleteById(id);
	}
	
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody OrdenesTrabajo ordenesTrabajo) {
		ordenesTrabajoDAO.save(ordenesTrabajo);
	}
	
	@GetMapping("/buscarId/{id}")
	public List<OrdenesTrabajo> listarId(@PathVariable("id")Long id){
		return ordenesTrabajoDAO.findAll();
	}
	



}
