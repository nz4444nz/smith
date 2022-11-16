new Vue({
    el:'#goodsdiv',
    data:{
        goodsList:[],
        pageNum:1,
        pageSize:3,
        pageInfo:{},
        gids:[],
        searchEntity:{},
        brandList:[],
        sellerList:[],
        proList:[],
        cityList:[],
        couList:[],
        goodsBean:{},
        proid:-1,
        cityid:-1,
        couid:-1,
        proname:'',
        cityname:'',
        couname:''
    },
    methods:{
        paging:function(pageNum){
            if(pageNum==0){
                pageNum = 1;
            }
            this.pageNum = pageNum;
            this.findAll();
        },
        getCnameByIdAndList:function(id,arr){
          if (arr!=null&&arr.length>=1){
              for (var x=0;x<arr.length;x++){
                  if (arr[x].id ==id){
                      return arr[x].cname;
                  }
              }
          }
        },
        getGaddress:function(){
            this.couname = this.getCnameByIdAndList(this.couid,this.couList);
            this.goodsBean.gaddress = this.proname+this.cityname+this.couname;
        },
        getCouList(){
            this.cityname = this.getCnameByIdAndList(this.cityid,this.cityList);
            this.goodsBean.gaddress = this.proname+this.cityname;
            var _this = this;
            axios.get('../goods/getCityListByPid?pid='+_this.cityid).then(function (response) {
                _this.couList = response.data;
            });
        },
        getCityList(){
            this.proname = this.getCnameByIdAndList(this.proid,this.proList);
            this.goodsBean.gaddress = this.proname;
            this.couList = [];
            var _this =this;
            axios.get('../goods/getCityListByPid?pid='+_this.proid).then(function (response) {
                _this.cityList = response.data;
            });
        },
        //城市列表
        getProList(){
            var _this =this;
            axios.get('../goods/getCityListByPid?pid=1').then(function (response) {
                _this.proList = response.data;
            });
        },
        //修改
        updateGoods(){
            var _this = this;
            axios.post("../goods/saveOrUpdateGoods",_this.goodsBean).then(function (response) {
                if (response.data.flag){
                    _this.findAll();
                    document.getElementById("goodsupdatediv").style.display="none";
                }else {
                    alert(response.data.msg);
                }
            })
        },
        //展示修改
        toUpdateGoods(gid){
            var _this = this;
            var url= '../goods/findOne?gid='+gid
            axios.get(url).then(function (response) {
                alert(response.data);
                _this.goodsBean = response.data;
            });
            this.getProList();
            this.getSellerList();
            this.getBrandList();
            document.getElementById("goodsupdatediv").style.display="block";
        },
        //添加
        saveGoods(){
            var _this = this;
            axios.post("../goods/saveOrUpdateGoods",_this.goodsBean).then(function (response) {
                if (response.data.flag){
                    _this.findAll();
                    document.getElementById("goodsadddiv").style.display="none";
                }else {
                    alert(response.data.msg);
                }
            })
        },
        //展示添加
        toAddGoods(){
            this.getProList();
            this.getSellerList();
            this.getBrandList();
            document.getElementById("goodsadddiv").style.display="block";
        },
        //商家列表
        getSellerList:function(){
            var _this =this;
            axios.get('../goods/getSellerList').then(function (response) {
                _this.sellerList = response.data;
            });
        },
        //品牌列表
        getBrandList:function(){
            var _this =this;
            axios.get('../goods/getBrandList').then(function (response) {
                _this.brandList = response.data;
            });
        },
        //删除  批量删除
        deleteBY:function(gid){
            this.gids.push(gid);
            this.deleteById();
        },
        deleteById(){
            if (this.gids!=null&&this.gids.length>=1){
                var _this = this;
                axios.post('../goods/deleteById',_this.gids).then(function (response) {
                    if(response.data.flag){
                        _this.findAll();
                    }else {
                        alert(response.data.msg);
                    }
                });
            }
        },
        //全查
        findAll:function(){
            var _this = this;
            var url = "../goods/findAll?pageNum="+this.pageNum+"&pageSize="+this.pageSize;
            axios.post(url,_this.searchEntity).then(function (response) {
                _this.goodsList = response.data.list;
                _this.pageInfo = response.data;
                _this.pageNum = response.data.pageNum;
            });
        },

    },
    created:function () {
        this.findAll();
        document.getElementById("goodsadddiv").style.display="none";
        document.getElementById("goodsupdatediv").style.display="none";
    }
});