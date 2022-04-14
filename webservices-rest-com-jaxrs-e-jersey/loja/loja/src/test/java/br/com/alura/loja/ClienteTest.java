package br.com.alura.loja;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import junit.framework.Assert;

public class ClienteTest {
	
	 private HttpServer server;	 
	 
	 //Metodo que inicia o servidor, com anota��o @Before: Toda vez que formos executar um teste dessa classe, ele far� o que esta dentro desse metodo, ANTES
	 @Before
	 public void startaServidor(){
		 startaServidor();
	 }
	 
	//Metodo que derruba o servidor, com anota��o @After: Toda vez que um teste dessa classe for executado, ele derrubar� o servidor, DEPOIS.
	 @After
	 public void stopServidor(){
		 server.stop();
	 }
     

    @Test
    public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
    	
    	//Cria o servidor cliente
        Client client = ClientBuilder.newClient();
        
        //Endere�o do cliente
        WebTarget target = client.target("http://localhost:8080");
        
        // Conte�do que vamos pegar, e qual path dentro do cliente vamos acessar para pegar o XML que queremos
        String conteudo = target.path("/carrinhos").request().get(String.class);
        System.out.println(conteudo);
        
        // XML que pegamos dentro do cliente, deserializa��o usando o XStream
        Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
        
        // Para teste do Junit. Ele vai verificar se o que lemos, tem esse trecho entre as aspas
        Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
    }
    
    
    
}
