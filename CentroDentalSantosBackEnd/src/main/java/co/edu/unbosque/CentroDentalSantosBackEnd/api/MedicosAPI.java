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

import co.edu.unbosque.CentroDentalSantosBackEnd.dao.MedicosDAO;
import co.edu.unbosque.CentroDentalSantosBackEnd.model.Medicos;

@RestController
@RequestMapping("medicos")
public class MedicosAPI {
	
	@Autowired
	private MedicosDAO medicosDAO;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody Medicos medicos) {
		medicosDAO.save(medicos);
	}
	
	@GetMapping("/listar")
	public List<Medicos> listar(){
		return medicosDAO.findAll();
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id")Long id) {
		medicosDAO.deleteById(id);
	}
	
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody Medicos medicos) {
		medicosDAO.save(medicos);
	}
	
	@GetMapping("/buscarId/{id}")
	public List<Medicos> listarId(@PathVariable("id")Long id){
		return medicosDAO.findAll();
	}
	

}
