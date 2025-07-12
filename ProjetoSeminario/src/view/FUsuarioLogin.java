package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.AdminDAO;

public class FUsuarioLogin {

	private JFrame frame;
	private JPasswordField textSenha;
	private JTextField textNome;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FUsuarioLogin window = new FUsuarioLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FUsuarioLogin() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame(); 
		frame.setTitle("Tela de login");
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 819, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 964, 493);
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 255));
		panel_1.setBounds(-114, 0, 504, 493);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("e participantes");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(223, 319, 169, 66);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Módulo gestão de voluntários");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(144, 282, 350, 66);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Edinaldo\\eclipse-workspace\\ProjetoSeminario\\Imagens\\cube (2).png"));
		lblNewLabel.setBounds(234, 161, 140, 134);
		panel_1.add(lblNewLabel);
				
		JLabel lblLogin_1 = new JLabel("LOGIN");
		lblLogin_1.setBounds(511, 149, 161, 43);
		panel.add(lblLogin_1);
		lblLogin_1.setForeground(new Color(0, 0, 255));
		lblLogin_1.setFont(new Font("Arial Black", Font.BOLD, 40));
		lblLogin_1.setBackground(new Color(0, 0, 255));
		
		JLabel lblEmail = new JLabel("Nome");
		lblEmail.setBounds(499, 210, 42, 19);
		panel.add(lblEmail);
		lblEmail.setForeground(new Color(0, 0, 255));
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBackground(new Color(51, 51, 51));
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(499, 272, 42, 19);
		panel.add(lblSenha);
		lblSenha.setForeground(new Color(0, 0, 255));
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSenha.setBackground(new Color(51, 51, 51));
		
		textNome = new JTextField();
		textNome.setText("Nome");
		textNome.setBounds(499, 233, 199, 29);
		panel.add(textNome);
		
		textSenha = new JPasswordField();
		textSenha.setBounds(499, 299, 199, 28);
		panel.add(textSenha);
		textSenha.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(499, 369, 199, 37);
		panel.add(btnLogin);
		btnLogin.setBackground(Color.WHITE);

		// Ação do botão login
		btnLogin.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nome = textNome.getText();
		        String senha = new String(textSenha.getPassword());

		        if (!nome.isEmpty() && !senha.isEmpty()) {
		        	 boolean condicao = AdminDAO.validarLogin(nome, senha);
		            
		        	 if(condicao) {
		        		JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
		        		frame.dispose();
		        		FVisualizarProjetos Fprojetos = new FVisualizarProjetos();
		        		Fprojetos.getFrame().setVisible(true);
		        	 
		        	} else {
		        		 JOptionPane.showMessageDialog(null, "Usuario não encontrado!");       
		        	}
		        	 
		      } else {
		           JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos");       
		       }   
		    }
		    
		});

		btnLogin.setForeground(new Color(0, 0, 255));
		btnLogin.setFont(new Font("Arial Black", Font.PLAIN, 15));
		}
		
		public JFrame getFrame() {
		
			return frame;
		}
}
