package scheduler.controller.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scheduler.advice.exception.CAuthenticationEntryPointException;
import scheduler.model.response.CommonResult;


@RequiredArgsConstructor
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/entrypoint")
    public CommonResult entrypointException() {
        throw new CAuthenticationEntryPointException();
    }

    @GetMapping(value = "/accessdenied")
    public CommonResult accessdeniedException() {
        throw new AccessDeniedException("");
    }
}

