package co.edu.unbosque.CentroDentalSantosBackEnd.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ordenes {

	@Id
	private Long numero_orden;
	private String fecha_orden;
	private Long cedula_paciente;
	private Long cedula_medico;
	private String clase_trabajo;
	
	public Long getNumero_orden() {
		return numero_orden;
	}
	public void setNumero_orden(Long numero_orden) {
		this.numero_orden = numero_orden;
	}
	public String getFecha_orden() {
		return fecha_orden;
	}
	public void setFecha_orden(String fecha_orden) {
		this.fecha_orden = fecha_orden;
	}
	public Long getCedula_paciente() {
		return cedula_paciente;
	}
	public void setCedula_paciente(Long cedula_paciente) {
		this.cedula_paciente = cedula_paciente;
	}
	public Long getCedula_medico() {
		return cedula_medico;
	}
	public void setCedula_medico(Long cedula_medico) {
		this.cedula_medico = cedula_medico;
	}
	public String getClase_trabajo() {
		return clase_trabajo;
	}
	public void setClase_trabajo(String clase_trabajo) {
		this.clase_trabajo = clase_trabajo;
	}


}
