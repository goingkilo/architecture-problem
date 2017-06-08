package bo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kraghunathan on 6/7/17.
 */
public class UserPreference {
    int id;
    List<String> preferred_languages;
    List<String> favourite_actors;
    List<String> favourite_directors;

    public UserPreference( int id,  List<String> preferred_languages, List<String> favourite_actors, List<String> favourite_directors) {
        this.favourite_actors = favourite_actors;
        this.favourite_directors = favourite_directors;
        this.id = id;
        this.preferred_languages = preferred_languages;
    }

    public List<String> getFavourite_actors() {
        return favourite_actors;
    }

    public List<String> getFavourite_directors() {
        return favourite_directors;
    }

    public int getId() {
        return id;
    }

    public List<String> getPreferred_languages() {
        return preferred_languages;
    }

    public static List<UserPreference> load( String json) throws IOException {

        List<UserPreference>  ret = new ArrayList<>();

        JSONArray root = new JSONArray(json);
        for( int i = 0; i < root.length(); i++ ){
            JSONObject item = root.getJSONObject(i);
            String name = item.keys().next();
            JSONObject values = item.getJSONObject(name);
            JSONArray preferred_languages = values.getJSONArray("preferred_languages");
            JSONArray favourite_actors = values.getJSONArray("favourite_actors");
            JSONArray favourite_directors = values.getJSONArray("favourite_directors");
            UserPreference userPreference = new UserPreference( Integer.parseInt(name),
                    toStringList(preferred_languages),
                    toStringList(favourite_actors),
                    toStringList(favourite_directors));
            ret.add( userPreference);

        }
        return ret;
    }

    private static List<String> toStringList(JSONArray j){
        List<String>  ret = new ArrayList<>();
        for( int i = 0 ; i < j.length() ; i++){
            ret.add( j.getString(i));
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        List<UserPreference> a = UserPreference.load(
                new String(
                        Files.readAllBytes(
                                Paths.get(
                                        UserPreference.class.getClassLoader().getResource("user_preferences.json").getPath()))));

        for( UserPreference b : a) {
            System.out.println( b.getId()
                    + "\n.." + b.getFavourite_actors().toString()
                    + "\n.." + b.getFavourite_directors().toString()
                    + "\n.." + b.getPreferred_languages().toString());
        }
    }
}
