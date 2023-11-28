package conta.controller;

import java.util.ArrayList;

import conta.model.conta;
import conta.repository.ContaRepository;

public class contaController implements ContaRepository {

	private ArrayList <conta> listaContas = new ArrayList<conta>();
	int numero = 0;
	
		
	@Override
	public void procurarPorNumero(int numero) {
		var conta = buscaNaCollection(numero);
		if(conta!=null)
			conta.visualizar();
		else
			System.out.println("\nA Conta número: "+numero+"não foi encontrada!");
	}

	@Override
	public void listarTodas() {
		for (var conta : listaContas) {
			conta.visualizar();
		}
		
	}

	@Override
	public void cadastrar(conta conta) {
		listaContas.add(conta);
		System.out.println("\nA conta número "+ conta.getNumero()+" foi criada com sucesso!");
		
	}

	@Override
	public void atualizar(conta conta) {
		var buscaConta = buscaNaCollection(conta.getNumero());
		
		if(buscaConta != null) {
			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.println("\nA Conta numero: " + conta.getAgencia() + " foi atualizada com sucesso");
			
		}else{
			System.out.println("\nA Conta numero: " + conta.getAgencia() + " Não foi encontrada");
		}
		
	}

	@Override
	public void deletar(int numero) {
		var conta = buscaNaCollection(numero);
		
		if(conta != null) {
			if(listaContas.remove(conta) == true)
				System.out.println("\nA Conta numero "+numero+" foi deletada co msucesso!");
		}else {
			System.out.println("\nA Conta numero: "+ numero+"não foi encontrada");
		}
		
	}

	@Override
	public void sacar(int numero, float valor) {
		var conta = buscaNaCollection(numero);
		if(conta != null) {
			if (conta.sacar(valor) == true) {
				System.out.println("\nO Saque na Conta numero: " + numero+"Foi efetuado com sucesso");
			}else
				System.out.println("\nA Conta: "+numero+"Não foi encontrada!");
		}
		
	}

	@Override
	public void depositar(int numero, float valor) {
		var conta = buscaNaCollection(numero);
		
		if(conta!=null) {
			conta.depositar(valor);
			System.out.println("\nO Depósito na Conta numero: "+numero+" foi efetuado com sucesso!");
		}else
			System.out.println("\nA conta numero "+numero+"não foi encontrada ou a conta destino não é conta corrente! ");
		
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		var contaOrigem = buscaNaCollection(numeroOrigem);
		var contaDestino = buscaNaCollection(numeroDestino);
		
		if(contaOrigem != null && contaDestino != null) {
			
			if(contaOrigem.sacar(valor)==true) {
				contaDestino.depositar(valor);
				System.out.println("\nA transferencia foi efetuada com sucesso! ");
				}
			}else
				System.out.println("\nA conta de Origem e/ou destino não foram encontradas! ");
		}
		public int gerarNumero() {
		return ++ numero;
	}
		
		
		public conta buscaNaCollection(int numero) {
			for(var conta : listaContas) {
				if(conta.getNumero() == numero) {
					return conta;
				}
			}
			return null;
		}

}
