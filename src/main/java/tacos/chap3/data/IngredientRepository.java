package tacos.chap3.data;

import java.util.Optional;

import tacos.chap3.Ingredient;

public interface IngredientRepository {

  Iterable<Ingredient> findAll();
  
  Optional<Ingredient> findById(String id);
  
  Ingredient save(Ingredient ingredient);
  
}
