package com.uitnetwork.alexa.service

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.slu.Slot
import com.uitnetwork.alexa.model.Intent.Companion.lookupAlexaIntent
import com.uitnetwork.alexa.model.IntentRequest
import org.springframework.stereotype.Service

@Service
class IntentRequestTranslatorService {

    fun translate(intentRequestEnvelope: SpeechletRequestEnvelope<com.amazon.speech.speechlet.IntentRequest>): IntentRequest {
        val intent = lookupAlexaIntent(intentRequestEnvelope.request.intent.name)
        val slots = extractSlots(intentRequestEnvelope.request.intent.slots)
        return IntentRequest(intent, slots, intentRequestEnvelope)
    }

    private fun extractSlots(slotMap: Map<String, Slot>): Map<String, String> {
        return slotMap.mapValues { it.value.value }
    }
}
