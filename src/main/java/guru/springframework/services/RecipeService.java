package guru.springframework.services;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import java.util.Set;


public interface RecipeService  {

    Set<Recipe> getRecipe();

    Recipe findById(Long id);

    RecipeCommand savedRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long l);

    void deleteById(Long id);

}
