package controller.todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.todo.TodoDao;
import model.todo.TodoVo;

/**
 * Servlet implementation class GetTodoAction
 */
@WebServlet("/GetTodoAction")
public class GetTodoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTodoAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		
		int no = Integer.parseInt(request.getParameter("todoNo")); 
		
		TodoDao todoDao = TodoDao.getInstance();
		TodoVo todo = todoDao.getTodoByNo(no);
		
		JSONObject responseList = new JSONObject(todo);
		response.getWriter().append(responseList.toString());
	}

}
