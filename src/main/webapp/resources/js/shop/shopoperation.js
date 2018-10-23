/*
*
 */
$(function () {
    var initUrl = '/o2o/shop/getshopinitinfo';
    var registerShopUrl = '/o2o/shopadmin/registershop';
    function getShopInitInfo() {
        $.getJSON(initUrl,function (data) {
            if(data.success){
                var tempHtml = '';
                var temAreaHtml = '';
                
            }
        })
    }
})