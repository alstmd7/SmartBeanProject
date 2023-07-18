package controller.todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.todo.TodoDao;
import model.todo.TodoRequestDto;


/**
 * Servlet implementation class CreateTodoFormAction
 */
public class CreateTodoFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTodoFormAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String email = (String) request.getSession().getAttribute("log");
		String content = request.getParameter("content_add");
		String target_at = request.getParameter("target_at_add");
	    
	    TodoRequestDto todo = new TodoRequestDto(email, content, target_at);

		TodoDao todoDao = TodoDao.getInstance();
		todoDao.createTodo(todo);
		response.sendRedirect("todoList");
	}

}
