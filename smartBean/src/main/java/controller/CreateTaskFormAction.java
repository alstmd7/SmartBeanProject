package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.task.TaskDao;
import model.task.TaskRequestDto;

/**
 * Servlet implementation class CreateTaskFormAction
 */
public class CreateTaskFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTaskFormAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int calendar_no = Integer.parseInt(request.getParameter("calendar_no"));
	    String name = request.getParameter("name");
	    
	    TaskRequestDto task = new TaskRequestDto(calendar_no, name);

		TaskDao taskDao = TaskDao.getInstance();
		boolean result = taskDao.createTask(calendar_no, name);
		
		String url = "createTask";

		if(result) 
			url = "home";

		response.sendRedirect(url);
	}

}
