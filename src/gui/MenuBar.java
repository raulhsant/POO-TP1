package gui;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar{

	public MenuBar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JMenuBar createMenuBar() {
		        JMenu bankMenu, clntMenu, sair, subMenu;


		        //Build the first menu.
		        bankMenu = new JMenu("Banco ");
		        bankMenu.setMnemonic(KeyEvent.VK_B);
		        bankMenu.getAccessibleContext().setAccessibleDescription("Menu de opções de execução do Banco");
		        
		        //a submenu
		        subMenu = new JMenu("Clientes");
		        
		        JMenuItem mntmListarClientes = new JMenuItem("Listar Clientes");
		        subMenu.add(mntmListarClientes);

		        JMenuItem mntmAdicionarCliente = new JMenuItem("Adicionar Cliente");
		        subMenu.add(mntmAdicionarCliente);

		        JMenuItem mntmRemoverCliente = new JMenuItem("Remover Cliente");
		        subMenu.add(mntmRemoverCliente);
		        bankMenu.add(subMenu);
		        
		        
		      //a submenu
		        bankMenu.addSeparator();
		        subMenu = new JMenu("Contas");
		        
		        JMenuItem mntmListarContas = new JMenuItem("Listar Contas");
		        subMenu.add(mntmListarContas);

		        JMenuItem mntmAdicionarConta = new JMenuItem("Adicionar Conta");
		        subMenu.add(mntmAdicionarConta);

		        JMenuItem mntmRemoverConta = new JMenuItem("Remover Conta");
		        subMenu.add(mntmRemoverConta);
		        bankMenu.add(subMenu);
		        
		        
		        
		        bankMenu.addSeparator();
		        subMenu = new JMenu("Cobran\u00E7as");
		        
		        JMenuItem mntmCobrarTarifas = new JMenuItem("Cobrar Tarifas");
		        subMenu.add(mntmCobrarTarifas);

		        JMenuItem mntmCobrarCPMF = new JMenuItem("Cobrar CPMF");
		        subMenu.add(mntmCobrarCPMF);

		        bankMenu.add(subMenu);
		        
		        
		        
		        //Build second menu in the menu bar.
		        clntMenu = new JMenu("Cliente");
		        clntMenu.setMnemonic(KeyEvent.VK_C);
		        clntMenu.getAccessibleContext().setAccessibleDescription("Menu de Opções do Cliente");
		        
		        
		        subMenu = new JMenu("Executar Transação");
		        
		        JMenuItem mntmDeposito = new JMenuItem("Depósito");
		        subMenu.add(mntmDeposito);

		        JMenuItem mntmSaque = new JMenuItem("Saque");
		        subMenu.add(mntmSaque);
		        
		        JMenuItem mntmTransfer = new JMenuItem("Transferência");
		        subMenu.add(mntmTransfer);

		        clntMenu.add(subMenu);
		        
		        JMenuItem balance = new JMenuItem("Saldo");
		        clntMenu.add(balance);
		        
		        JMenuItem extract = new JMenuItem("Extrato");
		        clntMenu.add(extract);
		        		        
		        
		        sair = new JMenu("Sair");
		        
		        
		        
		        this.add(bankMenu);
		        this.add(clntMenu);
		        this.add(sair);
		        		        
		        return this;
		    }
	
	

}
