package com.uitnetwork.alexa.service

import com.amazon.speech.speechlet.SpeechletResponse
import com.uitnetwork.alexa.model.AlexaIntent.MY_NAME_INTENT
import com.uitnetwork.alexa.model.AlexaIntentRequest
import com.uitnetwork.alexa.util.AppConstants.Companion.SESSION_NAME
import org.slf4j.LoggerFactory.getLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MyNameIntentService : AlexaIntentRequestService() {
    private val logger = getLogger(MyNameIntentService::class.java)

    companion object {
        private val SLOT_MY_NAME = "MY_NAME"

        val MY_NAME_MESSAGE = "Hello %s. What can I do for you?"
        val MY_NAME_PROMPT = "What can I do for you, %s?"
    }

    @Autowired
    private lateinit var speechletResponseService: SpeechletResponseService

    override fun doProcess(alexaIntentRequest: AlexaIntentRequest): SpeechletResponse {
        val nameSlot = alexaIntentRequest.intentRequestEnvelope.request.intent.getSlot(SLOT_MY_NAME)
        val name = nameSlot.getValue()

        val message = String.format(MY_NAME_MESSAGE, name)
        val repromptMessage = String.format(MY_NAME_PROMPT, name)

        alexaIntentRequest.intentRequestEnvelope.session.setAttribute(SESSION_NAME, name)

        logger.info("Message: {} and repromptMessage: {}", message, repromptMessage)

        return speechletResponseService.newAskResponse(message, repromptMessage)
    }

    override fun canProcess(alexaIntentRequest: AlexaIntentRequest): Boolean {
        return MY_NAME_INTENT == alexaIntentRequest.alexaIntent
    }
}
