package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.contaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {
	
	public static void main(String[] args) {
		
		contaController contas = new contaController();
		
		Scanner leia = new Scanner(System.in);
		
		int opcao,numero = 0,agencia,tipo,aniversario,numeroDestino;
		String titular;
		float saldo = 0,limite,valor;
		
		System.out.println("\nCriar Contas\n");
		
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(),123,1,"João da Silva",1000f,100.0f);
		contas.cadastrar(cc1);
		
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(),124,1,"Maria da Silva",2000f,100.0f);
		contas.cadastrar(cc1);
		
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(),125,2,"Mariana dos santos",4000f,12);
		contas.cadastrar(cp1);
		
		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(),125,2,"Juliana Ramos",8000f,12);
		contas.cadastrar(cp2);
		
		contas.listarTodas();
				
		while(true) {
			System.out.println(Cores.TEXT_WHITE + Cores.ANSI_BLUE_BACKGROUND
					+ "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                     SOULS BANK                      "); // Sociedade Unidos Pelo lucro
			System.out.println("                                                     ");
			System.out.println("******************************************1**********");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);
			
			try {
				opcao = leia.nextInt();
			}catch(InputMismatchException e){
				System.out.println("\nDigite valores inteiros!");
				leia.nextLine();
				opcao=0;
			}
							
			if (opcao == 9) {
				System.out.println(Cores.TEXT_WHITE + Cores.ANSI_BLUE_BACKGROUND+ 
		    			"*****************************************************");
				System.out.println("            SOUL BANK - Dividir para lucrar!         ");
				System.out.println("                                                     " + Cores.TEXT_RESET);
				sobre(); 
                 leia.close();
				System.exit(0);
			}
				
			switch (opcao) {
				case 1:
					System.out.println(Cores.TEXT_WHITE + "Criar Conta\n\n");
					
					System.out.println("Digite o Numero da Agência: ");
					agencia = leia.nextInt();
					System.out.println("Digite o Nome do Titular: ");
					leia.skip("\\R?");
					titular = leia.nextLine();
					
					do {
						System.out.println("Digite o tipo da conta (1-cc ou 2-cp): ");
						tipo = leia.nextInt();
					}while (tipo <1 && tipo > 2);
					
					System.out.println("Digite o Saldo da Conta(R$): ");
					saldo = leia.nextFloat();
					
					switch(tipo) {
					case 1 -> {
						System.out.println("Digite o Limite de Crédito (R$): ");
						limite = leia.nextFloat();
						contas.cadastrar(new ContaCorrente(contas.gerarNumero(),agencia,tipo,titular,saldo,limite));
						}
					case 2 -> {
							System.out.println("Digite o dia do Aniversário da Conta: ");
							aniversario = leia.nextInt();
							contas.cadastrar(new ContaPoupanca(contas.gerarNumero(),agencia,tipo,titular,saldo,aniversario));
						}
					}
					keyPress();
                    break;
				case 2:
					System.out.println(Cores.TEXT_WHITE + "Listar todas as Contas\n\n");
					contas.listarTodas();
					keyPress();
					break;
					
				case 3:
					System.out.println(Cores.TEXT_WHITE + "Consultar dados da Conta - por número\n\n");
					System.out.println("Digite o número da conta: ");
					numero = leia.nextInt();
					
					contas.procurarPorNumero(numero);
					keyPress();
                    break;
				case 4:
					System.out.println(Cores.TEXT_WHITE + "Atualizar dados da Conta\n\n");
					
					System.out.println("Digite o número da conta: ");
					numero = leia.nextInt();
					
					var buscaConta = contas.buscaNaCollection(numero);
					
					if(buscaConta != null) {
						tipo = buscaConta.getTipo();
						
						System.out.println("Digite o Numero da Agência: ");
						agencia = leia.nextInt();
						System.out.println("Digite o Nome do Titular: ");
						leia.skip("\\R?");
						titular = leia.next();
						
						switch(tipo) {
						case 1 -> {
							System.out.println("Digite o Limite de Crédito: (R$): ");
							limite = leia.nextFloat();
							
							contas.atualizar(new ContaCorrente(numero,agencia,tipo,titular,saldo,limite));
							}
						case 2 ->{
								System.out.println("Digite o dia do Aniversario daConta: ");
								aniversario = leia.nextInt();
								
								contas.atualizar(new ContaPoupanca(numero,agencia,tipo,titular,saldo,aniversario));
							}
						default ->{
							System.out.println("Tipo de conta inválido");
							}
						}
					}
					keyPress();
                    break;
				case 5:
					System.out.println(Cores.TEXT_WHITE + "Apagar a Conta\n\n");
					System.out.println("Digite o número da conta:");
					numero = leia.nextInt();
					
					contas.deletar(numero);
					keyPress();
                    break;
				case 6:
					System.out.println(Cores.TEXT_WHITE + "Saque\n\n");
					System.out.println("Digite o Numero da conta: ");
					numero = leia.nextInt();
					
					do {
						System.out.println("Digite o Valor da Saque: (R$): ");
						valor = leia.nextFloat();
					}while(valor <= 0 );
						contas.sacar(numero, valor);
						
					keyPress();
                    break;
				case 7:
					System.out.println(Cores.TEXT_WHITE + "Depósito\n\n");
					System.out.println("Digite o Valor do Depósito (R$): ");
					valor = leia.nextFloat();
					do {
						System.out.println("Digte o Valor do Depósito (R$): ");
						valor = leia.nextFloat();
					}while(valor <=0);
					contas.depositar(numero, valor);
					
					keyPress();
                    break;
				case 8:
					System.out.println(Cores.TEXT_WHITE + "Transferência entre Contas\n\n");
					System.out.println("Digite o Numero da Conta de Origem: ");
					numero = leia.nextInt();
					System.out.println("Digite o Numero da Conta de Destino ");
					numeroDestino = leia.nextInt();
					
					do {
						System.out.println("Digite o Valo da Transferencia (R$): ");
						valor = leia.nextFloat();
					}while(valor <= 0);
					keyPress();
                    break;
				default:
					System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
					keyPress();
                    break;
			}
		}	
    }    

    public static void sobre() {
    	System.out.println(Cores.TEXT_WHITE + Cores.ANSI_BLUE_BACKGROUND+ 
    			"*****************************************************");
    	
		System.out.println("Projeto Desenvolvido por:Kevin Oliveira              ");
		System.out.println("Generation Brasil - generation@generation.org        ");
		System.out.println("github.com/KievKiss/ContaBancaria                    ");
		System.out.println("***************************************************  ");
		System.out.println("                                                     " + Cores.TEXT_RESET);
   }
    public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}
    }
}