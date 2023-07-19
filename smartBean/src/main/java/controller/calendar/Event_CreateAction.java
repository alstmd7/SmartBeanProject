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
import model.task.TaskDao;
import model.task.TaskVo;
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
		TaskDao taskDao = TaskDao.getInstance();
		String email = (String) request.getSession().getAttribute("log");
	
		int calendarNo = Integer.parseInt(request.getParameter("calendarNo"));
		String name = request.getParameter("name");
		String start = request.getParameter("start");

		TaskVo taskVo = taskDao.getTaskByName(email, name);
		if(taskVo == null) {
			taskVo = taskDao.getTaskByName("admin", name);
		}
		
		System.out.println("calendarNo : " + calendarNo + "name" + name +  "start" + start + "taskVo.getNo()" + taskVo.getNo());
		
		EventRequestDto event = new EventRequestDto(calendarNo, taskVo.getNo(), name, email, name, start, start);
		boolean result = eventDao.createEvent(event);

		if (result) {
			System.out.println("성공성공성공");
		}else {
			System.out.println("실패실패실패");			
		}
	}

}
