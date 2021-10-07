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


public class TestMedicosJSON {

	private static URL url;
	private static String sitio = "http://localhost:5000/";
	
	public static ArrayList<Medicos> parsingMedicos(String json) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<Medicos> lista = new ArrayList<Medicos>();
		JSONArray medicos = (JSONArray) jsonParser.parse(json);
		Iterator i = medicos.iterator();
		
		while (i.hasNext()) {
		
			JSONObject innerObj = (JSONObject) i.next();
			Medicos medico = new Medicos();
			medico.setCedula_medico(innerObj.get("cedula_medico").toString());
			medico.setNombre_medico(innerObj.get("nombre_medico").toString());
			medico.setApellido_medico(innerObj.get("apellido_medico").toString());
			medico.setCelular_medico(innerObj.get("celular_medico").toString());
			medico.setEmail_medico(innerObj.get("email_medico").toString());
			lista.add(medico);
		}
		return lista;
	}
	
	
	public static ArrayList<Medicos> getJSON() throws IOException, ParseException{
		
		url = new URL(sitio +"medicos/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		
		for (int i = 0; i<inp.length ; i++) {
		   json += (char)inp[i];
		}
		
		ArrayList<Medicos> lista = new ArrayList<Medicos>();
		lista = parsingMedicos(json);
		http.disconnect();
		return lista;
	}

	public static int postJSON(Medicos medico) throws IOException {
		
		
		url = new URL(sitio+"medicos/guardar");
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
				+ "\"cedula_medico\":\""+ medico.getCedula_medico()
				+"\",\"nombre_medico\": \""+medico.getNombre_medico()
				+"\",\"apellido_medico\": \""+medico.getApellido_medico()
				+"\",\"celular_medico\":\""+medico.getCelular_medico()
				+"\",\"email_medico\":\""+medico.getEmail_medico()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	public static int putJSON(Medicos medico, Long id) throws IOException {
		
		
		url = new URL(sitio+"medicos/actualizar");
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
				+ "\"cedula_medico\":\""+ id
				+"\",\"nombre_medico\": \""+medico.getNombre_medico()
				+"\",\"apellido_medico\": \""+medico.getApellido_medico()
				+"\",\"celular_medico\":\""+medico.getCelular_medico()
				+"\",\"email_medico\":\""+medico.getEmail_medico()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	
	public static int deleteJSON(Long id) throws IOException {
		
		
		url = new URL(sitio+"medicos/eliminar/" + id);
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
