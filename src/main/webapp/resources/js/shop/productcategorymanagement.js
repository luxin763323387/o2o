/***/
$(function () {
    var listUrl = '/o2o/shopadmin/getproductcategorylist';
    var addUrl = '/o2o/shopadmin/addproductcategorys';
    var deleteUrl ='/o2o/shopadmin/removeproductcategory';

    getList();
    function getList() {
        $.getJSON(
            listUrl,
            function (data) {
                if(data.success){
                    var dataList = data.data;
                    $('.category-wrap').html('');
                    var tempHtml='';
                    dataList
                        .map(function (item,index) {
                            tempHtml +=''
                                +'<div class="row row-product-category now">'
                                +'<div class="col-33 product-category-name">'
                                +item.productCategoryName
                                +'</div>'
                                +'<div class="col-33">'
                                + item.priority
                                +'</div>'
                                + '<div class="col-33"><a href="#" class="button delete" data-id="'
                                + item.productCategoryId
                                + '">删除</a></div>'
                                + '</div>';
                        });
                    $('.category-wrap').append(tempHtml)
                }
            });
    }
});