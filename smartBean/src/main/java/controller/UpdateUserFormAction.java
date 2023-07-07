package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		UserRequestDto userDto = null;

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String new_password = request.getParameter("new_password");
		String name = request.getParameter("name");

		userDto = new UserRequestDto(email, password, name);

		UserDao userDao = UserDao.getInstance();
		userDao.updateUser(userDto, new_password);

		String url = "home";
		response.sendRedirect(url);
	}

}
