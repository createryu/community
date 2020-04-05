package com.yuqiliu.community.niuliucommunity.mapper;

import com.yuqiliu.community.niuliucommunity.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuqiliu
 * @create 2020-03-25  21:39
 */
@Repository
@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    public void create(Question question);

    @Select("select * from question limit #{offset} , #{size}")
    public List<Question> list(@Param("offset") int offset, @Param("size") int size);

    @Select("select count(1) from question")
    public Integer count();
}
