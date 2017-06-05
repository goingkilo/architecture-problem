
package examples;


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Paths;

public class Searcher {

    private static String FOLDER_CONTAINING_INDEX = "index";
    private static String SEARCH_FIELD = "contents";

    private Searcher() {
    }

    public static void main(String[] args) throws Exception {
        new Searcher().search( args[0]);
    }


    public void search( String queryString) throws IOException, ParseException {


        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(FOLDER_CONTAINING_INDEX)));
        IndexSearcher searcher = new IndexSearcher(reader);

        Query query = new QueryParser(SEARCH_FIELD, new StandardAnalyzer()).parse(queryString);
        System.out.println("Searching for: " + query.toString(SEARCH_FIELD));

        TopDocs results = searcher.search(query, 10);
        ScoreDoc[] hits = results.scoreDocs;
        for( ScoreDoc hit : hits) {
            Document doc = searcher.doc( hit.doc);
            for(IndexableField s : doc.getFields()) {
                System.out.println(s.name());

            }
            System.out.println( doc.get("path"));
            System.out.println(" ** ** ** ** ");
        }
        System.out.println( " == == == ==  " );

        reader.close();
    }


    public static void doPagingSearch(BufferedReader in, IndexSearcher searcher, Query query,
                                      int hitsPerPage, boolean raw, boolean interactive) throws IOException {

        TopDocs results = searcher.search(query, 5 * hitsPerPage);
        ScoreDoc[] hits = results.scoreDocs;

        int numTotalHits = results.totalHits;
        System.out.println(numTotalHits + " total matching documents");

        int start = 0;
        int end = Math.min(numTotalHits, hitsPerPage);

        while (true) {
            if (end > hits.length) {
                System.out.println("Only results 1 - " + hits.length + " of " + numTotalHits + " total matching documents collected.");
                System.out.println("Collect more (y/n) ?");
                String line = in.readLine();
                if (line.length() == 0 || line.charAt(0) == 'n') {
                    break;
                }

                hits = searcher.search(query, numTotalHits).scoreDocs;
            }

            end = Math.min(hits.length, start + hitsPerPage);

            for (int i = start; i < end; i++) {
                if (raw) {                              // output raw format
                    System.out.println("doc=" + hits[i].doc + " score=" + hits[i].score);
                    continue;
                }

                Document doc = searcher.doc(hits[i].doc);
                String path = doc.get("path");
                if (path != null) {
                    System.out.println((i + 1) + ". " + path);
                    String title = doc.get("title");
                    if (title != null) {
                        System.out.println("   Title: " + doc.get("title"));
                    }
                } else {
                    System.out.println((i + 1) + ". " + "No path for this document");
                }

            }


        }
    }
}




























































