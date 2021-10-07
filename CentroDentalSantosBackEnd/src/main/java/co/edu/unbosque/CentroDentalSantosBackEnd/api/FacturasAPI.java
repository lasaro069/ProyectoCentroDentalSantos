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

import co.edu.unbosque.CentroDentalSantosBackEnd.dao.FacturasDAO;
import co.edu.unbosque.CentroDentalSantosBackEnd.model.Facturas;


@RestController
@RequestMapping("facturas")

public class FacturasAPI {

	@Autowired
	private FacturasDAO facturasDAO;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody Facturas facturas) {
		facturasDAO.save(facturas);
	}
	
	@GetMapping("/listar")
	public List<Facturas> listar(){
		return facturasDAO.findAll();
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id")Long id) {
		facturasDAO.deleteById(id);
	}
	
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody Facturas facturas) {
		facturasDAO.save(facturas);
	}
	
	@GetMapping("/buscarId/{id}")
	public List<Facturas> listarId(@PathVariable("id")Long id){
		return facturasDAO.findAll();
	}
}
