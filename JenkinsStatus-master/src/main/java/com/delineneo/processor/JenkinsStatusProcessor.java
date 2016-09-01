package com.delineneo.processor;

import com.delineneo.communication.ControlePorta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import static org.apache.commons.lang.StringUtils.contains;

@Component
public class JenkinsStatusProcessor {
    private static final String JENKINS_URL = "http://192.168.2.81:8000/job/GI2S_Development/api/json" ;
    private static final String FAIL_BUILD_COLOR = "red";
    private static final int BUILD_FAIL = 0;
    private static final int BUILD_SUCCESS = 1;

    private RestTemplate restTemplate;
    private ControlePorta controlePorta;

    @Scheduled(fixedDelay=5000)
    public void process() {

        String jsonString = restTemplate.getForObject(JENKINS_URL, String.class);

        boolean buildSuccess = isBuildSuccessful(jsonString);
        controlePorta.enviaDados(buildSuccess ? BUILD_FAIL : BUILD_SUCCESS);
    }

    private boolean isBuildSuccessful(String jsonString) {
        if (contains(jsonString, FAIL_BUILD_COLOR)) {
            return true;
        }
        return false;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setControlePorta(ControlePorta controlePorta) {
    	controlePorta = new ControlePorta("COM3", 9600);
    	controlePorta.abrirPorta();
        this.controlePorta = controlePorta;
    }
}
