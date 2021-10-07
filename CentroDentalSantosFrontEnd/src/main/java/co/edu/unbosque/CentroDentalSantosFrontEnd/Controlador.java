package co.edu.unbosque.CentroDentalSantosFrontEnd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
// **************************************** NUEVAS VARIABLES *****************************
	long subtotal=0, iva=0, valor_iva=0, total_pagar=0, numfact=0, precio=0, numero_orden;
	String descripcion, cedula_paciente, cedula_medico, cedula_usuario;
	
	List<Ordenes> listaFacturas = new ArrayList<>();
	Usuarios usuarios = new Usuarios();
	Ordenes orden = new Ordenes();
	
// *********************************************************************************	
	
	public Controlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	// ***************** METODOS LOCALES PARA BUSCAR PACIENTE  ********************
    public void buscarPaciente(String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	try {
			ArrayList<Pacientes> listaP=TestPacientesJSON.getJSON();
			for(Pacientes pacientes:listaP) {
				if(pacientes.getCedula_paciente().equals(id)) {
					request.setAttribute("pacienteSeleccionado", pacientes);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

 // *********************************************************************************	
    
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String menu = request.getParameter("menu");
		String accion = request.getParameter("accion");
		
// *************** VARIABLE PARA RECIBIR LA CEDULA DEL USUARIO Y USARLA AL GUARDAR LA VENTA*****************************************************************    
		String cedula_usuario_activo = request.getParameter("UsuarioActivo");
		usuarios.setCedula_usuario(cedula_usuario_activo);
		request.setAttribute("usuarioSeleccionado", usuarios);
		
				
		
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
				     usuario.setNombre_usuario(request.getParameter("txtnombre"));
				     usuario.setEmail_usuario(request.getParameter("txtemail"));
				     usuario.setUsuario(request.getParameter("txtusuario"));
				     usuario.setPassword(request.getParameter("txtpassword"));
							
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
					     usuario.setNombre_usuario(request.getParameter("txtnombre"));
					     usuario.setEmail_usuario(request.getParameter("txtemail"));
					     usuario.setUsuario(request.getParameter("txtusuario"));
					     usuario.setPassword(request.getParameter("txtpassword"));
						
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
								request.getRequestDispatcher("Controlador?menu=Medicos&accion=Listar").forward(request, response);
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
									request.getRequestDispatcher("Controlador?menu=Medicos&accion=Listar").forward(request, response);	
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
				     cita.setFecha_cita(request.getParameter("txtfechacita"));
				     cita.setHora_cita(request.getParameter("txthoracita"));
				     cita.setCedula_paciente(request.getParameter("txtpaciente"));
				     cita.setCedula_medico(request.getParameter("txtmedico"));
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
					     cita.setFecha_cita(request.getParameter("txtfechacita"));
					     cita.setHora_cita(request.getParameter("txthoracita"));
					     cita.setCedula_paciente(request.getParameter("txtpaciente"));
					     cita.setCedula_medico(request.getParameter("txtmedico"));
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
				case "Ordenes":	
				
				 if (accion.equals("Listar")) {
					 try {
						 ArrayList<Ordenes> lista = TestOrdenesJSON.getJSON();
						 request.setAttribute("lista", lista);
					 } catch (Exception e) {
						 e.printStackTrace();
					 }
					 
				 }else if(accion.equals("Agregar")) {
				     Ordenes orden= new Ordenes();
				     orden.setNumero_orden(request.getParameter("txtnumeroorden"));
				     orden.setFecha_orden(request.getParameter("txtfechaorden"));
				     orden.setCedula_paciente(request.getParameter("txtpaciente"));
				     orden.setCedula_medico(request.getParameter("txtmedico"));
				     orden.setClase_trabajo(request.getParameter("txttrabajo"));
							
				     int respuesta=0;
				     try {
				    	 respuesta = TestOrdenesJSON.postJSON(orden);
				    	 if (respuesta==200) {
				    		 request.getRequestDispatcher("Controlador?menu=Ordenes&accion=Listar").forward(request, response);
				    	 } else {
				    		 System.out.println("Error: " +  respuesta);
				    	 }
				  	} catch (Exception e) {
				  		e.printStackTrace();
				  	}
							
					}else if(accion.equals("Actualizar")) {
					     Ordenes orden= new Ordenes();
					     orden.setNumero_orden(request.getParameter("txtnumeroorden"));
					     orden.setFecha_orden(request.getParameter("txtfechaorden"));
					     orden.setCedula_paciente(request.getParameter("txtpaciente"));
					     orden.setCedula_medico(request.getParameter("txtmedico"));
					     orden.setClase_trabajo(request.getParameter("txttrabajo"));
						
						int respuesta=0;
						try {
							respuesta = TestOrdenesJSON.putJSON(orden, Long.parseLong(orden.getNumero_orden()));
							PrintWriter write = response.getWriter();
										
							if (respuesta==200) {
								request.getRequestDispatcher("Controlador?menu=Ordenes&accion=Listar").forward(request, response);
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
							ArrayList<Ordenes> lista1 = TestOrdenesJSON.getJSON();
							System.out.println("Parametro: " + id);						
							for (Ordenes ordenes:lista1){
								if (ordenes.getNumero_orden().equals(id)) {
									request.setAttribute("ordenSeleccionado", ordenes);
									request.getRequestDispatcher("Controlador?menu=ordenes&accion=Listar").forward(request, response);	
								}
						   }
						} catch (Exception e) {
					       	e.printStackTrace();
						}
					}else if(accion.equals("Eliminar")) {
				    	Long id= Long.parseLong(request.getParameter("id"));			
						int respuesta=0;
						try {
						   respuesta = TestOrdenesJSON.deleteJSON(id);
						   PrintWriter write = response.getWriter();
						   if (respuesta==200) {
							   request.getRequestDispatcher("Controlador?menu=Ordenes&accion=Listar").forward(request, response);
						   } else {
							   write.println("Error: " +  respuesta);
						   }
						   		write.close();
						} catch (Exception e) {
							e.printStackTrace();
						}	
					}
				request.getRequestDispatcher("/Ordenes.jsp").forward(request, response);
				break;
				 
		
		

		
				
				// ******************************* CASO MENU ORDENES DE FACTURAS ***************************
				case "Facturas":	

					request.setAttribute("usuarioSeleccioando", usuarios);
					
					if(accion.equals("BuscarPaciente")) {
						String id=request.getParameter("cedulapaciente");
						this.buscarPaciente(id, request, response);
						
						orden = new Ordenes();
						total_pagar=0;
						descripcion = request.getParameter("nombreproducto");
						precio = Long.parseLong(request.getParameter("precioproducto"));
						iva = Long.parseLong(request.getParameter("ivaproducto"));
						
						subtotal = precio;
						valor_iva = (subtotal*iva/100);
						
						orden.setNumero_orden(String.valueOf(numero_orden));
						orden.setCedula_medico(cedula_medico);
						orden.setCedula_paciente(cedula_paciente);
						orden.setClase_trabajo(descripcion);
						listaFacturas.add(orden);
						
/*						for(int i=0; i<listaFacturas.size(); i++) {
							acusubtotal +=listaVentas.get(i).getValor_venta();
							subtotaliva += listaVentas.get(i).getValor_iva();
						}
						
*/						total_pagar = subtotal+valor_iva;
						
						request.setAttribute("listaFacturas", listaFacturas);
						request.setAttribute("totalsubtotal", subtotal);
						request.setAttribute("totaliva", valor_iva);
						request.setAttribute("totalapagar", total_pagar);
					}
					
					request.getRequestDispatcher("/Facturas.jsp").forward(request, response);
					break;
				}
				
	}	
				
}
