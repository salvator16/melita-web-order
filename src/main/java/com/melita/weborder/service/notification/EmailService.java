package com.melita.weborder.service.notification;

import com.melita.weborder.model.EmailDto;

public interface EmailService {

    boolean sendBasicMail(EmailDto emailDto);

}
