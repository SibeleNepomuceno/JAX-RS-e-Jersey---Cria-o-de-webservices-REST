package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

@Path("carrinhos")
public class CarrinhoResource {
	@Path("{id}")
    @GET
    //No get eu devolvo alguma coisa
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
    // No caso do post, eu consumo alguma coisa
 //   @Produces(MediaType.APPLICATION_XML)
  // @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_XML)
    public Response adiciona(String conteudo) {
    	
    	//Para XML
        Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
        new CarrinhoDAO().adiciona(carrinho);
        
        //Para Json
       //Fazer depois
    	
       // return "<status>Sucesso</status>"; nao precisamos disso, pq o status code ja retorna o resultado
        URI uri = URI.create("/carrinhos/"+ carrinho.getId()); 
       return Response.created(uri).build();//Para o return ser o resultado do status code
    }
    
  //Metodo de exclusão
    @Path("{id}/produtos/{produtoId}")
    @DELETE
    public Response removeProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId) {
    	 Carrinho carrinho = new CarrinhoDAO().busca(id);
         carrinho.remove(produtoId); // chamar metodo do banco que exluirá
    	return Response.ok().build();

    }
    
  //Metodo de atualização
    //ALTERA APENAS 1 PARAMETRO DO PRODUTO
    @Path("{id}/produtos/{produtoId}/quantidade")
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response alteraProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId, String conteudo) {
        Carrinho carrinho = new CarrinhoDAO().busca(id);
        Produto produto = (Produto) new XStream().fromXML(conteudo);//O que enviaremos para alterar
        carrinho.trocaQuantidade(produto);
        return Response.ok().build();
    }
 //Metodo de atualização
    //ALTERA O PRODUTO INTEIRO
    @Path("{id}/produtos/{produtoId}")
    @PUT
    public Response alteraProdutoInteiro(@PathParam("id") long id,
            @PathParam("produtoId") long produtoId, String conteudo) {
        Carrinho carrinho = new CarrinhoDAO().busca(id);
        Produto produto = (Produto) new XStream().fromXML(conteudo);
        carrinho.troca(produto);
        return Response.ok().build();
    }
}
