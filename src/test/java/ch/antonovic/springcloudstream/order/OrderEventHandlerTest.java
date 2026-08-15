package ch.antonovic.springcloudstream.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootTest(properties = "spring.cloud.stream.default-binder=test")
@Import(TestChannelBinderConfiguration.class)
class OrderEventHandlerTest {

	@Autowired
	private InputDestination inputDestination;

	@Autowired
	private OrderEventHandler orderEventHandler;

	@Test
	void givenOrderEvent_whenSentToOrdersDestination_thenHandlerProcessesIt() {
		// given
		OrderEvent orderEvent = OrderEvent.of("order-1", "widget", 3);

		// when
		inputDestination.send(MessageBuilder.withPayload(orderEvent).build(), "orders");

		// then
		assertThat(orderEventHandler.getProcessedEvents()).contains(orderEvent);
	}

}
