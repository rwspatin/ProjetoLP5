package action;

import Controller.Action;
import Persistence.AlunoDAO;
import Persistence.DisciplinaDAO;
import Persistence.ProfessorDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrepararGravarProvaAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute("disciplinas", DisciplinaDAO.getInstancia().obterDisciplinas());
            request.setAttribute("alunos", AlunoDAO.getInstancia().obterAlunos());
            request.setAttribute("professores", ProfessorDAO.getInstancia().obterProfessores());

            RequestDispatcher view = request.getRequestDispatcher("Prova/gravar.jsp");

            view.forward(request, response);

        } catch (ServletException | IOException | SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
