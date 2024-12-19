package fr.fms;

import fr.fms.entities.Article;
import fr.fms.dao.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Le point d'entrée de l'application
public class SpringStockMvcApplication implements CommandLineRunner {
    @Autowired // Injecte ArticleRepository
    ArticleRepository articleRepository;

    public static void main(String[] args) {
        // Démarre l'application Spring Boot
        SpringApplication.run(SpringStockMvcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Ajoute des articles dans la base de données
        articleRepository.save(new Article(null, "Samsung S8", 250));
        articleRepository.save(new Article(null, "Samsung S9", 300));
        articleRepository.save(new Article(null, "Iphone 10", 200));
        articleRepository.save(new Article(null, "Iphone 11", 400));
        articleRepository.save(new Article(null, "Iphone 12", 450));
        articleRepository.save(new Article(null, "Iphone 13", 600));
        articleRepository.save(new Article(null, "Iphone 14", 700));
        articleRepository.save(new Article(null, "Iphone 15", 800));
        articleRepository.save(new Article(null, "Iphone 16", 1500));
        articleRepository.save(new Article(null, "Samsung s10", 500));
        articleRepository.save(new Article(null, "Samsung s24", 1500));

        // Affiche tous les articles dans la console
        articleRepository.findAll().forEach(System.out::println);
    }
}

