package controller.todo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.todo.TodoDao;
import model.todo.TodoVo;


/**
 * Servlet implementation class SearchTodoAction
 */
public class SearchTodoRequestAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTodoRequestAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TodoDao todoDao = TodoDao.getInstance();

		String email = (String) request.getSession().getAttribute("log");

		ArrayList<TodoVo> todo = todoDao.getTodoAll(email);

		request.setAttribute("todo", todo);
		
		request.getRequestDispatcher("todoList").forward(request, response);
	}

}
