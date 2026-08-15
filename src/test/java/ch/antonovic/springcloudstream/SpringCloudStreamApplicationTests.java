package ch.antonovic.springcloudstream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootTest(properties = "spring.cloud.stream.default-binder=test")
@Import(TestChannelBinderConfiguration.class)
class SpringCloudStreamApplicationTests {

	@Test
	void contextLoads() {
	}

}
