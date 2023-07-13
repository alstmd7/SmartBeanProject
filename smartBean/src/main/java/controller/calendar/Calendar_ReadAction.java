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

	    CalendarDao calDao = CalendarDao.getInstance();
	    
	    // 로그인된 사용자의 이메일 가져오기
	    String email = (String) request.getSession().getAttribute("log");

	    // 해당 사용자의 모든 캘린더 가져오기
	    ArrayList<CalendarVo> calendarList = calDao.getAllCalendars(email);

	    // 가져온 캘린더를 출력 또는 처리하는 로직 작성

	    // test >>> 가져온 캘린더를 콘솔에 출력
	    for (CalendarVo calendar : calendarList) {
	        System.out.println("Calendar Name: " + calendar.getName());
	    }

	    // 미완성 
	}


}
