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

        <title>Cadastrar Professor</title>                                                                        
    </head>
    <body>
        <nav>
            <object width="100%" height="65px" data="./menu.jsp"></object>
        </nav>
        <div class="container">
            <h1>Cadastrar Professor</h1>

            <form action="FrontController?action=GravarProfessor" method="post" name="frmManterPessoa" onsubmit="">

                <div class="form-group">
                    <label for="usr">Nome:</label>
                    <input type="text" class="form-control" id="usr" name="txtNomePessoa">
                </div>
                <div class="form-group">
                    <label for="usr">Idade:</label>
                    <input type="text" class="form-control" id="usr" name="txtIdadePessoa">
                </div>
                <div class="form-group">
                    <label for="usr">Numero de faltas:</label>
                    <input type="text" class="form-control" id="usr" name="txtNumeroFaltas">
                </div>
                <div class="form-group">
                    <label for="usr">Nivel ensino Superior:</label>
                    <input type="text" class="form-control" id="usr" name="txtNivelEnsinoSuperior">
                </div>
                <button type="submit" class="btn btn-default" name="btnConfirmar" value="Confirmar">Confirmar</button>
            </form>
        </div>
    </body>
</html>