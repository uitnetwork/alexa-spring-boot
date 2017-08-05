package com.uitnetwork.alexa.model

import com.uitnetwork.alexa.exception.AlexaException
import java.util.*

enum class Intent(private val intent: String) {
    MY_NAME_INTENT("MyNameIntent"),
    TIME_INTENT("TimeIntent");

    companion object {
        private val alexaIntentMap = HashMap<String, Intent>()

        init {
            for (alexaIntent in Intent.values()) {
                alexaIntentMap.put(alexaIntent.intent, alexaIntent)
            }
        }

        fun lookupAlexaIntent(intent: String): Intent {
            val alexaIntent = alexaIntentMap[intent]
            return alexaIntent ?: throw AlexaException("Intent: $intent is not supported!")
        }
    }
}
