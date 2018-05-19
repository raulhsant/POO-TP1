package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;

import banco.Banco;

import java.awt.Color;
import javax.swing.SwingConstants;


public class Interface extends JFrame {

	private JPanel contentPane;
	private JMenu mnBanco;
	private JRadioButtonMenuItem rbMenuItem_1;
	private JCheckBoxMenuItem cbMenuItem_1;
	private JMenuItem mntmAdicionarCliente;
	private JMenuItem mntmRemoverCliente;
	private JMenuItem mntmListarClientes;
	private JMenu subMenu_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interface() {
		
		MenuBar menuBar = new MenuBar();
		
        this.setJMenuBar(menuBar.createMenuBar());
//        setContentPane(demo.createContentPane());

        setSize(400, 300);
        setVisible(true);
        
		Banco banco = Banco.readFile();
		this.setTitle(String.format("Banco : %s", banco.getNomeBanco()));
		
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		
	}
	
	
}
