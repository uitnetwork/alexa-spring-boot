package com.uitnetwork.alexa.service

import com.amazon.speech.speechlet.SpeechletResponse
import com.amazon.speech.ui.PlainTextOutputSpeech
import com.amazon.speech.ui.Reprompt
import com.amazon.speech.ui.SsmlOutputSpeech
import org.springframework.stereotype.Service

@Service
class SpeechletResponseService {

    fun newAskResponse(message: String, repromptMessage: String): SpeechletResponse {
        val plainTextOutputSpeech = PlainTextOutputSpeech()
        plainTextOutputSpeech.text = message

        val repromptOutputSpeech = PlainTextOutputSpeech()
        repromptOutputSpeech.text = repromptMessage
        val reprompt = Reprompt()
        reprompt.outputSpeech = repromptOutputSpeech

        return SpeechletResponse.newAskResponse(plainTextOutputSpeech, reprompt)
    }

    fun newTellResponse(ssmlMessage: String): SpeechletResponse {
        val ssmlOutputSpeech = SsmlOutputSpeech()
        ssmlOutputSpeech.ssml = "<speak>$ssmlMessage</speak>"

        return SpeechletResponse.newTellResponse(ssmlOutputSpeech)
    }
}
