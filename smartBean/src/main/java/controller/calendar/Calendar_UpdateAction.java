package controller.calendar;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.calendar.CalendarDao;
import model.calendar.CalendarVo;

/**
 * Servlet implementation class Calendar_UpdateAction
 */
public class Calendar_UpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Calendar_UpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// CalendarUpdateAction

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");

	    CalendarDao calendarDao = CalendarDao.getInstance();
	    int calendarId;
	    String newCalendarName = request.getParameter("newCalendarName");

	    String calendarIdString = request.getParameter("calendarId");
	    if (calendarIdString != null) {
	        calendarId = Integer.parseInt(calendarIdString);
	    } else {
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write("{\"message\": \"fail\"}");
	        return;
	    }

	    int result = calendarDao.updateCalendarName(calendarId, newCalendarName);

	    if (result > 0) {
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write("{\"message\": \"success\"}");
	    } else {
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write("{\"message\": \"fail\"}");
	    }
	}

}
