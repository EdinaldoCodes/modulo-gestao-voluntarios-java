package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.AdminDAO;

import javax.swing.ImageIcon;

public class FUsuarioCadastro {
	
	private JFrame frame;
	private JTextField textNome;
	private JLabel lblNome;
	private JLabel lblSenha;
	private JPasswordField textSenha;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FUsuarioCadastro window = new FUsuarioCadastro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FUsuarioCadastro() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Tela de Usuario");
		frame.setTitle("Tela de cadastro");
		frame.getContentPane().setForeground(new Color(51, 51, 51));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 818, 530);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(0, 0, 255));
		panel.setBounds(0, 0, 387, 493);
		frame.add(panel);
		frame.setLocationRelativeTo(null); 
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Módulo gestão de voluntários");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(30, 284, 357, 66);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("e participantes");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(104, 318, 173, 66);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Edinaldo\\eclipse-workspace\\ProjetoSeminario\\Imagens\\cube (2).png"));
		lblNewLabel.setBounds(120, 172, 147, 117);
		panel.add(lblNewLabel);
				
		JLabel lblCadastro = new JLabel("CADASTRO");
		lblCadastro.setBounds(472, 151, 257, 43);
		frame.getContentPane().add(lblCadastro);
		lblCadastro.setBackground(new Color(0, 0, 255));
		lblCadastro.setFont(new Font("Arial Black", Font.BOLD, 40));
		lblCadastro.setForeground(new Color(0, 0, 255));

		textNome = new JTextField();
		textNome.setBounds(499, 229, 187, 28);
		frame.add(textNome);
		textNome.setColumns(10);
		
		JButton btnNewButton = new JButton("CADASTRAR");
		btnNewButton.setBounds(499, 361, 194, 37);
		frame.add(btnNewButton);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setForeground(new Color(35, 61, 255));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        	cadastrarAdmin();
		    }
		});
		   
		
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 15));
		
		textSenha = new JPasswordField();
		textSenha.setBounds(499, 291, 190, 29);
		frame.add(textSenha);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(499, 272, 42, 19);
		frame.add(lblSenha);
		lblSenha.setForeground(new Color(0, 0, 255));
		lblSenha.setBackground(new Color(255, 255, 255));
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(499, 208, 39, 19);
		frame.add(lblNome);
		lblNome.setBackground(new Color(255, 255, 255));
		lblNome.setForeground(new Color(0, 0, 255));
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		}
	
		private void cadastrarAdmin() {		
		    String nome = textNome.getText();
		    // Obtém a senha como um array de caracteres e a converte para String
		    char[] senhaChars = textSenha.getPassword();
		    String senha = new String(senhaChars);

		  
		    if (!nome.isEmpty() && !senha.isEmpty()) {
		        // Chama o método para cadastrar o admin no banco de dados
		        boolean condicao = AdminDAO.cadastrarAdmin(nome, senha);

		   
		        if (condicao) {
		       
		            JOptionPane.showMessageDialog(null, "Admin cadastrado com sucesso");
		            frame.dispose();
		            // Abre a janela de login de usuário
		            FUsuarioLogin loginWindow = new FUsuarioLogin();
		            loginWindow.getFrame().setVisible(true);
		        } else {
		           
		            JOptionPane.showMessageDialog(null, "Não foi possível realizar cadastro!", "Error",
		                    JOptionPane.ERROR_MESSAGE);
		        }
		    } else {
		       
		        JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
		    }
		}


	public JFrame getFrame() {
		return frame;
	}
}
