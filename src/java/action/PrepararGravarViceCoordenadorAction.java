/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import Controller.Action;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author asus note
 */
public class PrepararGravarViceCoordenadorAction implements Action {
     @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {

            RequestDispatcher view = request.getRequestDispatcher("ViceCoordenador/gravar.jsp");

            view.forward(request, response);

        } catch (ServletException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
