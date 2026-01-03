package psdpal.restfulbooker.api.services;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingService {

	/**
	 * CREATE booking (POST)
	 */
	public Response createBooking(Object payload, Object requestSpec) {
		return given().spec((io.restassured.specification.RequestSpecification) requestSpec).body(payload).when()
				.post("/booking").then().extract().response();
	}

	/**
	 * GET booking by id
	 */
	public Response getBooking(int bookingId, Object requestSpec) {
		return given().spec((io.restassured.specification.RequestSpecification) requestSpec).when()
				.get("/booking/{id}", bookingId).then().extract().response();
	}

	/**
	 * UPDATE booking (PUT)
	 */
	public Response updateBooking(int bookingId, Object payload, Object requestSpec) {
		return given().spec((io.restassured.specification.RequestSpecification) requestSpec).body(payload).when()
				.put("/booking/{id}", bookingId).then().extract().response();
	}

	/**
	 * DELETE booking
	 */
	public Response deleteBooking(int bookingId, Object requestSpec) {
		return given().spec((io.restassured.specification.RequestSpecification) requestSpec).when()
				.delete("/booking/{id}", bookingId).then().extract().response();
	}
}
