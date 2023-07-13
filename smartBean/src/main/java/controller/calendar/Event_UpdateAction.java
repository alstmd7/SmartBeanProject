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

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email"); // 세션에서 이메일 가져오기

		String name = request.getParameter("name");
		String title = request.getParameter("event-title-input");
		String content = request.getParameter("event-description");
		String start = request.getParameter("start-date");
		String end = request.getParameter("end-date");
		String all_day = request.getParameter("all_day");

		// 이벤트의 ID를 사용하여 DB에서 이벤트 정보 가져오기
		int eventId = Integer.parseInt(request.getParameter("eventId")); // 이벤트 ID를 얻기 위한 적절한 파라미터명을 사용해야 합니다.
		EventDao eventDao = EventDao.getInstance();
		EventVo currentEvent = eventDao.getEventById(eventId);

		// 현재 이벤트의 task_no와 calendar_no 가져오기
		int task_no = currentEvent.getTask_no();
		int calendar_no = currentEvent.getCalendar_no();

		// EventRequestDto 객체 생성 및 값 설정
		EventRequestDto eventDto = new EventRequestDto(name, title, content, start, end, all_day);
		eventDto.setNo(eventId);
		eventDto.setEmail(email);
		eventDto.setTask_no(task_no);
		eventDto.setCalendar_no(calendar_no);

		boolean result = eventDao.updateEvent(eventDto);

        if(result) {
            response.getWriter().write("{\"상태\": \"event update 성공\"}");
        } else {
            response.getWriter().write("{\"상태\": \"event update 실패\", \"message\": \"event update 실패\"}");
        }
	}


}
