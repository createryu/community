package com.yuqiliu.community.niuliucommunity.mapper;

import com.yuqiliu.community.niuliucommunity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author yuqiliu
 * @create 2020-03-23  8:43
 */
@Repository
@Mapper
public interface UserMapper {

    @Insert("insert into user (name, account_id, token, gmt_create, gmt_modified) values(#{name}, #{account_id}, #{token}, #{gmt_create}, #{gmt_modified})")
    public int insert(User user);

    @Select("select * from user where token = #{token}")
    public User findByToken(@Param("token") String token);
}
