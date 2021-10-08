package es.joseluisgs.dam.blog.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private String texto;

    // TODO Bidireccionalidad
    // Lista de post que tiene asociado. Por ahora suprimo la bidireccionalidad
    // private final Set<Post> posts = new HashSet<>();


    // From/To JSON
    public static CategoryDTO fromJSON(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, CategoryDTO.class);
    }

    public String toJSON() {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(this);
    }

//    public String toString() {
//        return toJSON();
//    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                '}';
    }
}
