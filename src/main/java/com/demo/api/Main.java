package com.demo.api;

import com.demo.api.dao.ArticleDAO;
import com.demo.api.modele.Article;

public class Main {

    public static void main(String[] args) {
       Article a = ArticleDAO.getInstance().getById(5);
        System.out.println(a.getCategorie());
    }
}
