package mvoronin.lr.test_rest_service.service;

import mvoronin.lr.test_rest_service.model.Request;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ModifyRequestSystemTime implements ModifyRequestService {
    @Override
    public void modifyRequest(Request request) {

        var dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        var currentDateTime = LocalDateTime.now();

        request.setSystemTime(currentDateTime.format(dateTimeFormatter));

        var httpEntity = new HttpEntity<>(request);

        new RestTemplate().exchange("http://localhost:8082/feedback",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                });

    }
}
