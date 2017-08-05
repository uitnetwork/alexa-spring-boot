package com.uitnetwork.alexa.model

import com.amazon.speech.json.SpeechletRequestEnvelope
import com.amazon.speech.speechlet.IntentRequest

data class AlexaIntentRequest(val alexaIntent: AlexaIntent,
                              val intentRequestEnvelope: SpeechletRequestEnvelope<IntentRequest>)
