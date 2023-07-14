package controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.calendar.CalendarDao;
import model.calendar.CalendarRequestDto;
import model.calendar.CalendarVo;
import model.user.UserDao;
import model.user.UserRequestDto;
import model.user.UserVo;

/**
 * Servlet implementation class joinAction
 */
public class JoinFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinFormAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    String name = request.getParameter("name");
	    
	    request.setAttribute("email", email);
		request.setAttribute("password", password);
		request.setAttribute("name", name);
		request.setAttribute("dupl", "동일한 아이디가 존재합니다.");
	    
	    UserRequestDto user = new UserRequestDto(email, password, name);

		UserDao userDao = UserDao.getInstance();
		boolean result = userDao.createUser(user);		
		
		if(result) {
			UserVo joinUser = userDao.getUserByEmail(email);
			CalendarRequestDto calendarRequestDto = new CalendarRequestDto(joinUser.getCode(), email, name);
			CalendarDao calendarDao = CalendarDao.getInstance();
			calendarDao.createCalendar(calendarRequestDto);
			response.sendRedirect("joinSuccess");
		} else {
			request.getRequestDispatcher("join").forward(request, response);
		}
	}

}
