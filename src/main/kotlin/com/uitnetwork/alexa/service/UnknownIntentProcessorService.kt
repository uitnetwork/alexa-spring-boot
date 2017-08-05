package com.uitnetwork.alexa.service

import com.amazon.speech.speechlet.SpeechletResponse
import com.uitnetwork.alexa.model.IntentRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.Ordered.LOWEST_PRECEDENCE
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Service

@Order(LOWEST_PRECEDENCE)
@Service
class UnknownIntentProcessorService : IntentRequestProcessorService {


    @Autowired
    private val speechletResponseService: SpeechletResponseService? = null

    override fun doProcess(intentRequest: IntentRequest): SpeechletResponse {
        return speechletResponseService!!.newAskResponse(UNKNOWN_MESSAGE, UNKNOWN_REPROMPT)
    }

    override fun canProcess(intentRequest: IntentRequest): Boolean {
        return true
    }

    companion object {
        val UNKNOWN_MESSAGE = "I don't understand what you said. Please say again."
        val UNKNOWN_REPROMPT = "Please say again."
    }
}
