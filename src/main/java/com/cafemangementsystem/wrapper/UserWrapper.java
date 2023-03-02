package com.cafemangementsystem.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWrapper {

    UserWrapper user = new UserWrapper(1, "Abc", "abc@gmail.com", "5645", "false");
    private Integer id;

    private String name;

    private String email;

    private String contactNumber;

    private String status;

    public UserWrapper(Integer id, String name, String email, String contactNumber, String status) {
        this.user = user;
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.status = status;
    }
}
