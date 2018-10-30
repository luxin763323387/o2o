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

    $('#new')
        .click(function () {
            var tempHtml ='<div class="row row-product-category temp">'
                +'<div class="col-33"><input class="category-input category" type="text" placeholder="分类名"></div>'
                +'<div class="col-33"><input class="category-input priority" type="number" placeholder="优先级"></div>'
                +'<div class="col-33"><a href="#" class="button delete">删除</a> </div>'
                +'</div>';
            $('.category-wrap').append(tempHtml);
        });

    $('#submit').click(function () {
        var tempArr = $('.temp'); //遍历新增的数组tempArr，遍历新增的行
        var productCategoryList =[];//同时生成一个productCategoryList 数组
        tempArr.map(function (index,item) {
            var tempObj = {};
            tempObj.productCategoryName = $(item).find('.category').val(); //从.category取出
            tempObj.priority = $(item).find('.priority').val();//从.priority去除
            if(tempObj.productCategoryName && tempObj.priority){
                productCategoryList.push(tempObj);//拼接到到 tempObj中
            }
        });

        $.ajax({
            url:addUrl,
            type:'POST',
            data:JSON.stringify(productCategoryList),
            contentType:'application/json',
            success: function (data) {
                if(data.success){
                    $.toast('提交成功！');
                    getList();
                }else{
                    $.toast('提交失败');
                }
            }
        });
    });

});