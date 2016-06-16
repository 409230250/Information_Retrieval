package servlets;

/* 
 * Assignment 3
 * Chen, Andy K : 45168779
 * Lin, Junjie : 25792830
 * Samtani, Chirag V: 63279154
 * Derian, Fransiskus : 82691258
 * 
 */

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import searchEnginePackage.SearchManager;
import searchEnginePackage.DocInformation;

@WebServlet("/SearchBar")
public class FabFlixSearchBar extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		
		String movieSearch = request.getParameter("title");
		//System.out.println("SearchText: "+movieSearch.toString());
		movieSearch = movieSearch.trim();

		SearchManager search = new SearchManager(movieSearch);
		ArrayList<DocInformation> output = new ArrayList<DocInformation>();
		output = search.output;
		
//		String[] sArray = movieSearch.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split(" "); 
//		ArrayList<String> keywords = new ArrayList<String>(Arrays.asList(sArray));
//		//System.out.println("SearchText: "+keywords.toString());
		request.setAttribute("docInfo", output);
		request.setAttribute("size", output.size());
		request.setAttribute("SearchString", movieSearch);
	    request.getRequestDispatcher("/FabFlixAutocompleteDropdown.jsp").forward(request, response);

	}
	
}
