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

import co.edu.unbosque.CentroDentalSantosBackEnd.dao.CitasDAO;
import co.edu.unbosque.CentroDentalSantosBackEnd.model.Citas;

@RestController
@RequestMapping("usuarios")
public class CitasAPI {

	@Autowired
	private CitasDAO citasDAO;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody Citas citas) {
		citasDAO.save(citas);
	}
	
	@GetMapping("/listar")
	public List<Citas> listar(){
		return citasDAO.findAll();
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id")Long id) {
		citasDAO.deleteById(id);
	}
	
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody Citas citas) {
		citasDAO.save(citas);
	}
	
	@GetMapping("/buscarId/{id}")
	public List<Citas> listarId(@PathVariable("id")Long id){
		return citasDAO.findAll();
	}


}
