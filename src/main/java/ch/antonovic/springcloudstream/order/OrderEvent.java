package ch.antonovic.springcloudstream.order;

import java.time.Instant;

public record OrderEvent(String orderId, String product, int quantity, Instant timestamp) {

	public static OrderEvent of(String orderId, String product, int quantity) {
		return new OrderEvent(orderId, product, quantity, Instant.now());
	}

}
