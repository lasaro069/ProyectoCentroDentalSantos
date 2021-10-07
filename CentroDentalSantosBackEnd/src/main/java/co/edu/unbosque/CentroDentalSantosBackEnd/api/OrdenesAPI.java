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

import co.edu.unbosque.CentroDentalSantosBackEnd.dao.OrdenesDAO;
import co.edu.unbosque.CentroDentalSantosBackEnd.model.Ordenes;


@RestController
@RequestMapping("ordenes")
public class OrdenesAPI {



	@Autowired
	private OrdenesDAO ordenesDAO;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody Ordenes ordenes) {
		ordenesDAO.save(ordenes);
	}
	
	@GetMapping("/listar")
	public List<Ordenes> listar(){
		return ordenesDAO.findAll();
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id")Long id) {
		ordenesDAO.deleteById(id);
	}
	
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody Ordenes ordenes) {
		ordenesDAO.save(ordenes);
	}
	
	@GetMapping("/buscarId/{id}")
	public List<Ordenes> listarId(@PathVariable("id")Long id){
		return ordenesDAO.findAll();
	}

}
