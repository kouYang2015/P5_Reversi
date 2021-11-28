package controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReversiBoard;

public class ReversiServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1024594667223547961L;

	/**
		 * Constructor of the object.
		 */
	public ReversiServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. Get Data
		String locRec = request.getParameter("loc");
		String quitGame = request.getParameter("quit");
		
		//2. Validate
		if (quitGame != null) {
			request.getSession(true).setAttribute("game", new ReversiBoard());
		}
		else if (locRec != null && (locRec.matches("[0-5]?[0-9]") || locRec.matches("[6]?[0-3]"))) {
			int locInt = Integer.parseInt(locRec);
			//3. Do it
			((ReversiBoard) request.getSession(true).getAttribute("game")).placeDisk(locInt);
		}
		
		//4. Store info -- n/a
		
		//5. Forward Control
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}

}
