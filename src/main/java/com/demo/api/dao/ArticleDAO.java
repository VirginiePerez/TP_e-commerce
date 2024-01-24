package com.demo.api.dao;

import com.demo.api.modele.Article;
import com.demo.api.EntityManagerSingleton;
import com.demo.api.modele.Categorie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;

public class ArticleDAO {

    private static ArticleDAO instance;
    private EntityManager entityManager;

    public static ArticleDAO getInstance() {
        if (instance == null)
            instance = new ArticleDAO();

        return instance;
    }

    private ArticleDAO() {
        entityManager = EntityManagerSingleton.getEntityManager("e-commerce");
    }

    public List<Article> getArticles() {
        return entityManager.createQuery("SELECT a FROM Article a", Article.class).getResultList();
    }

    public void addArticle(Article a) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(a);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'enregistrement de l'article");
            tx.rollback();
        }
    }

    public Article getById(Integer articleId) {
        return entityManager.find(Article.class, articleId);
    }

    public void save(Article article) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(article);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'enregistrement de l'article");
            tx.rollback();
        }
    }

    public Article findById(Integer id) {
        return entityManager.find(Article.class, id);
    }

    public List<Article> findAll() {
        Query query = entityManager.createQuery("SELECT a FROM Article a", Article.class);
        return query.getResultList();
    }

    public void delete(Article article) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.remove(article);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression de l'article");
            tx.rollback();
        }
    }

    public void deleteById(Integer id) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            Query query = entityManager.createQuery("DELETE FROM Article a WHERE a.id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression de l'article par ID");
            tx.rollback();
        }
    }

    public void update(Article article) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.merge(article);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Erreur lors de la modification de l'article");
            tx.rollback();
        }
    }
}
