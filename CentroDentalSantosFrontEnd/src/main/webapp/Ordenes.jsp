<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="co.edu.unbosque.CentroDentalSantosFrontEnd.Ordenes"%>

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Gestión de Ordenes</title>
</head>
<body>

	<div class="row">
	   <div class="card col-md-4">
	       <div class="card-body">
	           <h5 class="card-title">Ordenes</h5>
	           <h6 class="card-subtitle mb-2 text-muted">En este panel podrás gestionar los datos de los clientes del sistema</h6>
	           <div>
	     			<form class="form-sign" method="get" action="Controlador">
	      		      
			        	<div class="form-group">
							<input type="hidden" name="menu" value="Ordenes">
				         	<label>Numero de Orden:</label>
				         	<input type="text" name="txtnumeroorden" class="form-control" value="${ordenSeleccionado.getNumero_orden()}">
			        	</div>
			        	<div class="form-group">
			         		<label>Fecha:</label>
			         		<input type="text" name="txtfechaorden" class="form-control" value="${ordenSeleccionado.getFecha_orden()}">
			        	</div>
			        	<div class="form-group">
			         		<label>Paciente:</label>
			         		<input type="text" name="txtpaciente" class="form-control" value="${ordenSeleccionado.getCedula_paciente()}">
			        	</div>
			        	<div class="form-group">
			         		<label>Medico:</label>
			         		<input type="text" name="txtmedico" class="form-control" value="${ordenSeleccionado.getCedula_medico()}">
			        	</div>
			        	<div class="form-group">
			         		<label>Clase de Trabajo:</label>
			         		<input type="text" name="txttrabajo" class="form-control" value="${ordenSeleccionado.getClase_trabajo()}">
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
		        	<th scope="col">Numero Orden</th>
	                <th scope="col">Fecha</th>
	                <th scope="col">Paciente</th>
	                <th scope="col">Medico</th>
	                <th scope="col">Clase de Trabajo</th>
				</tr>
			</thead>
	        <tbody>
	        	<% ArrayList<Ordenes> lista= (ArrayList<Ordenes>) request.getAttribute("lista");
				for (Ordenes orden:lista){
				%>
				<tr>
					<td><%=orden.getNumero_orden()%></td>
					<td><%=orden.getFecha_orden()%></td>
					<td><%=orden.getCedula_paciente()%></td>
					<td><%=orden.getCedula_medico()%></td>
					<td><%=orden.getClase_trabajo()%></td>
					<td> 
		               <a class="btn btn-warning" href="Controlador?menu=Ordenes&accion=Cargar&id=<%=orden.getNumero_orden()%>">Editar</a>
		               <a class="btn btn-danger" href="Controlador?menu=Ordenes&accion=Eliminar&id=<%=orden.getNumero_orden()%>">Eliminar</a>
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