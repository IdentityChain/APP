package com.project.isc.iscdbserver.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.project.isc.iscdbserver.viewentity.RetMsg;

public class ValidateErrorUtil {
	private ValidateErrorUtil() {
	}

	private static ValidateErrorUtil validateErrorUtil = new ValidateErrorUtil();

	public static ValidateErrorUtil getInstance() {
		return validateErrorUtil;
	}

	public RetMsg errorList(BindingResult bindingResult) {
		if (!bindingResult.hasErrors())
			return null;

		List<ObjectError> errors = bindingResult.getAllErrors();

		List<String> errorList = new ArrayList<String>();
		for (ObjectError error : errors) {
			errorList.add(error.getDefaultMessage());
		}

		RetMsg retMsg = new RetMsg();
		retMsg.setCode(400);
		retMsg.setData(errorList);
		retMsg.setMessage("数据不符合限制条件！");
		retMsg.setSuccess(false);

		return retMsg;
	}

}
