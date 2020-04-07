package com.yuqiliu.community.niuliucommunity.mapper;

import com.yuqiliu.community.niuliucommunity.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author yuqiliu
 * @create 2020-03-23  8:43
 */
@Repository
@Mapper
public interface UserMapper {

    @Insert("insert into user (name, account_id, token, gmt_create, gmt_modified, bio, avatar_url) values(#{name}, #{account_id}, #{token}, #{gmt_create}, #{gmt_modified}, #{bio}, #{avatar_url})")
    public int insert(User user);

    @Select("select * from user where token = #{token}")
    public User findByToken(@Param("token") String token);

    @Select("select * from user where id=#{id}")
    public User fingById(@Param("id") Integer id);

    @Select("select * from user where account_id=#{account_id}")
    public User findByAccountId(@Param("account_id") String account_id);

    @Update("update user set name=#{name}, token=#{token}, gmt_modified=#{gmt_modified}, bio=#{bio}, avatar_url=#{avatar_url} where id=#{id}")
    public void update(User user);
}
