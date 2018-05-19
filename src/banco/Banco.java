package banco;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JOptionPane;

public class Banco implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4106517860187051559L;
	
	
	private String nomeBanco;
	private List <Cliente> clientes;
	private List <Conta> contas;
	
	
	public Banco(String nomeBanco) {
		super();
		this.nomeBanco = nomeBanco;
		this.contas =  new ArrayList<Conta>();
		this.clientes =  new ArrayList<Cliente>();
		
	}
	
	public Banco() {
		// TODO Auto-generated constructor stub
	}

	public void addClient(Cliente cliente) {
		this.clientes.add(cliente);
	}
	
	public void newAccount(Cliente cliente) {
		Conta conta = new Conta(cliente);
		this.contas.add(conta);
		if(!this.clientes.contains(cliente)) {
			this.clientes.add(cliente);
		}
	}
	
	public Boolean removeClient(String cpfCnpj) {
		
		for (Conta conta : this.contas) {
			if(conta.getCliente().getCpfCnpj() == cpfCnpj) {
				return false;
			} else {
				this.clientes.removeIf(cliente -> cliente.getCpfCnpj() == cpfCnpj);
				return true;
			}
		}
		return null;		
	}
	
	public void removeAccount(int id) {
		this.contas.removeIf(conta -> conta.getNumConta() == id);
	}
	
	public void makeDeposit(int id, double value) {
		for (Conta conta : this.contas) {
			if (conta.getNumConta() == id) {
				conta.toCredit(value, "Depósito");
			}
		}	
	}
	
	public void makeWithdraw(int id, double value) {
		for (Conta conta : this.contas) {
			if (conta.getNumConta() == id) {
				conta.toDebit(value, "Saque");
			}
		}	
	}
	
	public void makeTransfer(int idFrom, int idTo, double value) {
		for (Conta contaFrom : this.contas) {
			if (contaFrom.getNumConta() == idFrom) {
				for (Conta contaTo : this.contas) {
					if(contaTo.getNumConta() == idTo ) {
						contaFrom.toDebit(value, String.format("Transferência para conta %d", contaTo.getNumConta()));
						contaTo.toCredit(value, String.format("Transferência da conta %d", contaFrom.getNumConta()));
					}
				}
			}
		}	
	}
	
	public void bankRatePayment() {
		for (Conta conta : this.contas) {
			conta.toDebit(15, "Cobrança de Tarifa");
		}
	}
	
	public void CpmfPayment() {
		
	    GregorianCalendar startDate = new GregorianCalendar();
	    startDate.set(GregorianCalendar.DATE, new GregorianCalendar().get(GregorianCalendar.DATE) - 7);
	    
		for (Conta conta : this.contas) {
			List<Movimentacao> moviments =  conta.getExtract();
			double totalDebit = 0;
			for (Movimentacao mov : moviments) {
				if (mov.getDebitoCredito() == 'D') {
					totalDebit += mov.getValor();
				}
			}
			conta.toDebit(totalDebit* 0.0038, "Cobrança de CPMF");
		}
	}
	
	public Double accountBalance(int id) {
		for (Conta conta : this.contas) {
			return conta.getSaldo();
		}
		return null;
	}
	
	public List<Movimentacao> accountExtract(int id) {
		for (Conta conta : this.contas) {
			return conta.getExtract();
		}
		return null;
	}
	
	public List<Movimentacao> accountExtract(int id, GregorianCalendar startDate) {
		for (Conta conta : this.contas) {
			return conta.getExtract(startDate);
		}
		return null;
	}
	
	public List<Movimentacao> accountExtract(int id, GregorianCalendar startDate, GregorianCalendar endDate) {
		for (Conta conta : this.contas) {
			return conta.getExtract(startDate, endDate);
		}
		return null;
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}
	
	//Saves one bank to file Banco
	public void writeFile() {
		
		try {
			FileOutputStream f = new FileOutputStream(new File("Banco.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(this);

			o.close();
			f.close();
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
	}
	
	public static Banco readFile() {
		
		try {
			FileInputStream fi = new FileInputStream(new File("BankData.dat"));
			ObjectInputStream oi = new ObjectInputStream(fi);
	
			// Read objects
			Banco banco = (Banco) oi.readObject();
	
			oi.close();
			fi.close();
			
			return banco;
	
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
			if (createBankObjectWithDefaultName())
			System.out.println("Default Bank Created. Try again!");
			JOptionPane.showMessageDialog(null, "Banco padrão criado.", "Erro ao iniciar banco", 1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Boolean createBankObjectWithDefaultName() {
		
		Banco b1 = new Banco("Meu Banco");
		
		try {
			FileOutputStream f = new FileOutputStream(new File("BankData.dat"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(b1);

			o.close();
			f.close();
			return true;
		} catch (IOException e) {
			System.out.println("Error initializing stream");
			return false;
		}
	}
	
//	//To save multiple banks to file
//	public void writeFile(List<Banco> bancos) {
//		
//		try {
//			FileOutputStream f = new FileOutputStream(new File("BancosAll.txt"));
//			ObjectOutputStream o = new ObjectOutputStream(f);
//			for (Banco banco : bancos) {
//				// Write objects to file
//				o.writeObject(banco);
//			}
//			o.close();
//			f.close();
//		} catch (IOException e) {
//			System.out.println("Error initializing stream");
//		}
//	}
//	//Save the names of the banks to file
//	public void writeFile(List<String> bancosNomes, Boolean justNames) {
//		
//		try {
//			FileOutputStream f = new FileOutputStream(new File("ListBancos.txt"));
//			ObjectOutputStream o = new ObjectOutputStream(f);
//
//			// Write objects to file
//			o.writeObject(bancosNomes);
//
//			o.close();
//			f.close();
//		} catch (IOException e) {
//			System.out.println("Error initializing stream");
//		}
//	}
	
	//TODO: Implementar métodos de leitura e gravação em arquivo.
	
//		public static void main(String[] args) {
//	
//			Banco b1 = new Banco("TesteBanco");
//			b1.newAccount(new Cliente("José", "11122233345", "Rua do carai", "123456789"));
//	
//			try {
//				FileOutputStream f = new FileOutputStream(new File("myObjects.txt"));
//				ObjectOutputStream o = new ObjectOutputStream(f);
//	
//				// Write objects to file
//				o.writeObject(b1);
//	
//				o.close();
//				f.close();
//	
//				FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
//				ObjectInputStream oi = new ObjectInputStream(fi);
//	
//				// Read objects
//				Banco br1 = (Banco) oi.readObject();
//	
//				System.out.println(br1);
//	
//	
//				oi.close();
//				fi.close();
//	
//			} catch (FileNotFoundException e) {
//				System.out.println("File not found");
//			} catch (IOException e) {
//				System.out.println("Error initializing stream");
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

		public void setNomeBanco(String nomeBanco) {
			this.nomeBanco = nomeBanco;
		}
	
}
