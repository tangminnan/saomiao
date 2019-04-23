package com.saomiao.common.service;

import org.springframework.stereotype.Service;
import com.saomiao.common.domain.LogDO;
import com.saomiao.common.domain.PageDO;
import com.saomiao.common.utils.Query;

@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
