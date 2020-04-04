package com.yuqiliu.community.niuliucommunity.mapper;

import com.yuqiliu.community.niuliucommunity.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
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

    @Select("select * from Question")
    public List<Question> list();
}
