package com.recipeye.recipeye_api.startdata;

import com.recipeye.recipeye_api.domain.*;
import com.recipeye.recipeye_api.repositories.CategoryRepository;
import com.recipeye.recipeye_api.repositories.RecipeRepository;
import com.recipeye.recipeye_api.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class RecipeData implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public RecipeData(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        loadCategories();
       loadRecipes();
    }


    private void loadCategories(){
        Category cat1 = new Category();
        cat1.setDescription("American");
        categoryRepository.save(cat1);

        Category cat2 = new Category();
        cat2.setDescription("Italian");
        categoryRepository.save(cat2);

        Category cat3 = new Category();
        cat3.setDescription("Greek");
        categoryRepository.save(cat3);

        Category cat4 = new Category();
        cat4.setDescription("Vegan");
        categoryRepository.save(cat4);

        User user = new User();

        user.setUsername("trey");
        user.setPassword("sax");
        user.setEmail("treysax@uom.gr");

        userRepository.save(user);


        System.out.println("Categories Loaded: " + categoryRepository.count());
        System.out.println("users Loaded: " + userRepository.count());

    }

    private void loadRecipes() {

        //Load Categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Italian");

        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> greekCategoryOptional = categoryRepository.findByDescription("Greek");

        if(!greekCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }


        Optional<Category> veganCategoryOptional = categoryRepository.findByDescription("Vegan");

        if(!veganCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }


        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();
        Category greekCategory = greekCategoryOptional.get();
        Category veganCategory = veganCategoryOptional.get();



        Recipe recipe1 = new Recipe();

        recipe1.setName("Pastitsio");
        recipe1.setDescription("Perfect Pastitsio");
        recipe1.setPrepTime(20);
        recipe1.setCookTime(30);
        recipe1.setDifficulty(Difficulty.KIND_OF_HARD);
        recipe1.setDirections("Cook the pasta and the meat. Pure Besamel on top and bake in the oven");
        recipe1.setNotes("Relax everything is going to be okay");

        Ingredient ingredient1 = new Ingredient("Pasta", new BigDecimal(2), "packet");
        Ingredient ingredient2 = new Ingredient("Beef Meat", new BigDecimal(1), "kg");

        recipe1.getIngredients().add(ingredient1);
        recipe1.getIngredients().add(ingredient2);


        /*recipe1.addIngredient(new Ingredient("Besamel", new BigDecimal(1), "packet"));
        recipe1.addIngredient(new Ingredient("Olive Oil", new BigDecimal(20), "ml"));
        recipe1.addIngredient(new Ingredient("Salt", new BigDecimal(1), "SoupSpoon"));*/

        recipe1.getCategoryNames().add(greekCategory.getDescription());

        //greekCategory.getRecipes().add(recipe1);

        recipe1.setServings(4);

        recipeRepository.save(recipe1);

        greekCategory.getRecipes().add(recipe1);
        //categoryRepository.save(greekCategory);

        Recipe recipe2 = new Recipe();

        recipe2.setName("Mousaka");
        recipe2.setDescription("Perfect Mousaka");
        recipe2.setPrepTime(20);
        recipe2.setCookTime(30);
        recipe2.setDifficulty(Difficulty.KIND_OF_HARD);
        recipe2.setDirections("Cook the potatoes and the meat. Pure Besamel on top and bake in the oven");
        recipe2.setNotes("Relax everything is going to be okay");

        Ingredient ingredient3 = new Ingredient("Potatoes", new BigDecimal(2), "kg");
        Ingredient ingredient4 = new Ingredient("Beef Meat", new BigDecimal(1), "kg");

        recipe2.getIngredients().add(ingredient3);
        recipe2.getIngredients().add(ingredient4);


        /*recipe1.addIngredient(new Ingredient("Besamel", new BigDecimal(1), "packet"));
        recipe1.addIngredient(new Ingredient("Olive Oil", new BigDecimal(20), "ml"));
        recipe1.addIngredient(new Ingredient("Salt", new BigDecimal(1), "SoupSpoon"));*/

        recipe2.getCategoryNames().add(greekCategory.getDescription());

       // greekCategory.getRecipes().add(recipe2);


        recipe2.setServings(4);

        recipeRepository.save(recipe2);


        System.out.println("Recipes Loaded: " + recipeRepository.count());


    }
}
