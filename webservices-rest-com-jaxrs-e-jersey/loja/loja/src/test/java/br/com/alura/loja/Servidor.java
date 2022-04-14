package br.com.alura.loja;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Before;

public class Servidor {

	
	public static void main(String[] args) throws IOException {
        System.out.println("Servidor rodando");
        System.in.read();
    }
	
}
