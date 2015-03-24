
var params = parseUrl(window.location.href);
var planid = params["id"];

//calculate time
var now = new Date(); //性能测试

//all data
var data = []; //传中的 全局变量

var PHASEORSTEP_cn = ['阶段', '任务', '交付物', '里程碑'];

var thtml = '';

jQuery(function($)
{
  //
  var modalTabsTitle=$('#modalTabs-title');
  var modalTabsContent=$('#modalTabs-content');

  modalTabsTitle.on('click','li',function(){
    var oindex=$(this).index();
    $(this).addClass('active').siblings().removeClass('active');
    modalTabsContent.find('>.item:eq('+oindex+')').addClass('active').siblings().removeClass('active');
  })

//获得数据
$.ajax({
  url: '/pams/plan/listsubplanjson.action',
  dataType: 'json',
  data:{id:planid}, // planid 为当前记录标识
  /*
  注意：直接 beforeSend:showAjaxDiv 的话
  event 会被传递到 showAjaxDiv 里面去，导致 错误
  */
  beforeSend: function() {
    showAjaxDiv();
  },
  complete: function() {
    hideAjaxDiv();
  },
  async: false, //同步，以逃避一个巨大的 括号
  success: function(d) {
    data = d;
  }
});  

ADVPLAN = $('#advplan');

  //root 部门 处理 ------------------
  var rootDiv = ADVPLAN.find('.root');

  //PLANSTARTDATE 中最小值
  var jihuakaishi = moment(_.min(data.items, function(x) {
    if (!$.trim(x.PLANSTARTDATE) == '') {
      return moment(x.PLANSTARTDATE)._d.getTime();
    }
  }).PLANSTARTDATE).format('YYYY-MM-DD');
  
  //proj_phase表中 PLANENDDATE 中最大值
  var jihuajieshu = moment(_.max(data.items, function(x) {
    if (!$.trim(x.PLANENDDATE) == '') {
      return moment(x.PLANENDDATE)._d.getTime();
    }
  }).PLANENDDATE).format('YYYY-MM-DD');

  //基准开始时间：proj_phase表中basestartdate中最小值
  var jizhunkaishi = moment(_.min(data.items, function(x) {
    if (!$.trim(x.BASESTARTDATE) == '') {
      return moment(x.BASESTARTDATE)._d.getTime();
    }
  }).BASESTARTDATE).format('YYYY-MM-DD');

  //基准结束时间：proj_phase表中 baseenddate 中最大值
  var jizhunjieshu = moment(_.max(data.items, function(x) {
    if (!$.trim(x.BASEENDDATE) == '') {
      return moment(x.BASEENDDATE)._d.getTime();
    }
  }).BASEENDDATE).format('YYYY-MM-DD');

  //实际开始时间：proj_phase表中 actualstartdate 中最小值
  var shijikaishi = moment(_.min(data.items, function(x) {
    if (!$.trim(x.ACTUALSTARTDATE) == '') {
      return moment(x.ACTUALSTARTDATE)._d.getTime();
    }
  }).ACTUALSTARTDATE).format('YYYY-MM-DD');

  //实际结束时间：proj_phase表中 acutalenddate 中最大值
  var shijijieshu = moment(_.max(data.items, function(x) {
    if (!$.trim(x.ACUTALENDDATE) == '') {
      return moment(x.ACUTALENDDATE)._d.getTime();
    }
  }).ACUTALENDDATE).format('YYYY-MM-DD');

  var rootHtml = '';
  rootHtml += '<div class="tr" data-type="-1">';
  rootHtml += '<div class="td name">' + data.cname + '</div>';
  rootHtml += '<div class="td" data-name="PLANSTARTDATE">' + jihuakaishi + '</div>';
  rootHtml += '<div class="td" data-name="PLANENDDATE">' + jihuajieshu + '</div>';
  rootHtml += '<div class="td">' + data.completepercent * 100 + '</div>';
  rootHtml += '<div class="td gongzuoliang">' + '' + '</div>';
  rootHtml += '<div class="td">' + 100 + '</div>';
  rootHtml += '<div class="td" data-name="ACTUALSTARTDATE">' + shijikaishi + '</div>';
  rootHtml += '<div class="td" data-name="ACUTALENDDATE">' + shijijieshu + '</div>';
  rootHtml += '<div class="td">' + data.planworkload + '</div>';
  rootHtml += '<div class="td">' + jizhunkaishi + '</div>';
  rootHtml += '<div class="td">' + jizhunjieshu + '</div></div>';

  rootDiv.html(rootHtml);
  

  //tree 部门 处理 ------------------

  _.levelRecursive(data.items, planid);

  data.items = _.sortBy(data.items, 'SEQUENCEKEY');

  console.log(data.items);

  var treeDiv = ADVPLAN.find('.tree');
  treeDiv.html(thtml);

  console.log(thtml);

});

  //迭代在此！输出 tree tr;顺便 调整 sequence 为正确数值
  _.levelRecursive = function(data, parentid, depth, seq) 
  {

    console.log("data:" + data);
    console.log("PARENTid:" + parentid);
    console.log("depth:" + depth);
    console.log("seq:" + seq);

    if (typeof depth == 'number') 
    { //depth
      depth++;
    } else {
      depth = 1;
    }
    if (!seq) {
      seq = '0001'; 
    }
    var found = _.filter(data, {
      "parentid": parentid
    })
    if (found.length > 0) {
      found = _.sortBy(found, 'sequencekey'); //根据 SEQUENCEKEY 排序！

      thtml += '<ul>';

      $.each(found, function(j, k) {
        
        console.log(j + ":" + k);
        
        var child = _.filter(data, {
          "parentid": k.id
        });
        var togglestr = '<i class="spacer"></i>';
        if (child.length > 0) {
          togglestr = '<i class="toggle"></i>';
        }
        var iconstr = '';
        if (k.phaseorstep == 0) {
          iconstr = '<i class="otype fa fa-flag" title="' + PHASEORSTEP_cn[k.phaseorstep] + '"></i>';
        } else if (k.phaseorstep == 1) {
          iconstr = '<i class="otype fa fa-flag-o" title="' + PHASEORSTEP_cn[k.phaseorstep] + '"></i>';
        } else if (k.phaseorstep == 2) {
          iconstr = '<i class="otype fa fa-dropbox" title="' + PHASEORSTEP_cn[k.phaseorstep] + '"></i>';
        } else {
          iconstr = '<i class="otype fa fa-flag-checkered" title="' + PHASEORSTEP_cn[k.phaseorstep] + '"></i>';
        }

        //SEQUENCEKEY
        var gsSequence = seq + padZero(j + 1);

        thtml += '<li data-seq="' + gsSequence + '" data-pid="' + k.parentid + '" data-id="' + k.id + '" data-depth="' + depth + '">';

        var jihuakaishi = '';
        if ($.trim(k.planstartdate) != '') {
          jihuakaishi = moment(k.planstartdate).format('YYYY-MM-DD');
        }

        var jihuajieshu = '';
        if ($.trim(k.planenddate) != '') {
          jihuajieshu = moment(k.planenddate).format('YYYY-MM-DD');
        }
        //完成比例：proj_phase表中(sum(planworkload*completepercent)/ sum(planworkload))*100  ?????
        var wanchengbili = '';
        if (k.phaseorstep == 0 || k.phaseorstep == 1) { //如果是 阶段和任务 完成比例 才有意义！
          wanchengbili = k.completepercent * 100;
        }

        //工作量
        var gongzuoliang = '';
        if (k.phaseorstep == 0 || k.phaseorstep == 1) { //如果是 阶段和任务 完成比例 才有意义！
          gongzuoliang = k.planworkload;
        }

        //基准工作量  k.BASEPLANWORKLOAD
        var jizhungongzuoliang = '';
        if (k.phaseorstep == 0 || k.phaseorstep == 1) { //如果是 阶段和任务 完成比例 才有意义！
          jizhungongzuoliang = k.baseplanworkload;
        }

        // console.log(k.PLANWORKLOAD, k.COMPLETEPERCENT, k.PLANWORKLOAD)

        var jizhunkaishi = '';
        if ($.trim(k.basestartdate) != '') {
          jizhunkaishi = moment(k.basestartdate).format('YYYY-MM-DD');
        }

        var jizhunjieshu = '';
        if ($.trim(k.baseenddate) != '') {
          jizhunjieshu = moment(k.baseenddate).format('YYYY-MM-DD');
        }

        var shijikaishi = '';
        if ($.trim(k.actualstartdate) != '') {
          shijikaishi = moment(k.ACTUALSTARTDATE).format('YYYY-MM-DD');
        }

        var shijijieshu = '';
        if ($.trim(k.acutalenddate) != '') {
          shijijieshu = moment(k.acutalenddate).format('YYYY-MM-DD');
        }

        thtml += '<div class="tr type' + k.phaseorstep + '" data-type="' + k.phaseorstep + '">';
        thtml += '<div class="td name depth' + depth + '">' + togglestr + iconstr + '<span class="t" data-name="cname">' + k.cname + '</span></div>';
        thtml += '<div class="td" data-name="planstartdate">' + jihuakaishi + '</div>';
        thtml += '<div class="td" data-name="planenddate">' + jihuajieshu + '</div>';
        thtml += '<div class="td" data-name="completepercent">' + wanchengbili + '</div>';
        thtml += '<div class="td gongzuoliang" data-name="planworkload">' + gongzuoliang + '</div>';
        thtml += '<div class="td gongzuoliangbili"></div>';
        // thtml += '<div class="td">' + k.REMARK + '</div>';

        thtml += '<div class="td" data-name="actualstartdate">' + shijikaishi + '</div>';
        thtml += '<div class="td" data-name="acutalenddate">' + shijijieshu + '</div>';

        thtml += '<div class="td">' + jizhungongzuoliang + '</div>';

        thtml += '<div class="td">' + jizhunkaishi + '</div>';
        thtml += '<div class="td">' + jizhunjieshu + '</div>';


        thtml += '</div>';

        //纠正为正确的 gsSequence
        k.SEQUENCEKEY = gsSequence;

        if (child.length != 0) {
          _.levelRecursive(data, k.id, depth, gsSequence);
        }

        thtml += '</li>';

      })
thtml += '</ul>';

console.log("thtml:" + thtml);
}
}



function diguiTask(li){
  //dom
  var tr=li.find('>.tr');

  if(tr.attr('data-type')!='1'){//只有任务 才递归
    return;
  }
  
  var num=0;
  var actualnum=0;
  var parentLi=li.parent().parent();

  li.closest('ul').find('>li>.tr').each(function(j,k){
    if($(k).attr('data-type')!='1'){//只有任务 才递归
      return;
    }
    num+=$(k).find('.gongzuoliang').text()*1;

    //找到 li
    var actualItem=_.find(data.items,{id:$(k).parent().attr('data-id')});
    actualnum+=actualItem.ACTUALWORKLOAD*1;
  })

  
  parentLi.find('>.tr').find('.gongzuoliang').text(num);

  //data.items
  var found=_.find(data.items,{id:parentLi.attr('data-id')});
  if(found){
    found.PLANWORKLOAD=num;
    found.ACTUALWORKLOAD=actualnum;

    //actualworkload 没有dom 有  data.items


    //ajax 保存父节点 被计算出来的 workload
    $.ajax({
      url: ajax_url_save_node_parentworkload,
      // type: 'post',
      // data: found,
      data:{
        sid:found.id,
        planworkload:found.PLANWORKLOAD,
        actualworkload:found.ACTUALWORKLOAD
      },
      success: function(d) {
        if (d.status == 'success') {
          // $('#gsModal').modal('hide');
          // console.log('saved');
        } else {
          alert('发生错误！');
        }
      }
    })

    

  }

  //递归父节点
  if(parentLi.attr('data-depth')!='1'){//还没有到达阶段
    diguiTask(parentLi);
  }


}

//递归 重新计算 seq
function calculateSeq(ul, seq) {
  var lis = ul.find('>li');
  if (!seq) {
    seq = '1000'; //怪异的 1000，为何不 0001 反正也是 字符排序
  }
  lis.each(function(j, k) {
    var gsSequence = seq + padZero(j + 1);
    $(this).attr('data-seq', gsSequence);
    var ul = $(this).find('>ul');
    if (ul.length != 0) {
      calculateSeq(ul, gsSequence)
    }

    //同步 data
    _.find(data.items, {
      id: $(this).attr('data-id')
    }).SEQUENCEKEY = gsSequence;
  })
}

//补0
function padZero(s) {
  var str = '' + s;
  var pad = "0000"; //0000 的数量（位数）
  return pad.substring(0, pad.length - str.length) + str;
}





