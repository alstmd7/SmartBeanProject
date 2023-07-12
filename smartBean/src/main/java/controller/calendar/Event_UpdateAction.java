package controller.calendar;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.event.EventDao;
import model.event.EventRequestDto;
import model.user.UserRequestDto;

/**
 * Servlet implementation class Event_UpdateAction
 */
@WebServlet("/Event_UpdateAction")
public class Event_UpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Event_UpdateAction() {
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
		
		EventRequestDto eventDto = null;

		EventDao eventDao = EventDao.getInstance();
		
//		int calendarCode = Integer.parseInt(request.getParameter("calendarCode"));
//		int taskNo = Integer.parseInt(request.getParameter("taskNo"));
//		String name = request.getParameter("name");
//        String email = request.getParameter("email");
        String taskTitle = request.getParameter("tastTitle");
        String teakContent = request.getParameter("teakContent");
        int startDate =  Integer.parseInt(request.getParameter("startDate"));
        int endDate =  Integer.parseInt(request.getParameter("endDate"));
        String all_day = request.getParameter("all_day");
        
        boolean result = eventDao.updateEvent(eventDto);
	}

}
