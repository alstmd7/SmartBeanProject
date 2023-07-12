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
		boolean result = userDao.updateUser(userDto, new_password);
		
		String url = "UpdateUserRequest";
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(result) {
			// 업데이트 성공 시
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			
			url = "home";
			out.println("<script> alert('업데이트가 완료되었습니다.');</script>");
		} else {
			out.println("<script> alert('업데이트에 실패했습니다.'); </script>");
		}
		out.print("<script>location.href='"+url+"';</script>");

		// response.sendRedirect 사용불가
		out.close();

	}

}
