package com.uitnetwork.alexa.model;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;

public class AlexaIntentRequest {
    public final AlexaIntent alexaIntent;

    public final SpeechletRequestEnvelope<IntentRequest> intentRequestEnvelope;

    public AlexaIntentRequest(AlexaIntent alexaIntent, SpeechletRequestEnvelope<IntentRequest> intentRequestEnvelope) {
        this.alexaIntent = alexaIntent;
        this.intentRequestEnvelope = intentRequestEnvelope;
    }

    public AlexaIntent getAlexaIntent() {
        return alexaIntent;
    }

    public SpeechletRequestEnvelope<IntentRequest> getIntentRequestEnvelope() {
        return intentRequestEnvelope;
    }
}
