package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
public class IngredientServiceImpl implements IngredientService{

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }



  /*  @Override
    public Set<Ingredient> getIngredients() {

        Set<Ingredient> ingredients = new HashSet<>();

        ingredientRepository.findAll().iterator().forEachRemaining(ingredients::add);

        return ingredients;
    }

    @Override
    public Ingredient findById(Long id) {

        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(id);

        if(!ingredientOptional.isPresent()){
            throw new RuntimeException("Ingredient is not found");
        }

        return ingredientOptional.get();
    }

    @Override
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }


    @Override
    public IngredientCommand savedRecipeCommand(IngredientCommand command) {
            Ingredient detachedIngredient = ingredientCommandToIngredient.convert(command);

            Ingredient ingredient =  ingredientRepository.save(detachedIngredient);

            return ingredientToIngredientCommand.convert(ingredient);

    }


    @Override
    public IngredientCommand findCommandById(Long l) {

        return ingredientToIngredientCommand.convert(findById(l));
    }*/

    @Override
    public IngredientCommand findRecipeIdAndId(Long recipeId, Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe not found by id recipe in ingredients");
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(id))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            throw new RuntimeException("Ingredient id not found");
        }

        return ingredientCommandOptional.get();

    }


    @Override
    @Transactional
    public void deleteIngredient(Long recipeId, Long id) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(recipeOptional.isPresent()){
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe.getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(id))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientToDelete = ingredientOptional.get();
                ingredientToDelete.setRecipe(null);
                recipe.getIngredients().remove(ingredientOptional.get());
                recipeRepository.save(recipe);
            }

        }

          /*Optional<Ingredient> ingredient = recipeRepository.findById(recipeId).get().getIngredients()
                  .stream()
                  .filter(ingredientRecipe -> ingredientRecipe.getId().equals(id))
                  .findFirst();*/



        //if(ingredient.isPresent()){

        //ingredientRepository.deleteById(id);
          //}

    }





    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand){
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());

        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe Optional not found in save IngredientCommand`");
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe.getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                    .findFirst();

            if(!ingredientOptional.isPresent()){
                //add new ingredient
                Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }else{
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(ingredientCommand.getDescription());
                ingredientFound.setAmount(ingredientCommand.getAmount());
                ingredientFound.setUnitOfMeasure(unitOfMeasureRepository.findById(ingredientCommand.getUom().getId())
                        .orElseThrow( () -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional =  savedRecipe.getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                    .findFirst();

            if(!savedIngredientOptional.isPresent()){
                //not totally save.. - we check if ingredient doesnt exists then we get values from web form and save it.
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredient -> recipeIngredient.getDescription().equals(ingredientCommand.getDescription()))
                        .filter(ingredient -> ingredient.getAmount().equals(ingredientCommand.getAmount()))
                        .filter(ingredient -> ingredient.getUnitOfMeasure().getId().equals(ingredientCommand.getUom().getId()))
                        .findFirst();

            }
            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());



        }

    }

}
