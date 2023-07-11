package controller.todo;

import java.io.IOException;
import java.io.PrintWriter;

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
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String email = (String) request.getSession().getAttribute("log");
		String content = request.getParameter("content");
		String dateInfo = request.getParameter("target_at");
		String[] data = dateInfo.split("-"); 
		int target_at = Integer.parseInt(data[0]+data[1]+data[2]);
//		out.println("<script> console.log(" + target_at + "); </script>");
	    
	    TodoRequestDto todo = new TodoRequestDto(email, content, target_at);

		TodoDao todoDao = TodoDao.getInstance();
		boolean result = todoDao.createTodo(todo);
		
		if(result) {
			out.println("<script> alert('일정이 추가되었습니다.'); </script>");
		} else {
			out.println("<script> alert('일정 추가에 실패했습니다.'); </script>");
		}

		out.close();
	}

}