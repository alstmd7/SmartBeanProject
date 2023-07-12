package controller.calendar;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.task.TaskDao;
import model.task.TaskRequestDto;
import model.user.UserDao;
import model.user.UserVo;


/**
 * Servlet implementation class Task_CreateAction
 */
@WebServlet("/Task_CreateAction")
public class Task_CreateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Task_CreateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String email = (String) request.getSession().getAttribute("log");
		String name = request.getParameter("name");

		TaskDao taskDao = TaskDao.getInstance();
		taskDao.createTask(email, name);

	}

}
