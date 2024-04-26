package com.selfstudy.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: 2891517520@qq.com
 */
@Data
@Configuration
@Component
@PropertySource(value = "classpath:config/message.yml", factory = MyPropertySourceFactory.class)
@ConfigurationProperties(prefix = "message", ignoreInvalidFields = false)
public class MessageProperties {

    @Value("${message.form.save.error}")
    private String formSaveError;

    @Value("${message.form.save.error}")
    private String formUpdateError;

    @Value("${message.form.delete.error}")
    private String formDeleteError;

    @Value("${message.form.mount.error}")
    private String formMountError;

    @Value("${message.get.error}")
    private String messageGetError;

    @Value("${message.user.password.delete}")
    private String userPasswordDelete;

    @Value("${message.user.blocked.error}")
    private String userBlockedError;

    @Value("${message.user.not.exists}")
    private String userNotExists;

    @Value("${message.user.blocked.error}")
    private String userBlocked;

    @Value("${message.user.code-fail.error}")
    private String codeFail;

    @Value("${message.user.already.exists}")
    private String userAlreadyExist;

    @Value("${message.user.account.exists}")
    private String userAccountExist;

    @Value("${message.energyItem.exists}")
    private String energyItemExist;

    @Value("${message.dataCenter.exists}")
    private String dataCenterExist;

    @Value("${message.data.exists}")
    private String dataExist;

    @Value("${message.user.not.competence}")
    private String userNotCompetence;

    @Value("${message.import.error}")
    private String importError;

    @Value("${message.basSite.code.exists}")
    private String basSiteCodeExists;

    @Value("${message.basDevice.code.exists}")
    private String basDeviceCodeExists;

    @Value("${message.department.exists.user}")
    private String departmentExistsUser;

    @Value("${message.label.name.exists}")
    private String labelNameExists;

    @Value("${message.label.exists.use}")
    private String labelExistsUse;
}
