package controller.calendar;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.calendar.CalendarDao;
import model.calendar.CalendarRequestDto;
import model.user.UserDao;
import model.user.UserVo;

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

		Map map = request.getParameterMap();
		Object[] keyset = map.keySet().toArray();
		for(Object key : keyset) {
			System.out.println(">> " + ((String) key));
		}
		
		String calendarName = request.getParameter("calendarName");
		System.out.println("calendarName : " + calendarName);
		
        CalendarDao dao = CalendarDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        
        String email = (String) request.getSession().getAttribute("log");
        UserVo userVo = userDao.getUserByEmail(email);

        CalendarRequestDto calendarDto = new CalendarRequestDto(userVo.getCode(), email, calendarName);

        response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
        
		boolean isSuccess1 = dao.createCalendar(calendarDto);
		if (isSuccess1) {
		    System.out.println("Create 성공");
		    out.println("<script> alert('Create calendar success'); </script>");
		} else {
		    System.out.println("Create 실패");
		    out.println("<script> alert('Create calendar fail'); </script>");
		}

        
	}
	


}
