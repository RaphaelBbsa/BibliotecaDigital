package br.com.rporto.service;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import br.com.rporto.domain.Cliente;
import br.com.rporto.domain.Emprestimo;
import br.com.rporto.domain.Livro;
import br.com.rporto.exception.EmprestimoException;

public class EmprestimoService {
	
	private static final double MultaDiaria = 2.0;
	
	public boolean isAtrasado (Emprestimo emprestimo) {
		return LocalDate.now().isAfter(emprestimo.getDataDevolucao());//verificando se minha data do dia é depois da data de devolução
	}
	
	public double calcularMulta(Emprestimo emprestimo) {
		if(isAtrasado(emprestimo)) {
			long diasAtraso = ChronoUnit.DAYS.between(emprestimo.getDataDevolucao(), LocalDate.now());
			return diasAtraso * MultaDiaria;
		}
		return 0.0;
	}
	
	private static final AtomicLong idCounter = new AtomicLong();
	//classe AtomicLong, uma classe que garante que outra thread não vá ter o mesmo ID que o outro, como um organizador de filas
	//é eficiente para manipular filas virtuais por exemplos, onde as pessoas não podem ter o mesmo id
	//chama-se atômicas, pois são operações que não podem ser alteradas
	public Emprestimo registrarEmprestimo(Cliente cliente, Livro livro) throws EmprestimoException{
		if(livro.IsDisponivel()) {
			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setId(idCounter.getAndIncrement());
			emprestimo.setCliente(cliente);
			emprestimo.setLivro(livro);;
			emprestimo.setDataEmprestimo(LocalDate.now());
			emprestimo.setDataDevolucao(LocalDate.now().plusWeeks(2));
			
			livro.setDisponivel(false); //marcando o livro como indisponível após o empréstimo
			
			return emprestimo;
			
		}else {
			throw new EmprestimoException("O livro está indisponível.");
		}
	}
	
	public Optional<Emprestimo> verificarAtraso(Emprestimo emprestimo){
		if(LocalDate.now().isAfter(emprestimo.getDataDevolucao())){//aqui verifica a data atual com a de devolução {
			return Optional.of(emprestimo); //aqui, ele retorna um optional contendo o objeto Emprestimo
		}
		return Optional.empty();//se não tiver nada, retorna o optional vazio
	}
}
