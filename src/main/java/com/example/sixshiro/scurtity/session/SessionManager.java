package com.example.sixshiro.scurtity.session;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.stereotype.Service;

/**
 * @author: lh
 * @date: 2021/4/1
 */

public class SessionManager extends DefaultWebSessionManager {
    public SessionManager() {
        super();
    }
}
