package com.my.blog.website.controller.admin;


import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.my.blog.website.controller.BaseController;
import com.my.blog.website.dto.LogActions;
import com.my.blog.website.dto.Types;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.modal.Bo.RestResponseBo;
import com.my.blog.website.modal.Vo.*;
import com.my.blog.website.modal.VoInput.RcUidParam;
import com.my.blog.website.service.IContentService;
import com.my.blog.website.service.ILogService;
import com.my.blog.website.service.IMetaService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 13 on 2017/2/21.
 */
@Controller
@RequestMapping("/admin/riskcontrol")
@Transactional(rollbackFor = TipException.class)
public class RiskControlController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RiskControlController.class);

    @Resource
    private IContentService contentsService;

    @Resource
    private IMetaService metasService;

    @Resource
    private ILogService logService;

    /**
     * 风控列表
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @GetMapping(value = "")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "15") int limit, HttpServletRequest request) {
        ContentVoExample contentVoExample = new ContentVoExample();
        contentVoExample.setOrderByClause("created desc");
        contentVoExample.createCriteria().andTypeEqualTo(Types.ARTICLE.getType());
        PageInfo<ContentVo> contentsPaginator = contentsService.getArticlesWithpage(contentVoExample,page,limit);
//        request.setAttribute("articles", contentsPaginator);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        RiskControlUidBo u1 = RiskControlUidBo.builder().id(1L).uid("123").banTag("M1").banDesc("天将降大任于斯人也").riskRank(1).riskScore(90).createTime(sdf.format(new Date())).build();
//        RiskControlUidBo u2 = RiskControlUidBo.builder().id(2L).uid("234").banTag("M2").banDesc("必先苦其心志").riskRank(2).riskScore(60).createTime(sdf.format(new Date())).build();
//        RiskControlUidBo u3 = RiskControlUidBo.builder().id(3L).uid("345").banTag("M3").banDesc("劳其筋骨").riskRank(3).riskScore(30).createTime(sdf.format(new Date())).build();
//        RiskControlUidBo u4 = RiskControlUidBo.builder().id(4L).uid("456").banTag("M4").banDesc("饿其体肤").riskRank(0).riskScore(10).createTime(sdf.format(new Date())).build();
        List<RiskControlUidBo> uidRcList = Lists.newArrayList(u1);
        PageInfo<RiskControlUidBo> pageInfo = new PageInfo<>(uidRcList);
        BeanUtils.copyProperties(contentsPaginator, pageInfo);
        pageInfo.setList(uidRcList);

        request.setAttribute("uidRcPageInfo", pageInfo);
        return "admin/risk_control_list";
    }


    /**
     * 风控条件查询
     * @return
     */
    @PostMapping(value = "queryByCondition")
    @ResponseBody
    public String queryByCondition(@RequestParam String uid, @RequestParam Integer riskScore, @RequestParam Integer riskRank,
                                   HttpServletRequest request) {
        if (uid == null){
            System.out.println("s");
        }
        System.out.println("hei");

        ContentVoExample contentVoExample = new ContentVoExample();
        contentVoExample.setOrderByClause("created desc");
        contentVoExample.createCriteria().andTypeEqualTo(Types.ARTICLE.getType());
        PageInfo<ContentVo> contentsPaginator = contentsService.getArticlesWithpage(contentVoExample,1,15);
//        request.setAttribute("articles", contentsPaginator);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        RiskControlUidBo u1 = RiskControlUidBo.builder().id(1L).uid("123").banTag("M1").banDesc("天将降大任于斯人也").riskRank(1).riskScore(90).createTime(sdf.format(new Date())).build();
        RiskControlUidBo u2 = RiskControlUidBo.builder().id(2L).uid("234").banTag("M2").banDesc("必先苦其心志").riskRank(2).riskScore(60).createTime(sdf.format(new Date())).build();
        RiskControlUidBo u3 = RiskControlUidBo.builder().id(3L).uid("345").banTag("M3").banDesc("劳其筋骨").riskRank(3).riskScore(30).createTime(sdf.format(new Date())).build();
        RiskControlUidBo u4 = RiskControlUidBo.builder().id(4L).uid("456").banTag("M4").banDesc("饿其体肤").riskRank(0).riskScore(10).createTime(sdf.format(new Date())).build();
        List<RiskControlUidBo> uidRcList = Lists.newArrayList(u1, u2, u3, u4);
        PageInfo<RiskControlUidBo> pageInfo = new PageInfo<>(uidRcList);
        BeanUtils.copyProperties(contentsPaginator, pageInfo);
        pageInfo.setList(uidRcList);

        request.setAttribute("uidRcPageInfo", pageInfo);

//        return RestResponseBo.ok();
        return "admin/risk_control_list";
    }


}
