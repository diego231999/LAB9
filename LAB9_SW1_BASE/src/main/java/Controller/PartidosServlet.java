package Controller;

import Bean.Arbitros;
import Bean.Partidos;
import Bean.SeleccionesNacionales;
import Dao.DaoArbitros;
import Dao.DaoPartidos;
import Dao.DaoSelecciones;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PartidosServlet", urlPatterns = {"/PartidosServlet", ""})
public class PartidosServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;

        switch (action) {

            case "guardar":
                request.getParameter("jornada");
                request.getParameter("fecha");
                request.getParameter("local");
                request.getParameter("visitante");
                request.getParameter("arbitro");




                break;

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        DaoPartidos daoPartidos = new DaoPartidos();
        switch (action) {
            case "lista":
                request.setAttribute("listaPartidos",daoPartidos.listaDePartidos());

                view = request.getRequestDispatcher("index.jsp");
                view.forward(request, response);
                break;
            case "crear":
                request.setAttribute("listaSelecciones",daoPartidos.listaSelecciones());
                request.setAttribute("listaArbitros",daoPartidos.listasArbitros());

                view = request.getRequestDispatcher("form.jsp");
                view.forward(request, response);
                break;

        }

    }
}
