package controller.calendar;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.calendar.CalendarDao;
import model.calendar.CalendarRequestDto;
import model.calendar.CalendarVo;

@WebServlet("/CalendarAction")
public class CalendarAction extends HttpServlet {
	
    private static final long serialVersionUID = 1L;

    public CalendarAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        CalendarDao dao = CalendarDao.getInstance();

        // Create
        System.out.println("----- Create -----");
        String email = (String) request.getSession().getAttribute("log");;
        String name = request.getParameter("newCalendarName");

        CalendarRequestDto calendarDto = new CalendarRequestDto(0, email, name);

        boolean isSuccess1 = dao.createCalendar(calendarDto);
        if (isSuccess1) {
            System.out.println("Create 성공");
        } else {
            System.out.println("Create 실패");
        }

        // Read
        ArrayList<CalendarVo> allCalendars = dao.getAllCalendars(email);
        if (!allCalendars.isEmpty()) {
            System.out.println("----- Read All Calendars -----");
            for (CalendarVo calendar : allCalendars) {
                System.out.println(calendar);
            }
        } else {
            System.out.println("Read 실패");
        }

        // Update
        System.out.println("----- Update -----");
        int updateCalendarNo = 1;
        String newCalendarName = "New Test Calendar";
        dao.updateCalendarName(updateCalendarNo, newCalendarName);
        System.out.println("Update 성공");

        // Read after update
        System.out.println("----- Read All Calendars -----");
        allCalendars = dao.getAllCalendars(email);
        for (CalendarVo calendar : allCalendars) {
            System.out.println(calendar);
        }

        // Delete
        System.out.println("----- Delete -----");
        int deleteCalendarNo = 2;
        boolean deleteResult = dao.deleteCalendarByNo(deleteCalendarNo);
        if (deleteResult) {
            System.out.println("Delete 성공: " + deleteResult);
        } else {
            System.out.println("Delete 실패");
        }

        // Read after delete
        System.out.println("----- Read All Calendars -----");
        allCalendars = dao.getAllCalendars(email);
        for (CalendarVo calendar : allCalendars) {
            System.out.println(calendar);
        }
    }
}
