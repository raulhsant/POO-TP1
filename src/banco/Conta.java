package banco;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Conta implements Serializable{
	

	private static final long serialVersionUID = 577928927645938285L;
	
	private int numConta;
	private double saldo;
	private Cliente cliente;
	private List<Movimentacao> movimentacoes;

	private static int proxNumConta = 1;
	
	public static void setproxNumConta(int numContas) {
		proxNumConta = numContas+1; 
	}
	
	public Conta(Cliente cliente) {
		super();
		this.cliente = cliente;
		this.saldo = 0;
		this.numConta = proxNumConta;
		this.movimentacoes = new ArrayList<Movimentacao>();
		proxNumConta++;
	}

	public Conta() {
		// TODO Auto-generated constructor stub
	}

	public int getNumConta() {
		return numConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public static int getProxNumConta() {
		return proxNumConta;
	}
	
	@SuppressWarnings("null")
	public double toDebit(double valor, String descricao) {
		if (this.saldo - valor >= 0) {
			Movimentacao mov = new Movimentacao(descricao, 'D', valor);
			movimentacoes.add(mov);
			
			this.saldo -= valor;
			
			return this.saldo;
		} 
		return (Double) null;
	}
	
	public double toCredit(double valor, String descricao) {

			Movimentacao mov = new Movimentacao(descricao, 'C', valor);
			this.movimentacoes.add(mov);
			this.saldo += valor;
			return this.saldo;
	}
	
	public List<Movimentacao> getExtract (GregorianCalendar startDate, GregorianCalendar endDate){
		List <Movimentacao> toReturnList =  new ArrayList<Movimentacao>();
		
		for (Movimentacao mov : this.movimentacoes) {
			if (mov.getDataMov().getTimeInMillis() >= startDate.getTimeInMillis() && mov.getDataMov().getTimeInMillis() <= endDate.getTimeInMillis()) {
				toReturnList.add(mov);
			}
		}
		
		return toReturnList;
	}
	
	//from start date to TODAY
	public List<Movimentacao> getExtract (GregorianCalendar startDate){
		return getExtract(startDate, new GregorianCalendar());
	}
	
	//get extract from current month
	public List<Movimentacao> getExtract(){

		    GregorianCalendar actualDate = new GregorianCalendar();
			int month = actualDate.get(GregorianCalendar.MONTH);
		    List<Movimentacao> toReturn =  new ArrayList<Movimentacao>();
		    
		    for (Movimentacao mov : this.movimentacoes) {
		    	
		    	if(month == mov.getDataMov().get(GregorianCalendar.MONTH)) {
		    		toReturn.add(mov);
		    	}
		    }
		    
	    	return toReturn;	    
	}

}
