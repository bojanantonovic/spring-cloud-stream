package ch.antonovic.springcloudstream.order;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderEventHandler implements Consumer<OrderEvent> {

	private final List<OrderEvent> processedEvents = new CopyOnWriteArrayList<>();

	@Override
	public void accept(OrderEvent orderEvent) {
		log.info("Received order event: {}", orderEvent);
		processedEvents.add(orderEvent);
	}

	public List<OrderEvent> getProcessedEvents() {
		return List.copyOf(processedEvents);
	}

}
