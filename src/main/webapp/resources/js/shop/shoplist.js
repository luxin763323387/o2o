/***/
$(function(){
    getlist();
    function getlist(e) {
        $.ajax({
           url:"/o2o/shopadmin/getshoplist",
           type:"get",
           dataType:"json",
           success:function (data) {
               if(data.success){
                   handleList(data.shopList); //渲染shopList
                   handlerUser(data.user);//显示ser
               }
           }
        });
    }
    //显示名字
    function handlerUser(data) {
        $('#user-name').text(data.name);
    }
    
    //渲染shopList
    function handleList(data) {
        var html = '';
        data.map(function(item, index) {
            html += '<div class="row row-shop"><div class="col-40">'
                + item.shopName + '</div><div class="col-40">'
                + shopStatus(item.enableStatus)
                + '</div><div class="col-20">'
                + goShop(item.enableStatus, item.shopId) + '</div></div>';

        });
        $('.shop-wrap').html(html);
    }

    //商铺状态
    function shopStatus(status) {
        if(status == 0){
            return '审核中';
        } else if(status == -1){
            return '店铺非法';
        }else {
            return '审核通过';
        }
    }

    //进入按钮
    function goShop(status, id) {
        if (status == 1) {
            return '<a href="/o2o/shopadmin/shopmanagement?shopId=' + id
                + '">进入</a>';
        } else {
            return '';
        }
    }
});