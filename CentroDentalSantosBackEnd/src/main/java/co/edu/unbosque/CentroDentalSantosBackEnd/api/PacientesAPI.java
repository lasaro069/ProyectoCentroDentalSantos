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

import co.edu.unbosque.CentroDentalSantosBackEnd.dao.PacientesDAO;
import co.edu.unbosque.CentroDentalSantosBackEnd.model.Pacientes;

@RestController
@RequestMapping("pacientes")
public class PacientesAPI {
	
	@Autowired
	private PacientesDAO pacientesDAO;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody Pacientes pacientes) {
		pacientesDAO.save(pacientes);
	}
	
	@GetMapping("/listar")
	public List<Pacientes> listar(){
		return pacientesDAO.findAll();
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id")Long id) {
		pacientesDAO.deleteById(id);
	}
	
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody Pacientes pacientes) {
		pacientesDAO.save(pacientes);
	}
	
	@GetMapping("/buscarId/{id}")
	public List<Pacientes> listarId(@PathVariable("id")Long id){
		return pacientesDAO.findAll();
	}
	


}
