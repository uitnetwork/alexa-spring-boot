package com.uitnetwork.alexa.service;

import org.springframework.stereotype.Service;

import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SsmlOutputSpeech;

@Service
public class SpeechletResponseService {

    public SpeechletResponse newAskResponse(String message, String repromptMessage) {
        PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
        plainTextOutputSpeech.setText(message);

        PlainTextOutputSpeech repromptOutputSpeech = new PlainTextOutputSpeech();
        repromptOutputSpeech.setText(repromptMessage);
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptOutputSpeech);

        return SpeechletResponse.newAskResponse(plainTextOutputSpeech, reprompt);
    }

    public SpeechletResponse newTellResponse(String ssmlMessage) {
        SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
        ssmlOutputSpeech.setSsml("<speak>" + ssmlMessage + "</speak>");

        return SpeechletResponse.newTellResponse(ssmlOutputSpeech);
    }
}
