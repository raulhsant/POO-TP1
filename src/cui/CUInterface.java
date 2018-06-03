package cui;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import banco.Banco;
import banco.Cliente;
import banco.Conta;
import banco.Movimentacao;

public class CUInterface {
	
	private static Banco banco;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
//		System.out.println("Running");
		CUInterface cui =  new CUInterface();
		banco = Banco.readFile();
		int selectedOption;
		Boolean close =  false;
		
		Scanner in = new Scanner(System.in);
		
		
		while (!close) {
			
			cui.writeMenu();
			
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
					System.out.println("\tAté logo!");
					break;
					
				case 1:
					cui.writeClientAccountMenu("Adicionar", 1);
					try {
						System.in.read();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
					
				case 2:
					cui.writeClientAccountMenu("Remover", 2);
					try {
						System.in.read();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				
				case 3:
					cui.writeClientAccountMenu("Listar", 3);
					try {
						System.in.read();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				
				case 4:
					cui.writeExecuteMenu("Dep");
					try {
						System.in.read();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;	
					
				case 5:
					cui.writeExecuteMenu("Saq");
					try {
						System.in.read();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
					
				case 6:
					cui.writeExecuteMenu("Transf");
					try {
						System.in.read();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
					
				case 7:
					cui.writeSaldoMenu();
					
					try {
						System.in.read();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					break;
					
				case 8:
					try {
						cui.writeExtractMenu();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("Error ocnverting date!");
					}
					
					try {
						System.in.read();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				
				case 9:
					banco.bankRatePayment();
					System.out.println("\nTarifas cobradas com sucesso!\nPressione ENTER para continuar.");
					
					try {
						System.in.read();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
					
				case 10:
					
					banco.CpmfPayment();
					System.out.println("\nCPMF cobrado com sucesso!\nPressione ENTER para continuar.");
					
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
	
	
	
	@SuppressWarnings("resource")
	private void writeExtractMenu() throws ParseException {
		Scanner in = new Scanner(System.in);
		int selectedOption,contaId;
		
		GregorianCalendar startDate, endDate;
		
		DateFormat newFormatter = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("\nConsulta de Extrato.");
		System.out.println("\nVocê deseja consultar o extrato: \n");
		System.out.println("1 - Do mês\n2 - A partir de uma data\n3 - De um período Específico\n0 - Voltar");
		System.out.printf("\nSua opção: ");
		
		selectedOption = in.nextInt();
		String trace = new String(new char[ 52 ]).replace('\0', '-');
		
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
				System.out.println("\nPressione ENTER para continuar");
				break;
				
			case 1:
				
				System.out.printf("\nExtrato do mês.\n Conta nº: ");
				contaId = in.nextInt();
						
				System.out.println("");
				System.out.println(trace);
				System.out.println(String.format("Extrato do mês da conta %d", contaId));
								
				for (Movimentacao mov : banco.accountExtract(contaId)) {
					System.out.println(trace);
					
					DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
					
					System.out.printf("Data da movimentação: ");
					System.out.println(formatter.format(mov.getDataMov().getTime()));
					
					System.out.printf("\nDescrição: \n   ");
					System.out.println(mov.getDescricao());
					System.out.printf("\nTipo de movimentação: %c \t Valor: %.2f\n", mov.getDebitoCredito(), mov.getValor());
					
				}
				System.out.println(trace);
				System.out.printf("\tSaldo atual: %.2f\n", banco.accountBalance(contaId));
				System.out.println(trace);
				System.out.println("\nPressione ENTER para continuar");
				break;
				
			case 2:
				
				in.nextLine();
				System.out.printf("\nExtrato a Partir de uma data.\n A partir de (dd/mm/yyyy): ");
				String data = in.nextLine();	
				
				System.out.printf("\nExtrato a partir de %s da conta: ", data);
				contaId= in.nextInt();
				
				System.out.println("");
				System.out.println(trace);
				System.out.println(String.format("Extrato da conta %d a partir de %s", contaId, data));
				
				startDate =  new GregorianCalendar();
			
		
				Date date = (Date)newFormatter.parse(data);
				
				startDate.setTime(date);
										
				for (Movimentacao mov : banco.accountExtract(contaId, startDate)) {
					System.out.println(trace);
					
					DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
					
					System.out.printf("Data da movimentação: ");
					System.out.println(formatter.format(mov.getDataMov().getTime()));
					
					System.out.printf("\nDescrição: \n   ");
					System.out.println(mov.getDescricao());
					System.out.printf("\nTipo de movimentação: %c \t Valor: %.2f\n", mov.getDebitoCredito(), mov.getValor());
					
				}
				System.out.println(trace);
				System.out.printf("\tSaldo atual: %.2f\n", banco.accountBalance(contaId));
				System.out.println(trace);
				System.out.println("\nPressione ENTER para continuar");
				break;
				
			case 3:
				
				in.nextLine();
				System.out.printf("\nExtrato de um período específico.\n\nA partir de (dd/mm/yyyy): ");
				String dataIn = in.nextLine();	
				
				System.out.printf("Até (dd/mm/yyyy): ");
				String dataFin = in.nextLine();	
				
				System.out.printf("\nExtrato de %s a %s da conta: ", dataIn, dataFin);
				contaId= in.nextInt();
				
				System.out.println("");
				System.out.println(trace);
				System.out.println(String.format("Extrato da conta %d de %s a %s", contaId, dataIn, dataFin));
				
				startDate =  new GregorianCalendar();
				endDate = new GregorianCalendar();
				
				Date dateAux = (Date)newFormatter.parse(dataIn);
				startDate.setTime(dateAux);
				dateAux = (Date)newFormatter.parse(dataFin);
				endDate.setTime(dateAux);
								
				for (Movimentacao mov : banco.accountExtract(contaId, startDate, endDate)) {
					System.out.println(trace);
					
					DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
					
					System.out.printf("Data da movimentação: ");
					System.out.println(formatter.format(mov.getDataMov().getTime()));
					
					System.out.printf("\nDescrição: \n   ");
					System.out.println(mov.getDescricao());
					System.out.printf("\nTipo de movimentação: %c \t Valor: %.2f\n", mov.getDebitoCredito(), mov.getValor());
					
				}
				System.out.println(trace);
				System.out.printf("\tSaldo atual: %.2f\n", banco.accountBalance(contaId));
				System.out.println(trace);
				System.out.println("\nPressione ENTER para continuar");
				
				break;
				
		}

		
		
	}



	@SuppressWarnings("resource")
	private void writeSaldoMenu() {
		Scanner in = new Scanner(System.in);
		System.out.println("\nConsulta de Saldo.");
		System.out.printf("Conta nº: ");
		int contaId = in.nextInt();
		
		System.out.printf("\nA conta %d tem um saldo de %.2f\n",contaId, banco.accountBalance(contaId));
		
		System.out.println("Pressione ENTER para continuar");
	}

	
	@SuppressWarnings("resource")
	private void writeExecuteMenu(String method) {
		Scanner in = new Scanner(System.in);
		
		if (method.equals("Dep")) {
			
			System.out.println("\nEm qual conta e de quanto é o depósito?");
			System.out.printf("Conta nº: ");
			
			int contaId = in.nextInt();
			System.out.printf("Valor do depósito (xx,xx): ");
			double valor = in.nextDouble();
			
			System.out.println("");
			
			banco.makeDeposit(contaId, valor);

			System.out.println("Pressione ENTER para continuar");			
			
		} else if (method.equals("Saq")) {
			
			System.out.println("\nDe qual conta e de quanto é o saque?");
			System.out.printf("Conta nº: ");
			
			int contaId = in.nextInt();
			System.out.printf("Valor do saque (xx,xx): ");
			double valor = in.nextDouble();
			
			System.out.println("");
			
			banco.makeWithdraw(contaId, valor);

			System.out.println("Pressione ENTER para continuar");
			
		}else if (method.equals("Transf")) {
			
			System.out.println("\nDe qual conta, para qual conta e de quanto é a transferência?");
			
			System.out.printf("De conta nº: ");
			int contaFromId = in.nextInt();
			
			System.out.printf("Para conta nº: ");
			int contaToId = in.nextInt();
			
			System.out.printf("Valor(xx,xx): ");
			double valor = in.nextDouble();
			
			System.out.println("");
			
			banco.makeTransfer(contaFromId, contaToId, valor);
			
			System.out.println("Pressione ENTER para continuar");
			
		}else {
			System.out.println("\nMétodo não identificado!\n");
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	@SuppressWarnings("resource")
	private void writeClientAccountMenu(String action, int whatToDo) {

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
				
				System.out.println(String.format("\nCliente %s adicionado com sucesso. \nPressione ENTER para continuar", nome));
//				in.nextLine();
				
			} else if (whatToDo == 2) {
				//asks for cpf to delete
				System.out.println("\nFavor digitar os CPF/CNPJ do Cliente a ser removido.");
				in.nextLine();
				System.out.printf("CPF/CNPJ: ");
				String cpf = in.nextLine();
				
				Boolean removed = banco.removeClient(cpf);
				
				if(removed == false)
					System.out.println("\nCliente não encontrado ou não pode ser removido.\nPressione ENTER para continuar");
				else
					System.out.println("\nCliente removido com sucesso.\nPressione ENTER para continuar");
				
			} else if (whatToDo == 3) {
				//asks for nothing, shows list
				List<Cliente> clientList = banco.getClientes();
				String trace = new String(new char[ 42 ]).replace('\0', '-');
				
				
				System.out.print("");
				System.out.println(trace);
				System.out.println(String.format("Clientes do banco %s", banco.getNomeBanco()));
								
				for (Cliente client : clientList) {
					System.out.println(trace);

					System.out.printf("Nome: %s \t CPF/CNPJ: %s\n", client.getNomeCliente(), client.getCpfCnpj());
					System.out.printf("Endereço: %s\n", client.getEndereco());
					System.out.printf("Telefone: %s\n", client.getFone());
				}
				
				System.out.println(trace);
				System.out.println("\nPressione ENTER para continuar");
			}
			
		} else if (selectedOption == 2) {
			if(whatToDo == 1) {
				//asks for data to add
			System.out.println("\nFavor digitar os CPF/CNPJ do Cliente para o qual será criada a conta");
			in.nextLine();
			System.out.printf("CPF/CNPJ: ");
			String cpf = in.nextLine();
			
			List <Cliente> clientList = banco.getClientes();
			
			for(Cliente client : clientList) {
				if(client.getCpfCnpj().equals(cpf)) {
					banco.newAccount(client);
					System.out.printf("\nNova conta criada para o cliente %s\n", client.getNomeCliente());
				}
			}
			
			System.out.println("Pressione ENTER para continuar.");
			
			} else if (whatToDo == 2) {
				//asks for data to delete
				
			System.out.println("\nFavor digitar o numero da Conta a ser removida.");
			in.nextLine();
			System.out.printf("Conta nº: ");
			Integer id = in.nextInt();
				
			banco.removeAccount(id);
			
			System.out.printf("Conta removida com sucesso\n");
			
			System.out.println("Pressione ENTER para continuar.");
			} else if (whatToDo == 3) {
				//asks for nothing, shows list
				
			List<Conta> contaList = banco.getContas();
			String trace = new String(new char[ 42 ]).replace('\0', '-');
						
			System.out.print("");
			System.out.println(trace);
			System.out.println(String.format("Contas do banco %s", banco.getNomeBanco()));
							
			for (Conta conta : contaList) {
				System.out.println(trace);

				System.out.printf("Conta nº: %d    Saldo: %.2f\n", conta.getNumConta(), conta.getSaldo());
				System.out.printf("Cliente: %s\n", conta.getCliente().getCpfCnpj());
				System.out.printf("Número de Movimentações: %d\n", conta.getMovimentacoes().size());
			}
			
			System.out.println(trace);
			System.out.println("\nPressione ENTER para continuar");
				
			}
		} else if(selectedOption == 0) {			
			System.out.println("\nPressione ENTER para continuar");
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


	private void writeMenu() {
		System.out.println(new String(new char[ 55 ]).replace('\0', '_'));
		System.out.println("O que você deseja?\n");
		
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
		linhas.add("|        9 - Tarifas           10 - CPMF          |");
		linhas.add(trace);
		
		
		whitespace = new String(new char[ (linhas.get(0).length())/2 - 15 ]).replace('\0', ' ');

		linhas.add(traceAux);
		linhas.add("|" + whitespace +  " 0 - Sair e Salvar Alterações" + whitespace + "|");
		linhas.add(trace);
		
		
		for(String line : linhas)
			System.out.println(line);
	}

}
