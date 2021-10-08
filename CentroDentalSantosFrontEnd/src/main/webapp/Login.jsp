<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/NuevoEstilo.css">
<meta charset="ISO-8859-1">
<link rel="icon" href="imagen/ico/cd_santos.ico">
<title>Login</title>
</head>
<body>

	<div class="contenedor1">
				<form class="form1" method="get" action="./ServletCentroDentalSantos">
					<div class="contenedor2 ">
						<h1 align="center">Centro Dental Santos</h1>
						<h2 align="center">Bienvenidos</h2>
							<img src="imagen/img/LOGO CDS.png" class="logo" width="40" 	alt="imagen">
							<label><h3 align="center">Login</h3></label>
					</div>

					<div class="contenedor6">
						<div class="contenedor3">
							<div class="label">
							<label><b> Usuario:</b></label>
							</div>
							<div >
							<input type="text" name="txtusuario" class="formtxt" placeholder="Ingrese el nombre de usuario">
							</div>
							
						</div>
					
						<div class="contenedor4">
							<div class="label">
							<label><b>Password:</b></label>
							</div>
							<div >
							<input type="password" name="txtpassword" placeholder="Ingrese la contraseña" class="formtxt">
							</div>
						</div>
					</div>


					<div class="contenedor5">
						<input type="submit" name="accion" value="Ingresar" class="btn"  >
					</div>
				</form>
	
	</div>

	
			
			
		
</html>