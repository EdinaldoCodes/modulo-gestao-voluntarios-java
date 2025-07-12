package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.AdminDAO;

public class FVisualizarUsuarios {

    private JFrame frame;
    private JTable table;
    private JTextField txtParticipante;
    private JComboBox<String> comboBoxProjetos;
    private JComboBox<String> comboBoxSelecionarPJ;
    private JTextField txtHorasTB;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FVisualizarUsuarios window = new FVisualizarUsuarios();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FVisualizarUsuarios() {
        initialize();
        loadProjetos();
        
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBounds(100, 100, 873, 530);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(319, 122, 530, 361);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        table.setSelectionForeground(new Color(255, 255, 255));
        table.setSelectionBackground(new Color(35, 61, 255));
        table.setBackground(new Color(230, 230, 250));
        table.setForeground(new Color(35, 61, 255));
        table.setFont(new Font("Arial", Font.BOLD, 11));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	 int row = table.getSelectedRow();
                 txtParticipante.setText(table.getValueAt(row, 1).toString());
                 txtHorasTB.setText(table.getValueAt(row, 4).toString());
            }
        });

        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "ID participante", "Nome participante", "Projeto", "ID projeto", "Horas de trabalho"
            }
        ));
        table.getColumnModel().getColumn(0).setPreferredWidth(86);
        table.getColumnModel().getColumn(1).setPreferredWidth(99);
        table.getColumnModel().getColumn(4).setPreferredWidth(95);
        scrollPane.setViewportView(table);

        JLabel lblListaParticipantes = new JLabel("LISTA DE PARTICIPANTES");
        lblListaParticipantes.setBackground(new Color(0, 128, 192));
        lblListaParticipantes.setBounds(321, 83, 392, 29);
        lblListaParticipantes.setForeground(new Color(35, 61, 255));
        lblListaParticipantes.setFont(new Font("Arial Black", Font.BOLD, 20));
        frame.getContentPane().add(lblListaParticipantes);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 309, 503);
        frame.getContentPane().add(panel);
        panel.setBackground(new Color(32, 32, 255));
        panel.setLayout(null);
                
        JButton btnExcluirParticipante = new JButton("EXLUIR ");
        btnExcluirParticipante.setForeground(new Color(0, 0, 0));
        btnExcluirParticipante.setBounds(10, 403, 287, 29);
        panel.add(btnExcluirParticipante);
        btnExcluirParticipante.setBackground(Color.WHITE);
        btnExcluirParticipante.setFont(new Font("Arial Black", Font.BOLD, 10));

        JButton btnAddParticipante = new JButton("ADICIONAR");
        btnAddParticipante.setForeground(new Color(0, 0, 0));
        btnAddParticipante.setBounds(10, 439, 287, 29);
        panel.add(btnAddParticipante);
        btnAddParticipante.setBackground(Color.WHITE);
        btnAddParticipante.setFont(new Font("Arial Black", Font.BOLD, 10));

        JButton btnEdit = new JButton("ATUALIZAR");
        btnEdit.setForeground(new Color(0, 0, 0));
        btnEdit.setBounds(10, 364, 287, 29);
        panel.add(btnEdit);
        btnEdit.setBackground(Color.WHITE);
        btnEdit.setFont(new Font("Arial Black", Font.BOLD, 10));
                                                                
        JLabel lblNomeParticipante = new JLabel("Nome do Participante");
        lblNomeParticipante.setForeground(new Color(255, 255, 255));
        lblNomeParticipante.setBounds(10, 119, 133, 29);
        panel.add(lblNomeParticipante);
        lblNomeParticipante.setFont(new Font("Arial Black", Font.PLAIN, 11));
        
        txtParticipante = new JTextField();
        txtParticipante.setBounds(10, 158, 287, 29);
        panel.add(txtParticipante);
        txtParticipante.setColumns(10);
                
                
        txtHorasTB = new JTextField();
        txtHorasTB.setBounds(10, 243, 119, 29);
        panel.add(txtHorasTB);
        txtHorasTB.setColumns(10);

        JLabel lblNewLabel = new JLabel("Horas de trabalho");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(10, 220, 119, 13);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 11));
        
        JButton btnViualizarProjetos = new JButton("IR PARA TABELA PROJETOS");
        btnViualizarProjetos.setForeground(new Color(0, 0, 0));
        btnViualizarProjetos.setBackground(Color.WHITE);
        btnViualizarProjetos.setBounds(10, 322, 287, 29);
        panel.add(btnViualizarProjetos);
        btnViualizarProjetos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FVisualizarProjetos Fprojetos = new FVisualizarProjetos();
                Fprojetos.getFrame().setVisible(true);
                frame.dispose();
            }
        });
        btnViualizarProjetos.setFont(new Font("Arial Black", Font.BOLD, 10));
        
        comboBoxSelecionarPJ = new JComboBox<>();
        comboBoxSelecionarPJ.setBounds(155, 247, 142, 25);
        panel.add(comboBoxSelecionarPJ);
        comboBoxSelecionarPJ.setBackground(Color.WHITE);
        comboBoxSelecionarPJ.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            comboBoxSelecionarPJ.getSelectedItem();
	         
	            
	        }
	    });
        
        JLabel lblProjeto_1 = new JLabel("Projeto");
        lblProjeto_1.setBounds(155, 212, 126, 29);
        panel.add(lblProjeto_1);
        lblProjeto_1.setForeground(new Color(255, 255, 255));
        lblProjeto_1.setFont(new Font("Arial Black", Font.PLAIN, 11));
        
        JButton btnNewButton = new JButton("Voltar");
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.setBackground(Color.WHITE);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FUsuarioLogin login = new FUsuarioLogin();
        		login.getFrame().setVisible(true);
        		frame.dispose();
        	}
        });
        btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 10));
        btnNewButton.setBounds(10, 10, 85, 21);
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Cadastrar Admin");
        btnNewButton_1.setForeground(Color.BLACK);
        btnNewButton_1.setBackground(Color.WHITE);
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FUsuarioCadastro cadastro = new FUsuarioCadastro();
        		cadastro.getFrame().setVisible(true);
        	}
        });
        btnNewButton_1.setFont(new Font("Arial Black", Font.BOLD, 10));
        btnNewButton_1.setBounds(105, 10, 142, 21);
        panel.add(btnNewButton_1);
	    
	    comboBoxProjetos = new JComboBox<>();
	    comboBoxProjetos.setBounds(707, 91, 142, 21);
	    frame.getContentPane().add(comboBoxProjetos);
	    comboBoxProjetos.setBackground(Color.WHITE);
	    comboBoxProjetos.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String nomeProjeto = (String) comboBoxProjetos.getSelectedItem();
	            loadParticipantes(nomeProjeto);
	            
	        }
	    });
	    
	  
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarParticipante();
	        }
	    });
	                                                                    
        btnAddParticipante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarParticipante();
            }
        });

        btnExcluirParticipante.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		excluirParticipante();
		    }
		});
    }

    private void loadParticipantes(String nomeProjeto) {
        AdminDAO adminDAO = new AdminDAO();
        try (ResultSet rs = adminDAO.consultarParticipantes(nomeProjeto)) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                int idParticipante = rs.getInt("id_participante");
                String nomeParticipante = rs.getString("nome_participante");
                String projeto = rs.getString("nome_projeto");
                int idProjeto = rs.getInt("id_projeto");
                int horasTB = rs.getInt("horas_trabalhadas");

                model.addRow(new Object[]{idParticipante, nomeParticipante, projeto, idProjeto, horasTB});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void adicionarParticipante() {
        String nome = txtParticipante.getText();
        String horasTB = txtHorasTB.getText();
        //Atribua a uma variável o nome do projeto escolhido no jcomboBox de associação de usuario a projeto
        String projeto = (String) comboBoxSelecionarPJ.getSelectedItem();

        if (nome.isEmpty() ) {
            JOptionPane.showMessageDialog(frame, "O campo Nome deve ser preenchido!");
            return;
        }

        if (projeto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Um projeto deve ser selecionado!");
            return;
        }

        if (horasTB.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "O campo Horas de Trabalho deve ser preenchido!");
            return;
        }

        try {
            AdminDAO adminDAO = new AdminDAO();
            adminDAO.adicionarParticipante(nome, projeto, horasTB);
            JOptionPane.showMessageDialog(frame, "Usuário adicionado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao adicionar participante: " + e.getMessage());
        } finally {
        	
            limparCampos();
            loadProjetos();
            //Adiciona o projeto escolhido ao jcombox que realiza o filtro de projetos
            comboBoxProjetos.setSelectedItem(projeto); 
            
        }
    }

    private void loadProjetos() {
        AdminDAO adminDAO = new AdminDAO();
        try {
            ResultSet rs = adminDAO.consultarProjetos();
           comboBoxProjetos.removeAllItems();
           comboBoxSelecionarPJ.removeAllItems();

            while (rs.next()) {
                String nomeProjeto = rs.getString("nome_projeto");
                comboBoxProjetos.addItem(nomeProjeto);
                comboBoxSelecionarPJ.addItem(nomeProjeto);
            }	
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void excluirParticipante() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(frame, "Nenhum participante selecionado!");
            return;
        }

        String nomeParticipante = txtParticipante.getText();
        int idParticipante = Integer.parseInt(table.getValueAt(row, 0).toString());

        try {
            AdminDAO adminDAO = new AdminDAO();
            adminDAO.excluirUsuario(nomeParticipante, idParticipante);
            JOptionPane.showMessageDialog(frame, "Participante excluído com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao excluir participante: " + e.getMessage());
        } finally {
            limparCampos();
            loadParticipantes((String) comboBoxProjetos.getSelectedItem());
        }
    }



    private void atualizarParticipante() {
        int row = table.getSelectedRow();

        String nome = txtParticipante.getText();
        String horasTB = txtHorasTB.getText();
        String projeto = (String) comboBoxProjetos.getSelectedItem();
        int idParticipante = Integer.parseInt(table.getValueAt(row, 0).toString()); 

        if (nome.isEmpty() || projeto == null || horasTB.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Todos os itens devem ser preenchidos!");
            return;
        }

        AdminDAO adminDAO = new AdminDAO();
        adminDAO.atualizarUsuario(nome, projeto, horasTB, idParticipante);
        loadParticipantes(projeto); 
    }

    private void limparCampos() {
        txtParticipante.setText("");
        txtHorasTB.setText("");
        comboBoxSelecionarPJ.setSelectedIndex(-1);
    }
    
    public JFrame getFrame() {
    	return frame;
    }
}
