package com.uitnetwork.alexa.service

import com.amazon.speech.speechlet.SpeechletResponse
import com.uitnetwork.alexa.model.AlexaIntentRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.Ordered.LOWEST_PRECEDENCE
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Service

@Order(LOWEST_PRECEDENCE)
@Service
class UnknownIntentService : AlexaIntentRequestService() {


    @Autowired
    private val speechletResponseService: SpeechletResponseService? = null

    override fun doProcess(alexaIntentRequest: AlexaIntentRequest): SpeechletResponse {
        return speechletResponseService!!.newAskResponse(UNKNOWN_MESSAGE, UNKNOWN_REPROMPT)
    }

    override fun canProcess(alexaIntentRequest: AlexaIntentRequest): Boolean {
        return true
    }

    companion object {
        val UNKNOWN_MESSAGE = "I don't understand what you said. Please say again."
        val UNKNOWN_REPROMPT = "Please say again."
    }
}
