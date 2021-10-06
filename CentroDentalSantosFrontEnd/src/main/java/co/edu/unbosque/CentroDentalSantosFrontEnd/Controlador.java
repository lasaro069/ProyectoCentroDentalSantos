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

		}
	}
	


}
