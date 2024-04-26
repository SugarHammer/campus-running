package com.selfstudy.modules.sys.form;

import lombok.Data;

/**
 * 密码表单
 *
 * @author Mark 2891517520@qq.com
 */
@Data
public class PasswordForm {
    /**
     * 原密码
     */
    private String password;
    /**
     * 新密码
     */
    private String newPassword;

}
