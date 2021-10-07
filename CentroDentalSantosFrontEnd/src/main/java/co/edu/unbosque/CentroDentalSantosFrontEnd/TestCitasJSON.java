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


public class TestCitasJSON {

	private static URL url;
	private static String sitio = "http://localhost:5000/";
	
	public static ArrayList<Citas> parsingCitas(String json) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<Citas> lista = new ArrayList<Citas>();
		JSONArray citas = (JSONArray) jsonParser.parse(json);
		Iterator i = citas.iterator();
		
		while (i.hasNext()) {
		
			JSONObject innerObj = (JSONObject) i.next();
			Citas  cita= new Citas();
			cita.setNumero_cita(innerObj.get("numero_cita").toString());
			cita.setFecha_cita(innerObj.get("fecha_cita").toString());
			cita.setHora_cita(innerObj.get("hora_cita").toString());
			cita.setCedula_paciente(innerObj.get("cedula_paciente").toString());
			cita.setCedula_medico(innerObj.get("cedula_medico").toString());
			cita.setEstado_cita(innerObj.get("estado_cita").toString());
			cita.setObservaciones(innerObj.get("observaciones").toString());
			lista.add(cita);
		}
		return lista;
	}


	
	public static ArrayList<Citas> getJSON() throws IOException, ParseException{
		
		url = new URL(sitio +"citas/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		
		for (int i = 0; i<inp.length ; i++) {
		   json += (char)inp[i];
		}
		
		ArrayList<Citas> lista = new ArrayList<Citas>();
		lista = parsingCitas(json);
		http.disconnect();
		return lista;
	}



	public static int postJSON(Citas cita) throws IOException {
		
		
		url = new URL(sitio+"citas/guardar");
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
				+ "\"numero_cita\":\""+ cita.getNumero_cita()
				+"\",\"fecha_cita\": \""+cita.getFecha_cita()
				+"\",\"hora_cita\": \""+cita.getHora_cita()
				+"\",\"cedula_paciente\":\""+cita.getCedula_paciente()
				+"\",\"cedula_medico\":\""+cita.getCedula_medico()
				+"\",\"estado_cita\":\""+cita.getEstado_cita()
				+"\",\"observaciones\":\""+cita.getObservaciones()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}




	public static int putJSON(Citas cita, Long id) throws IOException {
		
		
		url = new URL(sitio+"citas/actualizar");
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
				+ "\"numero_cita\":\""+ id
				+"\",\"fecha_cita\": \""+cita.getFecha_cita()
				+"\",\"hora_cita\": \""+cita.getHora_cita()
				+"\",\"cedula_paciente\":\""+cita.getCedula_paciente()
				+"\",\"cedula_medico\":\""+cita.getCedula_medico()
				+"\",\"estado_cita\":\""+cita.getEstado_cita()
				+"\",\"observaciones\":\""+cita.getObservaciones()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	
	
	public static int deleteJSON(Long id) throws IOException {
		
		
		url = new URL(sitio+"citas/eliminar/" + id);
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
