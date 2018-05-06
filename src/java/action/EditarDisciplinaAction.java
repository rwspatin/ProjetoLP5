/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import Controller.Action;
import Model.Disciplina;
import Model.Prova;
import Persistence.DisciplinaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author asus note
 */
public class EditarDisciplinaAction implements Action {

    public EditarDisciplinaAction() {
    }

    @Override
    public void execute(HttpServletRequest request,
        HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("txtId"));
        String nome = request.getParameter("txtNome");
        int numerocreditos = Integer.parseInt(request.getParameter("txtNumeroCreditos"));
        int numerovagas = Integer.parseInt(request.getParameter("txtNumeroVagas"));
        Prova prova = new Prova(Integer.parseInt(request.getParameter("txtNumeroCreditos")));
        
        if (nome.equals("")) {
            response.sendRedirect("index.jsp");
        } else {
            try {
                DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
                Disciplina disciplina = new Disciplina();
                disciplina = disciplinaDAO.obter(id);
                disciplinaDAO.editar(disciplina, nome, numerocreditos, numerovagas, prova.getIdProva());
                response.sendRedirect("sucesso.jsp");
            } catch (SQLException ex) {
                response.sendRedirect("erro.jsp");
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

}
