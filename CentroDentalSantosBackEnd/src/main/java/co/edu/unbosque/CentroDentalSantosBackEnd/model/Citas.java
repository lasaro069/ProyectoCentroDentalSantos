package co.edu.unbosque.CentroDentalSantosBackEnd.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Citas {
	
	@Id
	private Long numero_cita;
	private String fecha_cita;
	private String hora_cita;
	private Long cedula_paciente;
	private Long cedula_medico;
	private String estado_cita;
	private String observaciones;
	
	public Long getNumero_cita() {
		return numero_cita;
	}
	public void setNumero_cita(Long numero_cita) {
		this.numero_cita = numero_cita;
	}
	public String getFecha_cita() {
		return fecha_cita;
	}
	public void setFecha_cita(String fecha_cita) {
		this.fecha_cita = fecha_cita;
	}
	public String getHora_cita() {
		return hora_cita;
	}
	public void setHora_cita(String hora_cita) {
		this.hora_cita = hora_cita;
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
	public String getEstado_cita() {
		return estado_cita;
	}
	public void setEstado_cita(String estado_cita) {
		this.estado_cita = estado_cita;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
