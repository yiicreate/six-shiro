package com.example.sixshiro.scurtity;

import com.example.sixshiro.entity.User;
import com.example.sixshiro.service.UserService;
import com.example.sixshiro.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: lh
 * @date: 2021/4/1
 */

@Service
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }
    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.先获取到用户名
        String userName = (String) principalCollection.getPrimaryPrincipal();

        return null;
    }

    /**
     * 认证回调函数, 登录时调用
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        String token = (String) authcToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        User user = userService.getByToken(token);
        if (user == null) {
            throw new AuthenticationException("无效的token");
        }

        if (JwtUtil.verify(token) == 1) {
            throw new AuthenticationException("token已经过期");
        }else if (JwtUtil.verify(token) == 2) {
            throw new AuthenticationException("用户名或者密码错误");
        }


//		return new SimpleAuthenticationInfo(token, token, "my_realm");
        // 校验登录验证码
//		if ( LoginController.isValidateCodeLogin(token.getLoginName(), false, false)){
//			Session session = UserUtils.getSession();
//			String code = (String)session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
//			if (token.getCaptcha() == null || !token.getCaptcha().toUpperCase().equals(code)){
//				throw new AuthenticationException("msg:验证码错误, 请重试.");
//			}
//		}

        // 校验用户名密码
//        if (JeePlusProperites.NO.equals(user.getLoginFlag())){
//            throw new AuthenticationException("该已帐号禁止登录.");
//        }
//        byte[] salt = Encodes.decodeHex(user.getPassword().substring(0,16));
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
