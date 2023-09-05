package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Set;

public interface IngredientService {

   /* Set<Ingredient> getIngredients();
    Ingredient findById(Long id);
    void deleteById(Long id);

    IngredientCommand savedRecipeCommand(IngredientCommand command);

    IngredientCommand findCommandById(Long l);*/

    IngredientCommand findRecipeIdAndId(Long recipeId, Long id);
    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteIngredient(Long recipeId, Long id);

}
