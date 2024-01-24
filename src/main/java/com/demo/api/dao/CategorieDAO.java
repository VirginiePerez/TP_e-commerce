package com.demo.api.dao;
import com.demo.api.EntityManagerSingleton;
import com.demo.api.modele.Categorie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import java.util.List;

public class CategorieDAO {

    private static CategorieDAO instance;
    private EntityManager entityManager;

    public static CategorieDAO getInstance() {
        if (instance == null)
            instance = new CategorieDAO();

        return instance;
    }

    private CategorieDAO() {
        entityManager = EntityManagerSingleton.getEntityManager("ecommerce");
    }

    public List<Categorie> getCategories() {
        return entityManager.createQuery("SELECT c FROM Categorie c", Categorie.class).getResultList();
    }

    public void addCategorie(Categorie categorie) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(categorie);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'enregistrement de la catégorie");
            tx.rollback();
        }
    }

    public Categorie getById(Integer categorieId) {
        return entityManager.find(Categorie.class, categorieId);
    }

    public void update(Categorie categorie) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.merge(categorie);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Erreur lors de la modification de la catégorie");
            tx.rollback();
        }
    }

    public void delete(Categorie categorie) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.remove(categorie);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression de la catégorie");
            tx.rollback();
        }
    }
}
