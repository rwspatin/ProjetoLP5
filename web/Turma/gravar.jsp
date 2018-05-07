<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!doctype html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/bootstrap.css">
        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="./js/bootstrap.min.js"></script>

        <title>Cadastrar Disciplina</title>                                                                        
    </head>
    <body>
        <nav>
            <object width="100%" height="65px" data="./menu.jsp"></object>
        </nav>
        <div class="container">
            <h1>Cadastro Turmas</h1>

            <form action="FrontController?action=GravarTurma" method="post" name="frmManterTurma" onsubmit="">

                <div class="form-group">
                    <label for="usr">Periodo:</label>
                    <input type="text" class="form-control" id="usr" name="txtPeriodoTurma">
                </div>
                <div class="form-group">
                    <label for="usr">Tamanho:</label>
                    <input type="text" class="form-control" id="usr" name="txtTamanhoTurma">
                </div>
                <div class="form-group">
                    <label for="usr">Disciplina:</label>
                    <select class="selectpicker" name="txtIdDisciplina">
                        <option value="0" <c:if test="${disciplina.idDisciplina != null}"> selected</c:if>></option>
                        <c:forEach items="${disciplinas}" var="disciplina">
                            <option value="${disciplina.idDisciplina}">
                                ${disciplina.nomeDisciplina}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-default" name="btnConfirmar" value="Confirmar">Confirmar</button>
            </form>
        </div>
    </body>
</html>