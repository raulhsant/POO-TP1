package banco;

import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Movimentacao {
	
	private GregorianCalendar dataMov;
	private String descricao;
	private char debitoCredito; // c or d
	private double valor;
	
	
	
	public Movimentacao(String descricao, char debitoCredito, double valor) {
		super();
		this.descricao = descricao;
		this.debitoCredito = debitoCredito;
		this.valor = valor;
		
		//set default time zone to Sao Paulo
		TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
		TimeZone.setDefault(tz);
		//set dataMOv to actual time on timezone tz
		this.dataMov = new GregorianCalendar(tz);
	}

	public GregorianCalendar getDataMov() {
		return dataMov;
	}

	public String getDescricao() {
		return descricao;
	}

	public char getDebitoCredito() {
		return debitoCredito;
	}

	public double getValor() {
		return valor;
	}

}
