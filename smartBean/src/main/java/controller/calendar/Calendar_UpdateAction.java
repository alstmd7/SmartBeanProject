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
@WebServlet("/Calendar_UpdateAction")
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

        CalendarDao dao = CalendarDao.getInstance();
		
		 // Update
        System.out.println("----- Update -----");
        int updateCalendarNo = 1;
        String newCalendarName = "New Test Calendar";
        dao.updateCalendarName(updateCalendarNo, newCalendarName);
        System.out.println("Update 성공");

        // Read after update
        System.out.println("----- Read All Calendars -----");
        allCalendars = dao.getAllCalendars(email);
        for (CalendarVo calendar : allCalendars) {
            System.out.println(calendar);
        }
	}

}
