package controller.calendar;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.calendar.CalendarDao;
import model.calendar.CalendarRequestDto;
import model.calendar.CalendarVo;
import model.user.UserDao;
import model.user.UserVo;

/**
 * Servlet implementation class Calendar_DeleteAction
 */
@WebServlet("/Calendar_DeleteAction")
public class Calendar_DeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Calendar_DeleteAction() {
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");

	    CalendarDao calDao = CalendarDao.getInstance();
	    int calendarId = Integer.parseInt(request.getParameter("calendarId"));

	    boolean isSuccess = calDao.deleteCalendarByNo(calendarId); 

	    if (isSuccess) {
	        response.getWriter().write("캘린더가 성공적으로 삭제되었습니다.");
	    } else {
	        response.getWriter().write("캘린더 삭제에 실패하였습니다.");
	    }
	}


}
