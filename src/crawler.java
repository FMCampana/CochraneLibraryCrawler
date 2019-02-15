import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter; 
import java.*;

public class crawler {

	public static void main(String args[]){
		print("running...");
		Document document;
		try {
			//Get Document object after parsing the html from given url.
			document = Jsoup.connect("https://www.cochranelibrary.com/search?p_p_id=scolarissearchresultsportlet_WAR_scolarissearchresults&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_scolarissearchresultsportlet_WAR_scolarissearchresults_searchText=*&_scolarissearchresultsportlet_WAR_scolarissearchresults_searchType=basic&_scolarissearchresultsportlet_WAR_scolarissearchresults_facetQueryField=topic_id&_scolarissearchresultsportlet_WAR_scolarissearchresults_searchBy=6&_scolarissearchresultsportlet_WAR_scolarissearchresults_facetDisplayName=Allergy+%26+intolerance&_scolarissearchresultsportlet_WAR_scolarissearchresults_facetQueryTerm=z1506030924307755598196034641807&_scolarissearchresultsportlet_WAR_scolarissearchresults_facetCategory=Topics").get();

			
			
			//String title = document.title(); //Get title
			String title = document.select("span.facet-pill.secondary").text();
			String url = document.location();
			Elements links = document.select("a[href]");
			Elements results = document.select("div.search-results-item");
			Elements author = document.select("div.search-result-authors");  
			
			
			//Prints to the console 
			for (Element link : results) {
				
				System.out.println(("\n " + "https://www.cochranelibrary.com" +link.select("a").first().attr("href"))+ 
						("| "+  title) + ("|" + link.select("h3.result-title").text()) +
						("|" + link.select("div.search-result-authors").text()) +
						("| " + link.select("div.search-result-date").text()) );
				
			}
			//prints to a file named cochrane_reviews.txt
			PrintWriter out = new PrintWriter(new FileWriter("cochrane_reviews.txt"));
			for (Element link : results) {
				
				out.print(("\n " + "https://www.cochranelibrary.com" +link.select("a").first().attr("href"))+ 
						("| "+  title) + ("|" + link.select("h3.result-title").text()) +
						("|" + link.select("div.search-result-authors").text()) +
						("| " + link.select("div.search-result-date").text()) );
				
			}
			out.close();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	public static void print(String string) {
		System.out.println(string);
	
	}
	
}
