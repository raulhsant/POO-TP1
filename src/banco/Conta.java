package banco;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

public class Conta {
	
	private int numConta;
	private double saldo;
	private Cliente cliente;
	private List<Movimentacao> movimentacoes;

	//Maybe wont work this way
	private static int proxNumConta = 0;
	
	
	//TODO: Implements other methods
	
	public Conta(Cliente cliente) {
		super();
		this.cliente = cliente;
		this.saldo = 0;
		this.numConta = proxNumConta;
		this.movimentacoes = new ArrayList<Movimentacao>();
		proxNumConta++;
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
	
	public double toDebit(double valor, String descricao) {
		if (this.saldo - valor >= 0) {
			Movimentacao mov = new Movimentacao(descricao, 'D', valor);
			movimentacoes.add(mov);
			
			this.saldo -= valor;
			
			return this.saldo;
		} 
		//if saldo < valor return null
		return (Double) null;
	}
	
	public double toCredit(double valor, String descricao) {

			Movimentacao mov = new Movimentacao(descricao, 'C', valor);
			movimentacoes.add(mov);
			
			this.saldo += valor;
			
			return this.saldo;
	}
	
	public List<Movimentacao> getExtract (GregorianCalendar startDate, GregorianCalendar endDate){
		List <Movimentacao> toReturnList =  new ArrayList<Movimentacao>();
		
		for (Movimentacao mov : this.movimentacoes) {
			if (mov.getDataMov().getTimeInMillis() >= startDate.getTimeInMillis() && mov.getDataMov().getTimeInMillis() <= startDate.getTimeInMillis()) {
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
//		 	TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
//			TimeZone.setDefault(tz);
		    GregorianCalendar actualDate = new GregorianCalendar();
			int month = actualDate.get(GregorianCalendar.MONTH);
//		    LocalDate localDate = actualDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//		    int month = actualDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue();
		    
		    List<Movimentacao> toReturn =  new ArrayList<Movimentacao>();
		    
		    for (Movimentacao mov : this.movimentacoes) {
		    	//Checks if movimentacao mov was in the current month
//		    	if (month == mov.getDataMov().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue()) {
		    	if(month == mov.getDataMov().get(GregorianCalendar.MONTH)) {
		    		toReturn.add(mov);
		    	}
		    }
		    
	    	return toReturn;	    
	}
	
	
	
	

}
