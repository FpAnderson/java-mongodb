package challenge;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.domain.Sort;
import challenge.Recipe;

public interface RecipeRepository extends MongoRepository<Recipe, String> {

    @Query("{ ingredients: ?0 }")
    List<Recipe> findByIngredient(String ingredient);

    @Query("{ $or: [ { title: { $regex: ?0, $options: 'i' } }, { description: { $regex: ?0, $options: 'i' } } ] }")
    List<Recipe> searchRecipes(String searchTerm);

}