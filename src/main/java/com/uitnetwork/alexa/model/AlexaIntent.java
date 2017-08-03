package com.uitnetwork.alexa.model;

import java.util.HashMap;
import java.util.Map;

public enum AlexaIntent {
    MY_NAME_INTENT("MyNameIntent"),
    TIME_INTENT("TimeIntent");

    private final String intent;

    AlexaIntent(String intent) {
        this.intent = intent;
    }

    private static final Map<String, AlexaIntent> alexaIntentMap = new HashMap<>();

    static {
        for (AlexaIntent alexaIntent : AlexaIntent.values()) {
            alexaIntentMap.put(alexaIntent.intent, alexaIntent);
        }
    }

    public static final AlexaIntent lookupAlexaIntent(String intent) {
        AlexaIntent alexaIntent = alexaIntentMap.get(intent);
        return alexaIntent;
    }
}
