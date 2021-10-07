<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="co.edu.unbosque.CentroDentalSantosFrontEnd.Citas"%>

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Gestión de Citas</title>
</head>
<body>

	<div class="row">
	   <div class="card col-md-4">
	       <div class="card-body">
	           <h5 class="card-title">Citas</h5>
	           <h6 class="card-subtitle mb-2 text-muted">En este panel podrás gestionar los datos de los clientes del sistema</h6>
	           <div>
	     			<form class="form-sign" method="get" action="Controlador">
	      		      
			        	<div class="form-group">
							<input type="hidden" name="menu" value="Citas">
				         	<label>Numero Cita:</label>
				         	<input type="text" name="txtnumerocita" class="form-control" value="${citaSeleccionado.getNumero_cita()}">
			        	</div>
			        	<div class="form-group">
			         		<label>Fecha Cita:</label>
			         		<input type="text" name="txtfechacita" class="form-control" value="${citaSeleccionado.getFecha_cita()}">
			        	</div>
			        	<div class="form-group">
			         		<label>Hora Cita:</label>
			         		<input type="text" name="txthoracita" class="form-control" value="${citaSeleccionado.getHora_cita()}">
			        	</div>
			        	<div class="form-group">
			         		<label>Paciente:</label>
			         		<input type="text" name="txtpaciente" class="form-control" value="${citaSeleccionado.getCedula_paciente()}">
			        	</div>
			        	<div class="form-group">
			         		<label>Medico:</label>
			         		<input type="text" name="txtmedico" class="form-control" value="${citaSeleccionado.getCedula_medico()}">
			        	</div>
			        	<div class="form-group">
			         		<label>Estado Cita:</label>
			         		<input type="text" name="txtestado" class="form-control" value="${citaSeleccionado.getEstado_cita()}">
			        	</div>
			        	<div class="form-group">
			         		<label>Observaciones:</label>
			         		<input type="text" name="txtobservaciones" class="form-control" value="${citaSeleccionado.getObservaciones()}">
			        	</div>
			        
			        	<input type="submit" class="btn btn-primary" name="accion" value="Agregar">
			        	<input type="submit" class="btn btn-success" name="accion" value="Actualizar">
	       			</form>
	    		</div>
	  		</div>
	  	</div>
		<div class="col-md-8">
		<table class="table">
			<thead class="thead-dark">
		    	<tr>
		        	<th scope="col">Numero Cita</th>
	                <th scope="col">Fecha</th>
	                <th scope="col">Hora</th>
	                <th scope="col">Paciente</th>
	                <th scope="col">Médico</th>
	                <th scope="col">Estado</th>
	                <th scope="col">Observaciones</th>
				</tr>
			</thead>
	        <tbody>
	        	<% ArrayList<Citas> lista= (ArrayList<Citas>) request.getAttribute("lista");
				for (Citas cita:lista){
				%>
				<tr>
					<td><%=cita.getNumero_cita()%></td>
					<td><%=cita.getFecha_cita()%></td>
					<td><%=cita.getHora_cita()%></td>
					<td><%=cita.getCedula_paciente()%></td>
					<td><%=cita.getCedula_medico()%></td>
					<td><%=cita.getEstado_cita()%></td>
					<td><%=cita.getObservaciones()%></td>
					<td> 
		               <a class="btn btn-warning" href="Controlador?menu=Citas&accion=Cargar&id=<%=cita.getNumero_cita()%>">Editar</a>
		               <a class="btn btn-danger" href="Controlador?menu=Citas&accion=Eliminar&id=<%=cita.getNumero_cita()%>">Eliminar</a>
	            	</td>
	            </tr>
	            <%}%>
	        </tbody>
			
	    </table>
	</div>

<!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script> 
        


</body>
</html>