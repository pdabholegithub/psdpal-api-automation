package psdpal.restfulbooker.tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import psdpal.base.BaseTest;
import psdpal.restfulbooker.api.payloads.BookingPayload;
import psdpal.restfulbooker.api.services.BookingService;

public class BookingE2ETest extends BaseTest {

	@Test
	public void bookingCrudFlowTest() {

		BookingService bookingService = new BookingService();

		// 1️. CREATE BOOKING
		Response createResponse = bookingService.createBooking(BookingPayload.createBookingPayload(), publicSpec);

		Assert.assertEquals(createResponse.getStatusCode(), 200);

		int bookingId = createResponse.jsonPath().getInt("bookingid");
		Assert.assertTrue(bookingId > 0, "Booking ID should be generated");

		System.out.println("Booking created with ID: " + bookingId);

		// 2️. GET BOOKING
		Response getResponse = bookingService.getBooking(bookingId, publicSpec);

		Assert.assertEquals(getResponse.getStatusCode(), 200);
		Assert.assertEquals(getResponse.jsonPath().getString("firstname"), "Prasad");

		// 3️. UPDATE BOOKING
		Response updateResponse = bookingService.updateBooking(bookingId, BookingPayload.updateBookingPayload(),
				authSpec);

		Assert.assertEquals(updateResponse.getStatusCode(), 200);
		Assert.assertEquals(updateResponse.jsonPath().getString("lastname"), "Updated");

		// 4️. DELETE BOOKING
		Response deleteResponse = bookingService.deleteBooking(bookingId, authSpec);

		Assert.assertTrue(deleteResponse.getStatusCode() == 201 || deleteResponse.getStatusCode() == 200);

		System.out.println("Booking deleted successfully");
	}
}
