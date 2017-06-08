package web;

import org.apache.lucene.queryparser.classic.ParseException;
import service.SearchService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class SearchResource {

    private final SearchService searchService;
    public final static String QUERY_FORMAT =  "(director_name:%s) " +
            "OR (actor_1_name:%s) " +
            "OR (actor_2_name:%s) " +
            "OR (actor_3_name:%s) " +
            "OR (movie_title:%s)";

    public SearchResource(SearchService searchService) {
        this.searchService = searchService;
    }



    @GET
    @Path("/user/{userId}/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search( @PathParam("userId") String userId, @QueryParam("text") String query) throws IOException, ParseException {
//        if( query.contains(",")){
//            String[] queryTerms = query.split(",");
//
//
//        }
        return Response.ok().entity("OK").build();
    }

    String makeQuery(String term) {
        return String.format(QUERY_FORMAT, term, term, term, term, term );
    }


    public Response basicSearch(String query) throws IOException, ParseException {

        List<String> queryResults = searchService.search(query);
        List<SearchResult> searchResults = queryResults.stream().map(SearchResult::new).collect(Collectors.toList());
        return Response.ok().entity(searchResults).build();
    }
}
