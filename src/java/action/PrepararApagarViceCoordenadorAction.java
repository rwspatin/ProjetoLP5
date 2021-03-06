package action;

import Controller.Action;
import Persistence.ViceCoordenadorDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrepararApagarViceCoordenadorAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute("viceCoordenador", ViceCoordenadorDAO.getInstancia().obter(Integer.parseInt(request.getParameter("txtIdViceCoordenador"))));

            RequestDispatcher view = request.getRequestDispatcher("ViceCoordenador/excluir.jsp");
            view.forward(request, response);

        } catch (ServletException | IOException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

}
