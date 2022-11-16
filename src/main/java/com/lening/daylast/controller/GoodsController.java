package com.lening.daylast.controller;


import com.github.pagehelper.PageInfo;
import com.lening.daylast.ResultInfo;
import com.lening.daylast.entity.BrandBean;
import com.lening.daylast.entity.CityBean;
import com.lening.daylast.entity.GoodsBean;
import com.lening.daylast.entity.SellerBean;
import com.lening.daylast.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    @RequestMapping("/findAll")
    public PageInfo<GoodsBean> findAll(@RequestBody GoodsBean searchEntity,
                                       @RequestParam(defaultValue = "1") Integer pageNum,
                                       @RequestParam(defaultValue = "3") Integer pageSize){
        PageInfo<GoodsBean> pageInfo = goodsService.findAll(searchEntity, pageNum, pageSize);
        return pageInfo;
    }
//批量删除
    @RequestMapping("/deleteById")
    public ResultInfo deleteById(@RequestBody Long[] gids){
        try {
            goodsService.deleteById(gids);
            return new ResultInfo(true, "ok");
        }catch (Exception e){
            return new ResultInfo(false, "删除失败");
        }
    }
//查那三张附表
    @RequestMapping("/getBrandList")
    public List<BrandBean> getBrandList(){
        return goodsService.getBrandList();
    }
    @RequestMapping("/getSellerList")
    public List<SellerBean> getSellerList(){
        return goodsService.getSellerList();
    }
    @RequestMapping("/getCityListByPid")
    public List<CityBean> getCityListByPid(@RequestParam(defaultValue = "1") Long pid){
        return goodsService.getCityListByPid(pid);
    }
//查询商品根据id
    @GetMapping("/findOne")
    public GoodsBean findOne(Long gid){
        System.out.println(gid);
        GoodsBean one = goodsService.findOne(gid);
        return one;
    }


//修改和新增
    @RequestMapping("/saveOrUpdateGoods")
    public ResultInfo saveOrUpdateGoods(@RequestBody GoodsBean goodsBean){
        try {
            goodsService.saveOrUpdateGoods(goodsBean);
            return new ResultInfo(true, "编辑成功");
        }catch (Exception e){
            return new ResultInfo(false, "编辑失败");
        }
    }
}
