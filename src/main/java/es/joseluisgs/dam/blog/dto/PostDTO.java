package es.joseluisgs.dam.blog.dto;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.joseluisgs.dam.blog.dao.Category;
import es.joseluisgs.dam.blog.dao.Comment;
import es.joseluisgs.dam.blog.dao.User;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class PostDTO {
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
    private String titulo;
    private String url;
    private String contenido;
    private Timestamp fechaPublicacion;
    // Autor
    private User user;
    // Categoría a la que pertenece
    private Category category;
    // Para mejorar las relaciones y como es un dTO vamos a poner los ids
    private Long user_id, category_id;
    // Lista de comentarios asociados
    private List<Comment> comments;

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

    @Override
    public String toString() {
        return "PostDTO{" +
                "strategy=" + strategy +
                ", id=" + id +
                ", titulo='" + titulo + '\'' +
                ", url='" + url + '\'' +
                ", contenido='" + contenido + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                ", user=" + user +
                ", category=" + category +
                ", user_id=" + user_id +
                ", category_id=" + category_id +
                ", comments=" + comments +
                '}';
    }
}
