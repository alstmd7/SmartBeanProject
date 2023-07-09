package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.user.UserDao;
import model.user.UserVo;

/**
 * Servlet implementation class LoginFormAction
 */
public class LoginFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginFormAction() {
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

		String url = "login";

		if(user != null && user.getPassword().equals(password)) {
			// session log에 로그인한 email 값 넣기
			HttpSession session = request.getSession();
			session.setAttribute("log", email);
			session.setAttribute("name", user.getName());
			
			url = "home";
			
		} else {
			// 비밀번호 실패 알림 추가
		}

		response.sendRedirect(url);
	}

}
