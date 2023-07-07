package controller;

import java.util.ArrayList;

import model.calendar.CalendarDao;
import model.calendar.CalendarRequestDto;
import model.calendar.CalendarVo;

public class CalendarActionTest {

	public static void main(String[] args) {
		CalendarDao dao = CalendarDao.getInstance();

		// Create
		System.out.println("----- Create -----");
		CalendarRequestDto testDto1 = new CalendarRequestDto(1, "juntu09@gmail.com", "test2");

		boolean isSuccess1 = dao.createCalendar(testDto1);
		if (isSuccess1) {
			System.out.println("Create 성공");
		} else {
			System.out.println("Create 실패");
		}

		// Read
		ArrayList<CalendarVo> allCalendars = dao.getAllCalendars();
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
		allCalendars = dao.getAllCalendars();
		for (CalendarVo calendar : allCalendars) {
			System.out.println(calendar);
		}

		// Delete
		System.out.println("----- Delete -----");
		int deleteCalendarNo = 2;
		boolean deleteResult = dao.deleteCalendarByEmail(deleteCalendarNo);
		if (deleteResult) {
			System.out.println("Delete 성공: " + deleteResult);
		} else {
			System.out.println("Delete 실패");
		}

		// Read after delete
		System.out.println("----- Read All Calendars -----");
		allCalendars = dao.getAllCalendars();
		for (CalendarVo calendar : allCalendars) {
			System.out.println(calendar);
		}
	}
}
