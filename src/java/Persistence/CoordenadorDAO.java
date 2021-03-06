/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Model.Coordenador;
import Model.ViceCoordenador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus note
 */
public class CoordenadorDAO {

    private static CoordenadorDAO instancia = new CoordenadorDAO();

    public CoordenadorDAO() {

    }

    public static CoordenadorDAO getInstancia() {
        return instancia;
    }

    public void save(Coordenador coordenador) throws SQLException, ClassNotFoundException {

        Connection conn = null;
        PreparedStatement pstm = null;
        DatabaseLocator connector = DatabaseLocator.getInstance();

        try {
            conn = connector.getConnection();

            String sql = "INSERT INTO coordenador (nomeCoordenador, idadeCoordenador, salarioBase, tempoServico, ViceCoordenador_idViceCoordenador) VALUES (?, ?, ?, ?, ?)";
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, coordenador.getNomePessoa());
            pstm.setInt(2, coordenador.getIdadePessoa());
            pstm.setFloat(3, coordenador.getSalarioBase());
            pstm.setInt(4, coordenador.getTempoServico());
            pstm.setInt(5, coordenador.getViceCoordenador().getIdPessoa());

            pstm.execute();

        } catch (SQLException ex) {
            throw ex;
        } finally {
            connector.closeConnection(conn, pstm);
        }
    }

    public void delete(Coordenador coordenador) throws SQLException, ClassNotFoundException {

        Connection conn = null;
        PreparedStatement pstm = null;
        DatabaseLocator connector = DatabaseLocator.getInstance();

        try {
            conn = connector.getConnection();

            String sql = "DELETE FROM coordenador WHERE idCoordenador = ?";
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, coordenador.getIdPessoa());
            pstm.execute();

        } catch (SQLException ex) {
            throw ex;
        } finally {
            connector.closeConnection(conn, pstm);
        }
    }

    public Coordenador obter(Integer idPessoa) throws SQLException, ClassNotFoundException {

        Connection conn = null;
        Statement st = null;
        DatabaseLocator connector = DatabaseLocator.getInstance();
        Coordenador coordenador = null;

        try {
            conn = connector.getConnection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM coordenador join vicecoordenador on vicecoordenador.idViceCoordenador = coordenador.ViceCoordenador_idViceCoordenador WHERE idCoordenador =" + idPessoa);
            rs.first();

            ViceCoordenador viceCoordenador = new ViceCoordenador((float) 0.0, rs.getInt("tempoServico"),rs.getInt("idViceCoordenador"), rs.getString("nomeViceCoordenador"), 0);

            coordenador = new Coordenador(
                    viceCoordenador,
                    rs.getFloat("salarioBase"),
                    rs.getInt("tempoServico"),
                    rs.getInt("idCoordenador"),
                    rs.getString("nomeCoordenador"),
                    rs.getInt("idadeCoordenador")
            );

        } catch (SQLException ex) {
            throw ex;
        } finally {
            connector.closeConnection(conn, st);
        }

        return coordenador;
    }

    public List<Coordenador> obterCoordenadores() throws ClassNotFoundException, SQLException {

        Connection conn = null;
        Statement st = null;
        DatabaseLocator connector = DatabaseLocator.getInstance();
        List<Coordenador> coordenadors = new ArrayList<>();

        try {
            conn = connector.getConnection();
            st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM coordenador join vicecoordenador on vicecoordenador.idViceCoordenador = coordenador.ViceCoordenador_idViceCoordenador");

            while (rs.next()) {

                ViceCoordenador viceCoordenador = new ViceCoordenador((float) 0.0, rs.getInt("idViceCoordenador"), rs.getString("nomeViceCoordenador"), 0);

                Coordenador coordenador = new Coordenador(
                        viceCoordenador,
                        rs.getFloat("salarioBase"),
                        rs.getInt("tempoServico"),
                        rs.getInt("idCoordenador"),
                        rs.getString("nomeCoordenador"),
                        rs.getInt("idadeCoordenador")
                );

                coordenadors.add(coordenador);
            }

        } catch (SQLException ex) {
            throw ex;
        } finally {
            connector.closeConnection(conn, st);
        }

        return coordenadors;
    }

    public void editar(Coordenador coordenador) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstm = null;
        DatabaseLocator connector = DatabaseLocator.getInstance();

        try {
            conn = connector.getConnection();

            String sql = "UPDATE coordenador AS p SET"
                    + " nomeCoordenador = ?, idadeCoordenador = ?, salarioBase=?, tempoServico=?, ViceCoordenador_idViceCoordenador=? WHERE p.idCoordenador = ?";

            pstm = conn.prepareStatement(sql);

            pstm.setString(1, coordenador.getNomePessoa());
            pstm.setInt(2, coordenador.getIdadePessoa());
            pstm.setFloat(3, coordenador.getSalarioBase());
            pstm.setInt(4, coordenador.getTempoServico());
            pstm.setInt(5, coordenador.getViceCoordenador().getIdPessoa());
            pstm.setInt(6, coordenador.getIdPessoa());

            pstm.execute();

        } catch (SQLException | NullPointerException ex) {
            ex.printStackTrace();
        } finally {
            connector.closeConnection(conn, pstm);
        }
    }
}
