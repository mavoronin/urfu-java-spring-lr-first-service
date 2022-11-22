package mvoronin.lr.test_rest_service.controller;

import lombok.extern.slf4j.Slf4j;
import mvoronin.lr.test_rest_service.model.Request;
import mvoronin.lr.test_rest_service.model.Response;
import mvoronin.lr.test_rest_service.service.ModifyRequestService;
import mvoronin.lr.test_rest_service.service.MyModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MyController {

    private final MyModifyService myModifyService;
    private final ModifyRequestService modifyRequestService;

    @Autowired
    public MyController(@Qualifier("ModifyUid") MyModifyService myModifyService,
                        ModifyRequestService modifyRequestService) {
        this.myModifyService = myModifyService;
        this.modifyRequestService = modifyRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@RequestBody Request request) {

        log.info("Входящий request: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(request.getSystemTime())
                .code("success")
                .errorCode("")
                .errorMessage("")
                .build();

        modifyRequestService.modifyRequest(request);

        Response responseAfterModify = myModifyService.modify(response);

        log.info("Исходящий response: {}", responseAfterModify);

        return new ResponseEntity<>(responseAfterModify, HttpStatus.OK);
    }
}
