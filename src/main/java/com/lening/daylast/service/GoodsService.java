package com.lening.daylast.service;

import com.github.pagehelper.PageInfo;
import com.lening.daylast.entity.BrandBean;
import com.lening.daylast.entity.CityBean;
import com.lening.daylast.entity.GoodsBean;
import com.lening.daylast.entity.SellerBean;

import java.util.List;

public interface GoodsService {


    PageInfo<GoodsBean> findAll(GoodsBean searchEntity, Integer pageNum, Integer pageSize);

    void deleteById(Long[] gids);

    List<BrandBean> getBrandList();

    List<SellerBean> getSellerList();

    List<CityBean> getCityListByPid(Long pid);

    void saveOrUpdateGoods(GoodsBean goodsBean);


    GoodsBean findOne(Long gid);
}
