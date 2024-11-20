package com.example.demospring.jwtrestapi2.repository;

import com.example.demospring.jwtrestapi2.model.AppUser;
import com.example.demospring.jwtrestapi2.model.dto.request.AppUserRequest;
import com.example.demospring.jwtrestapi2.model.dto.response.AppUserResponse;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AppUserRepository {
    @Select("""
     SELECT * FROM users WHERE email = #{email}
     """)
    @Results(id = "userMap", value = {
    @Result(property = "userId", column = "user_id"),
    @Result(property = "fullName", column = "full_name")
    })
    AppUser findByEmail(String email);

    @Select("""
    INSERT INTO users(full_name, email, password)
    VALUES (#{appUser.fullName},#{appUser.email},#{appUser.password})
    returning *;
    """)
    @ResultMap("userMap")
    AppUser register(@Param("appUser") AppUserRequest appUserRequest);

    @Results(id = "getUser", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "fullName", column = "full_name")
    })
    @Select("""
    SELECT * FROM users WHERE user_id = #{id};
    """)
    AppUserResponse getUserById (Long id);
}
