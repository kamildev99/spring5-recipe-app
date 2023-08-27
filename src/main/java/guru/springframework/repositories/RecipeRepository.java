package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {




    Set<Recipe> saveAll(Set<Recipe> recipes);

    Long save(Recipe object);

    Optional<Recipe> findById(Long var1);

    long count();
    void delete(Recipe var1);
    <S extends Recipe> S save(S var1);

    <S extends Recipe> Iterable<S> saveAll(Iterable<S> var1);

    boolean existsById(Long var1);

    Iterable<Recipe> findAll();

    Iterable<Recipe> findAllById(Iterable<Long> var1);

    void deleteById(Long var1);

    void deleteAll(Iterable<? extends Recipe> var1);

    void deleteAll();

}
