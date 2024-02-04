package com.chethiya.visa.helloworld.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


/**
 * @author Chethiya
 *
 * This holds the response from the two-way ssl helloworld service
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HelloMessage {

    private String message;

    private Date timestamp;

}
