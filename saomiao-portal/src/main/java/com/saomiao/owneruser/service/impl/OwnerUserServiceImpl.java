package com.saomiao.owneruser.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.saomiao.common.config.BootdoConfig;
import com.saomiao.common.service.FileService;
import com.saomiao.owneruser.dao.OwnerUserDao;
import com.saomiao.owneruser.domain.ManagersDO;
import com.saomiao.owneruser.domain.OwnerUserDO;
import com.saomiao.owneruser.service.OwnerUserService;



@Transactional
@Service
public class OwnerUserServiceImpl implements OwnerUserService {
	@Autowired
	OwnerUserDao ownerUserMapper;
	@Autowired
	private FileService sysFileService;
	@Autowired
	private BootdoConfig bootdoConfig;
/*	private static final Logger logger = LoggerFactory.getLogger(UserService.class);*/

	@Override
	public ManagersDO get(Long id) {
		ManagersDO user = ownerUserMapper.get(id);
		return user;
	}
	
	@Override
	public ManagersDO getbyname(String username){
		
		ManagersDO user = ownerUserMapper.getbyname(username);
		return user;
	}

	@Override
	public List<ManagersDO> list(Map<String, Object> map) {
		return ownerUserMapper.list(map);
	}

	@Override
	public int save(ManagersDO user){
		return ownerUserMapper.save(user);
	}
	
	@Override
	public int count(Map<String, Object> map) {
		return ownerUserMapper.count(map);
	}


	@Override
	public int update(ManagersDO user) {
		int r = ownerUserMapper.update(user);
		
		return r;
	}

	@Override
	public int remove(Long mid) {
		/*userRoleMapper.removeByUserId(userId);*/
		return ownerUserMapper.remove(mid);
	}

	@Override
	public boolean exit(Map<String, Object> params) {
		boolean exit;
		exit = ownerUserMapper.list(params).size() > 0;
		return exit;
	}

	@Override
	public boolean exitWechat(Map<String, Object> mapP) {
		boolean exit;
		exit = ownerUserMapper.list(mapP).size() > 0;
		return exit;
	}

}
