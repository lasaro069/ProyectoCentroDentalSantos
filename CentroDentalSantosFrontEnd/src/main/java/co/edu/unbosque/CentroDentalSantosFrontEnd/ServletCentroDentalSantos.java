package co.edu.unbosque.CentroDentalSantosFrontEnd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletCentroDentalSantos")
public class ServletCentroDentalSantos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletCentroDentalSantos() {
        super();
        // TODO Auto-generated constructor stub
    }

// *************** METODO VALIDAR USUARIOS *******************    
    
    public void validarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	try {
			ArrayList<Usuarios> lista = TestUsuariosJSON.getJSON();
			request.setAttribute("lista", lista);
			String usua = request.getParameter("txtusuario");
			String pass = request.getParameter("txtpassword");
			int respuesta = 0;
			for (Usuarios usuario:lista) {
				if (usuario.getUsuario().equals(usua) && usuario.getPassword().equals(pass)) {
					request.setAttribute("usuario", usuario);
					request.getRequestDispatcher("/Principal.jsp").forward(request, response);
					respuesta=1;
				}
			}
			
			if(respuesta==0) {
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
				System.out.println("No se encontraron Datos");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
    
    
// *************** FIN METODO VALIDAR USUARIOS *******************    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accion=request.getParameter("accion");
		if (accion.equals("Ingresar")) {
			this.validarUsuarios(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
