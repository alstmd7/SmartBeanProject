package controller.share;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import model.calendar.CalendarVo;
import model.share.ShareDao;
import model.share.ShareVo;

/**
 * Servlet implementation class LoadShareAction
 */
@WebServlet("/LoadShare")
public class LoadShareAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadShareAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 한글 및 json 형태로 전달
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        ShareDao shareDao = ShareDao.getInstance();
        String email = (String) request.getSession().getAttribute("log");
        ArrayList<CalendarVo> sharedCalendars = shareDao.getSharedCalendars(email);

        // 공유된 캘린더 목록을 JSON 배열로 변환
        JSONArray jsonArray = new JSONArray(sharedCalendars);

        // 응답 데이터로 JSON 배열 전송
        response.getWriter().append(jsonArray.toString());
    }


}
