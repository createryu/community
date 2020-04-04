package com.yuqiliu.community.niuliucommunity.dto;

import com.yuqiliu.community.niuliucommunity.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuqiliu
 * @create 2020-03-26  21:49
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmt_create;
    private Long gmt_modified;
    private Integer creator;
    private Integer view_count;
    private Integer comment_count;
    private Integer like_count;
    private User user;
}
