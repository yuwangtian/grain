package com.grain.base.log;

import com.grain.base.log.bo.OperateLogBo;

public interface AppLogService {
    /**
     * 保存日志
     */
    public void insertLog(OperateLogBo log);
}
