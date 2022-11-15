package mvoronin.lr.test_rest_service.service;

import lombok.RequiredArgsConstructor;
import mvoronin.lr.test_rest_service.model.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("ModifySystemTime")
public class ModifySystemTime implements MyModifyService {
    @Override
    public Response modify(Response response) {

        response.setSystemTime("");
        return response;
    }
}
