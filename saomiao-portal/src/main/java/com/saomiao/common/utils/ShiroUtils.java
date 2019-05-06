package com.saomiao.common.utils;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.saomiao.owneruser.domain.ManagersDO;
import com.saomiao.owneruser.domain.OwnerUserDO;

public class ShiroUtils {
    @Autowired
    private static SessionDAO sessionDAO;

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }
    public static ManagersDO getUser() {
        Object object = getSubjct().getPrincipal();
        return (ManagersDO)object;
    }
    public static Long getUserId() {
        return getUser().getMid();
    }
    public static void logout() {
        getSubjct().logout();
    }

    public static List<Principal> getPrinciples() {
        List<Principal> principals = null;
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        return principals;
    }
}
