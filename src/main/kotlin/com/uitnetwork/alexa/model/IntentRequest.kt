package com.uitnetwork.alexa.model

import com.amazon.speech.json.SpeechletRequestEnvelope

data class IntentRequest(val intent: Intent,
                         val slots: Map<String, String>,
                         val originalRequestEnvelope: SpeechletRequestEnvelope<com.amazon.speech.speechlet.IntentRequest>) {

    fun getSlotValue(slotName: String): String? {
        return slots[slotName]
    }
}
