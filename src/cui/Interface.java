package cui;


import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import banco.Banco;
import banco.Cliente;

public class Interface {
	
	private static Banco banco;
	
	public static void main(String[] args) {
		
//		System.out.println("Running");
		Interface inter =  new Interface();
		banco = Banco.readFile();
		int selectedOption;
		Boolean close =  false;
		
		Scanner in = new Scanner(System.in);
		
		
		while (!close) {
	//		System.out.println(String.format("Informações do banco \"%s\" carregadas com sucesso.", banco.getNomeBanco()));
			inter.WriteMenu();
			
			System.out.printf("\nDigite o número da atividade desejada: ");
			
			selectedOption = in.nextInt();
			
			switch(selectedOption) {
				default:
					System.out.println("Não foi possível identificar a opção digitada. Pressione ENTER e tente novamente.");
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
					System.out.println("\tAté logo!");
				break;
					
				case 1:
					inter.WriteClientAccountMenu("Adicionar", 1);
					try {
						System.in.read();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				break;
					
				case 2:
					inter.WriteClientAccountMenu("Remover", 2);
					try {
						System.in.read();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				break;
				
				case 3:
					inter.WriteClientAccountMenu("Listar", 3);
					try {
						System.in.read();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				break;
					

			}
			
		}

	}
	
	
	private void WriteClientAccountMenu(String action, int whatToDo) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int selectedOption;
		
		System.out.println(String.format("\nVocê deseja %s: ", action));
		System.out.println("1 - Cliente\n2 - Conta\n0 - Voltar");
		
		selectedOption = in.nextInt();
		
		if(selectedOption == 1) {
			if(whatToDo == 1) {
				//asks for data to add
				System.out.println("\nFavor digitar os dados do Cliente a ser adicionado.");
				
				in.nextLine();
				
				System.out.printf("Nome: ");
				String nome = in.nextLine();
				
				System.out.printf("CPF/CNPJ: ");
				String cpf = in.nextLine();
				
				System.out.printf("Endereço: ");
				String end = in.nextLine();
				
				System.out.printf("Telefone: ");
				String tel = in.nextLine();
				
				Cliente client = new Cliente(nome, cpf, end, tel);
				
				banco.addClient(client);		
				
				System.out.println(String.format("Cliente %s adicionado com sucesso. \nPressione ENTER para continuar", nome));
				in.nextLine();
				
			} else if (whatToDo == 2) {
				//asks for cpf to delete
				System.out.println("\nFavor digitar os CPF/CNPJ do Cliente a ser removido.");
				in.nextLine();
				System.out.printf("CPF/CNPJ: ");
				String cpf = in.nextLine();
			
//				List<Cliente> clientList = banco.getClientes();
				
				Boolean removed = banco.removeClient(cpf);
				
//				for (Cliente client : clientList) {
//					if(client.getCpfCnpj().equals(cpf))
//						System.out.println(String.format("Cliente %s removido com sucesso. \nPressione ENTER para continuar", client.getNomeCliente()));
////						else {
////							System.out.printf("Deu ruim %s\n", client.getCpfCnpj());
////						}	
//				}
				if(removed == false)
					System.out.println("Cliente não encontrado ou não pode ser removido.\nPressione ENTER para continuar");
				else
					System.out.println("Cliente removido com sucesso.\nPressione ENTER para continuar");
				in.nextLine();
				
			} else if (whatToDo == 3) {
				//asks for nothing, shows list
				List<Cliente> clientList = banco.getClientes();
				System.out.println(String.format("\nClientes do banco %s", banco.getNomeBanco()));
				
				String trace = new String(new char[ 32 ]).replace('\0', '-');
				
				for (Cliente client : clientList) {
					System.out.println(trace);
					System.out.printf("Nome: %s \t CPF/CNPJ: %s\n", client.getNomeCliente(), client.getCpfCnpj());
					System.out.printf("Endereço: %s\n", client.getEndereco());
					System.out.printf("Telefone: %s\n", client.getFone());
				}
				
				System.out.println(trace);
				System.out.println("Pressione ENTER para continuar");
				in.nextLine();
			}
			
		} else if (selectedOption == 2) {
			if(whatToDo == 1) {
				//asks for data to add
			} else if (whatToDo == 2) {
				//asks for ? to delete
			} else if (whatToDo == 3) {
				//asks for nothing, shows list
			}
		} else if(selectedOption == 0) {
			
			System.out.println("Pressione ENTER para continuar");
			in.nextLine();
		} else {
			System.out.println("Não foi possível identificar a opção digitada, tente novamente.");
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


	public void WriteMenu() {
		System.out.println(new String(new char[ 55 ]).replace('\0', '_'));
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
