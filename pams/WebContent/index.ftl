
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no,minimum-scale=1,maximum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title></title>
    <link rel="shortcut icon" href="logo32.png">
    <link rel="stylesheet" href="${base}/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${base}/lib/font-awesome/css/font-awesome.min.css">
    <link href="logo60.png" rel="apple-touch-icon">
    <link href="logo76.png" rel="apple-touch-icon" sizes="76x76">
    <link href="logo120.png" rel="apple-touch-icon" sizes="120x120">
    <link href="logo152.png" rel="apple-touch-icon" sizes="152x152">
    <script src="${base}/lib/jquery-2.1.1.min.js"></script>
    <link rel="stylesheet" href="${base}/css/gs.css">
    <base target="mainFrame">
  </head>
  <body>
    <div id="gsLayout">
      <div id="gsSidebar"><a href="${base}/frame/home.action" class="home"><i class="fa fa-home"></i></a>
        <div class="logo"><img src="${base}/css/img/logo-v2.png">管理</div>
        <div class="user"><a href="cog.html" class="gravatar"><img src="pics/guoshuang.png"></a>
          <div class="name">
            <div class="t">guoshuang<a href="${base}/login.html" target="top" class="quit">退出</a></div><a href="_home/msg.html" class="msg">你有3个消息</a>
          </div>
        </div>
        <div id="leftNavi">
          <ul>
            <li>
              <div title="我的工作" class="t"><i class="fa fa-home"></i> <a href="${base}/frame/home.action">我的工作</a></div>
            </li>
            <li>
              <div title="计划管理" class="t"><i class="fa fa-calendar"></i> <a href="${base}/page/frame/group_plan.html">计划管理</a></div>
            </li>
            <li>
              <div title="费用管理" class="t"><i class="fa fa-list-alt"></i> 党费管理</div>
              <ul class="sub">
                <li><a href="${base}/party/partydue/research/usesuggest/mainframe.action">意见征求</a></li>
                <li><a href="${base}/party/partydue/research/baseapprove/mainframe.action">基数核准</a></li>
                <li><a href="">计划编制</a></li>
                <li><a href="">党费收取</a></li>
                <li><a href="">党费使用</a></li>
                <li><a href="">工作总结</a></li>
                <li><a href="">归档共享</a></li>

              </ul>
            </li>
            <li>
              <div title="统计分析" class="t"><i class="fa fa-bar-chart-o"></i> <a href="${base}/page/frame/group_report.html">考核统计</a></div>
            </li>
            <li>
              <div title="文档管理" class="t"><i class="fa fa-file-text"></i> 文档管理</div>
            </li>
            <li>
              <div title="系统管理" class="t"><i class="fa fa-cogs"></i> 系统管理</div>
            </li>
            <li>
              <div title="演示数据" class="t"><i class="fa fa-share"></i> <a href="${base}/page/frame/group_demo.html">演示数据</a></div>
            </li>
          </ul>
        </div>
      </div>
      <div id="gsMainpage">
        <div id="gsheader">
          <div id="gsToggleSidebar"><i class="fa fa-bars"></i></div>
          <div id="gsTopMenu">
            <ul>
              <li class="home"><a href="${base}/frame/home.action" title="首页"><i class="fa fa-home"></i></a></li>
              <li class="msg"><a href="_home/msg.html" title="系统消息"><i class="fa fa-comments"></i>
                  <div class="num">10</div></a></li>
            </ul><span class="user"><img src="pics/guoshuang.png">${Session.sys_login_token.sys_login_deptname}&nbsp;${Session.sys_login_token.sys_login_username}<i class="fa fa-angle-down"></i>
              <ul>
                <li><a href="#1" title="个人设置"><i class="fa fa-cog"></i> 个人设置</a></li>
                <li><a href="#1" title="帮助"><i class="fa fa-question-circle"></i> 帮助</a></li>
                <li class="quit"><a href="${base}/login.html" title="退出" target="top"><i class="fa fa-power-off"></i> 退出</a></li>
              </ul></span>
          </div>
        </div>
        <div id="gsBody">
          <iframe src="${base}/frame/home.action" frameborder="0" name="mainFrame" allowtransparency="true"></iframe>
        </div>
      </div>
    </div>
    <script src="${base}/lib/jquery-ui.min.js"></script>
    <script>

    jQuery(function($){
    //////

    //toggle sidebar
    $('#gsToggleSidebar').on('click',function(e){
    	e.stopPropagation();
    	$('#gsLayout').toggleClass('closed');
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

    //手机 关闭 sidebar
    if($(window).width()<420){
    	$(window).on('click',function(){
    		$('#gsLayout').removeClass('closed');
    	})
    }

    //暂时关闭 #1 链接
    $('a[href^=#]').on('click',function(){
    	console.error('对不起，还没写链接！')
    	return false;
    })


    //////	
    })
    </script>
  </body>
</html>