/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Model.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rwspa
 */
public class PessoaDAO {
    private static PessoaDAO instancia = new PessoaDAO();

    public PessoaDAO() {

    }

    public static PessoaDAO getInstancia() {
        return instancia;
    }

    public void save(Pessoa pessoa) throws SQLException, ClassNotFoundException {

        Connection conn = null;
        PreparedStatement pstm = null;
        DatabaseLocator connector = DatabaseLocator.getInstance();

        try {
            conn = connector.getConnection();

            String sql = "INSERT INTO Pessoa (nome, idade) VALUES (?, ?)";
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, pessoa.getNomePessoa());
            pstm.setInt(2, pessoa.getIdadePessoa());

            pstm.execute();

        } catch (SQLException ex) {
            throw ex;
        } finally {
            connector.closeConnection(conn, pstm);
        }
    }

    public void delete(Pessoa pessoa) throws SQLException, ClassNotFoundException {

        Connection conn = null;
        PreparedStatement pstm = null;
        DatabaseLocator connector = DatabaseLocator.getInstance();

        try {
            conn = connector.getConnection();

            String sql = "DELETE FROM Pessoa WHERE idpessoa = ?";
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, pessoa.getIdPessoa());
            pstm.execute();

        } catch (SQLException ex) {
            throw ex;
        } finally {
            connector.closeConnection(conn, pstm);
        }
    }

    public Pessoa obter(Integer idPessoa) throws SQLException, ClassNotFoundException {

        Connection conn = null;
        Statement st = null;
        DatabaseLocator connector = DatabaseLocator.getInstance();
        Pessoa pessoa = null;

        try {
            conn = connector.getConnection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM Pessoa WHERE idpessoa =" + idPessoa);
            rs.first();

            pessoa = new Pessoa(
                        rs.getInt("idpessoa"),
                        rs.getString("nome"),
                        rs.getInt("idade"));

        } catch (SQLException ex) {
            throw ex;
        } finally {
            connector.closeConnection(conn, st);
        }

        return pessoa;
    }

    public List<Pessoa> obterPessoas() throws ClassNotFoundException, SQLException {

        Connection conn = null;
        Statement st = null;
        DatabaseLocator connector = DatabaseLocator.getInstance();
        List<Pessoa> pessoas = new ArrayList<>();

        try {
            conn = connector.getConnection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT = FROM Pessoa");

            while (rs.next()) {

                Pessoa pessoa = new Pessoa(
                        rs.getInt("idpessoa"),
                        rs.getString("nome"),
                        rs.getInt("idade"));

                pessoas.add(pessoa);
            }

        } catch (SQLException ex) {
            throw ex;
        } finally {
            connector.closeConnection(conn, st);
        }

        return pessoas;
    }

    public void editar(Pessoa pessoa, String nome, int idade) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstm = null;
        DatabaseLocator connector = DatabaseLocator.getInstance();

        try {
            conn = connector.getConnection();

            String sql = "UPDATE Pessoa AS p SET"
                    + " nome = ?, idade = ? WHERE p.idpessoa = ?";
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, nome);
            pstm.setInt(2, idade);
            pstm.setInt(3, pessoa.getIdPessoa());
            
            pstm.execute();

        } catch (SQLException ex) {
            throw ex;
        } finally {
            connector.closeConnection(conn, pstm);
        }
    }
}