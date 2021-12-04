package com.my.blog.website.modal.Vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @desc:
 * @author: James Qiu
 * @datetime: 2021/12/4 21:52
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RiskControlUidBo {
    private Long id;
    private String uid;
    private String banTag;
    private String banDesc;
    private Integer riskRank;
    private Integer riskScore;
    private String createTime;
}
