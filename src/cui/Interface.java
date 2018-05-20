package cui;


import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import banco.Banco;

public class Interface {
	
	
	public static void main(String[] args) {
		
//		System.out.println("Running");
		Interface inter =  new Interface();
		Banco banco = Banco.readFile();
		Scanner in = new Scanner(System.in);
		int selectedOption;
		Boolean close =  false;
		
		
		while (!close) {
	//		System.out.println(String.format("Informações do banco \"%s\" carregadas com sucesso.", banco.getNomeBanco()));
			inter.WriteMenu();
			
			System.out.printf("\nDigite o número da atividade desejada: ");
			selectedOption = in.nextInt();
			
			switch(selectedOption) {
				default:
					System.out.println("Não foi possível identificar a opção digitada, tente novamente.");
					try {
						System.in.read();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				
				case 0:
					System.out.println("\nSalvando alterações...");
					close = true;
					banco.writeFile();
					System.out.println("Alterações salvas com sucesso!\n");
					System.out.println("\tAté logo.");
					break;

			}
			
		}

	}
	
	
	public void WriteMenu() {
		System.out.println("O que você deseja?\n");
		
//		System.out.println("\tAdicionar:\n 1 - Cliente\t 2 - Conta\n");
//		System.out.println("Remover:\n 3 - Cliente\t 4 - Conta\n");
//		System.out.println("Listar:\n 5 - Cliente\t 6 - Conta\n");
		
		List<String> linhas = new ArrayList<String> ();
		String linha = "|   1 - Adicionar    2 - Remover    3 - Listar    |";
					
		String trace = "";
		String traceAux = "";
		for (int i = 0; i <= linha.length() -1 ; i++) {
			trace += "=";
			traceAux += "-";
		}
		
		String whitespace = new String(new char[ (linha.length() - 18)/2 ]).replace('\0', ' ');
		linhas.add(traceAux);
		linhas.add(linha);
		linhas.add(traceAux);
		linhas.add("| " +  whitespace + "Cliente ou Conta" + whitespace + "|");
		linhas.add(trace);
//		System.out.println(linhas.get(3).length());
		
		
		whitespace = new String(new char[ (linhas.get(0).length())/2 - 4 ]).replace('\0', ' ');

		linhas.add(traceAux);
		linhas.add( "|" +  whitespace + "Efetuar" + whitespace + "|");
		linhas.add(traceAux);
		linhas.add("|  4 - Depósito    5 - Saque    6 - Transferência |");
		linhas.add(trace);
		
		
		whitespace = new String(new char[ (linhas.get(0).length())/2 - 6 ]).replace('\0', ' ');
		
		linhas.add(traceAux);
		linhas.add("|" + whitespace +  " Visualizar" + whitespace + "|");
		linhas.add(traceAux);
		linhas.add("|         7 - Saldo           8 - Extrato         |");
		linhas.add(trace);
		
		whitespace = new String(new char[ (linhas.get(0).length())/2 - 4 ]).replace('\0', ' ');
		
		linhas.add(traceAux);
		linhas.add("|" + whitespace +  " Cobrar" + whitespace + "|");
		linhas.add(traceAux);
		linhas.add("|         7 - Tarifas           8 - CPMF          |");
		linhas.add(trace);
		
		
		whitespace = new String(new char[ (linhas.get(0).length())/2 - 5 ]).replace('\0', ' ');

		linhas.add(traceAux);
		linhas.add("|" + whitespace +  " 0 - Sair" + whitespace + "|");
		linhas.add(trace);
		
//		System.out.println(traceAux);
//		System.out.println(linhas.get(0));
//		System.out.println(traceAux);
////		System.out.println();
//		System.out.println(linhas.get(1));
//		System.out.println(trace);
		
		
//		System.out.println(traceAux);
//		System.out.println(linhas.get(2));
//		System.out.println(traceAux);
//		System.out.println(linhas.get(3));
//		System.out.println(trace);
		
//		System.out.println(traceAux);
//		System.out.println(linhas.get(4));
		
		for(String line : linhas)
			System.out.println(line);
	
		
	}

}
