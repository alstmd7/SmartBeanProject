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

	// DB연동완료 DB 연동완료 DB연동완료 DB 연동완료 DB연동완료 DB 연동완료 DB연동완료 DB 연동완료 DB연동완료 DB 연동완료
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
//		UserDao userDao = UserDao.getInstance();
		
		String email = (String) request.getSession().getAttribute("log");
//        UserVo userVo = userDao.getUserByEmail(email);
		String name = request.getParameter("name");

		TaskDao taskDao = TaskDao.getInstance();
		taskDao.createTask(email, name);
		
		TaskRequestDto taskDto = new TaskRequestDto(email, name);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
        
		boolean result = taskDao.createTask(email, name);
//		boolean result = taskDao.createTask(taskDto);
		String url = "calendar";
		
		
		out.print("<script>location.href='"+url+"';</script>");
		
		out.close();

	}

}
