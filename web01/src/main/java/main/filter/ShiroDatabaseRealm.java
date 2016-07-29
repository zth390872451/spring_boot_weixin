package main.filter;

import main.domain.Admin;
import main.repository.AdminRepository;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroDatabaseRealm extends AuthorizingRealm{

    @Autowired
    private AdminRepository adminRepository;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String)principalCollection.fromRealm(getName()).iterator().next();
        Admin admin = adminRepository.findOneByUserName(username);
        if(admin==null){
            throw new  UnknownAccountException("Account does not exist!");
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission("admin:read");
        simpleAuthorizationInfo.addStringPermission("admin:write");
        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String username = usernamePasswordToken.getUsername();
        String password = String.valueOf(usernamePasswordToken.getPassword());
        if(username == null){
            throw new UnknownAccountException("Please provide your account!");
        }
        if(password == null){
            throw new UnknownAccountException("Please provide your password!");
        }
        Admin admin = adminRepository.findOneByUserName(username);
        if (admin==null)
            throw new AuthenticationException("账号不存在！");
        if(!password.equals(admin.getPassword())){
            throw new IncorrectCredentialsException("The password you provided not right!");
        }
        return new SimpleAuthenticationInfo(admin.getUserName(),admin.getPassword(), ByteSource.Util.bytes(admin.getPassword()),getName());
    }
}
