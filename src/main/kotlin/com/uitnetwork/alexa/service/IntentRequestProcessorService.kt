package com.uitnetwork.alexa.service

import com.amazon.speech.speechlet.SpeechletResponse
import com.uitnetwork.alexa.model.IntentRequest
import org.springframework.core.annotation.Order

@Order(1)
interface IntentRequestProcessorService {

    fun doProcess(intentRequest: IntentRequest): SpeechletResponse

    fun canProcess(intentRequest: IntentRequest): Boolean
}
