package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.alura.loja.modelo.Projeto;
import br.com.alura.loja.modelo.ProjetoDAO;

//Quando formos querer ver o XML pelo Browser deverá ser endereçoServidor+@Path
@Path("projetos")
public class ProjetoResource {

	@Path("{id}")
    @GET
    //@Produces(MediaType.APPLICATION_XML) //Para gerar XML
   // @Produces(MediaType.APPLICATION_JSON) //Para gerar JSON
	// Adicionamos o @PathParam(), para que possamos começar a passar parametros pela URI.
    public String busca(@PathParam("id") long id) {
        Projeto projeto = new ProjetoDAO().busca(id);
     // return projeto.toXML();
        return projeto.toJson();
    }

}
