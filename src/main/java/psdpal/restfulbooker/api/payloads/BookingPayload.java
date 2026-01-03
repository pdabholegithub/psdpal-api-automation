package psdpal.restfulbooker.api.payloads;

import java.util.HashMap;
import java.util.Map;

public class BookingPayload {
	public static Map<String, Object> createBookingPayload() {
		Map<String, Object> dates = new HashMap<>();
		dates.put("checkin", "2024-02-01");
		dates.put("checkout", "2024-02-10");
		
		Map<String, Object> payload = new HashMap<>();
		payload.put("firstname", "Prasad");
		payload.put("lastname", "Guruji");
		payload.put("totalprice", 1200);
		payload.put("depositpaid", true);
		payload.put("bookingdates", dates);
		payload.put("additionalneeds", "Breakfast");

		return payload;
	}

	public static Map<String, Object> updateBookingPayload() {

		Map<String, Object> dates = new HashMap<>();
		dates.put("checkin", "2024-03-01");
		dates.put("checkout", "2024-03-12");

		Map<String, Object> payload = new HashMap<>();
		payload.put("firstname", "Prasad");
		payload.put("lastname", "Updated");
		payload.put("totalprice", 1500);
		payload.put("depositpaid", false);
		payload.put("bookingdates", dates);
		payload.put("additionalneeds", "Lunch");

		return payload;
	}
}
