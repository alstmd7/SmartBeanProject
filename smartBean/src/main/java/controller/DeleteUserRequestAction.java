package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.user.UserDao;
import model.user.UserVo;

/**
 * Servlet implementation class DeleteUserRequestAction
 */

public class DeleteUserRequestAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserRequestAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		UserDao userDao = UserDao.getInstance();

		String email = (String) request.getSession().getAttribute("log");
		
		UserVo user = userDao.getUserByEmail(email);
		
		String url = "/login"; 

		if(user != null) {
			request.setAttribute("email", email);
			
			url = "/deleteUser";
		}

		request.getRequestDispatcher(url).forward(request, response);
	}

}
