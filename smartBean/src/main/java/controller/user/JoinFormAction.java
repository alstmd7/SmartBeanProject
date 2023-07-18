package controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.calendar.CalendarDao;
import model.calendar.CalendarRequestDto;
import model.calendar.CalendarVo;
import model.share.ShareDao;
import model.user.UserDao;
import model.user.UserRequestDto;
import model.user.UserVo;

/**
 * Servlet implementation class joinAction
 */
public class JoinFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinFormAction() {
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
	    String name = request.getParameter("name");
	    
	    request.setAttribute("email", email);
		request.setAttribute("password", password);
		request.setAttribute("name", name);
	    
	    UserRequestDto user = new UserRequestDto(email, password, name);

		UserDao userDao = UserDao.getInstance();
		boolean result = userDao.createUser(user);		
		
		if(result) {
			// p_code를 가진 유저의 기본 캘린더 생성
			UserVo joinUser = userDao.getUserByEmail(email);
			int code_user = joinUser.getCode();
			CalendarRequestDto calendarRequestDto = new CalendarRequestDto(code_user, email, name, code_user);
			CalendarDao calendarDao = CalendarDao.getInstance();
			boolean calendar_result = calendarDao.createCalendar(calendarRequestDto);
			
			// 유저의 code값과 같은 p_code를 가진 calendar_no값 반환
			int calendar_no = 0;
			ArrayList<CalendarVo> calendarList = calendarDao.getAllCalendars(email);
			for (int i = 0; i < calendarList.size(); i++) {
				int p_code = calendarList.get(i).getP_code();
				if (p_code == code_user) {
					calendar_no = calendarList.get(i).getNo();
				}
			}
			System.out.println(calendar_no);
			
			// 기본 캘린더 공유 추가
			ShareDao shareDao = ShareDao.getInstance();
			shareDao.addSharedCalendar(email, calendar_no);
		
			request.setAttribute("message", "회원가입이 완료되었습니다. 로그인 후 이용해주세요.");
			
			request.getRequestDispatcher("alert").forward(request, response);
		} else {
			request.setAttribute("message", "동일한 아이디가 존재합니다.");

			request.getRequestDispatcher("join").forward(request, response);
		}
	}

}
