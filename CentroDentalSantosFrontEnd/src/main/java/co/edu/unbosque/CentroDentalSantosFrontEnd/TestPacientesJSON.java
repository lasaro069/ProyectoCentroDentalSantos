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

public class TestPacientesJSON {

	private static URL url;
	private static String sitio = "http://localhost:5000/";
	
	
	public static ArrayList<Pacientes> parsingPacientes(String json) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<Pacientes> lista = new ArrayList<Pacientes>();
		JSONArray pacientes = (JSONArray) jsonParser.parse(json);
		Iterator i = pacientes.iterator();
		
		while (i.hasNext()) {
		
			JSONObject innerObj = (JSONObject) i.next();
			Pacientes paciente = new Pacientes();
			paciente.setCedula_paciente(innerObj.get("idpac").toString());
			paciente.setNombre_paciente(innerObj.get("nompac").toString());
			paciente.setApellido_paciente(innerObj.get("apepac").toString());
			paciente.setCelular_paciente(innerObj.get("celupac").toString());
			paciente.setEmail_paciente(innerObj.get("correopac").toString());
			lista.add(paciente);
		}
		return lista;
	}

	
	public static ArrayList<Pacientes> getJSON() throws IOException, ParseException{
		
		url = new URL(sitio +"pacientes/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		
		for (int i = 0; i<inp.length ; i++) {
		   json += (char)inp[i];
		}
		
		ArrayList<Pacientes> lista = new ArrayList<Pacientes>();
		lista = parsingPacientes(json);
		http.disconnect();
		return lista;
	}


	public static int postJSON(Pacientes paciente) throws IOException {
		
		
		url = new URL(sitio+"pacientes/guardar");
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
				+ "\"idpac\":\""+ paciente.getCedula_paciente()
				+"\",\"nompac\": \""+paciente.getNombre_paciente()
				+"\",\"apepac\": \""+paciente.getApellido_paciente()
				+"\",\"celupac\":\""+paciente.getCelular_paciente()
				+"\",\"correopac\":\""+paciente.getEmail_paciente()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	public static int putJSON(Pacientes paciente, Long id) throws IOException {
		
		
		url = new URL(sitio+"pacientes/actualizar");
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
				+ "\"idpac\":\""+ id
				+"\",\"nompac\": \""+paciente.getNombre_paciente()
				+"\",\"apepac\": \""+paciente.getApellido_paciente()
				+"\",\"celupac\":\""+paciente.getCelular_paciente()
				+"\",\"correopac\":\""+paciente.getEmail_paciente()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}

	
	public static int deleteJSON(Long id) throws IOException {
		
		
		url = new URL(sitio+"pacientes/eliminar/" + id);
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
