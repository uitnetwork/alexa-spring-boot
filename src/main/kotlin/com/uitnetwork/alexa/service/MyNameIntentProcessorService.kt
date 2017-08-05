package com.uitnetwork.alexa.service

import com.amazon.speech.speechlet.SpeechletResponse
import com.uitnetwork.alexa.exception.IntentRequestProcessingException
import com.uitnetwork.alexa.model.Intent.MY_NAME_INTENT
import com.uitnetwork.alexa.model.IntentRequest
import com.uitnetwork.alexa.util.AppConstants.Companion.SESSION_NAME
import org.slf4j.LoggerFactory.getLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MyNameIntentProcessorService : IntentRequestProcessorService {
    private val logger = getLogger(MyNameIntentProcessorService::class.java)

    companion object {
        private val SLOT_MY_NAME = "MY_NAME"

        val MY_NAME_MESSAGE = "Hello %s. What can I do for you?"
        val MY_NAME_PROMPT = "What can I do for you, %s?"
    }

    @Autowired
    private lateinit var speechletResponseService: SpeechletResponseService

    override fun doProcess(intentRequest: IntentRequest): SpeechletResponse {
        val myNameSlotValue = intentRequest.getSlotValue(SLOT_MY_NAME) ?: throw IntentRequestProcessingException("$SLOT_MY_NAME is missing.")

        val message = String.format(MY_NAME_MESSAGE, myNameSlotValue)
        val repromptMessage = String.format(MY_NAME_PROMPT, myNameSlotValue)

        intentRequest.originalRequestEnvelope.session.setAttribute(SESSION_NAME, myNameSlotValue)

        logger.info("Message: {} and repromptMessage: {}", message, repromptMessage)

        return speechletResponseService.newAskResponse(message, repromptMessage)
    }

    override fun canProcess(intentRequest: IntentRequest): Boolean {
        return MY_NAME_INTENT == intentRequest.intent
    }
}
