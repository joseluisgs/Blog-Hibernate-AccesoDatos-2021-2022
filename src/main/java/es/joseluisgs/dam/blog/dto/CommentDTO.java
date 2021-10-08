package es.joseluisgs.dam.blog.dto;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.joseluisgs.dam.blog.dao.Post;
import es.joseluisgs.dam.blog.dao.User;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class CommentDTO {
    ExclusionStrategy strategy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }

        @Override
        public boolean shouldSkipField(FieldAttributes field) {
            return field.getName().startsWith("password")
                    || field.getName().startsWith("user_id")
                    || field.getName().startsWith("category_id");
        }
    };
    private Long id;
    private String texto;
    private Timestamp fechaPublicacion;
    // Autor que la realiza
    private User user;
    // Post al que pertenece
    private Post post;

    // From/To JSON
    public static CategoryDTO fromJSON(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, CategoryDTO.class);
    }

    public String toJSON() {
        final Gson prettyGson = new GsonBuilder()
                .addSerializationExclusionStrategy(strategy)
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(this);
    }
}
