package com.some_service.dimitrii.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "userEntity")
public class UserEntity {

    @Id
    private String id;
    private String name;

}
