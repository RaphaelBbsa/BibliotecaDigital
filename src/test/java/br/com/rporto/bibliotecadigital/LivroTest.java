package br.com.rporto.bibliotecadigital;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import br.com.rporto.domain.Emprestimo;
import br.com.rporto.domain.Livro;
import br.com.rporto.service.EmprestimoService;

public class LivroTest {
	
	@Test
	public void testLivroAtributos () {
		Livro livro = new Livro();
		livro.setId(1);
		livro.setTitulo("Antifrágil");
		livro.setAutor("Nassim Taleb");
		livro.setAnoPublicacao(1954);
		
		
		assertEquals(1, livro.getId());
		assertEquals("Antifrágil",livro.getTitulo());
		assertEquals("Nassim Taleb", livro.getAutor());
		assertEquals(1954L, livro.getAnoPublicacao());
		
	}
	
	@Test
	public void testLivroDisponivel () {
		Livro livro1984 = new Livro(1, "1984", "George Orwell", 1949);
		
		assertEquals(true, livro1984.IsDisponivel());
		
		livro1984.setDisponivel(false);
		
		assertEquals(false, livro1984.IsDisponivel());
		}
	
	@Test
	public void testAtraso () {
		EmprestimoService emprestimoService = new EmprestimoService();
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setDataDevolucao(LocalDate.now().minusDays(3));//3 dias de atraso
		assertTrue(emprestimoService.isAtrasado(emprestimo));
		
	}
	
	@Test
	public void CalcularMultaAtraso () {
		Emprestimo emprestimo = new Emprestimo ();
		EmprestimoService e = new EmprestimoService();
		emprestimo.setDataDevolucao(LocalDate.now().minusDays(4));//minusDays subtrai o número de dias passado, no caso são 4
		double multaEsperada = 4 * 2.0;
		double multaCalculada = e.calcularMulta(emprestimo);
		assertEquals(multaEsperada, multaCalculada, 0.001);//aqui o assert espera um delta para o ponto flutuante
		//ponto flutuantes são por exemplo: 1,75 -> veja que 0,75 faz parte do número int, logo 0,75 é um ponto flutuante
	}

}
