package controller.calendar;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.calendar.CalendarDao;
import model.calendar.CalendarRequestDto;

/**
 * Servlet implementation class Calendar_CreateAction
 */
@WebServlet("/Calendar_CreateAction")
public class Calendar_CreateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Calendar_CreateAction() {
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

        int calendarCode = Integer.parseInt(request.getParameter("calendarCode"));
        String email = request.getParameter("email");
        String calendarName = request.getParameter("calendarName");

        CalendarRequestDto calendarDto = new CalendarRequestDto(0, email, calendarName);

        boolean isSuccess1 = dao.createCalendar(calendarDto);
        if (isSuccess1) {
            System.out.println("Create 성공");
        } else {
            System.out.println("Create 실패");
        }
		
	}

}
