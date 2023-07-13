package controller.calendar;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.event.EventDao;
import model.event.EventVo;

/**
 * Servlet implementation class Event_RequestAction
 */
@WebServlet("/Event_RequestAction")
public class Event_RequestAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Event_RequestAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 EventDao eventDao = EventDao.getInstance();
		    ArrayList<EventVo> eventList = eventDao.getEventAll();

		    ArrayList<EventRequestDto> eventDtoList = new ArrayList<>();
		    for (EventVo event : eventList) {
		        EventRequestDto eventDto = new EventRequestDto(
		            event.getNo(),
		            event.getCalendar_no(),
		            event.getTask_no(),
		            event.getName(),
		            event.getEmail(),
		            event.getTitle(),
		            event.getContent(),
		            event.getStart(),
		            event.getEnd(),
		            event.getAll_day()
		        );
		        eventDtoList.add(eventDto);
		    }

		    JSONArray jsonArray = new JSONArray();
		    for (EventRequestDto event : eventDtoList) {
		        JSONObject jsonObject = new JSONObject();
		        jsonObject.put("no", event.getNo());
		        jsonObject.put("calendar_no", event.getCalendar_no());
		        jsonObject.put("task_no", event.getTask_no());
		        jsonObject.put("name", event.getName());
		        jsonObject.put("email", event.getEmail());
		        jsonObject.put("title", event.getTitle());
		        jsonObject.put("content", event.getContent());
		        jsonObject.put("start", event.getStart());
		        jsonObject.put("end", event.getEnd());
		        jsonObject.put("all_day", event.getAll_day());

		        jsonArray.put(jsonObject);
		    }

		    out.print(jsonArray.toString());
		    
		    request.setCharacterEncoding("UTF-8");
		    response.setContentType("application/json; charset=UTF-8");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
