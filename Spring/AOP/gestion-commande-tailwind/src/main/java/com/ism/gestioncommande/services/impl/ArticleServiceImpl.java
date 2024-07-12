package com.ism.gestioncommande.services.impl;

import com.ism.gestioncommande.data.entities.Article;
import com.ism.gestioncommande.data.repositories.ArticleRepository;
import com.ism.gestioncommande.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public List<Article> getArticlesForCommande() {
        return articleRepository.findAllByActiveTrue();
    }

    @Override
    public void save(Article data) {

    }

    @Override
    public List<Article> getAll() {
        return null;
    }

    @Override
    public Optional<Article> show(Long dataID) {
        return articleRepository.findById(dataID);
    }
}
