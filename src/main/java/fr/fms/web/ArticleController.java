package fr.fms.web;

import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



import java.util.List;

@Controller
public class ArticleController {
    @Autowired // Injecte
    ArticleRepository articleRepository;

    @GetMapping("/index") // Mappe la route "/index" pour les requêtes GET
    public String index(Model model, @RequestParam(name = "page" ,defaultValue = "0") int page,
                                     @RequestParam(name = "keyword" , defaultValue = "") String kw) { // Param pour la recherche par mot-clé
        // Récupere un article et filtrés par mots clé et limite page à 5
        Page<Article> articles = articleRepository.findByDescriptionContains(kw,PageRequest.of(page,5));

        // Ajoute la liste des articles dans le modèle
        model.addAttribute("listArticle", articles.getContent());

        // Ajoute un tableau d'entiers
        model.addAttribute("pages", new int[articles.getTotalPages()]);

        // Ajoute le numéro de la page actuelle
        model.addAttribute("currentPage", page);

        // Ajoute le mot-clé et l'affiche dans la vue
        model.addAttribute("keyword", kw);

        return "articles";
    }
}
