package controller.calendar;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.calendar.CalendarDao;
import model.calendar.CalendarRequestDto;
import model.event.EventDao;
import model.event.EventRequestDto;
import model.user.UserRequestDto;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class Event_CreateAction
 */
public class Event_CreateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Event_CreateAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    EventDao eventDao = EventDao.getInstance();
	    String json = request.getParameter("events");
	    JSONArray jsonArray = new JSONArray(json);

	    for (int i = 0; i < jsonArray.length(); i++) {
	        JSONObject jsonObject = jsonArray.getJSONObject(i);

	        int calendarNo = jsonObject.getInt("calendar_no");
	        int taskNo = jsonObject.getInt("task_no");
	        String name = jsonObject.getString("name");
	        String email = jsonObject.getString("email");
	        String title = jsonObject.getString("title");
	        String content = jsonObject.getString("content");
	        String start = jsonObject.getString("start");
	        String end = jsonObject.getString("end");
	        String allDay = jsonObject.getString("all_day");

	        EventRequestDto event = new EventRequestDto(0, calendarNo, taskNo, name, email, title, content, start, end, allDay);
	        boolean result = eventDao.createEvent(event);

	        if (!result) {
	            response.getWriter().write("{\"status\": \"error\", \"message\": \"Failed to create event.\"}");
	            return;
	        }
	    }

	    response.getWriter().write("{\"status\": \"success\", \"message\": \"All events created successfully.\"}");
	}


}
