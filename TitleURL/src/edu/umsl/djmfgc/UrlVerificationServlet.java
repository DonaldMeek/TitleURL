package edu.umsl.djmfgc;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UrlVerificationServlet
 */

public class UrlVerificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UrlVerificationServlet() {
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> urlMap = new HashMap<String, String[]>();
		String[] webSiteTitles;
		TitleVerificationBean titleVerificationBean = new TitleVerificationBean();
		
		urlMap = request.getParameterMap();
		titleVerificationBean.setUrlMap(urlMap);
		webSiteTitles = titleVerificationBean.parseUrlTitles();
		request.setAttribute("titles", webSiteTitles);
		request.getRequestDispatcher("/valid_page_titles.jsp").forward(request, response);
	}
}
