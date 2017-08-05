package com.uitnetwork.alexa

import com.uitnetwork.alexa.service.IntentRequestProcessorService
import com.uitnetwork.alexa.service.UnknownIntentProcessorService
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class AlexaSpringBootApplicationTests {

    @Autowired
    private lateinit var intentRequestProcessorServices: List<IntentRequestProcessorService>;

    @Test
    fun contextLoads() {
    }

    @Test
    fun unknownIntentProcessorServiceShouldBeInTheLastOne() {
        val lastTntentRequestProcessorService = intentRequestProcessorServices.last()

        assertThat(lastTntentRequestProcessorService is UnknownIntentProcessorService)
    }

}
