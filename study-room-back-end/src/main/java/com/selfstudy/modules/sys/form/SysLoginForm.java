package com.selfstudy.modules.sys.form;

import lombok.Data;

/**
 * 登录表单
 *
 * @author Mark 2891517520@qq.com
 */
@Data
public class SysLoginForm {
    private String username;
    private String password;
    private String captcha;
    private String uuid;


}
