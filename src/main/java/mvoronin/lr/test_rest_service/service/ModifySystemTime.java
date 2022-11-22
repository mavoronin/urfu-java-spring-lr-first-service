package mvoronin.lr.test_rest_service.service;

import lombok.RequiredArgsConstructor;
import mvoronin.lr.test_rest_service.model.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Qualifier("ModifySystemTime")
public class ModifySystemTime implements MyModifyService {
    @Override
    public Response modify(Response response) {

        var dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        var currentDateTime = LocalDateTime.now();

        response.setSystemTime(currentDateTime.format(dateTimeFormatter));
        return response;
    }
}
