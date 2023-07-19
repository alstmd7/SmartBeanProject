package controller.calendar;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import model.calendar.CalendarVo;
import model.task.TaskDao;
import model.task.TaskVo;

/**
 * Servlet implementation class Task_ReadAction
 */
public class Task_ReadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Task_ReadAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// admin , user 두 번 불러야 함 
		// 한글 및 json 형태로 전달
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");

		TaskDao taskDao = TaskDao.getInstance();
		ArrayList<TaskVo> adminTask = taskDao.getTaskAll("admin");
		// System.out.println(adminTask.get(0).getName());
		
		String email = (String) request.getSession().getAttribute("log");
		ArrayList<TaskVo> userTask = taskDao.getTaskAll(email);
		// System.out.println(userTask.get(0).getName());
		
		for(int i = 0; i < userTask.size(); i++) {
			adminTask.add(userTask.get(i));
		}
		
		JSONArray responseList = new JSONArray(adminTask);
		response.getWriter().append(responseList.toString());

		
	}

}
