package com.saomiao.system.vo;

import com.saomiao.system.domain.UserDO;

/**
 * @author gaoyuzhe
 * @date 2017/12/15.
 */
public class UserVO {
    /**
     * 更新的用户对象
     */
    private UserDO userDO = new UserDO();
    /**
     * 旧密码
     */
    private String pwdOld;
    /**
     * 新密码
     */
    private String pwdNew;
    
    private String roleName;
    
    

    public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }

    public String getPwdOld() {
        return pwdOld;
    }

    public void setPwdOld(String pwdOld) {
        this.pwdOld = pwdOld;
    }

    public String getPwdNew() {
        return pwdNew;
    }

    public void setPwdNew(String pwdNew) {
        this.pwdNew = pwdNew;
    }

	@Override
	public String toString() {
		return "UserVO [userDO=" + userDO + ", pwdOld=" + pwdOld + ", pwdNew=" + pwdNew + "]";
	}
    
    
}
