package examples;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

public class Indexer {

    public Indexer() {
    }

    public void generateIndex(  String docsPath, String indexPath, boolean create) throws Exception {

        final Path documentsDirectory = Paths.get( docsPath);

        try {
            System.out.println("Indexing to directory '" + indexPath + "'...");

            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new StandardAnalyzer());
            indexWriterConfig.setOpenMode(create ? OpenMode.CREATE : OpenMode.CREATE_OR_APPEND);

            // Optional: for better indexing performance, increase the RAM
            // also need to increase the max heap size to the JVM (eg add -Xmx512m or -Xmx1g):
            // indexWriterConfig.setRAMBufferSizeMB(256.0);

            IndexWriter writer = new IndexWriter( FSDirectory.open(Paths.get(indexPath)), indexWriterConfig);
            indexDirectoryContents(writer, documentsDirectory);

            // costly, increases performance, one time usage preferred
            // writer.forceMerge(1);

            writer.close();

            Date end = new Date();
            System.out.println(end.getTime() - new Date().getTime() + " total milliseconds");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void indexDirectoryContents(final IndexWriter writer, Path documentDir) throws IOException {
        if (Files.isDirectory( documentDir)) {
            Files.walkFileTree( documentDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                    try {
                        indexSingleDocument( writer, file, attrs.lastModifiedTime().toMillis());
                    }
                    catch (IOException ignore) {
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } else {
            indexSingleDocument(writer, documentDir, Files.getLastModifiedTime(documentDir).toMillis());
        }
    }

    void indexSingleDocument(IndexWriter writer, Path file, long lastModified) throws IOException {

        try (InputStream stream = Files.newInputStream(file)) {
            Document doc = new Document();

            Field pathField = new StringField("path", file.toString(), Field.Store.YES);
            doc.add(pathField);
            doc.add(new LongPoint("modified", lastModified));
            doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))));

            if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
                writer.addDocument(doc);
            } else {
                writer.updateDocument(new Term("path", file.toString()), doc);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Indexer().generateIndex("index", "docs", true);
    }


}




























































