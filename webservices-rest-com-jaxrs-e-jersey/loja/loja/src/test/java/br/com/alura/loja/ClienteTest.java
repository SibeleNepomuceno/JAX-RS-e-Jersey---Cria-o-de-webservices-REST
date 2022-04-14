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
	
	@Before
    public void before() {
        ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
        URI uri = URI.create("http://localhost:8080/");
        this.server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
    }

    @After
    public void mataServidor() {
        server.stop();
    }

    @Test
    public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
    	
    	//Cria o servidor cliente
        Client client = ClientBuilder.newClient();
        
        //Endereço do cliente
        WebTarget target = client.target("http://localhost:8080");
        
        // Conteúdo que vamos pegar, e qual path dentro do cliente vamos acessar para pegar o XML que queremos
        String conteudo = target.path("/carrinhos").request().get(String.class);
        
       /* // XML que pegamos dentro do cliente, serialização/deserialização usando o XStream
        Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
        
        // Para teste do Junit. Ele vai verificar se o que lemos, tem esse trecho entre as aspas
        Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());*/
    }
}
