package controller.calendar;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.event.EventDao;
import model.event.EventVo;
import model.todo.TodoDao;
import model.todo.TodoVo;

/**
 * Servlet implementation class Event_getAction
 */
public class Event_getAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Event_getAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		

		EventDao eventDao = EventDao.getInstance();
		int no = Integer.parseInt(request.getParameter("no")); 
		EventVo event = eventDao.getEventById(no);
		
		JSONObject responseList = new JSONObject(event);
		response.getWriter().append(responseList.toString());
	}

}
