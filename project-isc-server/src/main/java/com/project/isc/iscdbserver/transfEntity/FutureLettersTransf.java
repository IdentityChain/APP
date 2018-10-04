package com.project.isc.iscdbserver.transfEntity;

import com.project.isc.iscdbserver.entity.FutureLetters;
import com.project.isc.iscdbserver.util.TimeUtil;
import com.project.isc.iscdbserver.viewentity.FutureLettersVO;
import com.project.isc.iscdbserver.viewentity.FutureLettersVOSimple;

public class FutureLettersTransf {
	public static FutureLettersVO transfToVO(FutureLetters futureLetters) {
		FutureLettersVO futureLettersVO = new FutureLettersVO();

		futureLettersVO.setFlId(futureLetters.getFlId());
		futureLettersVO.setFlMessage(futureLetters.getFlMessage());
		futureLettersVO.setSendTime(TimeUtil.getDateFormat(futureLetters.getSendTime()));
		futureLettersVO.setFlemail(futureLetters.getFlemail());
		futureLettersVO.setBlockChain(futureLetters.getBlockChain());
		futureLettersVO.setCreateTime(TimeUtil.getDateFormat(futureLetters.getCreateTime()));


		return futureLettersVO;
	}

	public static FutureLettersVOSimple transfToVOSimple(FutureLetters futureLetters) {
		FutureLettersVOSimple fs = new FutureLettersVOSimple();

		fs.setFlId(futureLetters.getFlId());
		fs.setContent(futureLetters.getFlMessage());
		fs.setTotime(TimeUtil.getDateFormat(futureLetters.getSendTime()));
		fs.setSender(futureLetters.getFlemail());

		return fs;
	}
}
