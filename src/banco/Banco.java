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


public class Banco implements Serializable{
	
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
		
		Boolean canBeRemoved = true;
		
		for (Conta conta : this.contas) {
			if(conta.getCliente().getCpfCnpj().equals(cpfCnpj)) {
				System.out.println(String.format("O Cliente não pode ser exlcuido pois possui a conta %d. ", conta.getNumConta()));
				canBeRemoved =  false;
			}
		}
		
		if (canBeRemoved == true) {
			this.clientes.removeIf(cl -> cl.getCpfCnpj().equals(cpfCnpj));
			return true;
		}
		return false;		
	}
	
	public void removeAccount(int id) {
		this.contas.removeIf(conta -> conta.getNumConta() == id);
	}
	
	public void makeDeposit(int id, double value) {
		
		for (Conta conta : this.contas) {
			if (conta.getNumConta() == id) {
				conta.toCredit(value, "Depósito");
				
				System.out.println(String.format("Depósito de %.2f efetuado na conta %d", value, id));
			}
		}	
	}
	
	public void makeWithdraw(int id, double value) {
		for (Conta conta : this.contas) {
			if (conta.getNumConta() == id) {
				conta.toDebit(value, "Saque");
				
				System.out.println(String.format("Saque de %.2f efetuado na conta %d", value, id));
			}
		}	
	}
	
	public void makeTransfer(int idFrom, int idTo, double value) {
		
		Conta contaTo = new Conta();
		Conta contaFrom = new Conta();
		
		for (Conta conta : this.contas) {
			if (conta.getNumConta() == idFrom ) {
				contaFrom = conta;
				
				if (contaTo.getNumConta() != 0) {
					contaFrom.toDebit(value, String.format("Transferência para conta %d", contaTo.getNumConta()));
					contaTo.toCredit(value, String.format("Transferência da conta %d", contaFrom.getNumConta()));
					
					System.out.println(String.format("Transferência de %.2f efetuada da conta %d para a conta %d.", value, idFrom, idTo));
				}
			} else if (conta.getNumConta() == idTo) {
				contaTo = conta;
				
				if (contaFrom.getNumConta() != 0) {
					contaFrom.toDebit(value, String.format("Transferência para conta %d", contaTo.getNumConta()));
					contaTo.toCredit(value, String.format("Transferência da conta %d", contaFrom.getNumConta()));
					
					System.out.println(String.format("Transferência de %.2f efetuada da conta %d para a conta %d", value, idFrom, idTo));
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
			List<Movimentacao> moviments =  conta.getExtract(startDate);
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
			if(conta.getNumConta() == id)
				return conta.getSaldo();
		}
		
		return null;
	}
	
	public List<Movimentacao> accountExtract(int id) {
		for (Conta conta : this.contas) {
			if (conta.getNumConta() == id)
				return conta.getExtract();
		}
		return null;
	}
	
	public List<Movimentacao> accountExtract(int id, GregorianCalendar startDate) {
		
		for (Conta conta : this.contas) {
			if (conta.getNumConta() == id)
				return conta.getExtract(startDate);
		}
		return null;
	}
	
	public List<Movimentacao> accountExtract(int id, GregorianCalendar startDate, GregorianCalendar endDate) {
		for (Conta conta : this.contas) {
			if (conta.getNumConta() == id)
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
			FileOutputStream f = new FileOutputStream(new File("BankData.dat"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(this);

			o.close();
			f.close();
			System.out.println("Alterações salvas com sucesso!\n");
			
		} catch (IOException e) {
			System.out.println("Error initializing stream");
			System.out.println("\nNão foi possível salvar as Alterações!\n");
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
			
			Conta.setproxNumConta(banco.getContas().get(banco.getContas().size() - 1).getNumConta());
			return banco;
	
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
//			if (createBankObjectWithDefaultName())
//			System.out.println("Default Bank Created. Try again!");
//			JOptionPane.showMessageDialog(null, "Banco padrão criado.", "Erro ao iniciar banco", 1);
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
	
		public void setNomeBanco(String nomeBanco) {
			this.nomeBanco = nomeBanco;
		}
	
}
