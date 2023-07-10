package controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.user.UserDao;
import model.user.UserRequestDto;

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
	    
	    UserRequestDto user = new UserRequestDto(email, password, name);

		UserDao userDao = UserDao.getInstance();
		boolean result = userDao.createUser(user);
		
		String url = "JoinRequest";
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(result) {
			url = "home";
			out.println("<script> alert('회원가입이 완료되었습니다.'); </script>");
		} else {
			out.println("<script> alert('이미 가입된 이메일입니다.'); </script>");
		}
		out.print("<script>location.href='"+url+"';</script>");
		
		out.close();
	}

}
