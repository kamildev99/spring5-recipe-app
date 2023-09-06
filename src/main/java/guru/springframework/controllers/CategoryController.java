package guru.springframework.controllers;


import guru.springframework.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.experimental.categories.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.rmi.server.LogStream.log;

@Slf4j
@Controller
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {this.categoryService = categoryService;}

    @RequestMapping({"/categories", "/categories/index", "/categories/index.html"})
    public String getCategories(Model model){
        log("I'm in controller Category");
        model.addAttribute("categories", categoryService.getCategories());

        return "categories/index";
    }

/*
    @RequestMapping("/category/show/{id}")
    public String getCategoryByID(@PathVariable String id, Model model){

        model.addAttribute("category", categoryService.getById(Long.parseLong(id)));

        return "category/show";
    }
*/
}
