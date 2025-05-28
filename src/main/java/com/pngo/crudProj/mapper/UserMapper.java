package com.pngo.crudProj.mapper;

import com.pngo.crudProj.dto.request.UserCreate;
import com.pngo.crudProj.dto.request.UserUpdate;
import com.pngo.crudProj.dto.response.UserResponse;
import com.pngo.crudProj.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreate request);
    void updateUserFromDto(@MappingTarget User user, UserUpdate request);

//    @Mapping(source = "firstName", target = "lastName")
//    @Mapping(target = "lastName", ignore = true)
    UserResponse toUserResponse(User user);
}
