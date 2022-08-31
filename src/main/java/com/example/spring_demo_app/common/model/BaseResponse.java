package com.example.spring_demo_app.common.model;

import com.example.spring_demo_app.common.date_time.DateTimeUtils;
import com.example.spring_demo_app.common.logging.CustomURLFilter;
import com.example.spring_demo_app.common.utils.RandomString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private String at;
    private String request_id;
    private Error error;
    private Object data;

    public static BaseResponse success(Object data) {
        return new BaseResponse(DateTimeUtils.convertTo(DateTimeUtils.P0, new Date()), getRequestId(),  Error.success(), data);
    }
    public static BaseResponse success() {
        return new BaseResponse(DateTimeUtils.convertTo(DateTimeUtils.P0, new Date()), getRequestId(),  Error.success(), "");
    }

    public static BaseResponse error(Error error) {
        return new BaseResponse(DateTimeUtils.convertTo(DateTimeUtils.P0, new Date()), getRequestId(),  error, null);
    }

    public static BaseResponse error() {
        return new BaseResponse(DateTimeUtils.convertTo(DateTimeUtils.P0, new Date()), getRequestId(),  Error.error(), null);
    }

    public static BaseResponse error(Object data, Error error) {
        return new BaseResponse(DateTimeUtils.convertTo(DateTimeUtils.P0, new Date()), getRequestId(),  error, data);
    }


    private static String getRequestId() {
//        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        HttpServletRequest request ;
//        if (attr instanceof NativeWebRequest) {
//            request = (HttpServletRequest) ((NativeWebRequest) attr).getNativeRequest();
//        }else{
//            request = attr.getRequest();
//        }
//
//        Object id = request.getAttribute(CustomURLFilter.REQUEST_ID);
//        if(id == null) id = new RandomString(16).nextString();
        return String.valueOf("Reuqest id fake");

    }
}
