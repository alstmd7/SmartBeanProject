package controller.share;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.share.ShareDao;

/**
 * Servlet implementation class ShareCalendar_RequestAction
 */
@WebServlet("/Calendar_RequestAction")
public class ShareCalendar_RequestAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareCalendar_RequestAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");

	    String userEmail = request.getParameter("email");
	    int calendarNo = request.getParameter();

	    ShareDao shareDao = ShareDao.getInstance();
	    shareDao.addShareEmail(userEmail, calendarNo);

	    response.setContentType("text/plain");
	    response.getWriter().write("이메일 정보가 성공적으로 처리되었습니다.");
	}


	
}