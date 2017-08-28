package com.grain.base.log.imp;

import com.grain.base.log.AppLogService;
import com.grain.base.log.bo.OperateLogBo;
import com.grain.base.log.dao.AppLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AppLogService")
public class AppLogServiceImp implements AppLogService {

    @Autowired
    private AppLogDao appLogDao;

    @Override
    public void insertLog(OperateLogBo log) {
        appLogDao.insert(log);
    }


}
