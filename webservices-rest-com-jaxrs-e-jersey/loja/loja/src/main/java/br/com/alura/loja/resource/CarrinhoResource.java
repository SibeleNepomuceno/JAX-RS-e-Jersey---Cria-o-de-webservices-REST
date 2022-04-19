package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;

@Path("carrinhos")
public class CarrinhoResource {
	@Path("{id}")
    @GET
    //@Produces(MediaType.APPLICATION_XML)
   // @Produces(MediaType.APPLICATION_JSON)
	// Adicionamos o @PathParam(), para que possamos começar a passar parametros pela URI.
    public String busca(@PathParam("id") long id) {
        Carrinho carrinho = new CarrinhoDAO().busca(id);
       return carrinho.toXML();
      // return carrinho.toJson();
    }
	
	//Metodo de adicionar
    @POST
    @Produces(MediaType.APPLICATION_XML)
  // @Produces(MediaType.APPLICATION_JSON)
    public String adiciona(String conteudo) {
    	
    	//Para XML
        Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
        new CarrinhoDAO().adiciona(carrinho);
        
        //Para Json
       //Fazer depois
    	
       return "<status>Sucesso</status>";
    }
}
