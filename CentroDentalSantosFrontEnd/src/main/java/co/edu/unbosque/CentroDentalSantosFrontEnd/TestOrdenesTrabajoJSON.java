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


public class TestOrdenesTrabajoJSON {

	private static URL url;
	private static String sitio = "http://localhost:5000/";
	
	public static ArrayList<OrdenesTrabajo> parsingOrdenesTrabajo(String json) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<OrdenesTrabajo> lista = new ArrayList<OrdenesTrabajo>();
		JSONArray ordenesTrabajo = (JSONArray) jsonParser.parse(json);
		Iterator i = ordenesTrabajo.iterator();
		
		while (i.hasNext()) {
		
			JSONObject innerObj = (JSONObject) i.next();
			OrdenesTrabajo ordenTrabajo= new OrdenesTrabajo();
			ordenTrabajo.setNumero_orden(innerObj.get("numero_orden").toString());;
			ordenTrabajo.setFecha_orden(innerObj.get("fecha_orden").toString());
			ordenTrabajo.setCedula_paciente(innerObj.get("cedula_paciente").toString());
			ordenTrabajo.setCedula_medico(innerObj.get("cedula_medico").toString());
			ordenTrabajo.setClase_trabajo(innerObj.get("clase_trabajo").toString());
			lista.add(ordenTrabajo);
		}
		return lista;
	}


	
	public static ArrayList<OrdenesTrabajo> getJSON() throws IOException, ParseException{
		
		url = new URL(sitio +"ordenesTrabajo/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		
		for (int i = 0; i<inp.length ; i++) {
		   json += (char)inp[i];
		}
		
		ArrayList<OrdenesTrabajo> lista = new ArrayList<OrdenesTrabajo>();
		lista = parsingOrdenesTrabajo(json);
		http.disconnect();
		return lista;
	}


	public static int postJSON(OrdenesTrabajo ordenTrabajo) throws IOException {
		
		
		url = new URL(sitio+"ordenesTrabajo/guardar");
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
				+ "\"numero_orden\":\""+ ordenTrabajo.getNumero_orden()
				+"\",\"fecha_orden\": \""+ordenTrabajo.getFecha_orden()
				+"\",\"cedula_paciente\": \""+ordenTrabajo.getCedula_paciente()
				+"\",\"cedula_medico\":\""+ordenTrabajo.getCedula_medico()
				+"\",\"clase_trabajo\":\""+ordenTrabajo.getClase_trabajo()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}



	public static int putJSON(OrdenesTrabajo ordenTrabajo, Long id) throws IOException {
		
		
		url = new URL(sitio+"ordenesTrabajo/actualizar");
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
				+"\",\"fecha_orden\": \""+ordenTrabajo.getFecha_orden()
				+"\",\"cedula_paciente\": \""+ordenTrabajo.getCedula_paciente()
				+"\",\"cedula_medico\":\""+ordenTrabajo.getCedula_medico()
				+"\",\"clase_trabajo\":\""+ordenTrabajo.getClase_trabajo()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	
	
	public static int deleteJSON(Long id) throws IOException {
		
		
		url = new URL(sitio+"ordenesTrabajo/eliminar/" + id);
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
