<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no,minimum-scale=1,maximum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>项目管理</title>
    <link rel="stylesheet" href="${base}/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${base}/lib/font-awesome/css/font-awesome.min.css">
    <link href="../logo60.png" rel="apple-touch-icon">
    <link href="../logo76.png" rel="apple-touch-icon" sizes="76x76">
    <link href="../logo120.png" rel="apple-touch-icon" sizes="120x120">
    <link href="../logo152.png" rel="apple-touch-icon" sizes="152x152">
    <link rel="stylesheet" href="${base}/lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="${base}/css/animation.css">
    <link rel="stylesheet" href="${base}/lib/slider.css">
    <link rel="stylesheet" href="${base}/css/main.css">
    <script src="${base}/lib/jquery-2.1.1.min.js"></script>
    <base target="rightFrame">
  </head>
  <body>
    <div id="gsLayoutContainer">
      <div id="toggleSubMenu"><i class="fa fa-bars"></i></div>
      <div id="leftNavi">
        <ul>

        <li> 
        <div class="t"><i class="fa fa-paper-plane-o"></i> 计划任务</div>
        <ul class="sub">
        <li><a href="${base}/party/partydue/research/usesuggest/browseplan.action">待执行任务</a></li>
        <li><a href="javascript:void(0);">已执行任务</a></li>
        <li><a href="javascript:void(0);">已完成任务</a></li>
        </ul>
        </li>
        
        <li class="active"> 
            <div class="t"><i class="fa fa-code-fork"></i> 业务流程</div>
            <ul class="sub">

              <li class="active"> <a href="${base}/party/partydue/research/usesuggest/browsewait.action">个人待办业务</a></li>
              <li><a href="${base}/party/partydue/research/usesuggest/browsehandle.action">个人已办业务</a></li>
              <li><a href="${base}/party/partydue/research/usesuggest/browseall.action">个人全部业务</a></li>
            </ul>
          </li>
          <li> 
            <div class="t"><i class="fa fa-file-text-o"></i> 报告与文档</div>
            <ul class="sub">
              <li><a href="javascript:void(0);">我的文档</a></li>
              <li><a href="javascript:void(0);">全部文档</a></li>
            </ul>
          </li>
        </ul>
      </div>
      <div id="gsLayoutContainer-right">
        <iframe src="${base}/party/partydue/research/usesuggest/browsewait.action" frameborder="0" name="rightFrame" allowtransparency="true"></iframe>
      </div>
    </div>
    <script src="${base}/lib/jquery-ui.min.js"></script>
    <script src="${base}/lib/bootstrap/js/bootstrap.min.js"></script>
    <script src="${base}/lib/moment.min.js"></script>
    <script src="${base}/lib/moment.zh-cn.js"></script>
    <script src="${base}/lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script src="${base}/lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="${base}/lib/lodash.min.js"></script>
    <script src="${base}/lib/bootstrap-slider.js"></script>
    <script src="${base}/lib/bootstrap-switch/js/bootstrap-switch.min.js"></script>
    <script src="${base}/js/main.js"></script>
  </body>
</html>