package controller.calendar;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.event.EventDao;
import model.event.EventRequestDto;
import model.event.EventVo;
import model.user.UserRequestDto;

/**
 * Servlet implementation class Event_UpdateAction
 */
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
		
		int no = Integer.parseInt(request.getParameter("no")); // 이벤트 ID를 얻기 위한 적절한 파라미터명을 사용해야 합니다.
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		EventDao eventDao = EventDao.getInstance();
		EventRequestDto eventDto = new EventRequestDto(no, title, content, start, end);
		eventDao.updateEvent(eventDto);
	}


}
