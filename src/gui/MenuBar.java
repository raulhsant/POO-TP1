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
//		        JMenuBar menuBar;
		        JMenu menu, subMenu;

		        //Create the menu bar.
//		        this = new JMenuBar();

		        //Build the first menu.
		        menu = new JMenu("Banco ");
		        menu.setMnemonic(KeyEvent.VK_A);
		        menu.getAccessibleContext().setAccessibleDescription(
		                "The only menu in this program that has menu items");
		        this.add(menu);

//		        //a group of JMenuItems
//		        menuItem = new JMenuItem("A text-only menu item",
//		                                 KeyEvent.VK_T);
//		        menuItem.setEnabled(false);
//		        //menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
//		        menuItem.setAccelerator(KeyStroke.getKeyStroke(
//		                KeyEvent.VK_1, ActionEvent.ALT_MASK));
//		        menuItem.getAccessibleContext().setAccessibleDescription(
//		                "This doesn't really do anything");
//		        mnBanco.add(menuItem);

//		        ImageIcon icon = createImage("images/middle.gif");
//		        menuItem = new JMenuItem("Both text and icon", icon);
//		        menuItem.setMnemonic(KeyEvent.VK_B);
//		        menu.add(menuItem);

//		        menuItem = new JMenuItem(icon);
//		        menuItem.setMnemonic(KeyEvent.VK_D);
//		        menu.add(menuItem);

		        //a group of radio button menu items
//		        mnBanco.addSeparator();
//		        ButtonGroup group = new ButtonGroup();
	//
//		        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
//		        rbMenuItem.setEnabled(false);
//		        rbMenuItem.setSelected(true);
//		        rbMenuItem.setMnemonic(KeyEvent.VK_R);
//		        group.add(rbMenuItem);
//		        mnBanco.add(rbMenuItem);
	//
//		        rbMenuItem_1 = new JRadioButtonMenuItem("Another one");
//		        rbMenuItem_1.setEnabled(false);
//		        rbMenuItem_1.setMnemonic(KeyEvent.VK_O);
//		        group.add(rbMenuItem_1);
//		        mnBanco.add(rbMenuItem_1);
	//
//		        //a group of check box menu items
//		        mnBanco.addSeparator();
//		        cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
//		        cbMenuItem.setEnabled(false);
//		        cbMenuItem.setMnemonic(KeyEvent.VK_C);
//		        mnBanco.add(cbMenuItem);
	//
//		        cbMenuItem_1 = new JCheckBoxMenuItem("Another one");
//		        cbMenuItem_1.setEnabled(false);
//		        cbMenuItem_1.setMnemonic(KeyEvent.VK_H);
//		        mnBanco.add(cbMenuItem_1);
//		        
		        
		        //a submenu
//		        mnBanco.addSeparator();
		        subMenu = new JMenu("Clientes");
		        subMenu.setMnemonic(KeyEvent.VK_S);
		        
		        JMenuItem mntmListarClientes = new JMenuItem("Listar Clientes");
		        subMenu.add(mntmListarClientes);

		        JMenuItem mntmAdicionarCliente = new JMenuItem("Adicionar Cliente");
		        subMenu.add(mntmAdicionarCliente);

		        JMenuItem mntmRemoverCliente = new JMenuItem("Remover Cliente");
		        subMenu.add(mntmRemoverCliente);
		        menu.add(subMenu);
		        
			      //a submenu
		        menu.addSeparator();
		        subMenu = new JMenu("Contas");
		        subMenu.setMnemonic(KeyEvent.VK_S);
		        
		        JMenuItem mntmListarContas = new JMenuItem("Listar Contas");
		        subMenu.add(mntmListarContas);

		        JMenuItem mntmAdicionarConta = new JMenuItem("Adicionar Conta");
		        subMenu.add(mntmAdicionarConta);

		        JMenuItem mntmRemoverConta = new JMenuItem("Remover Conta");
		        subMenu.add(mntmRemoverConta);
		        menu.add(subMenu);
		        
		        
		        menu.addSeparator();
		        subMenu = new JMenu("Cobran\u00E7as");
		        subMenu.setMnemonic(KeyEvent.VK_S);
		        
		        JMenuItem mntmCobrarTarifas = new JMenuItem("Cobrar Tarifas");
		        subMenu.add(mntmCobrarTarifas);

		        JMenuItem mntmCobrarCPMF = new JMenuItem("Cobrar CPMF");
		        subMenu.add(mntmCobrarCPMF);

		        menu.add(subMenu);
		        
		        

		        //Build second menu in the menu bar.
		        menu = new JMenu("Another Menu");
		        menu.setMnemonic(KeyEvent.VK_N);
		        menu.getAccessibleContext().setAccessibleDescription(
		                "This menu does nothing");
		        this.add(menu);
		        
		        JMenu mnNewMenu = new JMenu("New menu");
		        this.add(mnNewMenu);

		        return this;
		    }
	
	

}
