// FindCalendarNoServlet.java

package controller.calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import model.calendar.CalendarDao;
import model.calendar.CalendarVo;

public class FindCalendarNo_Action extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindCalendarNo_Action() {
        super();
    }

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String calendarName = request.getParameter("calendarName");

        CalendarDao dao = CalendarDao.getInstance();
        CalendarVo calendar = dao.getCalendarByName(calendarName);

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("{\"calendarNo\": " + calendar.getNo() + "}");
        out.flush();
        
        if(calendar != null) {
            out.print("{\"calendarNo\": " + calendar.getNo() + "}");
        } else {
            out.print("{\"error\": \"No calendar found with provided name.\"}");
        }

        
    }
}
