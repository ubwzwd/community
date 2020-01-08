package com.wudi.community.advice;

import com.wudi.community.exception.CustomizedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomizedExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable ex, Model model) {
//        HttpStatus status = getStatus(request);
        if(ex instanceof CustomizedException){
            model.addAttribute("message", ex.getMessage());
        } else{
            model.addAttribute("message", "The service is busy now, please try later.");
        }
        return new ModelAndView("error");
    }

//    private HttpStatus getStatus(HttpServletRequest request) {
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        if (statusCode == null) {
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return HttpStatus.valueOf(statusCode);
//    }
}
