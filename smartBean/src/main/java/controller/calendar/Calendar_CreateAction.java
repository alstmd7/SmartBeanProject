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

//		Map map = request.getParameterMap();
//		Object[] keyset = map.keySet().toArray();
//		for(Object key : keyset) {
//			System.out.println(">> " + ((String) key));
//		}
		
		String calendarName = request.getParameter("calendarName");
//		System.out.println("calendarName : " + calendarName);
//		System.out.println("email : " + email);
		
        CalendarDao dao = CalendarDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        
        String email = (String) request.getSession().getAttribute("log");
        UserVo userVo = userDao.getUserByEmail(email);
        String owner = request.getParameter("owner");

        CalendarRequestDto calendarDto = new CalendarRequestDto(userVo.getCode(), owner, calendarName);
        
        response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
        
		boolean result = dao.createCalendar(calendarDto);
		String url = "calendar";
		
		if (result) {
			out.println("<script> alert('새로운 캘린더 생성이 완료되었습니다.'); </script>");
		} else {
			out.println("<script> alert('새로운 캘린더 생성이 실패하였습니다.'); </script>");
		}
		
		out.print("<script>location.href='"+url+"';</script>");
		
		out.close();
		
	}
	
}
