package ch.antonovic.springcloudstream.order;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler implements Consumer<OrderEvent> {

	private static final Logger log = LoggerFactory.getLogger(OrderEventHandler.class);

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
