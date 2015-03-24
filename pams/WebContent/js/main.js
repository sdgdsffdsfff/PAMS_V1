/*
所有页面都要加载的全局 js
2014-08-04 19:35:19
*/

var portalColor = '#3083c8,#a5c838,#c4473a,#34586b,#006600,#338888,#318cbe,#623800,#a5c838,#ff3333,#34586b,purple,#2b4e82'.split(',');
var visibleThsArr = []; //gstable 可见的那些 column


$color_default = '#ccc';
$color_primary = '#2d6ca2';
$color_success = '#419641';
$color_info = '#2aabd2';
$color_warning = '#eb9316';
$color_danger = '#c12e2a';
$color_link = '#428bca';
$color_meta = '#999';
$color_inverse = '#333';
$color_purple = 'purple';
$color_grey = '#ccc';

function parseUrl(url) 
{
    var pos,str,para,parastr; 

    var array =[]

    str = url;

    parastr = str.split("?")[1]; 

    var arr = parastr.split("&");

    for (var i=0;i<arr.length;i++)
    {

        array[arr[i].split("=")[0]]=arr[i].split("=")[1];

    }

    return array;
}

jQuery(function($) {
  ///////////////////


$('.gsAnimatedTabs-container .tabTitle .item').on('mouseenter',function(){
  var tabContent=$(this).closest('.gsAnimatedTabs-container').find('.tabContent');
  $(this).addClass('active').siblings().removeClass('active');
  tabContent.removeClass('t0 t1 t2 t3 t4 t5').addClass('t'+$(this).index());
})


  //gstable init -------------start

  //init gstable the 第一次
  $('.gstable-container').each(function() {
    //gop 部份不需要重画！
    var data = generateFilterData($(this).find('.gop'));

    //在 画 table 之前修改 innerDiv 否则 差一个滚动条的距离！！！！
    var gstableContainer = $(this);
    var gstable = $(this).find('.gstable');
    if (gstableContainer.hasClass('fixedHeader')) { //如果锁定 表头
      var tbInnerDiv = gstable;
      var rows = gstable.attr('data-rows');
      if (!rows || rows == '') {
        rows = 10; //默认的 fixedHeader table 高度！
      }
      tbInnerDiv.css({
        height: rows * 37 + 'px'
      });
    }

    drawGsTable(gstable, data);
  })

  //gsreadyfirsttime
  $('.gstable').on('gsreadyfirsttime', function() {
    console.log('第一次表格渲染完毕！');
  })

  //gstable event
  $('.gstable').on('click', 'thead th.sortable', function() { //sortable
    var gstableContainer = $(this).closest('.gstable-container');
    var gstable = gstableContainer.find('.gstable');
    var pagi = gstableContainer.find('.pagination');
    var tr = $(this).parent();
    var data = {
      sortby: $.trim($(this).attr('data-inputname')),
      step: pagi.find('input.step').val()
    }; //如果排序的话，不需要提交 第几页（因为永远是第一页）

    //每次都重画，所以不需要前端跟踪处理样式！
    if ($(this).hasClass('desc')) {
      data.sort = 'asc';
    } else {
      data.sort = 'desc';
    }
    var advdata = generateFilterData(gstableContainer.find('.gop'));
    drawGsTable(gstable, $.param(advdata)+'&'+$.param(data));

  })

  //表格中的 checkall 切换自己 列 的所有 checkbox
  $('.gstable').on('change', 'thead .checkall', function() {
    var othis = $(this);
    var tb = $(this).closest('table');
    var cellIndex = $(this).closest('th').index();
    tb.find('tbody tr').each(function() {
      $(this).find('td:eq(' + cellIndex + ') :checkbox').prop('checked', othis.prop('checked'));
    })
  })

  //每页多少｜第几页 处理
  $('.gstable-container').on('change', '.pagination input', function() {
    var gstableContainer = $(this).closest('.gstable-container');
    var gstable = gstableContainer.find('.gstable');
    var pagi = gstableContainer.find('.pagination');
    var v = parseInt($(this).val(), 10);
    if (v < 1 || !v) {
      alert('必须是有效数字');
      $(this).focus()[0].select();
      return false;
    }
    $(this).val(v); // 类似 2d 也是 2

    //page 不能大于 totalPages
    if ($(this).hasClass('page')) {
      var totalPages = parseInt(pagi.find('.totalPages').text(), 10);
      if (v > totalPages) {
        $(this).val(totalPages);
      }
    }

    var sortbyTh = gstable.find('thead th.desc,thead th.asc');
    var sort = '';
    if (sortbyTh.hasClass('desc')) {
      sort = 'desc';
    } else if (sortbyTh.hasClass('asc')) {
      sort = 'asc';
    }
    var data = {
      sortby: $.trim(sortbyTh.attr('data-inputname')),
      sort: sort,
      step: pagi.find('input.step').val(),
      page: pagi.find('input.page').val()
    }
    var advdata = generateFilterData(gstableContainer.find('.gop'));
    drawGsTable(gstable, $.param(advdata)+'&'+$.param(data));
  })

  //上一页｜下一页
  $('.gstable-container').on('click', '.pagination .prevpage,.pagination .nextpage', function() {
    var pagi = $(this).closest('.pagination');
    var inputpage = pagi.find('input.page');
    // var inputpage=parseInt($.trim(pagi.find('input.page').val()),10);
    if ($(this).hasClass('prevpage')) {
      inputpage.val(parseInt($.trim(inputpage.val())) - 1);
      inputpage.trigger('change');
    } else if ($(this).hasClass('nextpage')) {
      inputpage.val(parseInt($.trim(inputpage.val())) + 1);
      inputpage.trigger('change');
    }
  })

  //trclick 可以点击一行
  $('.trclick').on('click', 'tbody tr', function() {
    $(this).siblings().removeClass('selected');
    $(this).addClass('selected');
  })

  $('.gop .advancedSearch-btn').on('click', function() {
    $(this).toggleClass('opened');
    var sdiv=$(this).closest('.gop').find('.advancedSearch');
    if(sdiv.length==0){
      sdiv=$(this).closest('.gop').find('.advancedSearch2');
    }
    sdiv.toggle();
  })

  //.gscolSeletor
  $('.gop').on('click', '.gscolSeletor ul li', function(e) {
    $(this).toggleClass('disabled');
    return false;
  }).on('hide.bs.dropdown', '.gscolSeletor', function(e) {
    //关闭 dropdown 才刷新 数据｜还要对比是否变化，没变化也不刷新
    var ul = $(this).find('ul');
    var arr = [];
    ul.find('li').each(function(j, k) {
      if ($(k).hasClass('disabled')) {
        arr.push(j);
      }
    })
    //比较 visibleThsArr 和 arr 数否一样。不一样才 留值
    if (arr.join(',') != visibleThsArr.join(',')) {
      visibleThsArr = arr;
      console.log('开始刷新 datagrid');
      //翻页的参数最完整 搜索＋高级搜索＋step+page
      $(this).closest('.gstable-container').find('.pagination input:first').trigger('change');
    }
  })

  //gstable 过滤器
  $('.gop').on('change', '.filter :input', function() {
    var gstableContainer = $(this).closest('.gstable-container');
    var gstable = gstableContainer.find('.gstable');
    var sortbyTh = gstable.find('thead th.desc,thead th.asc');
    var pagi = gstableContainer.find('.pagination');
    var sort = '';
    if (sortbyTh.hasClass('desc')) {
      sort = 'desc';
    } else if (sortbyTh.hasClass('asc')) {
      sort = 'asc';
    }
    var data = {
      sortby: $.trim(sortbyTh.attr('data-inputname')),
      sort: sort,
      step: pagi.find('input.step').val()
      //搜索后 不需要原理的页面！
    }
    var advdata = generateFilterData(gstableContainer.find('.gop'));
    drawGsTable(gstable, $.param(advdata)+'&'+$.param(data));
  })

  //搜索没有 确定 按钮；焦点离开，或者 回车  都会触发 ajax
  $('.gstable-container .gop .filter input.search').on('keyup', function(e) {
    if (e.keyCode == 13) {
      $(this).blur();
    }
  })

  //高级搜索 提交
  $('.gop').on('click', '.advancedSearchSubmit', function() {
    var gstableContainer = $(this).closest('.gstable-container');
    var gstable=gstableContainer.find('.gstable');
    var sortbyTh = gstableContainer.find('thead th.desc,thead th.asc');
    var pagi = gstableContainer.find('.pagination');
    var sort = '';
    if (sortbyTh.hasClass('desc')) {
      sort = 'desc';
    } else if (sortbyTh.hasClass('asc')) {
      sort = 'asc';
    }
    var data = {
      sortby: $.trim(sortbyTh.attr('data-inputname')),
      sort: sort,
      step: pagi.find('input.step').val()
      //搜索后 不需要原理的页面！
    }
    var advdata = generateFilterData(gstableContainer.find('.gop'));
    //把自己也传过去，处理 button ajaxing
    // drawGsTable(gstable, _.merge(advdata, data), $(this));
    drawGsTable(gstable, $.param(advdata)+'&'+$.param(data), $(this));
  })

  //gstable -------------end

  $('.showtip').tooltip();
  $('.gslevel4 li').tooltip();

  //portal 背景颜色 ｜ 用 css 给效率更高一点
  $('#portal').find('li').each(function(j, k) {
    var num = j % portalColor.length;
    var a=$(this).find('a');
    a.css({
      backgroundColor: portalColor[num]
    }).wrapInner('<span></span>');
    $(this).attr('title',a.text());
  })

  //fromNow
  $('.fromNow').each(function() {
    $(this).text(moment($(this).text()).fromNow());
  })

  /*
0 or 'hour' for the hour view
1 or 'day' for the day view
2 or 'month' for month view (the default)
3 or 'year' for the 12-month overview
4 or 'decade' for the 10-year overview. Useful for date-of-birth datetimepickers.
*/

if($.fn.datetimepicker){
//if datetimepicker ---   start  
  $(".datetimepicker").datetimepicker({
    format: "yyyy-mm-dd hh:ii",
    language: 'zh-CN',
    todayBtn: 1,
    autoclose: 1,
    todayHighlight: 1
  }).on('changeDate', function(obj) {

  });

  //timepicker
  $(".timepicker").datetimepicker({
    format: "hh:ii",
    startView: 1,
    language: 'zh-CN',
    autoclose: 1
  })

  //timepicker
  $(".datepicker").datetimepicker({
    format: "yyyy-mm-dd",
    minView: 2,
    language: 'zh-CN',
    autoclose: 1
  })
//if datetimepicker ---   end  
}

  //window resize calculate th width
  var lasyResize = null;
  $(window).on('resize', function() {
    clearInterval(lasyResize);
    lasyResize = setTimeout(function() {
      $('.fixedHeader').each(function() {
        fixedHeaderTableWidth($(this).find('table'));
      })
    }, 200)
  })


  //#leftNavi - toggle
  $('#leftNavi').on('click','.t',function(e){
    e.stopPropagation();
    var li=$(this).closest('li');
    li.siblings().removeClass('active');
    li.toggleClass('active');
  })

  //#leftNavi - click
  var leftNaviSubLis=$('#leftNavi').find('.sub li');
  $('#leftNavi').on('click','.sub a',function(){
    leftNaviSubLis.removeClass('active');
    $(this).closest('li').addClass('active');
  })

  //gsaccordion -------------start

  $('.gsaccordion').on('click', '.t', function() {
    //multiopen 允许同时打开多个！
    var gsaccordion = $(this).closest('.gsaccordion');
    var multiopen = gsaccordion.hasClass('multiopen');
    var li=$(this).closest('li');
    if (!multiopen) {
      li.siblings().removeClass('active');
    }
    $(this).parent().toggleClass('active');
  })

  $('.gsaccordion').on('click', '.sub li a', function() {
    var gsaccordion=$(this).closest('.gsaccordion');
    var li=$(this).closest('li');
    gsaccordion.find('.sub li').removeClass('active');
    li.addClass('active');

    //scroll to
    var contentContainer=gsaccordion.closest('.row').find('>div:eq(1)');
    $('body').animate({
      scrollTop: contentContainer.position().top - 60
    },300,'swing')
  })

  $('.gsvTabs').on('click', 'li a', function() {
    var gsvTabs=$(this).closest('.gsvTabs');
    var li=$(this).closest('li');
    gsvTabs.find('li').removeClass('active');
    li.addClass('active');

    //scroll to
    var contentContainer=gsvTabs.closest('.row').find('>div:eq(1)');
    $('body').animate({
      scrollTop: contentContainer.position().top - 60
    },300,'swing')
  })


  //gsaccordion -------------end

  //项目状态 中文定义
  var gsSlider_status_arr = ['良好', '一般', '警告', '危险'];

  $('.gsSlider-status').each(function(j, k) {
    var othis = $(k);
    var input = othis.find('input');
    var value = input.val() == '' ? 0 : input.val();

    //initial s* class
    if (input.val() != '') {
      othis.addClass('s' + value)
    }

    input.slider({

      min: 0,
      max: gsSlider_status_arr.length - 1,
      value: value,
      formater: function(value) {
        return gsSlider_status_arr[value];
      },
    }).on('slide', function(obj) {
      var cls = othis.attr('class');
      cls = cls.replace(/ s\d+/, '');
      othis.attr('class', cls);
    }).on('slideStop', function(obj) {
      var cls = othis.attr('class');
      cls = cls.replace(/ s\d+/, '');
      othis.attr('class', cls + ' s' + obj.value)
    })
    if (othis.hasClass('disabled')) {
      input.slider('disable');
    }
  })

  if (jQuery.fn.bootstrapSwitch) { //如果有的话
    $('.gsSwitch').bootstrapSwitch({
      wrapperClass: 'gsSwitch'
    })
  }

  // $('.gsSwitch').each(function(j,k){
  // 	var input=$(k);
  // 	input['bootstrapSwitch']({//funny writing about .
  // 		wrapperClass:'gsSwitch-Wrapper'
  // 	});
  // 	// $(k).on('click',function(){
  // 	// 	// $(k).find('input').trigger('click');
  // 	// 	// input.bootstrapSwitch('state',true,true);
  // 	// 	// input.trigger('change');
  // 	// })	
  // })


//人员选择  --start

//加人
function choosePerson_add(obj,sname,sid){
  var input=obj.find('.personids');
  var ul=obj.find('.plist');
  var inputVal=$.trim(input.val());
  var droplist=obj.find('.droplist');
  var limit=obj.attr('data-limit');
  var inputarr=[];
  if(inputVal!=''){
    inputarr=inputVal.split(',');
  }
  if(limit==undefined||inputarr.length < limit){
    //如果不是单选 --start 或者 还没有到达限制数

    //check repeated
    if($.inArray(sid,inputarr)!=-1){
    //有重复！
    return false;
    }
    inputarr.push(sid);
    input.val(inputarr.join(','));
    ul.append('<li data-id="'+sid+'"><span class="remove">×</span>'+sname+'</li>');
    //同步 droplist
    droplist.find('tr[data-id='+sid+']').addClass('selected');
    //如果不是单选 --end
  }else{
    //到达了限制人数 --start
    
    var lastid=inputarr[limit-1];
    // console.log(inputarr[limit-1],limit)
    inputarr[limit-1]=sid;
    input.val(inputarr.join(','));
    ul.find('li:last').remove();
    ul.append('<li data-id="'+sid+'"><span class="remove">×</span>'+sname+'</li>');
    droplist.find('tr[data-id='+lastid+']').removeClass('selected');
    droplist.find('tr[data-id='+sid+']').addClass('selected');

    //到达了限制人数 --end
  }
  
}

//减人
function choosePerson_remove(obj,sid){
  var input=obj.find('.personids');
  var ul=obj.find('.plist');
  var inputarr=input.val().split(',');
  var droplist=obj.find('.droplist');

  ul.find('li[data-id='+sid+']').remove();
  var oindex=$.inArray(sid,inputarr);
  inputarr.splice(oindex,1);

  input.val(inputarr.join(','));
  droplist.find('tr[data-id='+sid+']').removeClass('selected');//同步 droplist
}

//ajax 加人
$('.choosePerson').on('click','.droplist tbody tr',function(e){
  var container=$(this).closest('.choosePerson');
  var sid=$(this).attr('data-id');
  if(!$(this).hasClass('selected')){
    choosePerson_add(container,$.trim($(this).find('td:eq(0)').text()),sid);
  }else{
    choosePerson_remove(container,sid);
  }
  return false;
})

//删除人
$('.choosePerson').on('click','.plist li .remove',function(){
  var container=$(this).closest('.choosePerson');
  var sid=$(this).closest('li').attr('data-id');
  choosePerson_remove(container,sid);
})

//ajax 找人
$('.choosePerson').on('keyup','.inputable',function(){
  var v=$.trim($(this).val());
  var container=$(this).closest('.choosePerson');
  var droplist=container.find('.droplist');
  var ajaxLetterLength=0;//字母 限制
  if(v.length<=ajaxLetterLength){return false;}
  $.ajax({
    url:container.attr('data-ajax'),
    data:{
      v:v
    },
    cache:false,
    success:function(d){
      var html='';
      console.log(d);
      $.each(d.data.items,function(j,k){
        html+='<tr data-id="'+k.sid+'"><td class="min">'+k.sname+'</td><td class="min">'+k.loginname+'</td><td>'+k.department+'</td></tr>';
      })
      droplist.find('tbody').html(html);

      //删除掉 已经选择的人
      var inputval=container.find('.personids').val();
      if(inputval!=''){
        $.each(inputval.split(','),function(j,k){
          droplist.find('tbody tr[data-id='+k+']').addClass('selected');
        })
      }

      var tip=container.find('caption .tip');
      var resultNum=droplist.find('tbody tr').not('.selected').length;
      if(resultNum==0){
        tip.text('没有更多结果！')
      }else{
        tip.text(resultNum +' 个结果！')
      }
      
      droplist.show();
    }
  })
})

//清空
$('.choosePerson').on('blur','.inputable',function(){
  $(this).val('');
})

//隐藏
$(document).on('click',function(){
  $('.choosePerson .droplist').hide();
})

//让 ajax 输入框更容易点击
$('.choosePerson').on('click',function(){
  $(this).find('.inputable').trigger('focus');
})

//choosemore
$('.choosePerson').on('click','.addmore',function(e){
  var container=$(this).closest('.choosePerson');
  var url=container.attr('data-more');
  var chooseWin=window.open(url,'choose','');
  window.gsopener={
    ids:container.find('.personids'),
    namelist:container.find('.plist'),
    limit:container.attr('data-limit')
  }
  chooseWin.focus();
})

//人员选择  --end

//select data-option
$('select[data-option]').each(function(j,k){
  $.ajax({
    url:$(k).attr('data-option'),
    cache:false,
    success:function(d){
      var html='';
      $.each(d,function(n,m){
        var selected=m.s?' selected':'';
        html+='<option value="'+m.v+'"'+selected+'>'+m.t+'</option>'
      })
      $(k).html(html);
    }
  })
})

//select2select initial 把左边已经添加的 从 右边 删除
$('.select2select').each(function(j,k){
  var container=$(k);
  var left=container.find('select.left');
  var right=container.find('select.right');
  var selectedArr=[];
  left.find('option').each(function(n,m){
    // console.log(m.value)
    selectedArr.push(m.value)
  })
  right.find('option').each(function(n,m){
    // console.log(m.value)
    if($.inArray(m.value,selectedArr)!=-1){
      $(m).remove();
    }
  })
})

//select2select
$('.select2select').on('click','.toRight,.toLeft,.alltoRight,.alltoLeft',function(){
  var container=$(this).closest('.select2select');
  if($(this).hasClass('toRight')){
    var selected=container.find('.left option:selected');
    if(selected.length!=0){
      selected.prependTo(container.find('.right'));
    }
  }else if($(this).hasClass('toLeft')){
    var selected=container.find('.right option:selected');
    if(selected.length!=0){
      selected.prependTo(container.find('.left'));
    }
  }else if($(this).hasClass('alltoLeft')){
    container.find('.right option').prependTo(container.find('.left'));
  }else {
    container.find('.left option').prependTo(container.find('.right'));
  }
})

//select2select dblclick
$('.select2select').on('dblclick','select.left,select.right',function(){
  var container=$(this).closest('.select2select');
  if($(this).hasClass('left')){
    var selected=container.find('.left option:selected');
    if(selected.length!=0){
      selected.prependTo(container.find('.right'));
    }
  }else{
    var selected=container.find('.right option:selected');
    if(selected.length!=0){
      selected.prependTo(container.find('.left'));
    }
  }
})

//chooseDepartment addmore
$('.chooseDepartment').on('click','.addmore',function(e){
  var container=$(this).closest('.chooseDepartment');
  container.find('.dropTree').show();
  e.stopPropagation();
  return false;
})

$('.chooseDepartment').on('click','.dlist .remove',function(e){
  var container=$(this).closest('.chooseDepartment');
  var li= $(this).closest('li');
  var sid=li.attr('data-id');
  var idsinput=container.find('.departmentids');
  var idsarr=idsinput.val().split(',');
  var dropTree=container.find('.dropTree');

  var oindex=$.inArray(sid,idsarr);
  console.log(oindex)
  if(oindex!=-1){
    idsarr.splice(oindex,1);
  }
  idsinput.val(idsarr.join(','));
  li.remove();
  dropTree.find('li[data-id='+sid+']').removeClass('active');

  e.stopPropagation();
  return false;
})

$(document).on('click',function(){
  $('.dropTree').hide();
})

$('.chooseDepartment').on('click','.dropTree .line',function(e){
  var container=$(this).closest('.chooseDepartment');
  var li=$(this).closest('li');
  var sid=li.attr('data-id');
  var sname=li.find('>.line .t').text();
  var dlist=container.find('.dlist');
  var dropTree=$(this).closest('.dropTree');
  var limit=container.attr('data-limit');
  var idsarr=[];
  var idsinput=container.find('input.departmentids');
  if(idsinput.val()!=''){
    idsarr=idsinput.val().split(',');
  }
  
  li.toggleClass('active');
  if(limit==undefined||idsarr.length<limit){
    //多选
    
    if(li.hasClass('active')){
      idsarr.push(sid);
      //dlist
      dlist.append('<li data-id="'+sid+'"><span class="remove">× </span>'+sname+'</li>');
    }else{
      var oindex=$.inArray(sid,idsarr);
      idsarr.splice(oindex,1);
      //dlist
      dlist.find('li[data-id='+sid+']').remove();
    }
    idsinput.val(idsarr.join(','));

  }else{//单选（或者说 够了）
    

    if(li.hasClass('active')){
      dlist.find('li[data-id='+idsarr[idsarr.length-1]+']').remove();
      dropTree.find('li[data-id='+idsarr[idsarr.length-1]+']').removeClass('active');

      idsarr.splice(idsarr.length-1,1);
      console.log(idsarr)
      idsarr.push(sid);
      idsinput.val(idsarr.join(','));
      li.addClass('active');
      dlist.append('<li data-id="'+sid+'"><span class="remove">× </span>'+sname+'</li>');
    }else{
      // alert(31)
      var oindex=$.inArray(sid,idsarr);
      idsarr.splice(oindex,1);
      idsinput.val(idsarr.join(','));
      dlist.find('li[data-id='+sid+']').remove();
    }
    
  }

  e.stopPropagation();
  return false;
})


$('.chooseDepartment').on('click','i.toggleHandler',function(e){
  var container=$(this).closest('.chooseDepartment');
  var li=$(this).closest('li');
  if($(this).hasClass('leaf')){return false;}
  if($(this).hasClass('ajaxed')){
   if($(this).hasClass('fa-plus-square')){
    li.find('ul').show();
    $(this).removeClass('fa-plus-square').addClass('fa-minus-square');
   }else{
    li.find('ul').hide();
    $(this).removeClass('fa-minus-square').addClass('fa-plus-square');
   }
  }else{
   $(this).addClass('ajaxed');
   li.append(get_ajaxGsTree_department(container.attr('data-ajax'),li.attr('data-id')));

   //检查已选
  var idsarr=[];
  var idsinputVal=container.find('input.departmentids').val();
  if(idsinputVal!=''){
    idsarr=idsinputVal.split(',');
  }
  li.find('>ul>li').each(function(){
    if($.inArray($(this).attr('data-id'),idsarr)!=-1){
      $(this).addClass('active');
    }
  })

   $(this).removeClass('fa-plus-square').addClass('fa-minus-square');
  }

  e.stopPropagation();
  return false;
})

//上传部分 －－－－－ upload btn
$('.gsupload-btn').on('click',function(){
  var uploadwin=window.open('../upload.html','uploadwin');
  window.attachmentsObj=$(this).parent().find('.attachments');
  window.attachmentsIds=$(this).parent().find('.ids');
  window.attachmentsMode='multiple';
  if($(this).hasClass('single')){
    window.attachmentsMode='single';
  }
})

//初始化图标
$('ul.attachments li').each(function(j,k){
  var a=$(this).find('a');
  var href=a.attr('href');
  var html=suffix2icon(href);
  a.prepend(html)
})

//附件 删除
$('ul.attachments').on('click','li .remove',function(){
  var li=$(this).closest('li');
  var id=li.attr('data-id');
  var attachmentsontainer=li.closest('.attachments-container');
  var input=attachmentsontainer.find('input.ids')
  var idsArr=input.val().split(',');
  // console.log(idsArr);
  var index=$.inArray(id,idsArr);
  idsArr.splice(index,1);
  // console.log(idsArr)
  input.val(idsArr.join(','));
  li.remove();
})

initback2top();

//#gsmenu-container
$('#gsmenu-container').on('click','.togglemenu .toggle',function(){
  $('#gsmenu-container').toggleClass('opened');
})

//上下翻滚  隐藏菜单！
/*var lastScrollTop = 0;
var timer=null;
var $headMenu=$('#header').find('>.navbar');
$(window).scroll(function(event){
  var win=$(window);
  clearInterval(timer);
  timer=setTimeout(function(){
    var st = win.scrollTop();
     if (st > lastScrollTop){
         $headMenu.stop().animate({
          'margin-top':'-40px'
         },300,'swing')
     } else {
        $headMenu.stop().animate({
          'margin-top':0
         },300,'swing')
     }
     lastScrollTop = st;
    },300);
});*/

  //#toggleSubMenu
  $('#toggleSubMenu').on('click',function(){
    $(this).closest('#gsLayoutContainer').toggleClass('opened');
  })

  $('#leftNavi').on('click',function(e){
    if(!$(e.target).hasClass('t')){
      $(this).closest('#gsLayoutContainer').toggleClass('opened');
    }
  })


  ///////////////////
})



//// function define ------------------------------------------------------------------

//back2top
function initback2top(){
  //如果不是手机，没有
  if($(window).width()>430){return false;}
  var back2top=$('.back2top');
  if(back2top.length==0){
    var back2top=$('<div class="back2top"></div>');
    $('body').append(back2top);
  }

  back2top.on('click',function(){
    $('body').animate({
      scrollTop:0
    },300)
  })

  $(window).on('scroll',function(){
    if(document.body.scrollTop>$(window).height()/2){
      //如果大于半个屏幕才出 back2top
      back2top.show();
    }else{
      back2top.hide();
    }
  }) 
}

function suffix2icon(s){
//根据后缀名，判断文件类型
  if(s.match(/\.jpg$/) || s.match(/\.jpeg$/) || s.match(/\.png$/) || s.match(/\.gif$/)){
    return '<i class="filetype fa fa-file-picture-o"></i>';
  }else if(s.match(/\.txt$/) || s.match(/\.log$/) || s.match(/\.md$/) || s.match(/\.loc$/) || s.match(/\.ini$/) || s.match(/\.json$/)){
    return '<i class="filetype fa fa-file-text-o"></i>';
  }else if(s.match(/\.zip$/) || s.match(/\.rar$/) || s.match(/\.tar$/) || s.match(/\.tar.gz$/) || s.match(/\.7z$/)){
    return '<i class="filetype fa fa-file-archive-o"></i>';
  }else if(s.match(/\.mp4$/) || s.match(/\.rmvb$/) || s.match(/\.flv$/) || s.match(/\.mpeg$/) || s.match(/\.mov$/) || s.match(/\.mkv$/)){
    return '<i class="filetype fa fa-file-video-o"></i>';
  }else if(s.match(/\.mp3$/) || s.match(/\.wav$/) || s.match(/\.flac$/) || s.match(/\.midi$/)){
    return '<i class="filetype fa fa-file-audio-o"></i>';
  }else if(s.match(/\.pdf$/)){
    return '<i class="filetype fa fa-file-pdf-o"></i>';
  }else if(s.match(/\.doc$/) || s.match(/\.docx$/)){
    return '<i class="filetype fa fa-file-word-o"></i>';
  }else if(s.match(/\.xls$/) || s.match(/\.xlsx$/)){
    return '<i class="filetype fa fa-file-excel-o"></i>';
  }else if(s.match(/\.ppt$/) || s.match(/\.pptx$/)){
    return '<i class="filetype fa fa-file-powerpoint-o"></i>';
  }else if(s.match(/\.htm$/) || s.match(/\.xml$/) || s.match(/\.css$/) || s.match(/\.js$/) || s.match(/\.html$/)){
    return '<i class="filetype fa fa-file-code-o"></i>';
  }else {
    return '<i class="filetype fa fa-file-o"></i>';
  }
}

//部门选择 的 ajax 返回
function get_ajaxGsTree_department(ajaxurl,id){
 var html;
 $.ajax({
  data:{id:id},
  cache:false,
  async:false,
  dataType:'json',
  url:ajaxurl,

  success:function(d){
   //- console.log(d);
   html='<ul>';
   $.each(d.tree,function(j,k){
    var cls='fa-plus-square';
    if(k.isleaf==1){cls='leaf fa-users'}
    html+='<li data-id="'+k.oid+'"><div class="line"><i class="fa fa-check check"></i>';
    html+='<i class="fa toggleHandler '+cls+'"></i>';
    html+='<span class="t">'+k.t+'</span></div>';
    html+='</li>';
   })
   html+='</ul>';
   //- console.log(html)
  }
 });
 return html;
}

//根据 高级搜索 的 :input 产生 ajax data(不包括翻页)
function generateFilterData(obj) {
  // var data = {};
  // obj.find(':input[name]:not([type=checkbox])').each(function(j, k) {
  //   data[k.name] = $(k).val()
  // })

  // var checkboxData=obj.find(':input[type=checkbox]').serializeArray();
  // console.info(JSON.stringify(checkboxData))

  // console.info(JSON.stringify(data));
  // console.info(JSON.stringify(obj.find(':input').serializeArray()));
  
  // return data;
  
  return obj.find(':input').serializeArray();
}

//gs ajax table
function drawGsTable(gstable, data, buttonObj) {
  var withcheckbox = gstable.hasClass('withcheckbox');
  var url = gstable.attr('data-url');
  if (!url) {
    alert('必须指定数据源！');
    return false;
  }
  $.ajax({
    url: url,
    cache: false,
    // async: false,
    method:'post',
    data: data,
    beforeSend: function() {
      gstable.empty();
      gstable.addClass('ajaxing');
      if (buttonObj) {
        buttonObj.addClass('ajaxing').prop('disabled', 'disabled');
      }
    },
    complete: function(d) {
      if (buttonObj) {
        buttonObj.removeClass('ajaxing').removeAttr('disabled');
      }
      gstable.removeClass('ajaxing');

      //404 500错误
       if(d.status==404){
        gstable.text('404错误，文件找不到！');
        return false;
       }else if(d.status==500){
        gstable.text('500错误，后台程序报错！');
        return false;
       }

      try {
        JSON.parse(d.responseText)
      } catch (e) {
        alert('数据不是有效的json！')
      }
    },
    success: function(d) {
      if (!d.success) {
        if (d.message == "login") {
          window.location.href = webContext + "/login";
        } else {
          if(d.message){
            gstable.text(d.message);
          }else{
            gstable.text(d.error);
          }
        }
        return false;
      }

      var d=d.data;//外面包了1层

      //pagination
      var pagination = '';
      var totalPages = Math.ceil(d.total / d.step);

      var hidePagi='';
      if (totalPages < 2) { //多于1页才 画pagination
        hidePagi=' style="display:none;"'
      }

      // if (totalPages > 1) { //多于1页才 画pagination
        pagination += '<div class="pagination"'+hidePagi+'><span class="pl">共 ' + d.total + ' 条 每页<input class="step" value="' + d.step + '"></span><span class="pr">第<input class="page" value="' + d.currentpage + '">/ <span class="totalPages">' + totalPages + '</span> 页';
        if (d.currentpage > 1) {
          pagination += '<i class="prevpage fa fa-arrow-left" title="上一页"></i>';
        }
        if (d.currentpage < totalPages) {
          pagination += '<i class="nextpage fa fa-arrow-right" title="下一页"></i></span></div>';
        }
      // }

      var html = '<table class="table"><thead>';
      if (withcheckbox) {
        html += '<th class="min"><div class="t"><input class="checkall" type="checkbox"></div></th>';
      }

      var sortColIndex = -1; //当前排序的 th 
      var hidemobileArr = []; //hidemobile class
      var minClassArr = []; //min class

      $.each(d.thead, function(j, k) {

        if ($.inArray(j, visibleThsArr) != -1) { //这列被关掉了！
          return;
        }
        var cls = k.cls ? k.cls : ''; //class
        var sort = '';
        var clsArr = cls.split(' ');
        if ($.inArray('hidemobile', clsArr) != -1) {
          hidemobileArr.push(j);
        }
        if ($.inArray('min', clsArr) != -1) {
          minClassArr.push(j);
        }
        if (k.sortable) {
          cls += ' sortable'
        };
        if (k.sortable == 'desc') {
          cls += ' desc';
          sortColIndex = j;
        } else if (k.sortable == 'asc') {
          cls += ' asc';
          sortColIndex = j;
        }
        html += '<th data-inputname="'+k.inputname+'" title="' + k.th + '" class="' + cls + '"><div class="t">' + k.th + '</div></th>';
      })

      html += '</thead><tbody>';

      $.each(d.tbody, function(j, k) {

        html += '<tr data-id="' + k.trid + '">';
        // var tnum=0;
        if (withcheckbox) {
          html += '<td class="min"><input type="checkbox"></td>';
        }

        $.each(k.items, function(n, m) {

          if ($.inArray(n, visibleThsArr) != -1) { //这列被关掉了！
            return;
          }

          var tdtext = m.td;
          if (m.fromNow) {
            tdtext = moment(tdtext).fromNow();
          }
          var tdClass = '';
          if (m.cls) {
            tdClass = m.cls;
          }
          if (sortColIndex == n) { //根据 th 画被排序的 td
            tdClass = 'sorted';
          }

          if ($.inArray(n, hidemobileArr) != -1) {
            tdClass += ' hidemobile';
          }
          if ($.inArray(n, minClassArr) != -1) {
            tdClass += ' min';
          }

          if (m.link) {
            html += '<td class="' + tdClass + '"><a href="' + m.link + '">' + tdtext + '</a></td>';
          } else {
            html += '<td class="' + tdClass + '">' + tdtext + '</td>';
          }
        })
        html += '</tr>';
      })
      html += '</tbody></table>';
      gstable.html(html);
      gstable.parent().find('.pagination').remove().end().append(pagination);
      gstable.trigger('gsready');

      var gstableTotalWrapper = gstable.closest('.gstable-container');

      //判断第一次 初始化 gscolSeletor
      var gscolSeletor = gstableTotalWrapper.find('.gscolSeletor');
      if (!gscolSeletor.hasClass('notFirstTime')) {
        var gscolSeletorLi = '';
        $.each(d.thead, function(a, b) {
          gscolSeletorLi += '<li>' + b.th + '</li>';
        })
        gscolSeletor.find('ul').html(gscolSeletorLi);
        gscolSeletor.addClass('notFirstTime');
        gstable.trigger('gsreadyfirsttime');
      }
      //锁定表头

      if (gstableTotalWrapper.hasClass('fixedHeader')) {
        // fixedHeaderTableWidth(gstable.find('table'))
        //如果表格 先高后矮  导致 th 偏离10个像素！延迟一点就好！
        $(window).trigger('resize')
      }

      if(gstable.hasClass('treetable')){
        initTreeTable(gstable);
      }
    }
  })
}

function initTreeTable(tableContainer){
  var tbody=tableContainer.find('tbody');
  //计算 tree 深度
  var levelArr=[];
  tbody.find('div[data-level]').each(function(j,k){
    levelArr.push($(k).attr('data-level').length);
  })
  var leafDeep = Math.max.apply(Math, levelArr);
  //draw tree toggle
  tbody.find('div[data-level]').each(function(j,k){
    var level=$(k).attr('data-level');
    if(level.length<leafDeep){
      $(k).addClass('folder');
      $(k).prepend('<i class="fa folder fa-minus-square" style="margin-left:'+(level.length/3-1)*10+'px"></i> ');
    }else{
      $(k).prepend('<i class="fa file fa-file" style="margin-left:'+(level.length/3-1)*10+'px"></i> ');
    }
  }) 
  //behavior
  tbody.on('click','div[data-level] i',function(e){
    e.stopPropagation();//有时候需要点击整个 div toggle
    //leaf node do nothing!
    if($(this).hasClass('file')){return false;}
    var td=$(this).closest('td');
    var tdIndex=td.index();
    var tr=td.closest('tr');
    var thisLevel=$(this).parent().attr('data-level');
    // console.log(thisLevel);
    $(this).toggleClass('fa-minus-square fa-plus-square');
    var open=1;
    if($(this).hasClass('fa-plus-square')){
      open=0;
    }
    tr.nextAll('tr').each(function(j,k){
      var div=$(k).find('td:eq('+tdIndex+') div[data-level]');
      var level=div.attr('data-level');
      var i=div.find('i');
      if(level.length==thisLevel.length){
        return false;
      }
      // console.log(level);
      if(open==0){
        $(k).hide();
        i.removeClass('fa-minus-square').addClass('fa-plus-square');
      }else{
        $(k).show();
        i.removeClass('fa-plus-square').addClass('fa-minus-square');
      }
      
    })
  })
}

//锁定 表头 计算
function fixedHeaderTableWidth(table) {
  var firstRow = table.find('tbody tr:first');
  table.find('thead th').each(function(j, k) {
    var w = firstRow.find('td:eq(' + j + ')').outerWidth() + 'px';
    $(k).css({
      width: w
    });
    if (table.closest('.gstable-container').hasClass('fixedHeader')) {
      $(k).find('.t').css({
        width: w
      })
    }
  })
}

//本地存储 +
function gsSetLocal(k,v){
  //垃圾浏览器们将来可以 cookie 之
  if(window.localStorage){
    window.localStorage.setItem(k,v);
  }
}

//本地存储 get
function gsGetLocal(k){
  if(window.localStorage){
    return window.localStorage.getItem(k);
  }
}

//本地存储 -
function gsRemoveLocal(k){
  if(window.localStorage){
    window.localStorage.removeItem(k);
  }
}

//ajax 指定区域
function showAjaxDiv(container){
  if(container){
    var div=container.find('.divajaxing');
    if(div.length==0){
      div=$('<div class="divajaxing"></div>');
      div.appendTo(container);
    }
    div.show();
  }else{
    var div=$('#divajaxingMask');
    if(div.length==0){
      div=$('<div id="divajaxingMask"></div>');
      div.appendTo($('body'));
    }
    div.show();
  }
}

function hideAjaxDiv(container){
  if(container){
    container.find('.divajaxing').hide();
  }else{
    $('#divajaxingMask').hide();
  }
}

function showAjaxSpan(container,pos){
  if(!container){return;}
  if(!pos){
    pos='after';
  }
  var span=container.find('.spanajaxing');
  if(span.length==0){
    if(pos=='before'){
      container.prepend('<span class="spanajaxing"></span>');
    }else {
      container.append('<span class="spanajaxing"></span>');
    }
  }
  span.show();
}

function hideAjaxSpan(container){
  if(!container){
    $('.spanajaxing').hide();
  }else{
    container.find('.spanajaxing').hide();
  }
}

function drawChart(type, container, par) {
     
  if (type == 'pie') { ///////------------------------------------gspie

    //init legend,wrapper
    container.empty();
    var $legend = $('<div class="chart-legend"></div>');
    var $wrapper = $('<div class="chart-wrapper"></div>');
    container.append($legend).append($wrapper);

    //高度
    var height = container.attr('data-height');
    if (!height) {
      $wrapper.text('必须指定高度！');
      return; //处理下一个
    }
    $wrapper.css({
      height: height + 'px'
    });
    
    //ajax
    $.ajax({
      url: container.attr('data-src'),
      data: par,
      async:false,
      beforeSend: function() {
        $wrapper.addClass('ajaxing');
      },
      complete: function() {
        $wrapper.removeClass('ajaxing');
      },
      success: function(d) {
        ////
        var data = d.data.data;
        $("body").trigger("querypiesdata",[d.totalpiedata]);
        //参数处理
        if (container.hasClass('clickable')) {
          var clickable = true;
          var clickcls = ' clickable';
        } else {
          var clickable = false;
          var clickcls = '';
        }
        if (container.hasClass('labeloutside')) {
          var pieradius = 0.75;
          var labelradius = 1;
          var labelcls = ' outside';
        } else {
          var pieradius = 1;
          var labelradius = 0.5;
          var labelcls = '';
        }

        var myoption = {
          series: {
            pie: {
              show: true, //显示 pie
              radius: pieradius, //pie占容器大小
              stroke:{
                width:0.1,
                color:'#ffffff'
              },
              label: {
                show: true, //显示 label
                radius: labelradius, //label 文字的 位置
                threshold: 0.05, //小于10％，不显示
                formatter: function(label, series) {
                  // console.log(series)
                  return '<div data-sid="' + series.sid + '" title="' + series.data[0][1] + '" class="gslabel' + labelcls + clickcls + '">' + label + '<div class="percent">' + series.percent.toFixed(1) + '%</div></div>';
                }
              },
              highlight: {
                opacity: 0.1 //hover 
              }
            }
          },
          legend: {
            show: false
          },
          grid: {
            hoverable: clickable, //hover
            clickable: clickable //clickable
          },
          colors: portalColor //颜色。自定义颜色可在 数据中直接 "color":"red"
        };

        var plot = $.plot($wrapper, data, myoption);

        //数据绑定
        container.data('data', data); //（点击 legend）变化中的数据
        container.data('data-origin', data); //原始数据

        //tooltips
        $wrapper.find('.gslabel').tooltip();

        //hover
        container.on('plothover', function(e, pos, item) {
          if (item) {
            container.css({
              cursor: 'pointer'
            })
          } else {
            container.css({
              cursor: 'default'
            })
          }
        })
        //legend
        var legend = '<ul>';
        $.each(data, function(j, k) {
          var color = k.color;
          if (!color) {
            color = portalColor[j % portalColor.length]
          }
          legend += '<li title="' + k.data + '" data-index="' + j + '"><span class="square" style="background:' + color + '"></span>' + k.label + '</li>'
        })
        legend += '</ul>';
        $legend.html(legend);
        $legend.find('li').tooltip();

        //toggle legend 处理
        $legend.on('click', 'li', function() {
          $(this).toggleClass('disabled');
          var odata = _.cloneDeep(data);
          $.each(odata, function(j, k) {
            k.oindex = j;
          })
          // console.log(odata);
          $legend.find('li.disabled').each(function(j, k) {
            // console.log($(this).index())
            // odata.splice($(this).index(),1)
            var oindex = $(this).attr('data-index');
            _.remove(odata, function(obj) {
              return obj.oindex == oindex
            })
          })
          // console.info(odata);
          //数据绑定
          container.data('data', odata);

          plot.setData(odata);
          plot.draw();
        })

        ////
        
      }
    })
  } else if (type == "bar") { ///////------------------------------------gsbar
    //容器
    container.empty();
    var $legend = $('<div class="chart-legend"></div>');
    var $wrapper = $('<div class="chart-wrapper"></div>');
    container.append($legend).append($wrapper);

    //高度
    var height = container.attr('data-height');
    if (!height) {
      $wrapper.text('必须指定高度！');
      return; //处理下一个
    }
    $wrapper.css({
      height: height + 'px'
    });
    //ajax
    $.ajax({
      url: container.attr('data-src'),
      data: par,
      async:false,
      beforeSend: function() {
        $wrapper.addClass('ajaxing');
      },
      complete: function() {
        $wrapper.removeClass('ajaxing');
      },
      success: function(d) {
        ////
        var data = d.data.data;
      
        $("body").trigger("querybarsdata",[d.totalLists]);
        if(d.data.data[0].data.length>0){
        //参数处理
        if (container.hasClass('showValues')) {
          var valueLabelShow = true;
        } else {
          var valueLabelShow = false;
        }

        //数据处理 order 颜色；没有颜色用 portalColor；
        $.each(data, function(j, k) {
          if (!k.color) {
            k.bars = {
              order: (j + 1),
              fillColor: portalColor[j]
            }
          } else {
            k.bars = {
              order: (j + 1),
              fillColor: k.color
            }
          }
          k.color = "#fff";
        })

        //barWidth 比数量多1份 除
        var barWidth = 1 / (data.length + 1);

        //draw flot
        var plot = $.plot($wrapper, data, {
          series: {
            bars: {
              show: true,
              barWidth: barWidth,
              align: "center",
              lineWidth: 1 //配合 color 分开 bar
            },
            valueLabels: {
              show: valueLabelShow,
              showAsHtml: true,
              align: "start"
            }
          },
          xaxis: {
            tickLength: 1, ///关掉 x 背景格子
            tickDecimals: 0,
            // tickSize: 1, //坐标最小间距
            // autoscaleMargin: 0,
            tickFormatter: function(val, axis) {
              //注意 这里两头多两个！
              var text = d.data.gslabels[Math.floor(val) - 1] || '';
              return text + '&nbsp;';
            }
          },
          legend: {
            show: false
          },
          grid: {
            borderWidth: 0
          }
        })

        //flot valueLabels 多个 order 处理
        function calValueLabelPos() {
          // console.log(data)
          //计算每个 bar 的宽度
          var step = plot.width() / d.data.gslabels.length / (data.length + 1);
          //valueLabels 组 整体移动
          $wrapper.find('.valueLabels').each(function(j, k) {
            $(k).css({
              'margin-left': -1 * step * (data.length - 1 - j) + 'px'
            })
          })
        }
        calValueLabelPos();

        //window resize 重新计算！！
        var timer = null;
        $(window).on('resize', function() {
          clearInterval(timer);
          timer = setTimeout(calValueLabelPos, 200)
        })

        //legend
        var legend = '<ul>';
        $.each(data, function(j, k) {
          var color = k.bars.fillColor;
          if (!color) {
            color = portalColor[j % portalColor.length]
          }

          legend += '<li title="' + _.pluck(k.data, 1).join(',') + '" data-index="' + j + '"><span class="square" style="background:' + color + '"></span>' + k.label + '</li>'
        })
        legend += '</ul>';
        $legend.html(legend);

        //tooltips
        $legend.find('li').tooltip();

        //toggle legend 处理
        if (!valueLabelShow) {
          //valueLabelShow 不能处理 legend toggle !!!!
          $legend.on('click', 'li', function() {
            $(this).toggleClass('disabled');
            var odata = _.cloneDeep(data);
            $.each(odata, function(j, k) {
              k.oindex = j;
            })
            // console.log(odata);
            $legend.find('li.disabled').each(function(j, k) {
              // console.log($(this).index())
              // odata.splice($(this).index(),1)
              var oindex = $(this).attr('data-index');
              _.remove(odata, function(obj) {
                return obj.oindex == oindex
              })
            })
            // console.info(odata);

            //数据绑定
            container.data('data', odata);

            plot.setData(odata);
            plot.draw();
          })
        }

        ////
        
      }
      }
    })

  } else if (type == 'line') { ///////------------------------------------gslines

    //容器
    container.empty();
    var $legend = $('<div class="chart-legend"></div>');
    var $wrapper = $('<div class="chart-wrapper"></div>');
    container.append($legend).append($wrapper);

    //高度
    var height = container.attr('data-height');
    if (!height) {
      $wrapper.text('必须指定高度！');
      return; //处理下一个
    }
    $wrapper.css({
      height: height + 'px'
    });

    //ajax
    $.ajax({
      url: container.attr('data-src'),
      data: par,
      async:false,
      success: function(d) {
        ////
        var data = d.data.data;
        $("body").trigger("querylinesdata",[d.totalLists]);
        //参数处理
        if (container.hasClass('showValues')) {
          var valueLabelShow = true;
        } else {
          var valueLabelShow = false;
        }

        if (!container.hasClass('nopoint')) {
          var pointShow = true;
        } else {
          var pointShow = false;
        }

        //数据处理 order 颜色；没有颜色用 portalColor；
        $.each(data, function(j, k) {
          if (!k.color) {
            k.points = {
              fillColor: portalColor[j]
            }
            k.color = portalColor[j]
          } else {
            k.points = {
              fillColor: k.color
            }
          }
          k.lines = {};
          // k.curvedLines = {
          //  apply: true
          // };
        })

        //curvedLines class - lines 和 points 不能同时，所以要复制一份
        if (container.hasClass('curvedLines')) {
          //必须深度拷贝
          var doubledata = data.concat(_.cloneDeep(data));
          $.each(doubledata, function(j, k) {
            // console.log(j,k)
            if (j % 2 == 0) {
              k.points.show = false;
              k.lines.show = true;
              k.curvedLines = {
                apply: true
              };
            } else {
              k.points.show = pointShow;
              k.lines.show = false;
              k.curvedLines = {
                apply: false
              };

            }
          })
        }

        //draw flot
        var plot = $.plot($wrapper, doubledata ? doubledata : data, {
          series: {
            lines: {
              show: true,
              // fill:true,
              lineWidth: 2
            },
            curvedLines: {
              active: true
            },
            points: {
              show: pointShow,
              radius: 3,
              // fill: true,
              // fillColor:"red"
            },
            //只在 points 上处理一遍，否则出现 double valueLabels
            valueLabels: {
              show: valueLabelShow,
              showAsHtml: true,
              align: "center"
            }
          },
          xaxis: {
            tickLength: 1,
            tickDecimals: 0,
            // tickSize: 1, //坐标最小间距
            // autoscaleMargin: 0,
            tickFormatter: function(val, axis) {
              //注意 这里两头多两个！
              var text = d.data.gslabels[Math.floor(val) - 1] || '';
              return text + '&nbsp;';
            }
          },
          grid: {
            borderWidth: 0
          },
          legend: {
            show: false
          },
          colors: portalColor
        });

        //legend
        var legend = '<ul>';
        $.each(data, function(j, k) {
          var color = k.color;
          if (!color) {
            color = portalColor[j % portalColor.length]
          }
          legend += '<li title="' + _.pluck(k.data, 1).join(',') + '" data-index="' + j + '"><span class="square" style="background:' + color + '"></span>' + k.label + '</li>'
        })
        legend += '</ul>';
        $legend.html(legend);

        //tooltips
        $legend.find('li').tooltip();

        //toggle legend 处理
        if (!valueLabelShow) {
          //valueLabelShow 不能处理 legend toggle !!!!
          $legend.on('click', 'li', function() {
            $(this).toggleClass('disabled');
            if (doubledata) { //如果是曲线的话！
              var odata = _.cloneDeep(doubledata);
            } else {
              var odata = _.cloneDeep(data);
            }

            $.each(odata, function(j, k) {
              k.oindex = j;
            })

            // 遍历处理所有 disable li
            $legend.find('li.disabled').each(function(j, k) {
              // console.log($(this).index())
              // odata.splice($(this).index(),1)
              var oindex = $(this).attr('data-index');

              _.remove(odata, function(obj) {
                return obj.oindex == oindex
              })
              //如果是曲线的话！
              if (doubledata) {
                _.remove(odata, function(obj) {
                  return obj.oindex == oindex * 1 + data.length;
                })
              }
              //处理 valueLabels

              // valueLabels 不稳定啊！！！

              // console.log($wrapper.find('.valueLabels'))
              // console.log(oindex,oindex*1 + data.length)
              // valueLabelsArr.filter(':eq('+oindex+')').hide();
              // valueLabelsArr.filter(':eq('+(oindex*1+data.length)+')').hide();

              // valueLabelsArr.filter('#valueLabels'+oindex).remove();
              // valueLabelsArr.filter('#valueLabels'+(oindex*1 + data.length)).remove();

              // setTimeout(function(){
              //  $wrapper.find('#valueLabels'+oindex).remove();
              //  $wrapper.find('#valueLabels'+(oindex*1 + data.length)).remove();
              // },1000)
            })

            //数据绑定
            container.data('data', odata);
            // console.log(odata)
            plot.setData(odata);
            plot.draw();

          })
        }
        ////
     
      }
    })
  }
  
}


