package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

import junit.framework.Assert;



public class ClienteTest {
	
	@Test
	public void testaQueAConexaoComOServidorFunciona() {
		
		Client client = ClientBuilder.newClient();
		
		//Dizemos ao nosso cliente que trabalharemos com o alvo http://www.mocky.io
		WebTarget target = client.target("http://www.mocky.io");
		
		//Requisição básica, que pega dados do servidor, usando o método get:
		String conteudo = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);
		
		//Quero agr ter certeza que o xml que pegamos tem o conteúdo 'Rua Vergueiro 3185' <rua>
		Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185"));
		
	}
}
