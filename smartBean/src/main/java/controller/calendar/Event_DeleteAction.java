package controller.calendar;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.calendar.CalendarDao;
import model.event.EventDao;
import model.event.EventRequestDto;
import model.event.EventVo;

/**
 * Servlet implementation class Event_DeleteAction
 */
@WebServlet("/Event_DeleteAction")
public class Event_DeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Event_DeleteAction() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		int eventNo = Integer.parseInt(request.getParameter("eventNo"));

		EventDao eventDao = EventDao.getInstance();
		EventVo eventVo = eventDao.getEventById(eventNo);

		if (eventVo != null) {
		    // 이벤트가 존재하면 해당 캘린더에서만 삭제
		    CalendarDao calendarDao = CalendarDao.getInstance();
		    int calendarId = eventVo.getCalendar_no();
		    calendarDao.deleteEventFromCalendar(calendarId, eventNo);
		}

		eventDao.deleteEvent(eventNo);

		response.setContentType("text/plain");
		response.getWriter().write("이벤트가 성공적으로 삭제되었습니다.");
		
		
	}

}
