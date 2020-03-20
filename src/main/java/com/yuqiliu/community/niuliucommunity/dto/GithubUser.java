package com.yuqiliu.community.niuliucommunity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuqiliu
 * @create 2020-03-20  12:00
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
}
