package cui;


import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import banco.Banco;

public class Interface {
	
	
	public static void main(String[] args) {
		
//		System.out.println("Running");
		Interface inter =  new Interface();
		Banco banco = Banco.readFile();
//		System.out.println(String.format("Informações do banco \"%s\" carregadas com sucesso.", banco.getNomeBanco()));
		inter.WriteMenu();
	}
	
	
	public void WriteMenu() {
		System.out.println("O que você deseja?\n");
		
//		System.out.println("\tAdicionar:\n 1 - Cliente\t 2 - Conta\n");
//		System.out.println("Remover:\n 3 - Cliente\t 4 - Conta\n");
//		System.out.println("Listar:\n 5 - Cliente\t 6 - Conta\n");
		
		List<String> linhas = new ArrayList<String> ();
		linhas.add("    1 - Adicionar    2 - Remover    3 - Listar    ");
		String whitespace = new String(new char[ (linhas.get(0).length() - 16)/2 ]).replace('\0', ' ');
		linhas.add( whitespace + "Cliente ou Conta" + whitespace);
		
		String trace = "";
		for (int i = 0; i <= linhas.get(0).length()  ; i++) {
			trace += "*";
		}
		
		System.out.println(trace);
		System.out.println(linhas.get(0));
//		System.out.println(trace);
		System.out.println();
		System.out.println(linhas.get(1));
		
		
		System.out.println(trace);
//		System.out.println("Remover:\n 3 - Cliente\t 4 - Conta\n");
//		System.out.println("Listar:\n 5 - Cliente\t 6 - Conta\n");
		
	}

}
