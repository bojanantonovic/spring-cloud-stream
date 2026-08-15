package ch.antonovic.springcloudstream.order;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

	private final StreamBridge streamBridge;

	@PostMapping
	public ResponseEntity<Void> publish(@RequestBody OrderEvent orderEvent) {
		streamBridge.send("orders-out-0", orderEvent);
		return ResponseEntity.accepted().build();
	}

}
