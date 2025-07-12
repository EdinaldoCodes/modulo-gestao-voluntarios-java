package main;

import dao.Conexao;
import view.FVisualizarProjetos;

public class Main {

	public static void main(String[] args) {
	Conexao.conectar();
	
	FVisualizarProjetos Fprojetos = new FVisualizarProjetos();
	Fprojetos.getFrame().setVisible(true);
	

	}

}
