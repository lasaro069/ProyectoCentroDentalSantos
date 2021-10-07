package co.edu.unbosque.CentroDentalSantosFrontEnd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Controlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String menu = request.getParameter("menu");
		String accion = request.getParameter("accion");
		
		
		switch (menu) {

//			 ****************************************************** CASO MENU PRINCIPAL ******************************************************			 
			 
			 
		     case "Principal":
			  request.getRequestDispatcher("/Principal.jsp").forward(request, response);
			  break;
			  

			  
//				 ****************************************************** CASO MENU USUARIOS ******************************************************			 
			  
		     case "Usuarios":
		    	 
		    	 if (accion.equals("Listar")) {
		    		 try {
		    			 ArrayList<Usuarios> lista = TestUsuariosJSON.getJSON();
		    			 request.setAttribute("lista", lista);
		    		 } catch (Exception e) {
		    			 e.printStackTrace();
		    		 }
		    		 
		    	 }else if(accion.equals("Agregar")) {
				     Usuarios usuario = new Usuarios();
				     usuario.setCedula_usuario(request.getParameter("txtcedula"));
				     usuario.setEmail_usuario(request.getParameter("txtemail"));
				     usuario.setNombre_usuario(request.getParameter("txtnombre"));
				     usuario.setPassword(request.getParameter("txtpassword"));
				     usuario.setUsuario(request.getParameter("txtusuario"));
				     usuario.setEstado(1);
							
				     int respuesta=0;
				     try {
				    	 respuesta = TestUsuariosJSON.postJSON(usuario);
				    	 if (respuesta==200) {
				    		 request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
				    	 } else {
				    		 System.out.println("Error: " +  respuesta);
				    	 }
			      	} catch (Exception e) {
			      		e.printStackTrace();
			      	}
							
					}else if(accion.equals("Actualizar")) {
						Usuarios usuario = new Usuarios();
						usuario.setCedula_usuario(request.getParameter("txtcedula"));
						usuario.setEmail_usuario(request.getParameter("txtemail"));
						usuario.setNombre_usuario(request.getParameter("txtnombre"));
						usuario.setPassword(request.getParameter("txtpassword"));
						usuario.setUsuario(request.getParameter("txtusuario"));
						int respuesta=0;
						try {
							respuesta = TestUsuariosJSON.putJSON(usuario, Long.parseLong(usuario.getCedula_usuario()));
							PrintWriter write = response.getWriter();
										
							if (respuesta==200) {
								request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
							} else {
								write.println("Error: " +  respuesta);
							}
							write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}else if(accion.equals("Cargar")) {
						String id= request.getParameter("id");
						try {
							ArrayList<Usuarios> lista1 = TestUsuariosJSON.getJSON();
							System.out.println("Parametro: " + id);						
							for (Usuarios usuarios:lista1){
								if (usuarios.getCedula_usuario().equals(id)) {
									request.setAttribute("usuarioSeleccionado", usuarios);
									request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);	
								}
						   }
						} catch (Exception e) {
					       	e.printStackTrace();
						}
					}else if(accion.equals("Eliminar")) {
			        	Long id= Long.parseLong(request.getParameter("id"));			
			    		int respuesta=0;
			    		try {
			    		   respuesta = TestUsuariosJSON.deleteJSON(id);
			    		   PrintWriter write = response.getWriter();
			    		   if (respuesta==200) {
			    			   request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
			    		   } else {
			    			   write.println("Error: " +  respuesta);
			    		   }
			    		   		write.close();
			    		} catch (Exception e) {
			    			e.printStackTrace();
			    		}	
			    	}
		    	request.getRequestDispatcher("/Usuarios.jsp").forward(request, response);
		    	break;
		    		
// ******************************* CASO MENU MEDICOS ***************************
		     case "Medicos":	

		    	 if (accion.equals("Listar")) {
		    		 try {
		    			 ArrayList<Medicos> lista = TestMedicosJSON.getJSON();
		    			 request.setAttribute("lista", lista);
		    		 } catch (Exception e) {
		    			 e.printStackTrace();
		    		 }
		    		 
		    	 }else if(accion.equals("Agregar")) {
				     Medicos medico = new Medicos();
				     medico.setCedula_medico(request.getParameter("txtcedula"));
				     medico.setNombre_medico(request.getParameter("txtnombre"));
				     medico.setApellido_medico(request.getParameter("txtapellido"));
				     medico.setCelular_medico(request.getParameter("txtcelular"));
				     medico.setEmail_medico(request.getParameter("txtemail"));
							
				     int respuesta=0;
				     try {
				    	 respuesta = TestMedicosJSON.postJSON(medico);
				    	 if (respuesta==200) {
				    		 request.getRequestDispatcher("Controlador?menu=Medicos&accion=Listar").forward(request, response);
				    	 } else {
				    		 System.out.println("Error: " +  respuesta);
				    	 }
			      	} catch (Exception e) {
			      		e.printStackTrace();
			      	}
							
					}else if(accion.equals("Actualizar")) {
						Medicos medico = new Medicos();
					     medico.setCedula_medico(request.getParameter("txtcedula"));
					     medico.setNombre_medico(request.getParameter("txtnombre"));
					     medico.setApellido_medico(request.getParameter("txtapellido"));
					     medico.setCelular_medico(request.getParameter("txtcelular"));
					     medico.setEmail_medico(request.getParameter("txtemail"));
						
						int respuesta=0;
						try {
							respuesta = TestMedicosJSON.putJSON(medico, Long.parseLong(medico.getCedula_medico()));
							PrintWriter write = response.getWriter();
										
							if (respuesta==200) {
								request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
							} else {
								write.println("Error: " +  respuesta);
							}
							write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}else if(accion.equals("Cargar")) {
						String id= request.getParameter("id");
						try {
							ArrayList<Medicos> lista1 = TestMedicosJSON.getJSON();
							System.out.println("Parametro: " + id);						
							for (Medicos medicos:lista1){
								if (medicos.getCedula_medico().equals(id)) {
									request.setAttribute("medicoSeleccionado", medicos);
									request.getRequestDispatcher("Controlador?menu=medicos&accion=Listar").forward(request, response);	
								}
						   }
						} catch (Exception e) {
					       	e.printStackTrace();
						}
					}else if(accion.equals("Eliminar")) {
			        	Long id= Long.parseLong(request.getParameter("id"));			
			    		int respuesta=0;
			    		try {
			    		   respuesta = TestMedicosJSON.deleteJSON(id);
			    		   PrintWriter write = response.getWriter();
			    		   if (respuesta==200) {
			    			   request.getRequestDispatcher("Controlador?menu=Medicos&accion=Listar").forward(request, response);
			    		   } else {
			    			   write.println("Error: " +  respuesta);
			    		   }
			    		   		write.close();
			    		} catch (Exception e) {
			    			e.printStackTrace();
			    		}	
			    	}
		    	request.getRequestDispatcher("/Medicos.jsp").forward(request, response);
		    	break;
		    	 
		
	
	
	
//******************************* CASO MENU PACIENTES ***************************
				case "Pacientes":	
				
				 if (accion.equals("Listar")) {
					 try {
						 ArrayList<Pacientes> lista = TestPacientesJSON.getJSON();
						 request.setAttribute("lista", lista);
					 } catch (Exception e) {
						 e.printStackTrace();
					 }
					 
				 }else if(accion.equals("Agregar")) {
				     Pacientes paciente = new Pacientes();
				     paciente.setCedula_paciente(request.getParameter("txtcedula"));
				     paciente.setNombre_paciente(request.getParameter("txtnombre"));
				     paciente.setApellido_paciente(request.getParameter("txtapellido"));
				     paciente.setCelular_paciente(request.getParameter("txtcelular"));
				     paciente.setEmail_paciente(request.getParameter("txtemail"));
							
				     int respuesta=0;
				     try {
				    	 respuesta = TestPacientesJSON.postJSON(paciente);
				    	 if (respuesta==200) {
				    		 request.getRequestDispatcher("Controlador?menu=Pacientes&accion=Listar").forward(request, response);
				    	 } else {
				    		 System.out.println("Error: " +  respuesta);
				    	 }
				  	} catch (Exception e) {
				  		e.printStackTrace();
				  	}
							
					}else if(accion.equals("Actualizar")) {
					     Pacientes paciente = new Pacientes();
					     paciente.setCedula_paciente(request.getParameter("txtcedula"));
					     paciente.setNombre_paciente(request.getParameter("txtnombre"));
					     paciente.setApellido_paciente(request.getParameter("txtapellido"));
					     paciente.setCelular_paciente(request.getParameter("txtcelular"));
					     paciente.setEmail_paciente(request.getParameter("txtemail"));
						
						int respuesta=0;
						try {
							respuesta = TestPacientesJSON.putJSON(paciente, Long.parseLong(paciente.getCedula_paciente()));
							PrintWriter write = response.getWriter();
										
							if (respuesta==200) {
								request.getRequestDispatcher("Controlador?menu=Pacientes&accion=Listar").forward(request, response);
							} else {
								write.println("Error: " +  respuesta);
							}
							write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}else if(accion.equals("Cargar")) {
						String id= request.getParameter("id");
						try {
							ArrayList<Pacientes> lista1 = TestPacientesJSON.getJSON();
							System.out.println("Parametro: " + id);						
							for (Pacientes pacientes:lista1){
								if (pacientes.getCedula_paciente().equals(id)) {
									request.setAttribute("pacienteSeleccionado", pacientes);
									request.getRequestDispatcher("Controlador?menu=Pacientes&accion=Listar").forward(request, response);	
								}
						   }
						} catch (Exception e) {
					       	e.printStackTrace();
						}
					}else if(accion.equals("Eliminar")) {
				    	Long id= Long.parseLong(request.getParameter("id"));			
						int respuesta=0;
						try {
						   respuesta = TestPacientesJSON.deleteJSON(id);
						   PrintWriter write = response.getWriter();
						   if (respuesta==200) {
							   request.getRequestDispatcher("Controlador?menu=Pacientes&accion=Listar").forward(request, response);
						   } else {
							   write.println("Error: " +  respuesta);
						   }
						   		write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}	
					}
				request.getRequestDispatcher("/Pacientes.jsp").forward(request, response);
				break;
				 


				
				
				//******************************* CASO MENU CITAS ***************************
				case "Citas":	
				
				 if (accion.equals("Listar")) {
					 try {
						 ArrayList<Citas> lista = TestCitasJSON.getJSON();
						 request.setAttribute("lista", lista);
					 } catch (Exception e) {
						 e.printStackTrace();
					 }
					 
				 }else if(accion.equals("Agregar")) {
				     Citas cita= new Citas();
				     cita.setNumero_cita(request.getParameter("txtnumerocita"));
				     cita.setFecha_cita(request.getParameter("txtnumerocita"));
				     cita.setHora_cita(request.getParameter("txtnumerocita"));
				     cita.setCedula_paciente(request.getParameter("txtcedulapaciente"));
				     cita.setCedula_medico(request.getParameter("txtcedulamedico"));
				     cita.setEstado_cita(request.getParameter("txtestado"));
				     cita.setObservaciones(request.getParameter("txtobservaciones"));
							
				     int respuesta=0;
				     try {
				    	 respuesta = TestCitasJSON.postJSON(cita);
				    	 if (respuesta==200) {
				    		 request.getRequestDispatcher("Controlador?menu=Citas&accion=Listar").forward(request, response);
				    	 } else {
				    		 System.out.println("Error: " +  respuesta);
				    	 }
				  	} catch (Exception e) {
				  		e.printStackTrace();
				  	}
							
					}else if(accion.equals("Actualizar")) {
					     Citas cita= new Citas();
					     cita.setNumero_cita(request.getParameter("txtnumerocita"));
					     cita.setFecha_cita(request.getParameter("txtnumerocita"));
					     cita.setHora_cita(request.getParameter("txtnumerocita"));
					     cita.setCedula_paciente(request.getParameter("txtcedulapaciente"));
					     cita.setCedula_medico(request.getParameter("txtcedulamedico"));
					     cita.setEstado_cita(request.getParameter("txtestado"));
					     cita.setObservaciones(request.getParameter("txtobservaciones"));
						
						int respuesta=0;
						try {
							respuesta = TestCitasJSON.putJSON(cita, Long.parseLong(cita.getNumero_cita()));
							PrintWriter write = response.getWriter();
										
							if (respuesta==200) {
								request.getRequestDispatcher("Controlador?menu=Citas&accion=Listar").forward(request, response);
							} else {
								write.println("Error: " +  respuesta);
							}
							write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}else if(accion.equals("Cargar")) {
						String id= request.getParameter("id");
						try {
							ArrayList<Citas> lista1 = TestCitasJSON.getJSON();
							System.out.println("Parametro: " + id);						
							for (Citas citas:lista1){
								if (citas.getNumero_cita().equals(id)) {
									request.setAttribute("citaSeleccionado", citas);
									request.getRequestDispatcher("Controlador?menu=Citas&accion=Listar").forward(request, response);	
								}
						   }
						} catch (Exception e) {
					       	e.printStackTrace();
						}
					}else if(accion.equals("Eliminar")) {
				    	Long id= Long.parseLong(request.getParameter("id"));			
						int respuesta=0;
						try {
						   respuesta = TestCitasJSON.deleteJSON(id);
						   PrintWriter write = response.getWriter();
						   if (respuesta==200) {
							   request.getRequestDispatcher("Controlador?menu=Citas&accion=Listar").forward(request, response);
						   } else {
							   write.println("Error: " +  respuesta);
						   }
						   		write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}	
					}
				request.getRequestDispatcher("/Citas.jsp").forward(request, response);
				break;
				 
		
				
				//******************************* CASO MENU ORDENES DE TRABAJO ***************************
				case "OrdenesTrabajo":	
				
				 if (accion.equals("Listar")) {
					 try {
						 ArrayList<OrdenesTrabajo> lista = TestOrdenesTrabajoJSON.getJSON();
						 request.setAttribute("lista", lista);
					 } catch (Exception e) {
						 e.printStackTrace();
					 }
					 
				 }else if(accion.equals("Agregar")) {
				     OrdenesTrabajo ordenTrabajo= new OrdenesTrabajo();
				     ordenTrabajo.setNumero_orden(request.getParameter("txtnumeroorden"));
				     ordenTrabajo.setFecha_orden(request.getParameter("txtfechaorden"));
				     ordenTrabajo.setCedula_paciente(request.getParameter("txtcedulapaciente"));
				     ordenTrabajo.setCedula_medico(request.getParameter("txtcedulamedico"));
				     ordenTrabajo.setClase_trabajo(request.getParameter("txtclasetrabajo"));
							
				     int respuesta=0;
				     try {
				    	 respuesta = TestOrdenesTrabajoJSON.postJSON(ordenTrabajo);
				    	 if (respuesta==200) {
				    		 request.getRequestDispatcher("Controlador?menu=OrdenesTrabajo&accion=Listar").forward(request, response);
				    	 } else {
				    		 System.out.println("Error: " +  respuesta);
				    	 }
				  	} catch (Exception e) {
				  		e.printStackTrace();
				  	}
							
					}else if(accion.equals("Actualizar")) {
					     OrdenesTrabajo ordenTrabajo= new OrdenesTrabajo();
					     ordenTrabajo.setNumero_orden(request.getParameter("txtnumeroorden"));
					     ordenTrabajo.setFecha_orden(request.getParameter("txtfechaorden"));
					     ordenTrabajo.setCedula_paciente(request.getParameter("txtcedulapaciente"));
					     ordenTrabajo.setCedula_medico(request.getParameter("txtcedulamedico"));
					     ordenTrabajo.setClase_trabajo(request.getParameter("txtclasetrabajo"));
						
						int respuesta=0;
						try {
							respuesta = TestOrdenesTrabajoJSON.putJSON(ordenTrabajo, Long.parseLong(ordenTrabajo.getNumero_orden()));
							PrintWriter write = response.getWriter();
										
							if (respuesta==200) {
								request.getRequestDispatcher("Controlador?menu=OrdenesTrabajo&accion=Listar").forward(request, response);
							} else {
								write.println("Error: " +  respuesta);
							}
							write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}else if(accion.equals("Cargar")) {
						String id= request.getParameter("id");
						try {
							ArrayList<OrdenesTrabajo> lista1 = TestOrdenesTrabajoJSON.getJSON();
							System.out.println("Parametro: " + id);						
							for (OrdenesTrabajo ordenesTrabajo:lista1){
								if (ordenesTrabajo.getNumero_orden().equals(id)) {
									request.setAttribute("ordentrabajoSeleccionado", ordenesTrabajo);
									request.getRequestDispatcher("Controlador?menu=OrdenesTrabajo&accion=Listar").forward(request, response);	
								}
						   }
						} catch (Exception e) {
					       	e.printStackTrace();
						}
					}else if(accion.equals("Eliminar")) {
				    	Long id= Long.parseLong(request.getParameter("id"));			
						int respuesta=0;
						try {
						   respuesta = TestOrdenesTrabajoJSON.deleteJSON(id);
						   PrintWriter write = response.getWriter();
						   if (respuesta==200) {
							   request.getRequestDispatcher("Controlador?menu=OrdenesTrabajo&accion=Listar").forward(request, response);
						   } else {
							   write.println("Error: " +  respuesta);
						   }
						   		write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}	
					}
				request.getRequestDispatcher("/OrdenesTrabajo.jsp").forward(request, response);
				break;
				 
		
		

		
				
				//******************************* CASO MENU ORDENES DE FACTURAS ***************************
				case "Facturas":	
				
				 if (accion.equals("Listar")) {
					 try {
						 ArrayList<Facturas> lista = TestFacturasJSON.getJSON();
						 request.setAttribute("lista", lista);
					 } catch (Exception e) {
						 e.printStackTrace();
					 }
					 
				 }else if(accion.equals("Agregar")) {
				     Facturas factura = new Facturas();
				     factura.setNumero_factura(request.getParameter("txtnumerofactura"));
				     factura.setFecha_factura(request.getParameter("txtfechafactura"));
				     factura.setCedula_paciente(request.getParameter("txtcedulapaciente"));
				     factura.setConcepto_factura(request.getParameter("txtconcepto"));
				     factura.setValor_factura(request.getParameter("txtvalor"));
							
				     int respuesta=0;
				     try {
				    	 respuesta = TestFacturasJSON.postJSON(factura);
				    	 if (respuesta==200) {
				    		 request.getRequestDispatcher("Controlador?menu=Facturas&accion=Listar").forward(request, response);
				    	 } else {
				    		 System.out.println("Error: " +  respuesta);
				    	 }
				  	} catch (Exception e) {
				  		e.printStackTrace();
				  	}
							
					}else if(accion.equals("Actualizar")) {
					     Facturas factura = new Facturas();
					     factura.setNumero_factura(request.getParameter("txtnumerofactura"));
					     factura.setFecha_factura(request.getParameter("txtfechafactura"));
					     factura.setCedula_paciente(request.getParameter("txtcedulapaciente"));
					     factura.setConcepto_factura(request.getParameter("txtconcepto"));
					     factura.setValor_factura(request.getParameter("txtvalor"));
						
						int respuesta=0;
						try {
							respuesta = TestFacturasJSON.putJSON(factura, Long.parseLong(factura.getNumero_factura()));
							PrintWriter write = response.getWriter();
										
							if (respuesta==200) {
								request.getRequestDispatcher("Controlador?menu=Facturas&accion=Listar").forward(request, response);
							} else {
								write.println("Error: " +  respuesta);
							}
							write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}else if(accion.equals("Cargar")) {
						String id= request.getParameter("id");
						try {
							ArrayList<Facturas> lista1 = TestFacturasJSON.getJSON();
							System.out.println("Parametro: " + id);						
							for (Facturas facturas:lista1){
								if (facturas.getNumero_factura().equals(id)) {
									request.setAttribute("facturaSeleccionado", facturas);
									request.getRequestDispatcher("Controlador?menu=Facturas&accion=Listar").forward(request, response);	
								}
						   }
						} catch (Exception e) {
					       	e.printStackTrace();
						}
					}else if(accion.equals("Eliminar")) {
				    	Long id= Long.parseLong(request.getParameter("id"));			
						int respuesta=0;
						try {
						   respuesta = TestFacturasJSON.deleteJSON(id);
						   PrintWriter write = response.getWriter();
						   if (respuesta==200) {
							   request.getRequestDispatcher("Controlador?menu=Facturas&accion=Listar").forward(request, response);
						   } else {
							   write.println("Error: " +  respuesta);
						   }
						   		write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}	
					}
				request.getRequestDispatcher("/Factuas.jsp").forward(request, response);
				break;
				 
		
		

				
				
				
				
				
				
				
		}
				}
				
				
				
				
}
