package co.edu.unbosque.CentroDentalSantosFrontEnd;

public class Facturas {
	
	private String numero_factura;
	private String fecha_factura;
	private String cedula_paciente;
	private String concepto_factura;
	private String valor_factura;
	
	public String getNumero_factura() {
		return numero_factura;
	}
	public void setNumero_factura(String numero_factura) {
		this.numero_factura = numero_factura;
	}
	public String getFecha_factura() {
		return fecha_factura;
	}
	public void setFecha_factura(String fecha_factura) {
		this.fecha_factura = fecha_factura;
	}
	public String getCedula_paciente() {
		return cedula_paciente;
	}
	public void setCedula_paciente(String string) {
		this.cedula_paciente = string;
	}
	public String getConcepto_factura() {
		return concepto_factura;
	}
	public void setConcepto_factura(String concepto_factura) {
		this.concepto_factura = concepto_factura;
	}
	public String getValor_factura() {
		return valor_factura;
	}
	public void setValor_factura(String valor_factura) {
		this.valor_factura = valor_factura;
	}

}
