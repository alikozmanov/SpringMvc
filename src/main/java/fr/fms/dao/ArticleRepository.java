package fr.fms.dao;

import fr.fms.entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;


public interface ArticleRepository extends JpaRepository<Article, Long> {
    // Une méthode pour rechercher des articles par description (avec pagination)
    Page<Article> findByDescriptionContains(String description , Pageable pageable);
}
