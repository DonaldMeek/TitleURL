package edu.umsl.djmfgc;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jsoup.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class TitleVerificationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 570875728492568019L;
	private HashMap<String, String[]> urlMap;

	TitleVerificationBean()
	{
		
	}
	
	public void setUrlMap(Map<String, String[]> urlMap)
	{
		this.urlMap = new HashMap<String, String[]>(urlMap);
	}
	
	/* The parseUrlTitles() function returns the web page titles for each URL the user enters. 
	 * 
	 * If the user enters an incomplete URL that does not show the protocol or www. then 
	 * parseUrlTitles() throws an exception and attempts to correct the URL. 
	 * 
	 * The value of urlMap is set by UrlVerificationServlet and the jsoup-1.9.2.jar file is 
	 * included in the WEB-INF/lib folder. */
	public String[] parseUrlTitles() 
	{
		Document doc;
		String urlPrepend = "";
		String[] urls;
		boolean urlCorrectionFailed = false;
		boolean urlUnfixable = false;
		String[] pageTitles;
		Iterator<String> mapKeyItr = urlMap.keySet().iterator();
		String key = mapKeyItr.next();
		urls = urlMap.get(key);
		pageTitles = new String[urls.length];
		String urlTemp = "";
		int j;
		for (j = 0; j < urls.length; j = j + 1) {
			try {
				if (!urlCorrectionFailed && !urlUnfixable) urlTemp = urls[j];
				doc = Jsoup.connect(urls[j]).get();
				pageTitles[j] = doc.title();
				urlCorrectionFailed = false;
				urlUnfixable = false;
			} catch (IOException | IllegalArgumentException e) {
				// TODO Auto-generated catch block
				// Termination case. The urlUnfixable variable should be false unless all attempts to fix it failed. 
				if (urlUnfixable) {
					if (!(urlTemp.isEmpty())) pageTitles[j] = "Error connecting to " + urlTemp + ".\n";
					else pageTitles[j] = " "; // Ignore empty URL entries
					urlCorrectionFailed = false;
					urlUnfixable = false;
					continue;
				}
				// Attempt to correct the URL by adding https:// or http:// or https://www. or http://www.
				if (!urlCorrectionFailed) urlPrepend = "https://";
				else if (urlPrepend.equals("https://")) urlPrepend = "http://";
				else if (urlPrepend.equals("http://")) urlPrepend = "https://www.";
				else if (urlPrepend.equals("https://www."))
				{
					urlPrepend = "http://www.";
					urlUnfixable = true; /* Show the user an error if another exception is called immediately after the next connection attempt */
					urls[j] = urlPrepend + urlTemp;
					j = j - 1;
					continue;
				}
				
				// Try to connect again with a modified url
				if (!urlUnfixable) {
					urls[j] = urlPrepend + urlTemp;
					j = j - 1;
					urlCorrectionFailed = true; 
					continue;
				}
			}
		}
		return pageTitles;
	}
}	
		
		