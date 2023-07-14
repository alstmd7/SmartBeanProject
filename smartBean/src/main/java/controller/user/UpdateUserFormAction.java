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
 * Servlet implementation class UpdateUserFormAction
 */
public class UpdateUserFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserFormAction() {
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
		String new_password = request.getParameter("new_password");
		String name = request.getParameter("name");

		UserDao userDao = UserDao.getInstance();
		UserVo user = userDao.getUserByEmail(email);
		boolean result = userDao.updateUser(user, new_password);
		
		if(password == user.getPassword()) {
			if(result) {
				// 업데이트 성공 시
				HttpSession session = request.getSession();
				session.setAttribute("name", name);
				request.setAttribute("message", "회원정보 수정이 완료되었습니다.");
				
				request.getRequestDispatcher("alert").forward(request, response);
			} else {
				request.getRequestDispatcher("updateUser").forward(request, response);
			}
		} else {			
			response.sendRedirect("login");
		}

	}

}
