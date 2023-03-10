package api.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import api.contracts.InterfaceService;
import api.models.Message;
import api.services.CatService;
import api.views.View;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/api/cats")// es la llamada de nuestra base de datos en este caso "cats"
public class CatController extends HttpServlet {

    private InterfaceService catService;

    public CatController() {
        this.catService = new CatService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json;charset=utf-8");//definimos el tipo de datos que recibiremos

        PrintWriter out = resp.getWriter();

        try {
            List<Object> cats = catService.index();
            out.println(View.show(cats));
            resp.setStatus(HttpServletResponse.SC_OK);//SC_OK el status de nuestra consulta 
            out.close();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Message message = new Message();
            message.setMessage("Error en el traspaso de datos: " + e.getMessage());
            out.println(View.show(message));
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();

        try {
            Object cat = catService.store(req.getReader());
            out.println(View.show(cat));//nos muestra el gato con este comando
            resp.setStatus(HttpServletResponse.SC_CREATED);//status de que fue creado
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println("Error: " + e.getMessage());
        }

    }
}
