package controller.todo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.todo.TodoDao;
import model.todo.TodoVo;

/**
 * Servlet implementation class CheckTodoAction
 */
public class CheckTodoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckTodoAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		
		int check = Integer.parseInt(request.getParameter("check"));
		String no_check = request.getParameter("no");
		int no = Integer.parseInt(no_check.substring(5, no_check.length()));
		
		TodoDao todoDao = TodoDao.getInstance();
		todoDao.checkTodo(check, no);
	}

}
