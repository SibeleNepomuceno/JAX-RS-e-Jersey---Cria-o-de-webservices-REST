package br.com.alura.loja;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Before;

public class Servidor {

	//Comandos de iniciação de servidor. Poderemos chama-lo em outras classes, quando quisermos startar o servidor
	/*public static void startaServidor(){
		 ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
	        URI uri = URI.create("http://localhost:8080/");
	        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
	}*/
	
	 
	
	public static void main(String[] args) throws IOException {
		HttpServer server = startaServidor();
	        System.out.println("Servidor rodando");
	        System.in.read();
	        server.stop();
	        System.out.println("Servidor PAROU");
    }
	
	static HttpServer startaServidor(){
		 ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
	        URI uri = URI.create("http://localhost:8080/");
	        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
	        return server;
	}
	
}
