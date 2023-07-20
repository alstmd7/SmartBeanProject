package controller.calendar;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import model.event.EventDao;
import model.event.EventVo;

/**
 * Servlet implementation class Event_RequestAction
 */
public class Event_RequestAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Event_RequestAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		
		String[] list = request.getParameter("checkNum").split("checkEvent");
		
		System.out.println(list.toString());

		EventDao eventDao = EventDao.getInstance();
		
		ArrayList<EventVo> eventList = eventDao.getEventAll(list);

		JSONArray responseList = new JSONArray(eventList);
		response.getWriter().append(responseList.toString());

	}

}
