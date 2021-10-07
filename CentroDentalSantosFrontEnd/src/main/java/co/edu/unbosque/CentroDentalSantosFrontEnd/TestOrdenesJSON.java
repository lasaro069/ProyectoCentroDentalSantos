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

public class TestOrdenesJSON {

	private static URL url;
	private static String sitio = "http://localhost:5000/";
	


	public static ArrayList<Ordenes> parsingOrdenes(String json) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<Ordenes> lista = new ArrayList<Ordenes>();
		JSONArray ordenes = (JSONArray) jsonParser.parse(json);
		Iterator i = ordenes.iterator();
		
		while (i.hasNext()) {
		
			JSONObject innerObj = (JSONObject) i.next();
			Ordenes orden= new Ordenes();
			orden.setNumero_orden(innerObj.get("numero_orden").toString());;
			orden.setFecha_orden(innerObj.get("fecha_orden").toString());
			orden.setCedula_paciente(innerObj.get("cedula_paciente").toString());
			orden.setCedula_medico(innerObj.get("cedula_medico").toString());
			orden.setClase_trabajo(innerObj.get("clase_trabajo").toString());
			lista.add(orden);
		}
		return lista;
	}

	
	public static ArrayList<Ordenes> getJSON() throws IOException, ParseException{
		
		url = new URL(sitio +"ordenes/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		
		for (int i = 0; i<inp.length ; i++) {
		   json += (char)inp[i];
		}
		
		ArrayList<Ordenes> lista = new ArrayList<Ordenes>();
		lista = parsingOrdenes(json);
		http.disconnect();
		return lista;
	}



	public static int postJSON(Ordenes orden) throws IOException {
		
		
		url = new URL(sitio+"Ordenes/guardar");
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
				+ "\"numero_orden\":\""+ orden.getNumero_orden()
				+"\",\"fecha_orden\": \""+orden.getFecha_orden()
				+"\",\"cedula_paciente\": \""+orden.getCedula_paciente()
				+"\",\"cedula_medico\":\""+orden.getCedula_medico()
				+"\",\"clase_trabajo\":\""+orden.getClase_trabajo()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}


	public static int putJSON(Ordenes orden, Long id) throws IOException {
		
		
		url = new URL(sitio+"ordenes/actualizar");
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
				+ "\"numero_orden\":\""+ id
				+"\",\"fecha_orden\": \""+orden.getFecha_orden()
				+"\",\"cedula_paciente\": \""+orden.getCedula_paciente()
				+"\",\"cedula_medico\":\""+orden.getCedula_medico()
				+"\",\"clase_trabajo\":\""+orden.getClase_trabajo()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
	
	
	public static int deleteJSON(Long id) throws IOException {
		
		
		url = new URL(sitio+"ordenes/eliminar/" + id);
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
