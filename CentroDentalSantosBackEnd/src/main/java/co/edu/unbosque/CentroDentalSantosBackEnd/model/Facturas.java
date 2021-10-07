package co.edu.unbosque.CentroDentalSantosBackEnd.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Facturas {
	
	@Id
	private Long numero_factura;
	private String fecha_factura;
	private Long cedula_paciente;
	private String concepto_factura;
	private Long valor_factura;
	
	public Long getNumero_factura() {
		return numero_factura;
	}
	public void setNumero_factura(Long numero_factura) {
		this.numero_factura = numero_factura;
	}
	public String getFecha_factura() {
		return fecha_factura;
	}
	public void setFecha_factura(String fecha_factura) {
		this.fecha_factura = fecha_factura;
	}
	public Long getCedula_paciente() {
		return cedula_paciente;
	}
	public void setCedula_paciente(Long cedula_paciente) {
		this.cedula_paciente = cedula_paciente;
	}
	public String getConcepto_factura() {
		return concepto_factura;
	}
	public void setConcepto_factura(String concepto_factura) {
		this.concepto_factura = concepto_factura;
	}
	public Long getValor_factura() {
		return valor_factura;
	}
	public void setValor_factura(Long valor_factura) {
		this.valor_factura = valor_factura;
	}
	
	
	
}
