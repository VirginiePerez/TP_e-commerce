package com.demo.api.api;// ArticleApi.java
import com.demo.api.dao.ArticleDAO;
import com.demo.api.modele.Article;
import com.demo.api.modele.Categorie;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/articles")
public class ArticleApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getList() {
        return ArticleDAO.getInstance().getArticles();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Article article) {
        ArticleDAO.getInstance().addArticle(article);
        return Response.status(Response.Status.CREATED).entity(article).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id) {
        Article article = ArticleDAO.getInstance().getById(id);

        if (article != null) {
            return Response.status(Response.Status.OK).entity(article).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, Article updatedArticle) {
        Article existingArticle = ArticleDAO.getInstance().getById(id);

        if (existingArticle != null) {
            existingArticle.setReference(updatedArticle.getReference());
            existingArticle.setPrix(updatedArticle.getPrix());

            ArticleDAO.getInstance().update(existingArticle);

            return Response.status(Response.Status.OK).entity(existingArticle).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Integer id) {
        Article article = ArticleDAO.getInstance().getById(id);

        if (article != null) {
            ArticleDAO.getInstance().delete(article);
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
