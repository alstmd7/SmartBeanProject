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

		String email = (String) request.getSession().getAttribute("log");

		int calendarNo = Integer.parseInt(request.getParameter("calendar_no"));
		int task_no = Integer.parseInt(request.getParameter("task_no"));
		String name = request.getParameter("name");
		String start = request.getParameter("start");

		EventRequestDto event = new EventRequestDto(calendarNo, task_no, name, email, name, start, start);
		boolean result = eventDao.createEvent(event);

		if (result) {
			System.out.println("성공성공성공");
		}else {
			System.out.println("실패실패실패");			
		}
	}

}
