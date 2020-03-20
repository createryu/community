package com.yuqiliu.community.niuliucommunity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuqiliu
 * @create 2020-03-20  11:07
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
