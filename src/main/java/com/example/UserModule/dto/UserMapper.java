package com.example.UserModule.dto;

import com.example.UserModule.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User UserPostDtoToUser(UserPostDto userPostDto);
}
