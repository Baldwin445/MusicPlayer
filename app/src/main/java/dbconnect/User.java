package dbconnect;

import org.litepal.crud.DataSupport;

/**
 * Created by Baldwin on 19/12/7.
 */

public class User extends DataSupport{
    //用户登录账号
    String acct;
    //用户昵称
    String nickname;
    //用户密码
    String pwd;
    //用户权限
    String vip;

    public String getAcct() {
        return acct;
    }

    public void setAcct(String acct) {
        this.acct = acct;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }
}
