package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import dto.DTOProjeto;

public class AdminDAO {

    public AdminDAO() {
        new Conexao();
    }

    public void adicionarParticipante(String nomeParticipante, String nomeProjeto, String horasTB) {
        String sqlProjeto = "SELECT id_projeto FROM projetos WHERE nome_projeto = ?";
        String sqlParticipante = "INSERT INTO participantes (nome_participante, id_projeto, horas_trabalhadas) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmtProjeto = conn.prepareStatement(sqlProjeto);
            PreparedStatement stmtParticipante = conn.prepareStatement(sqlParticipante)) {

            // Obter o id_projeto
            stmtProjeto.setString(1, nomeProjeto);
            ResultSet rs = stmtProjeto.executeQuery();
            if (rs.next()) {
                int idProjeto = rs.getInt("id_projeto");
                
                // Inserir o novo participante
                stmtParticipante.setString(1, nomeParticipante);
                stmtParticipante.setInt(2, idProjeto);
                stmtParticipante.setString(3, horasTB);
                stmtParticipante.executeUpdate();
            } else {
                JOptionPane.showMessageDialog(null, "Projeto não encontrado: " + nomeProjeto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar participante: " + e.getMessage());
        }
    }

    public void adicionarProjeto(String nomeProjeto, String descricao, String dataInicio, String dataFim) {
     
        String sql = "INSERT INTO projetos (nome_projeto, descricao, data_inicio, data_fim) VALUES (?, ?, ?, ?)";

        // Cria um objeto DTOProjeto e define seus atributos com os valores fornecidos.
        DTOProjeto projetov = new DTOProjeto(nomeProjeto, descricao, dataInicio, dataFim);
        projetov.setNomeProjeto(nomeProjeto);
        projetov.setDescricao(descricao);
        projetov.setDataInicio(dataInicio);
        projetov.setDataFim(dataFim);

        try {
        
            Connection conAdmin = Conexao.conectar();
            
           
            PreparedStatement smtAdmin = conAdmin.prepareStatement(sql);

            // Define os parâmetros da declaração SQL com os valores do objeto DTOProjeto.
            smtAdmin.setString(1, projetov.getNomeProjeto());
            smtAdmin.setString(2, projetov.getDescricao());
            smtAdmin.setString(3, projetov.getDataInicio());
            smtAdmin.setString(4, projetov.getDataFim());

           
            smtAdmin.execute();
            
            // Fecha a declaração e a conexão com o banco de dados.
            smtAdmin.close();
            conAdmin.close();

            // Exibe uma mensagem de sucesso para o usuário.
            JOptionPane.showMessageDialog(null, "Projeto salvo com sucesso");
        } catch (SQLException e) {
            // Em caso de erro, exibe uma mensagem de falha para o usuário com a descrição do erro.
            JOptionPane.showMessageDialog(null, "Falha ao salvar projeto: " + e.getMessage());
        }
    }

    public void removerProjeto(String nomeProjeto) {
        String sql = "DELETE FROM projetos WHERE nome_projeto = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeProjeto);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Projeto removido com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover projeto: " + e.getMessage());
        }
    }

    public void atualizarProjeto(String nomeProjeto, String descricao, String dataInicio, String dataFim, int ID_projeto) {
        String sql = "UPDATE projetos SET nome_projeto = ?, descricao = ?, data_inicio = ?, data_fim = ? WHERE ID_projeto = ?";
        try (Connection conAdmin = Conexao.conectar();
             PreparedStatement stmtAdmin = conAdmin.prepareStatement(sql)) {
            stmtAdmin.setString(1, nomeProjeto);
            stmtAdmin.setString(2, descricao);
            stmtAdmin.setString(3, dataInicio);
            stmtAdmin.setString(4, dataFim);
            stmtAdmin.setInt(5, ID_projeto);
            stmtAdmin.executeUpdate();
            JOptionPane.showMessageDialog(null, "Projeto atualizado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar projeto: " + e.getMessage());
        }
    }

    public void atualizarUsuario(String nome, String nomeProjeto, String horasTrabalhadas, int idParticipante) {
        String sqlProjeto = "SELECT id_projeto FROM projetos WHERE nome_projeto = ?";
        String sqlParticipante = "UPDATE participantes SET nome_participante = ?, id_projeto = ?, horas_trabalhadas = ? WHERE id_participante = ?";
        try (Connection conAdmin = Conexao.conectar();
            PreparedStatement stmtProjeto = conAdmin.prepareStatement(sqlProjeto);
            PreparedStatement stmtAdmin = conAdmin.prepareStatement(sqlParticipante)) {

      
            stmtProjeto.setString(1, nomeProjeto);
            ResultSet rs = stmtProjeto.executeQuery();

            if (rs.next()) {
                int idProjeto = rs.getInt("id_projeto");

             
                stmtAdmin.setString(1, nome);
                stmtAdmin.setInt(2, idProjeto);
                stmtAdmin.setString(3, horasTrabalhadas);
                stmtAdmin.setInt(4, idParticipante);
                stmtAdmin.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso");
            } else {
                JOptionPane.showMessageDialog(null, "Usuario não encontrado: ");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e.getMessage());
        }
    }

    public static boolean cadastrarAdmin(String nomeAdmin, String senha) {
        String sql = "INSERT INTO administradores (nome_usuario, senha) VALUES (?, ?)";
        boolean condicao = false;
        try {
            Connection conAdmin = Conexao.conectar();
            PreparedStatement smtAdmin = conAdmin.prepareStatement(sql);
            smtAdmin.setString(1, nomeAdmin);
            smtAdmin.setString(2, senha);

            int linhasAfetadas = smtAdmin.executeUpdate();       
            if (linhasAfetadas > 0) {
                condicao = true; 
            }
            smtAdmin.close();
            conAdmin.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }

        return condicao;
    }

    public static boolean validarLogin(String nome, String senha) {
        String sql = "SELECT * FROM administradores WHERE nome_usuario = ? AND senha = ?";
        boolean resultado = false;
        try {
            Connection conAdmin = Conexao.conectar();
            PreparedStatement smtAdmin = conAdmin.prepareStatement(sql);
            smtAdmin.setString(1, nome);
            smtAdmin.setString(2, senha);

            ResultSet rs = smtAdmin.executeQuery();
            if (rs.next()) {
                resultado = true;
            }
            rs.close();
            smtAdmin.close();
            conAdmin.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }

        return resultado;
    }
    
    public void excluirUsuario(String nome, int ID) {
        String sql = "DELETE FROM participantes WHERE Nome_participante = ? and ID_participante = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setInt(2, ID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e.getMessage());
        }
    }

    public ResultSet consultarProjetos() {
        String sql = "SELECT * FROM projetos";
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet consultarParticipantes(String nomeProjeto) throws SQLException {
        // Consulta SQL para junção das tabelas
        String sql = "SELECT p.id_participante, p.nome_participante, pr.nome_projeto, p.id_projeto, p.horas_trabalhadas " +
                     "FROM participantes p " +
                     "JOIN projetos pr ON p.id_projeto = pr.id_projeto";

        // Adiciona a cláusula WHERE se o nome do projeto estiver presente
        if (nomeProjeto != null && !nomeProjeto.isEmpty()) {
            sql += " WHERE pr.nome_projeto = ?";
        }

        // Conexão com o banco de dados
        Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);

        // Define o valor do parâmetro se nomee do projeto estiver presente
        if (nomeProjeto != null && !nomeProjeto.isEmpty()) {
            stmt.setString(1, nomeProjeto);
        }

   
        return stmt.executeQuery();
    }

}
