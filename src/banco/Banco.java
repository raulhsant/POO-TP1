package banco;

import java.util.GregorianCalendar;
import java.util.List;

public class Banco {
	
	private String nomeBanco;
	private List <Cliente> clientes;
	private List <Conta> contas;
	
	
	public Banco(String nomeBanco) {
		super();
		this.nomeBanco = nomeBanco;
	}
	
	public void addClient(Cliente cliente) {
		this.clientes.add(cliente);
	}
	
	public void newAccount(Cliente cliente) {
		Conta conta = new Conta(cliente);
		this.contas.add(conta);
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
				conta.toCredit(value, "Dep�sito");
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
						contaFrom.toDebit(value, String.format("Transfer�ncia para conta %d", contaTo.getNumConta()));
						contaTo.toCredit(value, String.format("Transfer�ncia da conta %d", contaFrom.getNumConta()));
					}
				}
			}
		}	
	}
	
	public void bankRatePayment() {
		for (Conta conta : this.contas) {
			conta.toDebit(15, "Cobran�a de Tarifa");
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
			conta.toDebit(totalDebit* 0.0038, "Cobran�a de CPMF");
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
	
	//TODO: Implementar m�todos de leitura e grava��o em arquivo.

//	public void setNomeBanco(String nomeBanco) {
//		this.nomeBanco = nomeBanco;
//	}
	
}
