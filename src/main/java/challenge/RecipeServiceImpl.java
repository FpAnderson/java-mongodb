package challenge;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	@Override
	public Recipe save(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	@Override
	public void update(String id, Recipe recipe) {
		Optional<Recipe> recipeSearch = recipeRepository.findById(id);
		if(recipeSearch.isPresent()) {

			Recipe recipe1 = recipeSearch.get();
			recipe1.setTitle(recipe.getTitle());
			recipe1.setDescription(recipe.getDescription());
			recipe1.setIngredients(recipe.getIngredients());

			recipeRepository.save(recipe1);
		}
	}

	@Override
	public void delete(String id) {
		recipeRepository.deleteById(id);
	}

	@Override
	public Recipe get(String id) {
		Optional<Recipe> recipe = recipeRepository.findById(id);
		return recipe.orElse(null);
	}

	@Override
	public List<Recipe> listByIngredient(String ingredient) {
		List<Recipe> recipes = recipeRepository.findByIngredient(ingredient);
		recipes.sort(Comparator.comparing(Recipe::getTitle));
		return recipes;
	}

	@Override
	public List<Recipe> search(String search) {
		List<Recipe> recipes = recipeRepository.searchRecipes(search);
		recipes.sort(Comparator.comparing(Recipe::getTitle));

		return recipes;
	}

	@Override
	public void like(String id, String userId) {
		Optional<Recipe> recipe =  recipeRepository.findById(id);
		if(recipe.isPresent()) {
			recipe.get().getLikes().add(userId);
			recipeRepository.save(recipe.get());
		}
	}

	@Override
	public void unlike(String id, String userId) {
		Optional<Recipe> recipe = recipeRepository.findById(id);
		if(recipe.isPresent()) {
			recipe.get().getLikes().remove(userId);
			recipeRepository.save(recipe.get());
		}
	}

	@Override
	public RecipeComment addComment(String id, RecipeComment comment) {
		Optional<Recipe> recipe = recipeRepository.findById(id);
		if(recipe.isPresent()) {
			ObjectId objId = new ObjectId();
			comment.setId(objId.toString());
			recipe.get().getComments().add(comment);
			recipeRepository.save(recipe.get());
		}
		return comment;
	}

	@Override
	public void updateComment(String id, String commentId, RecipeComment comment) {
		Optional<Recipe> recipe = recipeRepository.findById(id);
		if(recipe.isPresent()) {
			recipe.get().getComments().stream().filter(recipeComment -> recipeComment.getId()
											.equals(commentId)).findFirst().get().setComment(comment.getComment());
			recipeRepository.save(recipe.get());
		}
	}

	@Override
	public void deleteComment(String id, String commentId) {
		Optional<Recipe> recipe = recipeRepository.findById(id);
		if(recipe.isPresent()) {
			RecipeComment removeComment = recipe.get().getComments().stream()
					.filter(recipeComment -> recipeComment.getId().equals(commentId)).findFirst().get();
			recipe.get().getComments().remove(removeComment);
			recipeRepository.save(recipe.get());
		}
	}

}
