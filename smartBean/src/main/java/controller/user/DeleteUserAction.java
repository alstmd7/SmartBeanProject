package controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.calendar.CalendarDao;
import model.user.UserDao;
import model.user.UserRequestDto;
import model.user.UserVo;

/**
 * Servlet implementation class DropUserAction
 */
public class DeleteUserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserAction() {
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
		
		UserDao userDao = UserDao.getInstance();
		UserVo user = userDao.getUserByEmail(email);
		
		HttpSession session = request.getSession();
		if(user.getPassword().equals(password)) {
			userDao.deleteUserByEmail(email);
			session.setAttribute("log", null);
		    session.setAttribute("name", null);
		    request.setAttribute("message", "탈퇴가 완료되었습니다.");
		    request.getRequestDispatcher("alert").forward(request, response);
		} else {
			request.setAttribute("message", "비밀번호가 올바르지 않습니다.");
			request.getRequestDispatcher("alert").forward(request, response);
		}

	}

}
