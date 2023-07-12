package controller.todo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.todo.TodoDao;
import model.todo.TodoVo;

/**
 * Servlet implementation class LoadTodoAction
 */
@WebServlet("/LoadTodoAction")
public class LoadTodoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadTodoAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TodoDao todoDao = TodoDao.getInstance();
		String emailUser = (String) request.getSession().getAttribute("log");
		ArrayList<TodoVo> todo = todoDao.getTodoAll(emailUser);
		
		JSONArray responseList = new JSONArray(todo);
		response.getWriter().append(responseList.toString());
	}

}
