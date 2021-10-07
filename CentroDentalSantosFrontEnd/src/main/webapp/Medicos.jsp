<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="co.edu.unbosque.CentroDentalSantosFrontEnd.Medicos"%>

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Gestión de Medicos</title>
</head>
<body>

	<div class="row">
	   <div class="card col-md-4">
	       <div class="card-body">
	           <h5 class="card-title">Médicos</h5>
	           <h6 class="card-subtitle mb-2 text-muted">En este panel podrás gestionar los datos de los clientes del sistema</h6>
	           <div>
	     			<form class="form-sign" method="get" action="Controlador">
	      		      
			        	<div class="form-group">
							<input type="hidden" name="menu" value="Medicos">
				         	<label>Cedula:</label>
				         	<input type="text" name="txtcedula" class="form-control" value="${medicoSeleccionado.getCedula_medico()}">
			        	</div>
			        	<div class="form-group">
			         		<label>Nombres:</label>
			         		<input type="text" name="txtnombre" class="form-control" value="${medicoSeleccionado.getNombre_medico()}">
			        	</div>
			        	<div class="form-group">
			         		<label>Apellidos:</label>
			         		<input type="text" name="txtapellido" class="form-control" value="${medicoSeleccionado.getApellido_medico()}">
			        	</div>
			        	<div class="form-group">
			         		<label>Celular:</label>
			         		<input type="text" name="txtcelular" class="form-control" value="${medicoSeleccionado.getCelular_medico()}">
			        	</div>
			        	<div class="form-group">
			         		<label>Email:</label>
			         		<input type="text" name="txtemail" class="form-control" value="${medicoSeleccionado.getEmail_medico()}">
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
		        	<th scope="col">Cedula</th>
	                <th scope="col">Nombres</th>
	                <th scope="col">Apellidos</th>
	                <th scope="col">Celular</th>
	                <th scope="col">Email</th>
				</tr>
			</thead>
	        <tbody>
	        	<% ArrayList<Medicos> lista= (ArrayList<Medicos>) request.getAttribute("lista");
				for (Medicos medico:lista){
				%>
				<tr>
					<td><%=medico.getCedula_medico()%></td>
					<td><%=medico.getNombre_medico()%></td>
					<td><%=medico.getApellido_medico()%></td>
					<td><%=medico.getCelular_medico()%></td>
					<td><%=medico.getEmail_medico()%></td>
					<td> 
		               <a class="btn btn-warning" href="Controlador?menu=Medicos&accion=Cargar&id=<%=medico.getCedula_medico()%>">Editar</a>
		               <a class="btn btn-danger" href="Controlador?menu=Medicos&accion=Eliminar&id=<%=medico.getCedula_medico()%>">Eliminar</a>
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