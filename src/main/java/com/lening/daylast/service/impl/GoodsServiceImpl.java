package com.lening.daylast.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lening.daylast.entity.BrandBean;
import com.lening.daylast.entity.CityBean;
import com.lening.daylast.entity.GoodsBean;
import com.lening.daylast.entity.SellerBean;
import com.lening.daylast.mapper.BrandMapper;
import com.lening.daylast.mapper.CityMapper;
import com.lening.daylast.mapper.GoodsMapper;
import com.lening.daylast.mapper.SellerMapper;
import com.lening.daylast.service.GoodsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private SellerMapper sellerMapper;
    @Resource
    private BrandMapper brandMapper;
    @Resource
    private CityMapper cityMapper;


    @Override
    public PageInfo<GoodsBean> findAll(GoodsBean searchEntity, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(GoodsBean.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchEntity!=null){
            if(!StringUtils.isEmpty(searchEntity.getGname())){
                criteria.andLike("gname", "%"+searchEntity.getGname()+"%");
            }
            if(searchEntity.getGprice()!=null){
                criteria.andGreaterThanOrEqualTo("gprice", searchEntity.getGprice());
            }
            if(searchEntity.getEgprice()!=null){
                criteria.andLessThanOrEqualTo("gprice", searchEntity.getEgprice());
            }
        }
        List<GoodsBean> list = goodsMapper.selectByExample(example);
        for (GoodsBean bean : list) {
            if (bean.getSid()!=null) {
                SellerBean sellerBean = sellerMapper.selectByPrimaryKey(bean.getSid());
                bean.setSellerBean(sellerBean);
            }
            if (bean.getId()!=null) {
                BrandBean brandBean = brandMapper.selectByPrimaryKey(bean.getId());
                bean.setBrandBean(brandBean);
            }
        }
        PageInfo<GoodsBean> pageInfo = new PageInfo<GoodsBean>(list);
        return  pageInfo;
    }

    @Override
    public void deleteById(Long[] gids) {
        if (gids!=null&&gids.length>=1){
            for (Long gid : gids) {
                goodsMapper.deleteByPrimaryKey(gid);
            }
        }
    }

    @Override
    public List<BrandBean> getBrandList() {
        return brandMapper.selectAll();
    }

    @Override
    public List<SellerBean> getSellerList() {
        return sellerMapper.selectAll();
    }

    @Override
    public List<CityBean> getCityListByPid(Long pid) {
        Example example = new Example(CityBean.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pid", pid);
        return cityMapper.selectByExample(example);
    }

    @Override
    public void saveOrUpdateGoods(GoodsBean goodsBean) {
        if(goodsBean!=null){
            if(goodsBean.getGid()!=null){
                goodsMapper.updateByPrimaryKeySelective(goodsBean);
            }else {
                goodsMapper.insertSelective(goodsBean);
            }
        }
    }

    @Override
    public GoodsBean findOne(Long gid) {
        return goodsMapper.selectByPrimaryKey(gid);
    }


}
