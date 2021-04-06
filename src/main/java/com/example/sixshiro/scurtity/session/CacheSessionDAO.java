package com.example.sixshiro.scurtity.session;

import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.stereotype.Component;

/**
 * @author: lh
 * @date: 2021/4/1
 */

@Component("sessionDAO")
public class CacheSessionDAO extends EnterpriseCacheSessionDAO implements SessionDAO {

}
