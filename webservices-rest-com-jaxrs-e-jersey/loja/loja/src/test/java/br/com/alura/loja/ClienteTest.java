package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.modelo.Projeto;
import junit.framework.Assert;

public class ClienteTest {
	
	 private HttpServer server;	 
	 
	 //Metodo que inicia o servidor, com anotação @Before: Toda vez que formos executar um teste dessa classe, ele fará o que esta dentro desse metodo, ANTES
	 @Before
	 public void startaServidor(){
		 Servidor.startaServidor();
	 }
	 
	//Metodo que derruba o servidor, com anotação @After: Toda vez que um teste dessa classe for executado, ele derrubará o servidor, DEPOIS.
	 @After
	 public void stopServidor(){
		 server.stop();
	 }
     

    //@Test
    public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
    	
    	//Cria o servidor cliente
        Client client = ClientBuilder.newClient();
        
        //Endereço do cliente
        WebTarget target = client.target("http://localhost:8080");
        
        // Conteúdo que vamos pegar, e qual path dentro do cliente vamos acessar para pegar o XML que queremos
        String conteudo = target.path("/carrinhos/1").request().get(String.class);
        System.out.println(conteudo);
        
        // XML que pegamos dentro do cliente, deserialização usando o XStream
        Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
        
        // Para teste do Junit. Ele vai verificar se o que lemos, tem esse trecho entre as aspas
        Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
    }
    
    //Atividade 4 - Testando os projetos: De maneira equivalente ao nosso Carrinho, crie um teste para nosso Projeto de id 1. 
    public void TestandoOsprojetos() {
    	
    	//Cria o servidor cliente
        Client client = ClientBuilder.newClient();
        
        //Endereço do cliente
        WebTarget target = client.target("http://localhost:8080");
        
        // Conteúdo que vamos pegar, e qual path dentro do cliente vamos acessar para pegar o XML que queremos
        //É Projetos porque tem um @Path(projetos) no ProjetoResource
        String conteudo = target.path("/projetos").request().get(String.class);
        System.out.println(conteudo);
        
        // XML que pegamos dentro do cliente, deserialização usando o XStream
        Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
        
        // Para teste do Junit. Ele vai verificar se o que lemos, tem esse trecho entre as aspas
        Assert.assertEquals(2014, projeto.getAnoDeInicio());
    }
    
    
  //Modulo 5/Ativ 5 - Criando teste para o post 
    @Test
    public void testaQueSuportaNovosCarrinhos() {
    	
    	//Cria o servidor cliente
        Client client = ClientBuilder.newClient();
        
        //Endereço do cliente
        WebTarget target = client.target("http://localhost:8080");
        
        //Criamos um carrinho e transformamos ele em XML para realizar o post
        Carrinho carrinho = new Carrinho();
        carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
        carrinho.setRua("Rua Vergueiro");
        carrinho.setCidade("Sao Paulo");
        String xml = carrinho.toXML();
        
        //Representar isso de alguma maneira:
        //Utilizaremos a classe Entity do próprio JAX-RS, para criar tal representação 
        Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);

        Response response = target.path("/carrinhos").request().post(entity);
        //Assert.assertEquals("<status>Sucesso</status>", response.readEntity(String.class));
        Assert.assertEquals(201, response.getStatus());
        String location = response.getHeaderString("Location"); 
        String conteudo = client.target(location).request().get(String.class);
        Assert.assertTrue(conteudo.contains("Microfone"));
    }
    
}
