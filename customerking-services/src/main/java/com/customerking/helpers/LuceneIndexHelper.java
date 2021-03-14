package com.customerking.helpers;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.customerking.model.Customers;

@Component
public class LuceneIndexHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(LuceneIndexHelper.class);
	
	private static final String INDEX_DIR = "C:\\CustomerKing\\Index";
	
	public void createIndex(Customers customer) {
		try {
			IndexWriter writer = createWriter();
			List<Document> documents = new ArrayList<>();

			Document document = createDocument(customer.getId(), customer.getName(), customer.getEmail(),
					customer.getAddress(), customer.getDob(), customer.getProfession(), customer.getGender());
			documents.add(document);

			writer.addDocuments(documents);
			writer.commit();
			writer.close();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	private static Document createDocument(Long id, String custName, String custEmail, String address, String dob, String profession, String gender) {
    	Document document = new Document();
    	document.add(new StringField("customerId", id.toString() , Field.Store.YES));
    	document.add(new TextField("customerName", custName , Field.Store.YES));
    	document.add(new TextField("customerEmail", custEmail , Field.Store.YES));
    	document.add(new TextField("customerAddress", address , Field.Store.YES));
    	document.add(new TextField("customerDOB", dob , Field.Store.YES));
    	document.add(new TextField("customerProfession", profession , Field.Store.YES));
    	document.add(new TextField("customerGender", gender , Field.Store.YES));
    	return document;
    }

	private static IndexWriter createWriter() throws IOException {
		FSDirectory dir = FSDirectory.open(Paths.get(INDEX_DIR));
		IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
		IndexWriter writer = new IndexWriter(dir, config);
		return writer;
	}
	
	private static TopDocs searchByCustomerName(String custName, IndexSearcher searcher) throws Exception {
		LOGGER.info("Searching by name :: {}",custName);
		QueryParser qp = new QueryParser("customerName", new StandardAnalyzer());
		Query custNameQuery = qp.parse(custName);
		TopDocs hits = searcher.search(custNameQuery, 10);
		return hits;
	}

	private static TopDocs searchById(Integer id, IndexSearcher searcher) throws Exception {
		QueryParser qp = new QueryParser("customerId", new StandardAnalyzer());
		Query idQuery = qp.parse(id.toString());
		TopDocs hits = searcher.search(idQuery, 10);
		return hits;
	}
	
	private static IndexSearcher createSearcher() throws IOException {
		Directory dir = FSDirectory.open(Paths.get(INDEX_DIR));
		IndexReader reader = DirectoryReader.open(dir);
		IndexSearcher searcher = new IndexSearcher(reader);
		return searcher;
	}

	public List<Long> readIndex(String searchText) {
		List<Long> ids = new ArrayList<>();
		try {
			IndexSearcher searcher = createSearcher();
			TopDocs foundDocs = searchByCustomerName(searchText, searcher);
		
			LOGGER.info("Total Results :: {}",foundDocs.totalHits);
			for (ScoreDoc sd : foundDocs.scoreDocs) {
				Document d = searcher.doc(sd.doc);
				LOGGER.info("customerId {}",String.format(d.get("customerId")));
				ids.add(Long.parseLong(d.get("customerId")));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return ids;		
	}
}
