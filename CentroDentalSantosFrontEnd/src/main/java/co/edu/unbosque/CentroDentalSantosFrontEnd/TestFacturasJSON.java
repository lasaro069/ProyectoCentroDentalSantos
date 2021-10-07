package co.edu.unbosque.CentroDentalSantosFrontEnd;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestFacturasJSON {


	private static URL url;
	private static String sitio = "http://localhost:5000/";
	
	public static ArrayList<Facturas> parsingFacturas(String json) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<Facturas> lista = new ArrayList<Facturas>();
		JSONArray facturas = (JSONArray) jsonParser.parse(json);
		Iterator i = facturas.iterator();
		
		while (i.hasNext()) {
		
			JSONObject innerObj = (JSONObject) i.next();
			Facturas factura = new Facturas();
			factura.setNumero_factura(innerObj.get("numero_factura").toString());
			factura.setFecha_factura(innerObj.get("fecha_factura").toString());
			factura.setCedula_paciente(innerObj.get("cedula_paciente").toString());
			factura.setConcepto_factura(innerObj.get("concepto").toString());
			factura.setValor_factura(innerObj.get("valor_factura").toString());
			lista.add(factura);
		}
		return lista;
	}
	
	
	public static ArrayList<Facturas> getJSON() throws IOException, ParseException{
		
		url = new URL(sitio +"facturas/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		
		for (int i = 0; i<inp.length ; i++) {
		   json += (char)inp[i];
		}
		
		ArrayList<Facturas> lista = new ArrayList<Facturas>();
		lista = parsingFacturas(json);
		http.disconnect();
		return lista;
	}


	public static int postJSON(Facturas factura) throws IOException {
		
		
		url = new URL(sitio+"facturas/guardar");
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		
		try {
		  http.setRequestMethod("POST");
		} catch (ProtocolException e) {
		  e.printStackTrace();
		}
	
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		
		String data = "{"
				+ "\"numero_factura\":\""+ factura.getNumero_factura()
				+"\",\"fecha_factura\": \""+factura.getFecha_factura()
				+"\",\"cedula_paciente\": \""+factura.getCedula_paciente()
				+"\",\"concepto\":\""+factura.getConcepto_factura()
				+"\",\"valor_factura\":\""+factura.getValor_factura()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	



	public static int putJSON(Facturas factura, Long id) throws IOException {
		
		
		url = new URL(sitio+"facturas/actualizar");
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		
		try {
		  http.setRequestMethod("PUT");
		} catch (ProtocolException e) {
		  e.printStackTrace();
		}
		
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		
		String data = "{"
				+ "\"numero_factura\":\""+ id
				+"\",\"fecha_factura\": \""+factura.getFecha_factura()
				+"\",\"cedula_paciente\": \""+factura.getCedula_paciente()
				+"\",\"concepto\":\""+factura.getConcepto_factura()
				+"\",\"valor_factura\":\""+factura.getValor_factura()
				+ "\"}";

		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	
	
	
	
	
	public static int deleteJSON(Long id) throws IOException {
		
		
		url = new URL(sitio+"facturas/eliminar/" + id);
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		
		try {
		  http.setRequestMethod("DELETE");
		} catch (ProtocolException e) {
		  e.printStackTrace();
		}
		
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		
		
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}



	
	
	
	
	
	
	
	
}
