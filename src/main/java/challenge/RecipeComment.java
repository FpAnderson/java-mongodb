package challenge;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Classe para mapear o comentario da receita no MongoDB
 *
 */
@Document(collection = "comment")
public class RecipeComment {

    @Id
    private String id;

    private String comment;

    public RecipeComment() {
    }

    public String getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

