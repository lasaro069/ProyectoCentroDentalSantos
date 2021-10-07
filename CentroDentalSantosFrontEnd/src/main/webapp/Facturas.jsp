
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Gestión de Facturas</title>
</head>
<body>

	<div class="row">
		<div class="col-md-5 seccion1" >
			<form method="get" action="Controlador" >
				<div class="card" >
					<div class="card-body" >
						<div class="form-group">
							<label>Datos del Paciente</label>
						</div>
						<input type="hidden" name="menu" value="Facturas">
						<input type="hidden" name="UsuarioActivo" value="${usuarioSeleccionado.getCedula_usuario()}">
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="number" name="cedulapaciente" class="form-control" placeholder="Cédula Cliente" value="${clienteSeleccionado.getCedula_cliente()}">
								<input type="submit" name="accion" value="BuscarPaciente" class="btn btn-outline-info">
							</div>
							<div class="col-sm-6">
								<input type="text" name="nombrepaciente" class="form-control" placeholder="Nombre Cliente" value="${clienteSeleccionado.getNombre_cliente()}">
							</div>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-body">
                    	<div class="form-group">
                        	<label>Orden de Trabajo</label>
						</div>
						<div class="form-group d-flex">
                        	<div class="col-sm-6 d-flex">
                            	<input type="number" name="cedulamedico" class="form-control" placeholder="Cédula Medico" value="${medicoSeleccionado.getCedula_medico()}">
                                <input type="submit" name="accion" value="BuscarProducto" class="btn btn-outline-info">
							</div>
                            <div class="col-sm-6">
                            	<input type="text" name="nombreproducto" class="form-control" placeholder="Nombre Producto" value="${productoSeleccionado.getNombre_producto()}">
							</div>
						</div>
						<div class="form-group d-flex">
                        	<div class="col-sm-6 d-flex">
                            	<input type="text" name="precioproducto" class="form-control" placeholder="$ 0000.00" value="${productoSeleccionado.getPrecio_venta()}">
							</div>
                            <div class="col-sm-3">
                            	<input type="number" name="cantidadproducto" class="form-control" placeholder="Cantidad">
							</div>
                            <div class="col-sm-3">
                            	<input type="text" name="ivaproducto" class="form-control" placeholder="Valor iva"  value="${productoSeleccionado.getIva_compra()}">
							</div>
						</div>
						<div class="form-group d-flex">
                        	<input type="submit" name="accion" value="AgregarProducto" class="btn btn-outline-primary">
						</div>
                   	</div>
                </div>
			</form>
		</div>							

		<div class="col-md-7 seccion2">
			<div class="card">
				<div class="card-header">
					<div class="form-group row">
						<label class="col-sm-3 col-form-label">Numero de Factura</label>
						<input class="form-contrl col-md-4" type="text" name="numerofactura" value="">
					</div>
				</div>
				<div class="card-body">
					<table class="table">
						<thead class="thead-dark">
							<tr>
								<th>#</th>
								<th>Codigo</th>
								<th>Producto</th>
								<th>Precio</th>
								<th>Cantidad</th>
								<th>Iva</th>
								<th>Total</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="lista" items="${listaFacturas}">
								<tr>
									<th>${lista.getCodigo_detalle_venta()}</th>
									<th>${lista.getCodigo_producto()}</th>
									<th>${lista.getDescripcion_producto()}</th>
									<th>${lista.getPrecio_producto()}</th>
									<th>${lista.getCantidad_producto()}</th>
									<th>${lista.getValor_iva()}</th>
									<th>${lista.getValor_venta()}</th>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="card-footer d-flex">
					<div class="col-md-4">
						<label>SubTotal</label><br>
						<label>Iva</label><br>
						<label>Total a Pagar</label>
					</div>
					<div class="col-md-4">
						<input type="text" name="txtsubtotal" class="form-control" placeholder="$ 00.000.00" disabled="disabled" value="${totalsubtotal}">
						<input type="text" name="txttotaliva="form-control" placeholder="$ 00.000.00" disabled="disabled" value="${totaliva}">
						<input type="text" name="txttotalapagar" class="form-control" placeholder="$ 00.000.00" disabled="disabled" value="${totalapagar}">
					</div>
				</div>
			</div>
			<div class="card-footer d-flex">
				<div class="col-md-8">
					<a class="btn btn-success" onclick="print()" href="Controlador?menu=Facturas&accion=GenerarFactura">Generar Factura</a>
					<a class="btn btn-danger" href="Controlador?menu=Facturas&accion=NuevaFactura">Nueva Factura</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>