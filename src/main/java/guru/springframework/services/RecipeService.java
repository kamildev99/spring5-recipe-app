package guru.springframework.services;


import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RecipeService implements RecipeRepository<Recipe, Long> {

    @Override
    public Set<Recipe> findByAll();

    @Override
    public Long save(Recipe object) {
        this.save(object);

    };
    @Override
    public Optional<Recipe> findById(Long var1);
    public long count();


    public void delete(Recipe var1);
}
