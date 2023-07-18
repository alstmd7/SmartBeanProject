package controller.todo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.todo.TodoDao;
import model.todo.TodoRequestDto;
import model.todo.TodoVo;

/**
 * Servlet implementation class UpdateTodoAction
 */
@WebServlet("/UpdateTodoAction")
public class UpdateTodoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTodoAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		String target_at = request.getParameter("target_at");
		String content = request.getParameter("content");
		
		TodoDao todoDao = TodoDao.getInstance();
		
		TodoRequestDto todo = new TodoRequestDto(no, content, target_at);
		
		todoDao.updateTodo(todo);
	}

}
