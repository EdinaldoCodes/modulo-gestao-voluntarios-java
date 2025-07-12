package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {
  
    private static Connection conn;

    // Método estático para estabelecer a conexão com o banco de dados
    public static Connection conectar() {
        try {
            // Tenta estabelecer uma conexão com o banco de dados MySQL
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestaovoluntarios", "root", "root");
        } catch (SQLException erro) {
            // Em caso de erro na conexão, exibe uma mensagem com o erro
            JOptionPane.showMessageDialog(null, "Erro de conexão" + erro);
        }
        
        // Retorna a conexão
        return conn;
    }   
}
