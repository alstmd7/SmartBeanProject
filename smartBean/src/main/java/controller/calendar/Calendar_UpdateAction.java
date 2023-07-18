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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");

	    CalendarDao calDao = CalendarDao.getInstance();
	    
	    System.out.println(request.getParameter("calendarId"));
	    
	    int calendarId = Integer.parseInt(request.getParameter("calendarId"));
	    String newCalendarName = request.getParameter("newCalendarName");
	    
	    boolean isSuccess = calDao.updateCalendarName(calendarId, newCalendarName); 

	    if (isSuccess) {
	        response.getWriter().write("캘린더가 성공적으로 삭제되었습니다.");
	    } else {
	        response.getWriter().write("캘린더 삭제에 실패하였습니다.");
	    }
	}

}
