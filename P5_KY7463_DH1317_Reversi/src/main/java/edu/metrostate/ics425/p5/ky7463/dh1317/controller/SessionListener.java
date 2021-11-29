package edu.metrostate.ics425.p5.ky7463.dh1317.controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import edu.metrostate.ics425.p4.ky7463.dh1317.model.ReversiBoard;

/**
 * 
 * @author Kou Yang & Dominic Hannah
 * ICS 425 - Ralph Foy
 * Assignment P5 - Reversi
 * Date: 11/28/2021
 * This class represents the Session Listener used to set a ReversiBoard as a session attribute.
 *
 */

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public SessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * Sets 3 session attributes. A ReversiBoard() as a "game", false as "helpBlack" and "helpWhite".
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         se.getSession().setAttribute("game", new ReversiBoard());
         se.getSession().setAttribute("helpBlack", false);
         se.getSession().setAttribute("helpWhite", false);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }
	
}
