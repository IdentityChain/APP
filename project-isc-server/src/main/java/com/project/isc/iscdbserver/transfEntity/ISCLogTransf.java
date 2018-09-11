package com.project.isc.iscdbserver.transfEntity;

import com.project.isc.iscdbserver.entity.ISCLog;
import com.project.isc.iscdbserver.statusType.ISCConstant;
import com.project.isc.iscdbserver.util.TimeUtil;
import com.project.isc.iscdbserver.viewentity.ISCLogVO;

public class ISCLogTransf {
	public static ISCLogVO transfToVO(ISCLog isclog) {
		ISCLogVO logVO = new ISCLogVO();
		logVO.setIsclId(isclog.getIsclId());
		logVO.setAddISC(isclog.getAddISC());
		logVO.setCreateTime(TimeUtil.getDateFormat(isclog.getCreateTime()));
		if (ISCConstant.ISC_LOG_NEW.equals(isclog.getStatus())) {
			logVO.setStatus(ISCConstant.ISC_LOG_NEW_ZH);
		}else if (ISCConstant.ISC_LOG_CHECK.equals(isclog.getStatus())) {
			logVO.setStatus(ISCConstant.ISC_LOG_CHECK_ZH);
		}else if (ISCConstant.ISC_LOG_OVER.equals(isclog.getStatus())) {
			logVO.setStatus(ISCConstant.ISC_LOG_OVER_ZH);
		}else {
			logVO.setStatus(ISCConstant.ISC_LOG_OTHER_ZH);
		}
		return logVO;
	}
}
