package controller.user;

import java.io.IOException;
import java.io.PrintWriter;

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
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if(user != null && user.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("log", email);
			session.setAttribute("name", user.getName());
			
			url = "home";
		} else {
			out.println("<script> alert('아이디/비밀번호를 확인해주세요.'); </script>");
		}
		out.print("<script>location.href='"+url+"';</script>");
		
		out.close();
	}

}
