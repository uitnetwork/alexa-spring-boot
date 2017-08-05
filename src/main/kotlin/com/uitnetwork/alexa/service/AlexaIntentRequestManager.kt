package com.uitnetwork.alexa.service

import com.amazon.speech.speechlet.SpeechletResponse
import com.uitnetwork.alexa.exception.AlexaException
import com.uitnetwork.alexa.model.AlexaIntentRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AlexaIntentRequestManager {

    @Autowired
    private lateinit var alexaIntentRequestServices: List<AlexaIntentRequestService>;

    fun process(alexaIntentRequest: AlexaIntentRequest): SpeechletResponse {
        for (alexaIntentRequestService in alexaIntentRequestServices) {
            if (alexaIntentRequestService.canProcess(alexaIntentRequest)) {
                return alexaIntentRequestService.doProcess(alexaIntentRequest)
            }
        }
        throw AlexaException("Something is wrong. Should never get this exception: " + alexaIntentRequest)
    }
}
