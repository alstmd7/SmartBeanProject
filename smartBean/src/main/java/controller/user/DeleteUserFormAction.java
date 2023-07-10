package controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.user.UserDao;
import model.user.UserRequestDto;
import model.user.UserVo;

/**
 * Servlet implementation class DropUserAction
 */
public class DeleteUserFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserFormAction() {
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
		boolean result = userDao.deleteUserByEmail(email);
		
		String url = "deleteUser";
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if (result) {
			HttpSession session = request.getSession();
	    	session.setAttribute("log", null);
	    	session.setAttribute("name", null);

			url = "login";
			out.println("<script> alert('회원탈퇴가 완료되었습니다.');</script>");
		} else {
			out.println("<script> alert('회원탈퇴에 실패했습니다.'); </script>");
		}
		out.print("<script>location.href='"+url+"';</script>");

		response.sendRedirect(url);
	}

}
