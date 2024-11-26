package br.com.rporto.bibliotecadigital;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.rporto.domain.Cliente;

public class ClienteTest {
	
	@Test
	public void testCliente() {
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		cliente.setEmail("raphael@gmail.com");
		cliente.setNome("Raphael");
		cliente.setTelefone("21969197792");
		
		assertEquals(Long.valueOf(1L), cliente.getId());
		assertEquals("raphael@gmail.com", cliente.getEmail());
		assertEquals("Raphael", cliente.getNome());
		assertEquals("21969197792", cliente.getTelefone());
	}
}
