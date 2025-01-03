package fr.fms;

import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication // Le point d'entrée de l'application
public class SpringStockMvcApplication implements CommandLineRunner {
    @Autowired // Injecte ArticleRepository
    ArticleRepository articleRepository;

    @Autowired // Injecte
    CategoryRepository categoryRepository;

    public static void main(String[] args) {
        // Démarre l'application Spring Boot
        SpringApplication.run(SpringStockMvcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        generateDatas();

    }

    private void generateDatas() {
        Category smartphone = categoryRepository.save(new Category(0, "Smartphone", "Tel mobile", null));
        Category pc = categoryRepository.save(new Category(0, "Ordinateur", "PC & Laptop", null));
        Category tablet = categoryRepository.save(new Category(0, "Tablette", " ", null));
        Category printer = categoryRepository.save(new Category(0, "Imprimante", " ", null));


        articleRepository.save(new Article(0, "Samsung", "S8", 250, smartphone));
        articleRepository.save(new Article(0, "Samsung", "S9", 300, smartphone));
        articleRepository.save(new Article(0, "Iphone", "10", 500, smartphone));
        articleRepository.save(new Article(0, "Xiaomi", "MI11", 100, smartphone));
        articleRepository.save(new Article(0, "OnePlus", "9 Pro", 200, smartphone));
        articleRepository.save(new Article(0, "Google", "Pixel 5", 350, smartphone));
        articleRepository.save(new Article(0, "Poco", "F3", 150, smartphone));

        articleRepository.save(new Article(0, "Dell", "810", 550, pc));
        articleRepository.save(new Article(0, "Asus", "F756", 600, pc));
        articleRepository.save(new Article(0, "Asus", "E80", 700, pc));
        articleRepository.save(new Article(0, "MacBook", "Pro", 1000, pc));
        articleRepository.save(new Article(0, "MacBook", "Air", 1200, pc));

        articleRepository.save(new Article(0, "IPad", "XL 5", 300, tablet));
        articleRepository.save(new Article(0, "IPad", "XL 7", 500, tablet));

        articleRepository.save(new Article(0, "Canon", "MG30", 50, printer));
        articleRepository.save(new Article(0, "Canon", "MG50", 60, printer));
        articleRepository.save(new Article(0, "HP", "800", 50, printer));
        articleRepository.save(new Article(0, "Epson", "3T", 100, printer));

    }

}

