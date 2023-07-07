package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.user.UserDao;

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
		String email = (String) request.getSession().getAttribute("log");
		
		UserDao userDao = UserDao.getInstance();
		boolean result = userDao.deleteUserByEmail(email);
		
		String url = "deleteUser";
				
		if(result) {
			request.getSession().removeAttribute("log");
			url = "index.jsp";
		}
		
		response.sendRedirect(url);
	}

}
