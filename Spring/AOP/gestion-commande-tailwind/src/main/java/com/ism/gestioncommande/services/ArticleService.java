package com.ism.gestioncommande.services;

import com.ism.gestioncommande.data.entities.Article;

import java.util.List;

public interface ArticleService extends Service<Article,Long>{

    List<Article> getArticlesForCommande();
}
