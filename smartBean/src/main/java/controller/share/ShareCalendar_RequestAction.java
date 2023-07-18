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
	    int calendarNo = Integer.parseInt(request.getParameter("calendarNo"));

	    ShareDao shareDao = ShareDao.getInstance();
	    boolean result = shareDao.addSharedCalendar(userEmail, calendarNo);

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    // Result based on the success of the addSharedCalendar operation
	    if(result) {
	        response.getWriter().write("{\"message\":\"success\"}");
	    } else {
	        response.getWriter().write("{\"message\":\"fail\"}");
	    }
	}

}
