package web;

/**
 * Created by kraghunathan on 6/6/17.
 */
public class SearchResult {

    private String color ;
    private String director_name ;
    private String num_critic_for_reviews ;
    private String duration ;
    private String director_facebook_likes ;
    private String actor_3_facebook_likes ;
    private String actor_2_name ;
    private String actor_1_facebook_likes ;
    private String gross ;
    private String genres ;
    private String actor_1_name ;
    private String movie_title ;
    private String num_voted_users ;
    private String cast_total_facebook_likes ;
    private String actor_3_name ;
    private String facenumber_in_poster ;
    private String plot_keywords ;
    private String movie_imdb_link ;
    private String num_user_for_reviews ;
    private String language ;
    private String country ;
    private String content_rating ;
    private String budget ;
    private String title_year ;
    private String actor_2_facebook_likes ;
    private String imdb_score ;
    private String aspect_ratio ;



    private String movie_facebook_likes ;

    public SearchResult(String row) {
        String[] b = row.split(",");
        for( int i = 0 ; i < b.length ; i++) {
            this.color = b[i];
            this.director_name = b[i];
            this.num_critic_for_reviews = b[i];
            this.duration = b[i];
            this.director_facebook_likes = b[i];
            this.actor_3_facebook_likes = b[i];
            this.actor_2_name = b[i];
            this.actor_1_facebook_likes = b[i];
            this.gross = b[i];
            this.genres = b[i];
            this.actor_1_name = b[i];
            this.movie_title = b[i];
            this.num_voted_users = b[i];
            this.cast_total_facebook_likes = b[i];
            this.actor_3_name = b[i];
            this.facenumber_in_poster = b[i];
            this.plot_keywords = b[i];
            this.movie_imdb_link = b[i];
            this.num_user_for_reviews = b[i];
            this.language = b[i];
            this.country = b[i];
            this.content_rating = b[i];
            this.budget = b[i];
            this.title_year = b[i];
            this.actor_2_facebook_likes = b[i];
            this.imdb_score = b[i];
            this.aspect_ratio = b[i];
            this.movie_facebook_likes = b[i];
        }

    }

    public String getActor_1_facebook_likes() {
        return actor_1_facebook_likes;
    }

    public String getActor_1_name() {
        return actor_1_name;
    }

    public String getActor_2_facebook_likes() {
        return actor_2_facebook_likes;
    }

    public String getActor_2_name() {
        return actor_2_name;
    }

    public String getActor_3_facebook_likes() {
        return actor_3_facebook_likes;
    }

    public String getActor_3_name() {
        return actor_3_name;
    }

    public String getAspect_ratio() {
        return aspect_ratio;
    }

    public String getBudget() {
        return budget;
    }

    public String getCast_total_facebook_likes() {
        return cast_total_facebook_likes;
    }

    public String getColor() {
        return color;
    }

    public String getContent_rating() {
        return content_rating;
    }

    public String getCountry() {
        return country;
    }

    public String getDirector_facebook_likes() {
        return director_facebook_likes;
    }

    public String getDirector_name() {
        return director_name;
    }

    public String getDuration() {
        return duration;
    }

    public String getFacenumber_in_poster() {
        return facenumber_in_poster;
    }

    public String getGenres() {
        return genres;
    }

    public String getGross() {
        return gross;
    }

    public String getImdb_score() {
        return imdb_score;
    }

    public String getLanguage() {
        return language;
    }

    public String getMovie_facebook_likes() {
        return movie_facebook_likes;
    }

    public String getMovie_imdb_link() {
        return movie_imdb_link;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public String getNum_critic_for_reviews() {
        return num_critic_for_reviews;
    }

    public String getNum_user_for_reviews() {
        return num_user_for_reviews;
    }

    public String getNum_voted_users() {
        return num_voted_users;
    }

    public String getPlot_keywords() {
        return plot_keywords;
    }

    public String getTitle_year() {
        return title_year;
    }
}
