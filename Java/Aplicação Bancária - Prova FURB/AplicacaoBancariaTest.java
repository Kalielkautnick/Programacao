//KALIEL KAUTNICK

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AplicacaoBancariaTest {

	@Test
	void testeSaqueEspecialMaiorQueSaldoTotalDoCliente_naoDeveSacarDeNenhumaConta() {
		Pessoa p = new Pessoa("789-0", "Kaliel Kautnick");
		
		Conta c = new Conta("1234");
		Conta c1 = new Conta("12345");
		Conta c2 = new Conta("123456");
		Conta c3 = new Conta("1234567");
		Conta c4 = new Conta("12345678");
		
		p.criarConta(c);
		p.criarConta(c1);
		p.criarConta(c2);
		p.criarConta(c3);
		p.criarConta(c4);
		
		c.depositar(500);
		c1.depositar(500);
		c2.depositar(500);
		c3.depositar(500);
		c4.depositar(500);
		
		//IREI TENTAR SACAR 5000 USANDO O SAQUE ESPECIAL, MAS COMO O SALDO GERAL É 2500, ELE NÃO PODE SACAR
		//DE NENHUMA CONTA, LOGO, EU ESPERO QUE TODAS AS CONTAS CONTINUEM COM SALDO DE 500.
		p.saqueEspecialCliente(2400);
		assertEquals(0.0, c.getSaldo());
		assertEquals(0.0, c1.getSaldo());
		assertEquals(0.0, c2.getSaldo());
		assertEquals(0.0, c3.getSaldo());
		assertEquals(100.0, c4.getSaldo());
	}
	
	@Test
	void testeSaqueEspecial() {
		Pessoa p = new Pessoa("789-0", "Kaliel Kautnick");
		
		Conta c = new Conta("1234");
		Conta c1 = new Conta("12345");
		Conta c2 = new Conta("123456");
		Conta c3 = new Conta("1234567");
		Conta c4 = new Conta("12345678");
		
		p.criarConta(c);
		p.criarConta(c1);
		p.criarConta(c2);
		p.criarConta(c3);
		p.criarConta(c4);
		
		c.depositar(500.0);
		c1.depositar(500.0);
		c2.depositar(500.0);
		c3.depositar(500.0);
		c4.depositar(500.0);
		
		//IREI TENTAR SACAR 5000 USANDO O SAQUE ESPECIAL, MAS COMO O SALDO GERAL É 2500, ELE NÃO PODE SACAR
		//DE NENHUMA CONTA, LOGO, EU ESPERO QUE TODAS AS CONTAS CONTINUEM COM SALDO DE 500.
		p.saqueEspecialCliente(2300.0);
		assertEquals(200.0, c4.getSaldo());
		
	}
	
	@Test
	void testeTransferirDeUmaContaParaAOutra_EsperadoQueOSaldoResultanteDasDuasSeja250_E750_respectivamente() {
		Pessoa p = new Pessoa("789-0", "Kaliel Kautnick");

		Conta c = new Conta("1234");
		Conta c1 = new Conta("12345");
		
		p.criarConta(c);
		p.criarConta(c1);
		
		//DEPOSITO 500 NAS DUAS CONTAS,E IREI TRANSFERIR DA CONTA C 250 DA CONTA C PARA A CONTA C1
		c.depositar(500.0);
		c1.depositar(500.0);
		
		//IREI TENTAR SACAR 5000 USANDO O SAQUE ESPECIAL, MAS COMO O SALDO GERAL É 2500, ELE NÃO PODE SACAR
		//DE NENHUMA CONTA, LOGO, EU ESPERO QUE TODAS AS CONTAS CONTINUEM COM SALDO DE 500.
		p.transferir("1234", "12345", 500.0);
		assertEquals(0.0, c.getSaldo());
		assertEquals(1000.0, c1.getSaldo());
		
	}
	
	@Test
	void testeSaldoGeralCliente() {
		Pessoa p = new Pessoa("789-0", "Kaliel Kautnick");

		Conta c = new Conta("1234");
		Conta c1 = new Conta("12345");
		
		p.criarConta(c);
		p.criarConta(c1);
		
		//DEPOSITO 500 NAS DUAS CONTAS,E IREI TRANSFERIR DA CONTA C 250 DA CONTA C PARA A CONTA C1
		c.depositar(500.0);
		c1.depositar(501.0);
		
		c1.sacar(100.0);
		
		//IREI TENTAR SACAR 5000 USANDO O SAQUE ESPECIAL, MAS COMO O SALDO GERAL É 2500, ELE NÃO PODE SACAR
		//DE NENHUMA CONTA, LOGO, EU ESPERO QUE TODAS AS CONTAS CONTINUEM COM SALDO DE 500.
		
		assertEquals(p.getContas().get(0), c);
		assertEquals(901.0, p.getSaldoGeralCliente());
		
	}
	
	@Test
	void testeSacar() {
		Pessoa p = new Pessoa("789-0", "Kaliel Kautnick");
		
		Conta c = new Conta("1234");
		Conta c1 = new Conta("12345");
		
		p.criarConta(c);
		p.criarConta(c1);
		
		//DEPOSITO 500 NAS DUAS CONTAS,E IREI TRANSFERIR DA CONTA C 250 DA CONTA C PARA A CONTA C1
		c.depositar(500.0);
		c1.depositar(500.0);
		
		c.sacar(100.0);
		c1.sacar(200.0);
		
		//IREI TENTAR SACAR 5000 USANDO O SAQUE ESPECIAL, MAS COMO O SALDO GERAL É 2500, ELE NÃO PODE SACAR
		//DE NENHUMA CONTA, LOGO, EU ESPERO QUE TODAS AS CONTAS CONTINUEM COM SALDO DE 500.
		assertEquals(400.0, c.getSaldo());
		assertEquals(300.0, c1.getSaldo());
		
	}
	
	@Test
	void testeGetSaldoConta() {
		Pessoa p = new Pessoa("789-0", "Kaliel Kautnick");
		
		Conta c = new Conta("1234");
		Conta c1 = new Conta("12345");
		
		p.criarConta(c);
		p.criarConta(c1);
		
		//DEPOSITO 500 NAS DUAS CONTAS,E IREI TRANSFERIR DA CONTA C 250 DA CONTA C PARA A CONTA C1
		c.depositar(500.0);
		c1.depositar(500.0);
		
		//IREI TENTAR SACAR 5000 USANDO O SAQUE ESPECIAL, MAS COMO O SALDO GERAL É 2500, ELE NÃO PODE SACAR
		//DE NENHUMA CONTA, LOGO, EU ESPERO QUE TODAS AS CONTAS CONTINUEM COM SALDO DE 500.
		assertEquals(500.0, p.getSaldoConta("1234"));
		assertEquals(500.0, p.getSaldoConta("12345"));
		
	}

}
