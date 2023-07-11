package controller.calendar;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.calendar.CalendarDao;
import model.calendar.CalendarVo;

/**
 * Servlet implementation class Calendar_ReadAction
 */
@WebServlet("/Calendar_ReadAction")
public class Calendar_ReadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Calendar_ReadAction() {
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
        
        // Read
        ArrayList<CalendarVo> allCalendars = dao.getAllCalendars(email);
        if (!allCalendars.isEmpty()) {
            System.out.println("----- Read All Calendars -----");
            for (CalendarVo calendar : allCalendars) {
                System.out.println(calendar);
            }
        } else {
            System.out.println("Read 실패");
        }

	}

}
