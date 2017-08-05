package com.uitnetwork.alexa.service

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.IntentRequest
import com.uitnetwork.alexa.model.AlexaIntent.Companion.lookupAlexaIntent
import com.uitnetwork.alexa.model.AlexaIntentRequest
import org.slf4j.LoggerFactory.getLogger
import org.springframework.stereotype.Service

@Service
class AlexaIntentRequestMappingService {
    private val logger = getLogger(AlexaIntentRequestMappingService::class.java)

    fun translate(intentRequestEnvelope: SpeechletRequestEnvelope<IntentRequest>): AlexaIntentRequest {
        logger.info("Mapping for intentName: {}", intentRequestEnvelope.request.intent.name)

        val alexaIntent = lookupAlexaIntent(intentRequestEnvelope.request.intent.name)
        return AlexaIntentRequest(alexaIntent, intentRequestEnvelope)
    }
}
