package com.uitnetwork.alexa.config

import com.amazon.speech.speechlet.servlet.SpeechletServlet
import com.uitnetwork.alexa.speechlet.AlexaSpeechletService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CommonsRequestLoggingFilter

@Configuration
class AlexaConfig {
    companion object {
        const val ALEXA_URL = "/alexa"
    }

    @Autowired
    private lateinit var alexaSpeechletService: AlexaSpeechletService

    @Bean
    fun registerServlet(): ServletRegistrationBean {
        val speechletServlet = SpeechletServlet()
        speechletServlet.setSpeechlet(alexaSpeechletService)

        val servletRegistrationBean = ServletRegistrationBean(speechletServlet, ALEXA_URL)
        return servletRegistrationBean
    }

    @Bean
    fun commonsRequestLoggingFilter(): CommonsRequestLoggingFilter {
        val commonsRequestLoggingFilter = CommonsRequestLoggingFilter()
        commonsRequestLoggingFilter.isIncludeHeaders = true

        return commonsRequestLoggingFilter
    }

    @Bean
    fun registerCommonsRequestLoggingFilter(commonsRequestLoggingFilter: CommonsRequestLoggingFilter): FilterRegistrationBean {
        val filterRegistrationBean = FilterRegistrationBean()
        filterRegistrationBean.filter = commonsRequestLoggingFilter
        filterRegistrationBean.urlPatterns = listOf(ALEXA_URL)

        return filterRegistrationBean
    }
}
