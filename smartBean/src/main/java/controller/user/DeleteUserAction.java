package controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.calendar.CalendarDao;
import model.calendar.CalendarVo;
import model.user.UserDao;
import model.user.UserRequestDto;
import model.user.UserVo;

/**
 * Servlet implementation class DropUserAction
 */
public class DeleteUserAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");	
		
		UserDao userDao = UserDao.getInstance();
		UserVo user = userDao.getUserByEmail(email);
		
		HttpSession session = request.getSession();
		
		// 입력한 비밀번호와 user 비밀번호가 같으면
		if(user.getPassword().equals(password)) {
			
			// 유저의 code값과 같은 p_code를 가진 calendar_no값 반환
			CalendarDao calendarDao = CalendarDao.getInstance();
			int code = user.getCode();
			int calendar_no = 0;
			ArrayList<CalendarVo>calendarList = calendarDao.getAllCalendars(email);
			for (int i = 0; i < calendarList.size(); i++) {
				int p_code = calendarList.get(i).getP_code();
				if (p_code == code) {
					calendar_no = calendarList.get(i).getNo();
				}
			}
			System.out.println(calendar_no);
			// 캘린더 -> 유저 삭제
			calendarDao.deleteCalendarByNo(calendar_no);
			userDao.deleteUserByEmail(email);
			
			session.setAttribute("log", null);
		    session.setAttribute("name", null);
		    request.setAttribute("message", "탈퇴가 완료되었습니다.");
		    request.getRequestDispatcher("alert").forward(request, response);
		} else {
			request.setAttribute("message", "비밀번호가 올바르지 않습니다.");
			request.getRequestDispatcher("alert").forward(request, response);
		}

	}

}
