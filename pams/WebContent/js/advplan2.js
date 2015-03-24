var params = parseUrl(window.location.href);
var planid = params["id"];

//calculate time
var now = new Date(); //性能测试

//all data
var data = []; //传中的 全局变量

jQuery(function($) {
	//////////

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
	})

	ADVPLAN = $('#advplan');

	console.log("pujian data items:" + data.items);


	//root 部门 处理 ------------------
	var rootDiv = ADVPLAN.find('.root');



	//PLANSTARTDATE 中最小值
	var jihuakaishi = moment(_.min(data.items, function(x) {
		if (!$.trim(x.PLANSTARTDATE) == '') {
			return moment(x.PLANSTARTDATE)._d.getTime();
		}
	}).PLANSTARTDATE).format('YYYY-MM-DD');
	
	console.log("pujian jihuakaishi:" + jihuakaishi);

	//proj_phase表中 PLANENDDATE 中最大值
	var jihuajieshu = moment(_.max(data.items, function(x) {
		if (!$.trim(x.PLANENDDATE) == '') {
			return moment(x.PLANENDDATE)._d.getTime();
		}
	}).PLANENDDATE).format('YYYY-MM-DD');

	//工作量：proj_phase表中planworkload
	// var gongzuoliang = 0;
	// $.each(data.items, function(j, k) {
	// 	if(k.PHASEORSTEP=='0'){
	// 		gongzuoliang += k.PLANWORKLOAD * 1;
	// 	}
	// })

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


	var rootHtml = '<div class="tr" data-type="-1"><div class="td name">' + data.projname + '</div><div class="td" data-name="PLANSTARTDATE">' + jihuakaishi + '</div><div class="td" data-name="PLANENDDATE">' + jihuajieshu + '</div><div class="td">' + data.completepercent * 100 + '</div><div class="td gongzuoliang">' + '' + '</div><div class="td">' + 100 + '</div><div class="td" data-name="ACTUALSTARTDATE">' + shijikaishi + '</div><div class="td" data-name="ACUTALENDDATE">' + shijijieshu + '</div><div class="td">' + data.planworkload + '</div><div class="td">' + jizhunkaishi + '</div><div class="td">' + jizhunjieshu + '</div></div>';
	rootDiv.html(rootHtml);
	
	console.log("pujian jihuakaishi1:" + rootHtml);

	//tree 部门 处理 ------------------

	var PHASEORSTEP_cn = ['阶段', '任务', '交付物', '里程碑'];

	//迭代在此！输出 tree tr;顺便 调整 sequence 为正确数值
	_.levelRecursive = function(data, PARENTid, depth, seq) {
		if (typeof depth == 'number') { //depth
			depth++;
		} else {
			depth = 1;
		}
		if (!seq) {
			seq = '1000'; //怪异的 1000，为何不 0001 反正也是 字符排序
		}
		var found = _.filter(data, {
			PARENTid: PARENTid
		})
		if (found.length != 0) {
			found = _.sortBy(found, 'SEQUENCEKEY'); //根据 SEQUENCEKEY 排序！

			thtml += '<ul>';

			$.each(found, function(j, k) {
				
				console.log(j + ":" + k);
				
				var child = _.filter(data, {
					PARENTid: k.id
				});
				var togglestr = '<i class="spacer"></i>';
				if (child.length != 0) {
					togglestr = '<i class="toggle"></i>';
				}
				var iconstr = '';
				if (k.PHASEORSTEP == 0) {
					iconstr = '<i class="otype fa fa-flag" title="' + PHASEORSTEP_cn[k.PHASEORSTEP] + '"></i>';
				} else if (k.PHASEORSTEP == 1) {
					iconstr = '<i class="otype fa fa-flag-o" title="' + PHASEORSTEP_cn[k.PHASEORSTEP] + '"></i>';
				} else if (k.PHASEORSTEP == 2) {
					iconstr = '<i class="otype fa fa-dropbox" title="' + PHASEORSTEP_cn[k.PHASEORSTEP] + '"></i>';
				} else {
					iconstr = '<i class="otype fa fa-flag-checkered" title="' + PHASEORSTEP_cn[k.PHASEORSTEP] + '"></i>';
				}

				//SEQUENCEKEY
				var gsSequence = seq + padZero(j + 1);

				thtml += '<li data-seq="' + gsSequence + '" data-pid="' + k.PARENTid + '" data-id="' + k.id + '" data-depth="' + depth + '">';

				var jihuakaishi = '';
				if ($.trim(k.PLANSTARTDATE) != '') {
					jihuakaishi = moment(k.PLANSTARTDATE).format('YYYY-MM-DD');
				}

				var jihuajieshu = '';
				if ($.trim(k.PLANENDDATE) != '') {
					jihuajieshu = moment(k.PLANENDDATE).format('YYYY-MM-DD');
				}
				//完成比例：proj_phase表中(sum(planworkload*completepercent)/ sum(planworkload))*100  ?????
				var wanchengbili = '';
				if (k.PHASEORSTEP == 0 || k.PHASEORSTEP == 1) { //如果是 阶段和任务 完成比例 才有意义！
					wanchengbili = k.COMPLETEPERCENT * 100;
				}

				//工作量
				var gongzuoliang = '';
				if (k.PHASEORSTEP == 0 || k.PHASEORSTEP == 1) { //如果是 阶段和任务 完成比例 才有意义！
					gongzuoliang = k.PLANWORKLOAD;
				}

				//基准工作量  k.BASEPLANWORKLOAD
				var jizhungongzuoliang = '';
				if (k.PHASEORSTEP == 0 || k.PHASEORSTEP == 1) { //如果是 阶段和任务 完成比例 才有意义！
					jizhungongzuoliang = k.BASEPLANWORKLOAD;
				}

				// console.log(k.PLANWORKLOAD, k.COMPLETEPERCENT, k.PLANWORKLOAD)

				var jizhunkaishi = '';
				if ($.trim(k.BASESTARTDATE) != '') {
					jizhunkaishi = moment(k.BASESTARTDATE).format('YYYY-MM-DD');
				}

				var jizhunjieshu = '';
				if ($.trim(k.BASEENDDATE) != '') {
					jizhunjieshu = moment(k.BASEENDDATE).format('YYYY-MM-DD');
				}

				var shijikaishi = '';
				if ($.trim(k.ACTUALSTARTDATE) != '') {
					shijikaishi = moment(k.ACTUALSTARTDATE).format('YYYY-MM-DD');
				}

				var shijijieshu = '';
				if ($.trim(k.ACUTALENDDATE) != '') {
					shijijieshu = moment(k.ACUTALENDDATE).format('YYYY-MM-DD');
				}

				thtml += '<div class="tr type' + k.PHASEORSTEP + '" data-type="' + k.PHASEORSTEP + '">';
				thtml += '<div class="td name depth' + depth + '">' + togglestr + iconstr + '<span class="t" data-name="cname">' + k.cname + '</span></div>';
				thtml += '<div class="td" data-name="PLANSTARTDATE">' + jihuakaishi + '</div>';
				thtml += '<div class="td" data-name="PLANENDDATE">' + jihuajieshu + '</div>';
				thtml += '<div class="td" data-name="COMPLETEPERCENT">' + wanchengbili + '</div>';
				thtml += '<div class="td gongzuoliang" data-name="PLANWORKLOAD">' + gongzuoliang + '</div>';
				thtml += '<div class="td gongzuoliangbili"></div>';
				// thtml += '<div class="td">' + k.REMARK + '</div>';

				thtml += '<div class="td" data-name="ACTUALSTARTDATE">' + shijikaishi + '</div>';
				thtml += '<div class="td" data-name="ACUTALENDDATE">' + shijijieshu + '</div>';

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
			
			console.log("pujian 4" + thtml);
		}
	};

	var thtml = '';
	_.levelRecursive(data.items, '0');

	//顺便排个序，否则 顺序是乱的；刚才处理的时候，也排过序；其实也可以在后台排序!！！！！
	data.items = _.sortBy(data.items, 'SEQUENCEKEY');
	
	console.log(data.items);
	
	console.log(thtml);
	
	var treeDiv = ADVPLAN.find('.tree');
	treeDiv.html(thtml);
	


	//初始化 name 宽度到 最大的那个！
	function resizeWidth2MaxName() {
		var allNameCol = ADVPLAN.find('.name');

		//reset 2 minimum 否则会继承 刚才的 宽度！！！！
		allNameCol.css({
			width: 0
		});

		var maxNameWidth = _.max(allNameCol, function(a) {
			return $(a)[0].scrollWidth;
		}).scrollWidth + 10 + 'px';
		allNameCol.css({
			width: maxNameWidth
		})
	}
	resizeWidth2MaxName();

	//计算---------

	function calcWordload(){
		var totalWordload = 0;
		$.each(data.items, function(j, k) {
			if(k.PHASEORSTEP=='0'){
				totalWordload += k.PLANWORKLOAD * 1;
			}
		})
		rootDiv.find('.gongzuoliang').text(totalWordload);

		//all .tr 处理一遍  工作量比例
		treeDiv.find('.tr').each(function(j,k){
			var wordload=$(this).find('.gongzuoliang').text()*1;
			var bilitd=$(this).find('.gongzuoliangbili');
			bilitd.text((wordload/totalWordload*100).toFixed(2));
			// console.log(wordload);
		})

	}
	calcWordload();

	// 事件 -------------------------------------

	//toggle
	ADVPLAN.on('click', '.toggle', function(e) {
		e.stopPropagation();
		$(this).closest('li').toggleClass('closed');
	})

	//#gsModal
	var formContainer = $('#formContainer');

	//编辑窗口   －－－－－－start
	ADVPLAN.on('click', '.tr .name', function() {

		//判断类型
		// console.log($(this).attr('data-type'))
		//-1 root 0-3 '阶段', '任务', '交付物', '里程碑'

		//只能在 .name 点击
		var otr = $(this).closest('.tr');

		//modal 加类型 控制表单 内容
		$('#gsModal').attr('data-type', otr.attr('data-type'));


		//标题 类型
		// console.log(otr.attr('data-type'),$.trim($(this).text()))
		var title='';
		if(otr.attr('data-type')=='-1'){
			title='<i class="fa fa-calendar"></i> 项目'
		}else if(otr.attr('data-type')=='0'){
			title='<i class="fa fa-flag"></i> 阶段'
		}else if(otr.attr('data-type')=='1'){
			title='<i class="fa fa-flag-o"></i> 任务'
		}else if(otr.attr('data-type')=='2'){
			title='<i class="fa fa-dropbox"></i> 交付物'
		}else if(otr.attr('data-type')=='3'){
			title='<i class="fa fa-flag-checkered"></i> 里程碑'
		}
		$('#gsModal').find('.modal-title').html(title);



/*	if (k.PHASEORSTEP == 0) {
					iconstr = '<i class="otype fa fa-flag" title="' + PHASEORSTEP_cn[k.PHASEORSTEP] + '"></i>';
				} else if (k.PHASEORSTEP == 1) {
					iconstr = '<i class="otype fa fa-flag-o" title="' + PHASEORSTEP_cn[k.PHASEORSTEP] + '"></i>';
				} else if (k.PHASEORSTEP == 2) {
					iconstr = '<i class="otype fa fa-dropbox" title="' + PHASEORSTEP_cn[k.PHASEORSTEP] + '"></i>';
				} else {
					iconstr = '<i class="otype fa fa-flag-checkered" title="' + PHASEORSTEP_cn[k.PHASEORSTEP] + '"></i>';
				}
*/		

		if (otr.attr('data-type') == '-1') {
			//项目属性
			$('#gsModal').modal('show');
			return false;
		}

		var li = otr.closest('li');
		var sid = li.attr('data-id');
		var found = _.find(data.items, {
			id: sid
		});

		var siblings = li.parent().find('>li');

		var html = '';
		siblings.each(function(j, k) {
			var selected = '';
			if (j == li.index()) {
				selected = 'selected'
			}
			html += '<option ' + selected + ' value="' + (j + 1) + '">' + (j + 1) + '</option>'

		})
		$('#gsIndex').html(html);

		//回填数据到 modal
		formContainer.find(':input').each(function(j, k) {
			if (this.name) {
				$(this).val(found[this.name])
			}
		})

		//add number to num input
		$('.gsRange input[type=range]').trigger('change');

		//dateBox 初始化

		//初次基准
		// FIRSTENDDATE
		var thistr = $('#dateBox').find('tbody tr:eq(0)');
		var start = $.trim(found['FIRSTSTARTDATE']);
		var end = $.trim(found['FIRSTENDDATE']);
		if (start != '') {
			thistr.find('td:eq(0)').text(moment(start).format('YYYY-MM-DD'));
		} else {
			thistr.find('td:eq(0)').text('');
		}
		if (end != '') {
			thistr.find('td:eq(1)').text(moment(end).format('YYYY-MM-DD'));
		} else {
			thistr.find('td:eq(1)').text('');
		}
		thistr.find('td:eq(2)').text(''); //reset 以免留给别人
		if (start && end) {
			//都有,才计算
			var period = parseInt((moment(end)._d.getTime() - moment(start)._d.getTime()) / (1000 * 60 * 60 * 24), 10);
			period += 1;
			thistr.find('td:eq(2)').text(period);
		}
		thistr.find('td:eq(3)').text(found['FIRSTPLANWORKLOAD']);

		//前次基准
		// PREVIOUSSTARTDATE
		var thistr = $('#dateBox').find('tbody tr:eq(1)');
		var start = $.trim(found['PREVIOUSSTARTDATE']);
		var end = $.trim(found['PREVIOUSENDDATE']);
		if (start != '') {
			thistr.find('td:eq(0)').text(moment(start).format('YYYY-MM-DD'));
		} else {
			thistr.find('td:eq(0)').text('');
		}
		if (end != '') {
			thistr.find('td:eq(1)').text(moment(end).format('YYYY-MM-DD'));
		} else {
			thistr.find('td:eq(1)').text('');
		}
		thistr.find('td:eq(2)').text(''); //reset 以免留给别人
		if (start && end) {
			//都有,才计算
			var period = parseInt((moment(end)._d.getTime() - moment(start)._d.getTime()) / (1000 * 60 * 60 * 24), 10);
			period += 1;
			thistr.find('td:eq(2)').text(period);
		}
		thistr.find('td:eq(3)').text(found['PREVIOSPLANWORKLOAD']);


		//基准
		// BASESTARTDATE
		var thistr = $('#dateBox').find('tbody tr:eq(2)');
		var start = $.trim(found['BASESTARTDATE']);
		var end = $.trim(found['BASEENDDATE']);
		if (start != '') {
			thistr.find('td:eq(0)').text(moment(start).format('YYYY-MM-DD'));
		} else {
			thistr.find('td:eq(0)').text('');
		}
		if (end != '') {
			thistr.find('td:eq(1)').text(moment(end).format('YYYY-MM-DD'));
		} else {
			thistr.find('td:eq(1)').text('');
		}
		thistr.find('td:eq(2)').text(''); //reset 以免留给别人
		if (start && end) {
			//都有,才计算
			var period = parseInt((moment(end)._d.getTime() - moment(start)._d.getTime()) / (1000 * 60 * 60 * 24), 10);
			period += 1;
			thistr.find('td:eq(2)').text(period);
		}
		thistr.find('td:eq(3)').text(found['BASEPLANWORKLOAD']);

		//计划
		var planTr = $('#dateBox').find('.plan');
		var planinput0 = planTr.find('input:eq(0)');
		var planinput1 = planTr.find('input:eq(1)');
		var planinput2 = planTr.find('input:eq(2)');
		var planinput3 = planTr.find('input:eq(3)');

		planinput2.val(''); //reset 否则会 留给 后面的家伙

		planinput0.val($.trim(found['PLANSTARTDATE']) == '' ? '' : moment(found['PLANSTARTDATE']).format('YYYY-MM-DD'));
		planinput1.val($.trim(found['PLANENDDATE']) == '' ? '' : moment(found['PLANENDDATE']).format('YYYY-MM-DD'));

		if (planinput0.val() != '' && planinput1.val() != '') {
			var period = moment(planinput1.val())._d.getTime() - moment(planinput0.val())._d.getTime();
			if (period < 0) {
				alert('开始 应该在 结束 之前！');
			} else {
				period = parseInt(period / (1000 * 60 * 60 * 24), 10);
				period += 1;
				planinput2.val(period);
			}
		}
		// planinput3.val(found['PLANWORKLOAD']);

		//PLANINCOMEPERCENT


		//实际
		var actualTr = $('#dateBox').find('.actual');
		var actualinput0 = actualTr.find('input:eq(0)');
		var actualinput1 = actualTr.find('input:eq(1)');
		var actualinput2 = actualTr.find('input:eq(2)');
		var actualinput3 = actualTr.find('input:eq(3)');

		actualinput2.val(''); //reset 否则会 留给 后面的家伙

		actualinput0.val($.trim(found['ACTUALSTARTDATE']) == '' ? '' : moment(found['ACTUALSTARTDATE']).format('YYYY-MM-DD'));
		actualinput1.val($.trim(found['ACUTALENDDATE']) == '' ? '' : moment(found['ACUTALENDDATE']).format('YYYY-MM-DD')); //传说中的 拼写错误在此！ atcual 应该是 actual

		if (actualinput0.val() != '' && actualinput1.val() != '') {
			var period = moment(actualinput1.val())._d.getTime() - moment(actualinput0.val())._d.getTime();

			if (period < 0) {
				alert('开始 应该在 结束 之前！');
			} else {
				period = parseInt(period / (1000 * 60 * 60 * 24), 10);
				period += 1;
				actualinput2.val(period);
			}
		}
		// actualinput3.val(found['ACTUALWORKLOAD']);

		//记住原来的数值
		$('#dateBox').find('input').each(function() {
			$(this).attr('data-origin', $(this).val());
		})

		//实际结束 和 完成比例
		gsRangeInputs.removeAttr('disabled');

		//实际结束 有日期 则  并且被 disable
		if($.trim(actualInput.val())!=''){
			gsRangeInputs.attr({disabled:'disabled'});
		}


		//如果有子任务，则工作量不可编辑
		if(li.find('>ul .tr[data-type=1]').length!=0){
			formContainer.find('[name=PLANWORKLOAD]').attr({readonly:'readonly'});
			formContainer.find('[name=ACTUALWORKLOAD]').attr({readonly:'readonly'});
		}else{
			formContainer.find('[name=PLANWORKLOAD]').removeAttr('readonly');
			formContainer.find('[name=ACTUALWORKLOAD]').removeAttr('readonly');
		}

		$('#gsModal').modal('show');

	});
	//编辑窗口   －－－－－－end	


	/*
	- 实际结束 有日期 则  并且被 disable
	- 实际结束 blur 如果有数值 则 完成比例 100% 
	- 日期被删除 完成比例 enabled
	- 完成比例 100 时  alert('必须有结束日期，才能100%') 然后 99
	*/
	var actualInput=formContainer.find('[name=ACUTALENDDATE]');
	var gsRangeInputs=formContainer.find('.gsRange').find('input');

	actualInput.on('change blur',function(){
		if($.trim($(this).val())!=''){
			/*- 实际结束 有日期 则  并且被 disable
	- 实际结束 blur 如果有数值 则 完成比例 100%	*/
			gsRangeInputs.filter(':eq(0)').val(100).attr({disabled:'disabled'});
			gsRangeInputs.filter(':eq(1)').val(1).attr({disabled:'disabled'});
		}else{//日期被删除 完成比例 enabled
			gsRangeInputs.removeAttr('disabled');
		}
	})

	gsRangeInputs.filter(':eq(0)').on('blur',function(){
		if($(this).val()=='100'){
			alert('必须有结束日期，才能100%');
			$(this).val('99');
			actualInput.trigger('focus');
		}
	})

	gsRangeInputs.filter(':eq(1)').on('blur',function(){
		if($(this).val()=='1'){
			alert('必须有结束日期，才能100%');
			$(this).val('.99');
			actualInput.trigger('focus');
		}
	})

	//时间计算
	$('#dateBox').find('tbody tr').on('change', 'input:lt(2)', function() {
		var othis = $(this);
		var tr = $(this).closest('tr');
		var v1 = $.trim(tr.find('input:eq(0)').val());
		var v2 = $.trim(tr.find('input:eq(1)').val());

		if (v1 && v2) { //如果有开始 结束
			if (moment(v1)._d.getTime() > moment(v2)._d.getTime()) {
				alert('开始 应该在 结束 之前！');
				setTimeout(function() {
					othis.val(othis.attr('data-origin'));
				}, 50)
			} else {
				//保存这次修改
				othis.attr('data-origin', othis.val());
			}

			//重新计算
			setTimeout(function() {
				var period = parseInt((moment(v2)._d.getTime() - moment(v1)._d.getTime()) / (1000 * 60 * 60 * 24), 10);
				period += 1; //多算一天！！！包含结束那天！
				tr.find('input:eq(2)').val(period);
			}, 60)

		}
	})

	$('#dateBox').find('tbody tr').on('change', 'input:eq(2)', function() {
		var v = parseInt($.trim($(this).val()), 10);
		// console.log(isNaN(v))
		if (isNaN(v)) {
			alert('必须是数字！');
			$(this).val($(this).attr('data-origin'));
			return false;
		}
		if (parseInt(v, 10) < 0) {
			alert('要大于0！');
			$(this).val($(this).attr('data-origin'));
			return false;
		}
		var tr = $(this).closest('tr');
		var start = tr.find('input:eq(0)');
		var end = tr.find('input:eq(1)');
		var v1 = $.trim(start.val());
		var v2 = $.trim(end.val());
		if (v1 != '') {
			var day = moment(v1).add(parseInt(v, 10) - 1, 'days'); //多算一天！！！包含结束那天！
			end.val(day.format('YYYY-MM-DD'))
		}
		$(this).attr('data-origin', v);
	})

	//调整顺序
	$('#gsIndex').on('change', function() {
		var sid = formContainer.find(':input[name=id]').val();
		var pid = formContainer.find(':input[name=PARENTid]').val();

		//DOM sort
		var li = $('li[data-id=' + sid + ']');
		var pul = li.closest('ul');
		var toIndex = $(this).val();
		var fromIndex = li.index();

		$('#gsModal').modal('hide');

		if (fromIndex > toIndex - 1) {
			//向上挪动

			// li.removeClass('animateIn animateOut').addClass('animateOut');
			// setTimeout(function(){
			// 	pul.find('>li:eq(' + (toIndex - 1) + ')').before(li);
			// 	li.removeClass('animateIn animateOut').addClass('animateIn');
			// },300)

			pul.find('>li:eq(' + (toIndex - 1) + ')').before(li);
		} else {

			// li.removeClass('animateIn animateOut').addClass('animateOut');
			// setTimeout(function(){
			// 	pul.find('>li:eq(' + (toIndex - 1) + ')').after(li);
			// 	li.removeClass('animateIn animateOut').addClass('animateIn');
			// },300)

			pul.find('>li:eq(' + (toIndex - 1) + ')').after(li);
		}
		//翻滚 以至于 被看见！
		// li[0].scrollIntoView();//这个翻滚 会导致 跑到 最右侧！如果 浏览器 很窄的话（可能跟下面动画有关）

		li.removeClass('animateIn').addClass('animateIn');


		//注意，上面的 setTimeout 动画（出去），会导致下面 异步执行！！呵呵

		/*
		//这段没用！！只是 孩子们纠正了！孙子们 和 外甥 们 都没有处理！！！！！！
		//自己的序号 补全 前缀
		$.each(pul.find('li'), function() {
			//拿到 parent seq 前缀
			var pseq = $(this).closest('ul').closest('li').attr('data-seq');
			if (!pseq) {
				pseq = '1000';
			}
			var sseq = pseq + padZero($(this).index() + 1);
			$(this).attr('data-seq', sseq);
			//data seq 纠正！
			_.find(data.items, {
				id: $(this).attr('data-id')
			}).SEQUENCEKEY = sseq;
		})
		*/

		//重新计算 dom 和 data.items 中的 SEQUENCEKEY
		calculateSeq($('#advplan .tree>ul'));

		//排不排 似乎没用！后面不知何时 才用到这个顺序
		data.items = _.sortBy(data.items, 'SEQUENCEKEY');

		//_.pluck(ADVPLAN.find('li'),function(x){return x.getAttribute('data-seq');})

		// _.pluck(_.sortBy(data.items,'SEQUENCEKEY'),function(x){
		// 	return x.SEQUENCEKEY+':'+x.cname
		// })

		//ajax save sequence

		var seqArr = [];

		if (pid == '0') {
			var parentObj = treeDiv;
		} else {
			var parentObj = ADVPLAN.find('[data-id=' + pid + ']');
		}

		parentObj.find('li').each(function(j, k) {
			seqArr.push({
				sid: $(k).attr('data-id'),
				seq: $(k).attr('data-seq')
			})
		})

		$.ajax({
			url: ajax_url_save_node_sequence,
			// data: {data:seqArr},
			data: JSON.stringify(seqArr),
			success: function(d) {
				//done
				if(d.status!='success'){
					alert('发生错误，顺序并没有被正确保存！');
				}
			}
		})


	})

	//#addNewNode
	$('#addNewNode').on('click', 'li', function() {
		var otype = $(this).attr('data-type');
		var sname = $.trim(window.prompt('给个名字吧', ''));
		if (sname != '') {
			if (!ajax_url_add_node) {
				alert('页面中要指定 ajax_url_add_node ！');
				return false;
			}
			var cid = formContainer.find(':input[name=id]').val();

			if (otype == '0') { //如果点击  项目 root
				// cid=data.projid;
				cid = 0;
			}
			
			console.log("pujian test 1.");

			// console.log(cid);

			// var pid=formContainer.find(':input[name=PARENTid]').val();
			var pujian_x = {
					type: otype,
					sname: sname,
					pid: cid,
				};
			console.log(pujian_x);

			$.ajax({
				url: ajax_url_add_node,
				dataType: 'json',
				method: 'post',
				data: {
					type: otype,
					sname: sname,
					pid: cid,
				},
				success: function(d) {
					console.log("pujian test 1.");
					console.log(d);
					var sid = d.item.id;

					//在 dom 中添加

					if (otype == '0') { //如果点击  项目 root
						var li = $('');
						var childUl = treeDiv.find('>ul');


						var depth = 1;

					} else {
						var li = treeDiv.find('li[data-id=' + cid + ']');
						var childUl = li.find('>ul');
						//seq
						var seq = li.attr('data-seq') + padZero(1);
						//depth
						var depth = li.attr('data-depth') * 1 + 1;
					}


					console.log("pujian test 2.");


					//PHASEORSTEP_cn
					var iconstr = '';
					if (otype == 0) {
						iconstr = '<i class="otype fa fa-flag" title="' + PHASEORSTEP_cn[otype] + '"></i>';
					} else if (otype == 1) {
						iconstr = '<i class="otype fa fa-flag-o" title="' + PHASEORSTEP_cn[otype] + '"></i>';
					} else if (otype == 2) {
						iconstr = '<i class="otype fa fa-dropbox" title="' + PHASEORSTEP_cn[otype] + '"></i>';
					} else {
						iconstr = '<i class="otype fa fa-flag-checkered" title="' + PHASEORSTEP_cn[otype] + '"></i>';
					}
					
					console.log("pujian test 3.");

					//新建的时候  如果父容器是 关闭的 那就打开！否则 没看见，还以为没有呢
					if (li) {
						li.removeClass('closed');
					}

					if (childUl.length == 0) {
						li.append('<ul class="animateIn"><li data-seq="' + seq + '" data-pid="' + cid + '" data-id="' + sid + '" data-depth="' + depth + '"><div class="tr type' + otype + '" data-type="' + otype + '"><div class="td name depth' + depth + '"><i class="spacer"></i>' + iconstr + '<span class="t">' + d.item.cname + '</span></div><div class="td" data-name="PLANSTARTDATE"></div> <div class="td" data-name="PLANENDDATE"></div> <div class="td" data-name="COMPLETEPERCENT"></div> <div class="td gongzuoliang" data-name="PLANWORKLOAD"></div> <div class="td gongzuoliangbili"></div> <div class="td" data-name="ACTUALSTARTDATE"></div> <div class="td" data-name="ACUTALENDDATE"></div> <div class="td"></div> <div class="td"></div> <div class="td"></div> </div> </li></ul>');

						//生 孩子 之后 要被冠以“妈妈”称号 spacer 2 toggle
						li.find('>.tr>.name>i.spacer').replaceWith('<i class="toggle"></i>');

					} else {
						//seq  排在 众兄弟 之后！
						var seq = li.attr('data-seq') + padZero(childUl.find('>li').length + 1);

						if (otype == '0') { //新建 阶段
							var seq = '1000' + padZero(childUl.find('>li').length + 1);
						}

						childUl.append('<li class="animateIn" data-seq="' + seq + '" data-pid="' + cid + '" data-id="' + sid + '" data-depth="' + depth + '"><div class="tr type' + otype + '" data-type="' + otype + '"><div class="td name depth' + depth + '"><i class="spacer"></i>' + iconstr + '<span class="t">' + d.item.cname + '</span></div><div class="td" data-name="PLANSTARTDATE"></div> <div class="td" data-name="PLANENDDATE"></div> <div class="td" data-name="COMPLETEPERCENT"></div> <div class="td gongzuoliang" data-name="PLANWORKLOAD"></div> <div class="td gongzuoliangbili"></div> <div class="td" data-name="ACTUALSTARTDATE"></div> <div class="td" data-name="ACUTALENDDATE"></div> <div class="td"></div> <div class="td"></div> <div class="td"></div> </div> </li>');
					}
					
					console.log("pujian test 4.");

					//翻滚 以至于 被看见！
					if (li[0]) {
						li[0].scrollIntoView();
					}

					//重新计算 .name 宽度
					resizeWidth2MaxName();

					//然后在 data 中添加
					var oindex = _.indexOf(data.items, _.find(data.items, {
						id: cid
					})); //这是 data 中 前面一个 的 index

					if (otype == '0') {
						var oindex = data.items.length;
					}

					//如果 新建儿子，就是这个 oindex;如果已经有 儿子，则需要排排 顺序
					if (childUl.find('>li').length > 1) {
						oindex += childUl.find('>li').length - 1;
					}

					data.items.splice(oindex + 1, 0, {
						"id": sid,
						"PARENTid": cid,
						"cname": d.item.cname,
						"CONTACTID": "",
						"ISMANTORY": "",
						"SEQUENCEKEY": seq,
						"COMPLETEPERCENT": "0",
						"APPROVALSTATUS": "",
						"DESCRIPTION": "",
						"REMARK": "",
						"MODIFYDATE": "",
						"MODIFYPERSON": "",
						"PLANSTARTDATE": "",
						"PLANENDDATE": "",
						"FORECASTDATE": "",
						"FORECASTENDDATE": "",
						"ACTUALSTARTDATE": "",
						"ACUTALENDDATE": "",
						"PROJECTID": "",
						"PHASEORSTEP": otype,
						"DELFLAG": "",
						"DRAFTFLAG": "",
						"FIXWORKFLOW": "",
						"METHODTEMPLATEID": "",
						"FIRSTSTARTDATE": "",
						"FIRSTENDDATE": "",
						"PREVIOUSSTARTDATE": "",
						"PREVIOUSENDDATE": "",
						"BASESTARTDATE": "",
						"BASEENDDATE": "",
						"METHODid": "",
						"PLANWORKLOAD": "",
						"BASEPLANWORKLOAD": "",
						"FIRSTPLANWORKLOAD": "",
						"PREVIOSPLANWORKLOAD": "",
						"ACTUALWORKLOAD": "",
						"PLANINCOMEPERCENT": "",
						"ACTUALINCOMEPERCENT": "",
						"PHASESTATUS": "",
						"PLANWORKLOADRATE": "",
						"ACTUALWORKLOADRATE": "",
						"DELAYREASON": "",
						"STRATEGY": "",
						"REMARK2": "",
						"ACTUAINCOMEDATE": "",
						"ACTUACONTRACTPERCENT": "",
						"PHASEBUDGET": "",
						"PHASEPLANPERCENT": "",
						"PHASECONTRACTFEE": "",
						"ACTUALINCOMEFEE": "",
						"INCOMEREMARK": "",
						"ALLWORKLOAD": ""
					})
					
					console.log("pujian test 5.");
				}
			})
		}
	})

	//删除
	$('#deleteNode').on('click', function() {

		if (!window.confirm('注意：该操作会删除所有子节点！你确定吗？')) {
			return false;
		}
		var sid = formContainer.find(':input[name=id]').val();
		$('#gsModal').modal('hide');

		var li = $('#advplan .tree').find('li[data-id=' + sid + ']');
		var delArr = [];
		delArr.push(sid);
		li.find('li').each(function() {
			delArr.push($(this).attr('data-id'));
		})

		// console.log(delArr.join(','));

		//ajax
		$.ajax({
			url: ajax_url_delete_node,
			data: {
				removeids: delArr.join(',')
			},
			success: function() {
				//先删除  data
				_.remove(data.items, function(x) {
					// console.log( $.inArray(x.id,delArr))
					if ($.inArray(x.id, delArr) != -1) {
						return true;
					}
				})

				//然后删除 dom
				li.animate({
					height: 0
				}, 300, 'swing', function() {
					var pul = li.closest('ul');
					var pli = pul.closest('li');
					$(this).remove();

					//重新计算 dom 和 data.items 中的 SEQUENCEKEY
					calculateSeq($('#advplan .tree>ul'));
					//重新计算 .name 宽度
					resizeWidth2MaxName();

					//如果是 独子 挂了，则需要删除 ul 以及 toggle
					if (pul.find('>li').length == 0) {
						pul.remove();
						pli.find('.toggle').replaceWith('<i class="spacer"></i>');
					}
				});

			}
		})
	})

	//#showLevel 只显示  1 2 和 全部级别
	$('#showLevel').on('click', 'li', function() {
		$(this).addClass('active').siblings().removeClass('active');
		var oindex = $(this).attr('data-level');
		if (oindex == '1') {
			$('#advplan .tree').find('>ul>li').addClass('closed');
		} else if (oindex == '2') {
			$('#advplan .tree').find('>ul>li').removeClass('closed').find('>ul>li').addClass('closed');
		} else if (oindex == '3') {
			$('#advplan .tree').find('>ul>li').removeClass('closed').find('>ul>li').removeClass('closed').find('>ul>li').addClass('closed');
		} else {
			$('#advplan .tree').find('li').removeClass('closed');
		}
	})

	/*.gsRange {
	.num {
		line-height: 30px;height:30px;padding: 0 5px;
		display: inline-block;width:4em;
	}
	input[type=range] {
		margin-top: 8px;
	}*/

	$('.gsRange').on('change', 'input', function() {
		var container = $(this).closest('.gsRange');
		var numInput = container.find('.num');
		var rangeInput = container.find('input[type=range]');
		if ($(this).hasClass('num')) {
			rangeInput.val($(this).val() / 100);
		} else {
			numInput.val(parseInt($(this).val() * 100, 10))
		}
	})

	//
	/*$('#saveAll').on('click', function() {
		console.log(JSON.stringify(data, null, 1))
	})*/

	//测试效率
	console.log((new Date().getTime() - now.getTime()) + 'ms');

	//处理滚轮以后  日期框 留在原地的问题
	$("#gsModal").scroll(function() {
		$('.datepicker').datetimepicker('place');
	});

	//保存按钮
	$('#save').on('click', function() {
		var sid = formContainer.find(':input[name=id]').val();

		//dom
		var li = treeDiv.find('[data-id=' + sid + ']');

		if (li.length == 0) {
			alert('没找到这个 li');
			return false;
		}

		var litr = li.find('>.tr');
		var otype = litr.attr('data-type');

		if (otype == '0' || otype == '1') { //如果 阶段和任务
			litr.find('[data-name]').each(function(j, k) {

				var dataname = $(k).attr('data-name');
				var v = formContainer.find('[name=' + $(k).attr('data-name') + ']').val();
				if (dataname == 'COMPLETEPERCENT') {
					v*=100;
				}
				$(k).text(v);
			})
		} else { //里程碑和交付物 没有 百分比 工作量
			litr.find('[data-name]').each(function(j, k) {

				var dataname = $(k).attr('data-name');
				if (dataname == 'COMPLETEPERCENT' || dataname == 'PLANWORKLOAD') {
					return;
				}
				var v = formContainer.find('[name=' + $(k).attr('data-name') + ']').val();
				$(k).text(v);
			})
		}

		//data
		var found = _.find(data.items, {
			id: sid
		});
		formContainer.find(':input').each(function(j, k) {
			if (k.name) {
				found[k.name] = $(this).val();
			}
		})
		// _.find(data.items, {id: '2740' });//4 test in console

		//diguiTask
		diguiTask(li);

		//如果优化，可以看一下 $('[name=PLANWORKLOAD]') 的 value 和 data-origin="8888" shifou 一致来判断 是否有过修改！！
		calcWordload();

		//修改 root 的 min max －－－－－start

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

		// console.log(rootDiv)
		rootDiv.find('[data-name=PLANSTARTDATE]').text(jihuakaishi);
		rootDiv.find('[data-name=PLANENDDATE]').text(jihuajieshu);
		rootDiv.find('[data-name=ACTUALSTARTDATE]').text(shijikaishi);
		rootDiv.find('[data-name=ACUTALENDDATE]').text(shijijieshu);

		//修改 root 的 min max end

		//ajax
		$.ajax({
			url: ajax_url_save_node,
			type: 'post',
			data: found,
			success: function(d) {
				if (d.status == 'success') {
					$('#gsModal').modal('hide');
					// console.log('saved');
				} else {
					alert('发生错误！');
				}
			}
		})
	})


	//////////
})

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