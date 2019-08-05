package challenge;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("recipe")
public class RecipeController {

	@Autowired
	private RecipeService service;

	@PostMapping
	public Recipe save(@RequestBody Recipe recipe) {
		return service.save(recipe);
	}

	@PutMapping(value = "{id}")
	public void update(@PathVariable("id")String id, @RequestBody Recipe recipe) {
		service.update(id, recipe);
	}

	@DeleteMapping(value = "{id}")
	public void delete(@PathVariable("id")String id) {
		service.delete(id);
	}

	@GetMapping(value = "{id}")
	public Recipe get(@PathVariable("id")String id) {
		return service.get(id);
	}

	@GetMapping(value = "/ingredient", params = "ingredient")
	public List<Recipe> listByIngredient(@RequestParam("ingredient")  String ingredient) {
		return service.listByIngredient(ingredient);
	}

	@GetMapping(value = "/search", params = "search")
	public List<Recipe> search(@RequestParam("search")  String search) {
		return service.search(search);
	}

	@PostMapping(value = "{id}/like/{userId}")
	public void like(@PathVariable("id")String id, @PathVariable("userId")String userId) {
		service.like(id, userId);
	}

	@DeleteMapping(value = "{id}/like/{userId}")
	public void unlike(@PathVariable("id")String id, @PathVariable("userId")String userId) {
		service.unlike(id, userId);
	}


	@PostMapping(value = "{id}/comment")
	public RecipeComment addComment(@PathVariable("id")String id, @RequestBody RecipeComment recipeComment) {
		return service.addComment(id, recipeComment);
	}

	@PutMapping(value = "{id}/comment/{commentId}")
	public void updateComment(@PathVariable("id")String id, @PathVariable("commentId")String commentId,
							  @RequestBody RecipeComment recipeComment) {
		service.updateComment(id, commentId, recipeComment);
	}

	@DeleteMapping(value = "{id}/comment/{commentId}")
	public void deleteComment(@PathVariable("id")String id, @PathVariable("commentId")String commentId) {
		service.deleteComment(id, commentId);
	}

}