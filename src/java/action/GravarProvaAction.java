package action;

import Controller.Action;
import Model.Aluno;
import Model.Disciplina;
import Model.Professor;
import Model.Prova;
import Persistence.ProvaDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Observable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GravarProvaAction extends Observable implements Action {

    public Prova prova;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Prova prova = new Prova(
                Float.parseFloat(request.getParameter("txtValor")),
                new Aluno(Integer.parseInt(request.getParameter("txtIdAluno"))),
                new Professor(Integer.parseInt(request.getParameter("txtIdProfessor"))),
                new Disciplina(Integer.parseInt(request.getParameter("txtIdDisciplina"))));

        this.prova = prova;

        try {

            ProvaDAO.getInstancia().save(prova);

            /*Observer*/
            ObserverAlunoProva observer = new ObserverAlunoProva(this);
            setChanged();
            notifyObservers();

            response.sendRedirect("FrontController?action=LerProva");

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
