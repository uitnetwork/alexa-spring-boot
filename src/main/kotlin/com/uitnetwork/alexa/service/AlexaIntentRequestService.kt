package com.uitnetwork.alexa.service

import com.amazon.speech.speechlet.SpeechletResponse
import com.uitnetwork.alexa.model.AlexaIntentRequest
import org.springframework.core.annotation.Order

@Order(1)
abstract class AlexaIntentRequestService {

    abstract fun doProcess(alexaIntentRequest: AlexaIntentRequest): SpeechletResponse

    abstract fun canProcess(alexaIntentRequest: AlexaIntentRequest): Boolean
}
