package com.melita.weborder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {

    private String recipient;
    private String msgBody;
    private String subject;

}
