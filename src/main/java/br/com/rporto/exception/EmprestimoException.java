package br.com.rporto.exception;

public class EmprestimoException extends Exception {
	public EmprestimoException(String mensagem) {
		super(mensagem); //herda esse super da classe exception, recebendo como parâmetro minha mensagem
	}
}
