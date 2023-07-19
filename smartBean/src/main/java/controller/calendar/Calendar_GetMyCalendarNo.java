package controller.calendar;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import model.calendar.CalendarDao;
import model.calendar.CalendarRequestDto;
import model.calendar.CalendarVo;
import model.user.UserDao;
import model.user.UserVo;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.calendar.CalendarDao;
import model.calendar.CalendarVo;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.calendar.CalendarDao;
import model.calendar.CalendarVo;

/**
 * Servlet implementation class Calendar_GetMyCalendarNo
 */
public class Calendar_GetMyCalendarNo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Calendar_GetMyCalendarNo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");

	    String calendarName = request.getParameter("calendarName");
	    CalendarDao calendarDao = CalendarDao.getInstance();
	    UserDao userDao = UserDao.getInstance();
	    
	    String email = (String) request.getSession().getAttribute("log");
	    UserVo user = userDao.getUserByEmail(email);

	    response.setContentType("application/json");
	    
		// 유저의 code값과 같은 p_code를 가진 calendar_no값 반환
		int calendar_no = 0;
		ArrayList<CalendarVo> calendarList = calendarDao.getAllCalendars(email);
		for (int i = 0; i < calendarList.size(); i++) {
			int p_code = calendarList.get(i).getP_code();
			if (p_code == user.getCode()) {
				calendar_no = calendarList.get(i).getNo();
			}
		}
		
		CalendarVo calVo = calendarDao.getCalendarByNo(calendar_no);
		
		JSONObject responseList = new JSONObject(calVo);
		response.getWriter().append(responseList.toString());
	}

}
