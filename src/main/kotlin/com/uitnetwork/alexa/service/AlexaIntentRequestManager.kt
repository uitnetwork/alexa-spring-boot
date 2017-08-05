package com.uitnetwork.alexa.service

import com.amazon.speech.speechlet.SpeechletResponse
import com.uitnetwork.alexa.exception.AlexaException
import com.uitnetwork.alexa.model.IntentRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AlexaIntentRequestManager {
    private val logger = LoggerFactory.getLogger(AlexaIntentRequestManager::class.java)

    @Autowired
    private lateinit var intentRequestProcessorServices: List<IntentRequestProcessorService>

    fun process(intentRequest: IntentRequest): SpeechletResponse {
        logger.info("Processing IntentRequest: $intentRequest")

        for (alexaIntentRequestService in intentRequestProcessorServices) {
            if (alexaIntentRequestService.canProcess(intentRequest)) {
                return alexaIntentRequestService.doProcess(intentRequest)
            }
        }
        throw AlexaException("Something is wrong. Should never get this exception: " + intentRequest)
    }
}
