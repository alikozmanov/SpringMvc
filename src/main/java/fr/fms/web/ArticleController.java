package fr.fms.web;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CategoryRepository categoryRepository;

    //@RequestMapping(value="/index" , methode=RequestMethod.GET)
    @GetMapping("/index")   //dans une servlet on utilisait request.getParameter("page")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0")int page,
                        @RequestParam(name = "keyword",defaultValue = "")String kw,
                        @RequestParam(name = "categoryId",defaultValue = "0") Long categoryId) {//le model est fourni par spring, je peux l'utiliser comme ci
        Page<Article> articles = articleRepository.findByDescriptionContains(kw,PageRequest.of(page,5));
        List<Category> categories = categoryRepository.findAll();
//        //en retour, au lieu d'une liste d'articles, on a tous les articles formatés en page pointant sur la page demandée
        model.addAttribute("listArticle",articles.getContent()); //pour récupérer sous forme de liste la page pointée
        model.addAttribute("categories", categories);
        model.addAttribute("pages",new int[articles.getTotalPages()]);

        model.addAttribute("currentPage",page);

        model.addAttribute("keyword", kw);
        return "articles"; //cette méthode retourne au dispacterServlet une vue
    }

    @GetMapping("/delete")
    public String delete(Long id, int page, String keyword) {
        articleRepository.deleteById(id);

        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Model model) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            model.addAttribute("error", "Article not found");
            return "redirect:/index";
        }
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("article", article);
        return "article"; // Redirige vers le formulaire pour modifier
    }

    @GetMapping("/category")
    public String filterByCategory(Model model,@RequestParam Long categoryId,
                                   @RequestParam(name = "page", defaultValue = "0") int page) {
        List<Article> articlesByCat = articleRepository.findByCategoryId(categoryId);
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("listArticle", articlesByCat);
        model.addAttribute("currentPage", page);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategoryId", categoryId);

        return "articles";
    }

    @GetMapping("/article")
    public String article(Model model) {
        model.addAttribute("article", new Article());
        return "article";
    }

    @PostMapping("/save")
    public String save(@Valid Article article, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "article";
        articleRepository.save(article);
        return "redirect:/index";
    }

    @GetMapping("/403")
    public String error() {
        return "403";
    }
}
