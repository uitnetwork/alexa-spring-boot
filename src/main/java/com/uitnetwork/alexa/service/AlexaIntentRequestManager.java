package com.uitnetwork.alexa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazon.speech.speechlet.SpeechletResponse;
import com.uitnetwork.alexa.exception.AlexaException;
import com.uitnetwork.alexa.model.AlexaIntentRequest;

@Service
public class AlexaIntentRequestManager {

    @Autowired
    public List<AlexaIntentRequestService> alexaIntentRequestServices;

    public SpeechletResponse process(AlexaIntentRequest alexaIntentRequest) {
        for (AlexaIntentRequestService alexaIntentRequestService : alexaIntentRequestServices) {
            if (alexaIntentRequestService.canProcess(alexaIntentRequest)) {
                return alexaIntentRequestService.doProcess(alexaIntentRequest);
            }
        }
        throw new AlexaException("Something is wrong. Should never get this exception: " + alexaIntentRequest);
    }
}
