package com.geekbrains.geekspring.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class DeliveryAddressAndPhoneInput {
    @NotBlank
    private String deliveryAddress;

    @NotBlank
    @Size(min = 11, max = 15)
//    @Pattern(regexp = "([0,9]{1,})", message ="phone number can contain numbers from 0 to 9")
    @Pattern(regexp = "^(\\\\+\\\\d{1,3}( )?)?((\\\\(\\\\d{3}\\\\))|\\\\d{3})[- .]?\\\\d{3}[- .]?\\\\d{4}$",
            message ="phone number can contain numbers from 0 to 9")
    private String phoneForDelivery;
}
