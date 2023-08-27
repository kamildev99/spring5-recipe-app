package guru.springframework.services;


import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


public interface RecipeService  {

    Set<Recipe> getRecipe();
}
