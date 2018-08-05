package com.project.isc.iscdbserver.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.project.isc.iscdbserver.viewentity.RetMsg;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public RetMsg defaultErrorHandler(HttpServletRequest req,Exception e) throws Exception {
		RetMsg retMsg = new RetMsg();
		retMsg.setMessage(e.getMessage());
		retMsg.setCode(404);
		retMsg.setSuccess(false);
		retMsg.setData(null);
		
		return retMsg;
	}
	
}
