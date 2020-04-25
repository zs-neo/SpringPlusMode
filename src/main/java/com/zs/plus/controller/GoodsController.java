package com.zs.plus.controller;

import com.zs.plus.domain.Result;
import com.zs.plus.domain.po.MUser;
import com.zs.plus.domain.vo.GoodsVO;
import com.zs.plus.domain.vo.MVO;
import com.zs.plus.service.GoodsService;
import com.zs.plus.service.MUserService;
import com.zs.plus.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/app/goods")
public class GoodsController {
  
  @Autowired
  MUserService mUserService;
  
  @Autowired
  RedisService redisService;
  
  @Autowired
  GoodsService goodsService;
  
  @RequestMapping("/list")
  @ResponseBody
  public Result<List<GoodsVO>> list() {
    List<GoodsVO> goodsVOList = goodsService.listGoodsVO();
    return new Result<List<GoodsVO>>(goodsVOList);
  }
  
  @RequestMapping("/detail/{goodsId}")
  @ResponseBody
  public Result<MVO> detail(Model model, MUser mUser, @PathVariable("goodsId") long goodsId) {
    model.addAttribute("user", mUser);
    GoodsVO good = goodsService.getGoodsVoByGoodsId(goodsId);
    long startAt = good.getStart_Date().getTime();
    long endAt = good.getEnd_Date().getTime();
    long now = System.currentTimeMillis();
    int miaoshaStatus = 0;
    int remainSeconds = 0;
    if (now < startAt) {
      miaoshaStatus = 0;
      remainSeconds = (int) ((startAt - now) / 1000);
    } else if (now > endAt) {
      miaoshaStatus = 2;
      remainSeconds = -1;
    } else {
      miaoshaStatus = 1;
      remainSeconds = 0;
    }
    MVO mvo = new MVO();
    mvo.setMiaoshaStatus(miaoshaStatus);
    mvo.setRemainSeconds(remainSeconds);
    return new Result<MVO>(mvo);
  }
  
}
