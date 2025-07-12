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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.AdminDAO;

public class FVisualizarProjetos {

    private JFrame frame;
    private JTable table;
    private JTextField textFieldNome;
    private JTextField textFieldDescricao;
    private JTextField textFieldDataInicio;
    private JTextField textFieldDataFim;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FVisualizarProjetos window = new FVisualizarProjetos();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FVisualizarProjetos() {
        initialize();
        carregarProjetos(); // Recarrega a tabela de projetos toda vez que a página é atualizada
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().setForeground(Color.DARK_GRAY);
        frame.setBounds(100, 100, 873, 527);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);

        JPanel panel_1 = new JPanel();
        panel_1.setForeground(Color.BLACK);
        panel_1.setBounds(0, 0, 314, 490);
        frame.getContentPane().add(panel_1);
        panel_1.setBackground(new Color(32, 32, 255));
        panel_1.setLayout(null);

        JButton btnNewButton = new JButton("ADICIONAR");
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.setBackground(Color.WHITE);
        btnNewButton.setBounds(10, 398, 279, 29);
        panel_1.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarProjeto();
            }
        });
        btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 10));

        JButton btnExcluirProjeto = new JButton("EXCLUIR");
        btnExcluirProjeto.setForeground(Color.BLACK);
        btnExcluirProjeto.setBackground(Color.WHITE);
        btnExcluirProjeto.setBounds(10, 437, 279, 29);
        panel_1.add(btnExcluirProjeto);
        btnExcluirProjeto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirProjeto();
            }
        });
        btnExcluirProjeto.setFont(new Font("Arial Black", Font.BOLD, 10));

        JButton btnEdit = new JButton("ATUALIZAR");
        btnEdit.setForeground(Color.BLACK);
        btnEdit.setBackground(Color.WHITE);
        btnEdit.setBounds(10, 359, 279, 29);
        panel_1.add(btnEdit);
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               atualizarProjeto();
            }
        });
        btnEdit.setFont(new Font("Arial Black", Font.BOLD, 10));

        textFieldNome = new JTextField();
        textFieldNome.setBounds(10, 122, 237, 19);
        panel_1.add(textFieldNome);
        textFieldNome.setColumns(10);

        textFieldDataInicio = new JTextField();
        textFieldDataInicio.setBounds(10, 180, 103, 19);
        textFieldDataInicio.setColumns(10);
        panel_1.add(textFieldDataInicio);

        textFieldDescricao = new JTextField();
        textFieldDescricao.setBounds(10, 237, 279, 48);
        textFieldDescricao.setColumns(10);
        panel_1.add(textFieldDescricao);

        JLabel lblNomeProjeto = new JLabel("Nome do projeto");
        lblNomeProjeto.setForeground(Color.WHITE);
        lblNomeProjeto.setBounds(10, 95, 133, 29);
        lblNomeProjeto.setFont(new Font("Arial Black", Font.PLAIN, 11));
        panel_1.add(lblNomeProjeto);

        JLabel lblDesc = new JLabel("Descrição");
        lblDesc.setForeground(Color.WHITE);
        lblDesc.setBackground(Color.WHITE);
        lblDesc.setBounds(10, 195, 133, 59);
        lblDesc.setFont(new Font("Arial Black", Font.PLAIN, 11));
        panel_1.add(lblDesc);

        textFieldDataFim = new JTextField();
        textFieldDataFim.setBounds(153, 180, 103, 19);
        textFieldDataFim.setColumns(10);
        panel_1.add(textFieldDataFim);

        JLabel lblDataTermino = new JLabel("Data de término");
        lblDataTermino.setForeground(Color.WHITE);
        lblDataTermino.setBounds(150, 151, 133, 29);
        lblDataTermino.setFont(new Font("Arial Black", Font.PLAIN, 11));
        panel_1.add(lblDataTermino);

        JLabel lblDataInicio = new JLabel("Data de início");
        lblDataInicio.setForeground(Color.WHITE);
        lblDataInicio.setBackground(new Color(240, 240, 240));
        lblDataInicio.setBounds(10, 151, 126, 29);
        lblDataInicio.setFont(new Font("Arial Black", Font.PLAIN, 11));
        panel_1.add(lblDataInicio);
        
        JButton btnNewButton_1 = new JButton("IR PARA TABELA PARTICIPANTES");
        btnNewButton_1.setForeground(Color.BLACK);
        btnNewButton_1.setBackground(Color.WHITE);
        btnNewButton_1.setBounds(10, 320, 279, 29);
        panel_1.add(btnNewButton_1);
       
        // Direciona para a tela de gestão de participantes
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FVisualizarUsuarios FParticipantes = new FVisualizarUsuarios();
                FParticipantes.getFrame().setVisible(true);
                frame.dispose();
            }
        });
        
        
        btnNewButton_1.setFont(new Font("Arial Black", Font.BOLD, 10));
        
        JButton btnNewButton_2 = new JButton("Voltar");
        btnNewButton_2.setForeground(Color.BLACK);
        
        // Direciona para a tela de gestão de login
        
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		FUsuarioLogin login = new FUsuarioLogin();
        		login.getFrame().setVisible(true);
        		frame.dispose();
        	}
        });
        btnNewButton_2.setFont(new Font("Arial Black", Font.BOLD, 10));
        btnNewButton_2.setBackground(Color.WHITE);
        btnNewButton_2.setBounds(10, 10, 75, 21);
        panel_1.add(btnNewButton_2);
        
        JButton btnNewButton_3 = new JButton("Cadastrar Admin");
        btnNewButton_3.setForeground(Color.BLACK);
        btnNewButton_3.setBackground(Color.WHITE);
        btnNewButton_3.addActionListener(new ActionListener() {
        	
//        	Ação - visualização de cadastro de admin
        	public void actionPerformed(ActionEvent e) {
        		FUsuarioCadastro fcadastro = new FUsuarioCadastro();
        		fcadastro.getFrame().setVisible(true);
        		
        	}
        });
        btnNewButton_3.setFont(new Font("Arial Black", Font.BOLD, 10));
        btnNewButton_3.setBounds(95, 10, 152, 21);
        panel_1.add(btnNewButton_3);

        JLabel lblNewLabel = new JLabel("LISTA DE PROJETOS");
        lblNewLabel.setBounds(324, 69, 274, 29);
        frame.getContentPane().add(lblNewLabel);
        lblNewLabel.setForeground(new Color(35, 61, 255));
        lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 20));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setForeground(new Color(35, 61, 255));
        scrollPane.setBounds(323, 108, 515, 372);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        table.setSelectionForeground(new Color(255, 255, 255));
        table.setSelectionBackground(new Color(35, 61, 255));
        table.setFont(new Font("Arial", Font.BOLD, 11));
        table.setBackground(new Color(230, 230, 250));
        table.setForeground(new Color(35, 61, 255));
        
        //Captura os dados da tabela e coloca nos campos de texto
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                textFieldNome.setText(table.getValueAt(row, 1).toString());
                textFieldDataInicio.setText(table.getValueAt(row, 2).toString());
                textFieldDataFim.setText(table.getValueAt(row, 3).toString());
                textFieldDescricao.setText(table.getValueAt(row, 4).toString());
            }
        });

        table.setModel(new DefaultTableModel(
            new Object[][] {
                {null, null, null, null, null},
            },
            new String[] {
                "id", "nome", "dataInicio", "dataFim", "descricao"
            }
        ));
        table.getColumnModel().getColumn(3).setPreferredWidth(72);
        scrollPane.setViewportView(table);
    }

    private void excluirProjeto() {
        String nomeProjeto = textFieldNome.getText();
        if (!nomeProjeto.isEmpty()) {
            AdminDAO adminDAO = new AdminDAO();
            adminDAO.removerProjeto(nomeProjeto );  
            limparCampos();
            carregarProjetos(); 
        } else {
            JOptionPane.showMessageDialog(frame, "Nome do projeto deve ser informado!");
        }
    }

    private void atualizarProjeto() {
    	 int row = table.getSelectedRow();
	        if (row != -1) {
	            int id = Integer.parseInt(table.getValueAt(row, 0).toString());
	            String nome = textFieldNome.getText();
	            String descricao = textFieldDescricao.getText(); 
	            String dataInicio = textFieldDataInicio.getText();
	            String dataFim = textFieldDataFim.getText();

	            AdminDAO adminDAO = new AdminDAO();
	            adminDAO.atualizarProjeto(nome, descricao, dataInicio, dataFim, id);
	            carregarProjetos(); 
	        }else {
	        	 JOptionPane.showMessageDialog(frame, "Nome do projeto deve ser informado!");
	        }
    }

    private void carregarProjetos() {
        AdminDAO adminDAO = new AdminDAO();
        ResultSet rs = adminDAO.consultarProjetos();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); 

        try {
            while (rs.next()) {
                int id = rs.getInt("id_projeto");
                String nome = rs.getString("nome_projeto");
                String descricao = rs.getString("descricao");
                String dataInicio = rs.getString("data_inicio");
                String dataFim = rs.getString("data_fim");

                model.addRow(new Object[]{id, nome, dataInicio, dataFim, descricao});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void adicionarProjeto() {
        String nome = textFieldNome.getText();
        String dataInicio = textFieldDataInicio.getText();
        String dataFim = textFieldDataFim.getText();
        String descricao = textFieldDescricao.getText();

        if (nome.isEmpty() || descricao.isEmpty() || dataInicio.isEmpty() || dataFim.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Todos os campos devem ser preenchidos!");
            return;
        } else {
        	AdminDAO adminDAO = new AdminDAO();
            adminDAO.adicionarProjeto(nome, descricao, dataInicio, dataFim);
            limparCampos();
            carregarProjetos();
        }

    }

    public void limparCampos() {
        textFieldNome.setText("");
        textFieldDataInicio.setText("");
        textFieldDescricao.setText("");
        textFieldDataFim.setText("");
        
    }

	public JFrame getFrame() {
		return frame;
	}
}
