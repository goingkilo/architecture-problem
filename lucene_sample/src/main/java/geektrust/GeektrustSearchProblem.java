package geektrust;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by kraghunathan on 6/5/17.
 * Next steps:
 * Over the web via servlets ? spring ?
 * UI with websockets
 * redis in between, so that we get a type as you go and stuff is fetched from cache
 * inventory data to be indexed
 * no solr - 
 */
public class GeektrustSearchProblem {

    public static final String indexDir = "index";

    public void index() throws IOException {

        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new StandardAnalyzer());
        indexWriterConfig.setOpenMode(  IndexWriterConfig.OpenMode.CREATE );
        IndexWriter writer = new IndexWriter( FSDirectory.open(Paths.get( indexDir)), indexWriterConfig);

        Path fileToIndex  = Paths.get( "docs/movie_metadata.csv");


        List<String> movies = Files.readAllLines(fileToIndex) ;
        for( String movie : movies ) {

            movie = movie.toLowerCase();

            Document doc = new Document();
            Field pathField = new StringField("path", movie, Field.Store.YES);
            doc.add(pathField);

            String[] b = movie.split(",");
            System.out.println( b.length);

            doc.add(new StringField("color", b[0], Field.Store.YES));
            doc.add(new StringField("director_name", b[1], Field.Store.YES));
            doc.add(new StringField("num_critic_for_reviews", b[2], Field.Store.YES));
            doc.add(new StringField("duration", b[3], Field.Store.YES));
            doc.add(new StringField("director_facebook_likes", b[4], Field.Store.YES));
            doc.add(new StringField("actor_3_facebook_likes", b[5], Field.Store.YES));
            doc.add(new StringField("actor_2_name", b[6], Field.Store.YES));
            doc.add(new StringField("actor_1_facebook_likes", b[7], Field.Store.YES));
            doc.add(new StringField("gross", b[8], Field.Store.YES));
            doc.add(new StringField("genres", b[9], Field.Store.YES));
            doc.add(new StringField("actor_1_name", b[10], Field.Store.YES));
            doc.add(new StringField("movie_title", b[11], Field.Store.YES));
            doc.add(new StringField("num_voted_users", b[12], Field.Store.YES));
            doc.add(new StringField("cast_total_facebook_likes", b[13], Field.Store.YES));
            doc.add(new StringField("actor_3_name", b[14], Field.Store.YES));
            doc.add(new StringField("facenumber_in_poster", b[15], Field.Store.YES));
            doc.add(new StringField("plot_keywords", b[16], Field.Store.YES));
            doc.add(new StringField("movie_imdb_link", b[17], Field.Store.YES));
            doc.add(new StringField("num_user_for_reviews", b[18], Field.Store.YES));
            doc.add(new StringField("language", b[19], Field.Store.YES));
            doc.add(new StringField("country", b[20], Field.Store.YES));
            doc.add(new StringField("content_rating", b[21], Field.Store.YES));
            doc.add(new StringField("budget", b[22], Field.Store.YES));
            doc.add(new StringField("title_year", b[23], Field.Store.YES));
            doc.add(new StringField("actor_2_facebook_likes", b[24], Field.Store.YES));
            doc.add(new StringField("imdb_score", b[25], Field.Store.YES));
            doc.add(new StringField("aspect_ratio", b[26], Field.Store.YES));
            doc.add(new StringField("movie_facebook_likes", b[27], Field.Store.YES));

            writer.addDocument(doc);
        }
        writer.close();
    }

    public void search( String searchTerm) throws IOException, ParseException {
        IndexReader reader = DirectoryReader.open( FSDirectory.open(Paths.get(indexDir)));
        IndexSearcher searcher = new IndexSearcher(reader);

        QueryParser parser = new QueryParser("movie_title", new StandardAnalyzer());
        Query query = parser.parse(searchTerm);

        ScoreDoc[] hits = searcher.search( query, 25).scoreDocs;

        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = searcher.doc(hits[i].doc);
            System.out.println(hitDoc.get("path"));
            System.out.println( " __________________ " );
        }
        System.out.println( "-------------------" );

        reader.close();

    }

    public static void main(String[] args) throws IOException, ParseException {
       //new GeektrustSearchProblem().index();
       new GeektrustSearchProblem().search( args[0]);
    }
}
