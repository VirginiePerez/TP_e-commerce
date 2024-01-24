package com.demo.api.api;
import com.demo.api.dao.CategorieDAO;
import com.demo.api.modele.Categorie;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/categories")
public class CategorieApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Categorie> getCategories() {
        return CategorieDAO.getInstance().getCategories();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Categorie categorie) {
        CategorieDAO.getInstance().addCategorie(categorie);
        return Response.status(Response.Status.CREATED).entity(categorie).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id) {
        Categorie categorie = CategorieDAO.getInstance().getById(id);

        if (categorie != null) {
            return Response.status(Response.Status.OK).entity(categorie).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, Categorie updatedCategorie) {
        Categorie existingCategorie = CategorieDAO.getInstance().getById(id);

        if (existingCategorie != null) {
            existingCategorie.setNom(updatedCategorie.getNom());
            CategorieDAO.getInstance().update(existingCategorie);

            return Response.status(Response.Status.OK).entity(existingCategorie).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Integer id) {
        Categorie categorie = CategorieDAO.getInstance().getById(id);

        if (categorie != null) {
            CategorieDAO.getInstance().delete(categorie);
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
