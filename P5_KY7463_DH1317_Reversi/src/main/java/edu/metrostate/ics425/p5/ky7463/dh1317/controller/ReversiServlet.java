package edu.metrostate.ics425.p5.ky7463.dh1317.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReversiBoard;

/**
 * 
 * @author Kou Yang & Dominic Hannah
 * ICS 425 - Ralph Foy
 * Assignment P5 - Reversi
 * Date: 11/28/2021
 * This class represents the Servlet used to validate server side requests.
 *
 */

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
	
	/**
	 * Processes the incoming button requests from the session. If quitGame button is clicked, a new session will start.
	 * If the helpBlackButton is clicked, then the session attribute "helpBlack" will be the anti-boolean of its current value.
	 * If the helpWhiteButton is clicked, then the session attribute "helpWhite" will be the anti-boolean of its current value.
	 * If a location on the board is clicked, then it will call the game's placeDisk() method.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. Get Data
		String locRec = request.getParameter("loc");
		String quitGame = request.getParameter("quit");
		String helpBlackButton = request.getParameter("helpBlack");
		String helpWhiteButton = request.getParameter("helpWhite");
		boolean helpBlackSessionValue = (boolean) request.getSession(true).getAttribute("helpBlack");
		boolean helpWhiteSessionValue = (boolean) request.getSession(true).getAttribute("helpWhite");
		
		//2. Validate
		if (quitGame != null) {
			//3. Do it - Start a new game
			request.getSession(true).setAttribute("game", new ReversiBoard());
			request.getSession(true).setAttribute("helpWhite", false);
			request.getSession(true).setAttribute("helpBlack", false);
		}
		else if (Boolean.parseBoolean(helpWhiteButton)) {
			//3. Do it - Enables/Disables helper images on the jsp by setting the session attribute to opposite of itself.
			request.getSession(true).setAttribute("helpWhite", !helpWhiteSessionValue);
		}
		else if (Boolean.parseBoolean(helpBlackButton)) {
			//3. Do it - Enables/Disables helper images on the jsp by setting the session attribute to opposite of itself.
			request.getSession(true).setAttribute("helpBlack", !helpBlackSessionValue);
		}
		else if (locRec != null && (locRec.matches("[0-5]?[0-9]") || locRec.matches("[6]?[0-3]"))) {
			int locInt = Integer.parseInt(locRec);
			//3. Do it - Place a new disk at the locInt on the board.
			((ReversiBoard) request.getSession(true).getAttribute("game")).placeDisk(locInt);
		}
		
		//4. Store info -- n/a All info saved on session.
		
		//5. Forward Control
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}

}
