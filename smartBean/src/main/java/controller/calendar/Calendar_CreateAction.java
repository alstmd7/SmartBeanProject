package controller.calendar;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.calendar.CalendarDao;
import model.calendar.CalendarRequestDto;
import model.calendar.CalendarVo;
import model.share.ShareDao;
import model.user.UserDao;
import model.user.UserVo;

/**
 * Servlet implementation class Calendar_CreateAction
 */
public class Calendar_CreateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// DB연동완료 DB 연동완료 DB연동완료 DB 연동완료 DB연동완료 DB 연동완료 DB연동완료 DB 연동완료 DB연동완료 DB 연동완료
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String calendarName = request.getParameter("calendarName");
		
        CalendarDao dao = CalendarDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        
        String email = (String) request.getSession().getAttribute("log");
        UserVo userVo = userDao.getUserByEmail(email);
        String owner = request.getParameter("owner");

        CalendarRequestDto calendarDto = new CalendarRequestDto(userVo.getCode(), owner, calendarName);
        
        response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
        
		boolean result = dao.createCalendar(calendarDto);
		
		if (result) {
			ShareDao shareDao = ShareDao.getInstance();
			ArrayList<CalendarVo> calendarList = dao.getAllCalendars(email);
			int max = calendarList.get(0).getNo();
			for(int i=0; i<calendarList.size(); i++) {
				if(calendarList.get(i).getNo() > max) {
					max = calendarList.get(i).getNo();
				}
			}
			shareDao.addSharedCalendar(email, max);
			
		} else {
			out.println("<script> alert('새로운 캘린더 생성이 실패하였습니다.'); </script>");
		}
		
		response.sendRedirect("calendar");
	}
	
}