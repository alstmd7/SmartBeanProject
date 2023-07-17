package controller.calendar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import model.calendar.CalendarDao;
import model.event.EventDao;
import model.event.EventRequestDto;
import model.event.EventVo;

/**
 * Servlet implementation class Event_DeleteAction
 */
public class Event_DeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Event_DeleteAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int eventNo = Integer.parseInt(request.getParameter("eventNo"));

        EventDao eventDao = EventDao.getInstance();
        boolean success = eventDao.deleteEvent(eventNo);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();
        
        if (success) {
            json.put("success", true);
            json.put("message", "이벤트가 성공적으로 삭제되었습니다.");
        } else {
            json.put("success", false);
            json.put("message", "이벤트 삭제 실패");
        }
        
        out.print(json.toString());
    }

}
