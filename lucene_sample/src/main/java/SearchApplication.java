import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import service.SearchService;
import web.SearchResource;

public class SearchApplication extends Application<SearchConfiguration> {


    @Override
    public void initialize(Bootstrap<SearchConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
    }

    @Override
    public void run(SearchConfiguration config, Environment environment) throws ClassNotFoundException {

        environment.jersey().register(new SearchResource( new SearchService()));
    }

    public static void main(String[] args) throws Exception {
        new SearchApplication().run(args);
    }
}
