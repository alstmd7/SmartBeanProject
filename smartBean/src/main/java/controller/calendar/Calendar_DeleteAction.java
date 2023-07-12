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

	    // 삭제할 캘린더의 번호를 파라미터에서 가져옴
	    int calendarNo = Integer.parseInt(request.getParameter("calendarNo"));

	    CalendarDao calDao = CalendarDao.getInstance();

	    // 캘린더 삭제
	    boolean result = calDao.deleteCalendarByNo(calendarNo);
		String url = "calendar";
		
        response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (result) {
			out.println("<script> alert('캘린더 삭제가 완료되었습니다.'); </script>");
		} else {
			out.println("<script> alert('캘린더 삭제가 실패하였습니다.'); </script>");
		}
		
		out.print("<script>location.href='"+url+"';</script>");
		
		out.close();
	}


}
