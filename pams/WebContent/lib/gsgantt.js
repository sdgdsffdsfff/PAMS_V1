

function initGsGantt(ele, data) {
	var successCls = '';
	if (data.actual * 1 >= data.plan * 1) {
		successCls = ' success';
	}

	//1-31 计算出来是 30天而不是 31
	var total = moment(data.end).diff(moment(data.start), 'days');

	var html = '<div class="gsGantt">';


	//阶段 开始 结尾
	/*
	html += '<ul class="timeline">';
	$.each(data.phases, function(j, k) {
		var start = moment(k.start);
		var end = moment(k.end);
		var totalStart = moment(data.start);
		html += '<li class="startLi" style="left:' + start.diff(totalStart, 'days') * 100 / total + '%;height:' + ((j + 2) * 45 - 14) + 'px;"><span class="date">' + start.format('M-D') + '</span></li>';
		html += '<li class="endLi" style="margin-left:-1px;left:' + end.diff(totalStart, 'days') * 100 / total + '%;height:' + ((j + 2) * 45  - 14) + 'px;"><span class="date">' + end.format('M-D') + '</span></li>';
	})
	html += '</ul>';
	*/

	//阶段 3点
	html += '<ul class="timeline">';
	html +='<li><span class="date">'+data.start+'</span></li>';
	html +='<li style="left:100%;"><span class="date">'+data.end+'</span></li>';
	html += '</ul>';


	html += '<div class="total' + successCls + '"><div class="bar"><div style="width:' + data.actual * 100 + '%;" class="indi"></div></div><div class="t">' + data.t + '</div><div class="plan" style="left:' + data.plan * 100 + '%;"></div></div>';

	html += '<ul class="phases">';

	$.each(data.phases, function(j, k) {
		var successCls = '';
		if (k.actual * 1 >= k.plan * 1) {
			successCls = ' success';
		}
		html += '<li title="从 ' + k.start + ' 到 ' + k.end + ' 计划：' + k.plan + ' 实际：' + k.actual + '" class="' + successCls + '" style="width:' + moment(k.end).diff(moment(k.start), 'days') * 100 / total + '%;left:' + moment(k.start).diff(moment(data.start), 'days') * 100 / total + '%;"><div class="bar"><div style="width:' + k.actual * 100 + '%;" class="indi"></div></div><div class="t">' + k.t + '</div><span class="start">'+k.start+'</span><span class="end">'+k.end+'</span><div class="plan" style="left:' + k.plan * 100 + '%;"></div></li>';
	})
	html += '</ul>';

	html += '</div>';
	ele.html(html);
}

function renderTasks(start,end,projid){
	$.ajax({
		url:'../data/proj_home_tasks.json',
		data:{
			start:start,
			end:end,
			projid:projid
		},
		dataType:'json',
		success:function(d){
			
			var table=$('#tasks').find('table');

			if(d.body.length==0){
				table.html('<p class="text-danger">本周没有任务！</p>');
				return false;
			}

			var html='<table><thead><tr>';
			$.each(d.head,function(j,k){
				html+='<th>'+k+'</th>';
			})
			html+='</tr></thead><tbody>';
			$.each(d.body,function(a,b){
				html+='<tr>';
				$.each(b.tds,function(j,k){
					html+='<td>'+k+'</td>';
				})
				html+='</tr>';
			})
			html+='</tbody></table>';

			table.html(html)
			
		}
	})
}

jQuery(function($) {

	$.ajax({
		url:'../data/proj_home_step.json',
		data:{
			projid:projID
		},
		dataType:'json',
		success:function(d){
			initGsGantt($('#gantt'), d);
		}
	})
	
	var days=$('#tasks').find('.days');
	var weekdateContainer=$('#tasks').find('.weekdate');

	var firstdayofweek=moment().add(-1 * parseInt(days.val()),'days');
	var lastdayofweek=moment();
	
	weekdateContainer.text(firstdayofweek.format('MM-DD')+' 到 '+lastdayofweek.format('MM-DD'))

	renderTasks(firstdayofweek.format('YYYY-MM-DD'),lastdayofweek.format('YYYY-MM-DD'),projID);

	$('#tasks').on('click','.prev,.next',function(){
		var step=parseInt(days.val());
		if($(this).hasClass('prev')){
			firstdayofweek.add(-1*step,'days');
			lastdayofweek.add(-1*step,'days');
		}else {
			firstdayofweek.add(step,'days');
			lastdayofweek.add(step,'days');
		}
		renderTasks(firstdayofweek.format('YYYY-MM-DD'),lastdayofweek.format('YYYY-MM-DD'),projID);
		weekdateContainer.text(firstdayofweek.format('MM-DD')+' 到 '+lastdayofweek.format('MM-DD'));
	})

	days.on('change',function(){
		firstdayofweek=moment(lastdayofweek).subtract(parseInt($(this).val()),'days');
		renderTasks(firstdayofweek.format('YYYY-MM-DD'),lastdayofweek.format('YYYY-MM-DD'),projID);
		weekdateContainer.text(firstdayofweek.format('MM-DD')+' 到 '+lastdayofweek.format('MM-DD'));
	})

	

	// $('#hideEnd').on('click', function() {
	// 	$('li.end').hide();
	// })

	// $('#arrowBottom').on('click', function() {
	// 	$('.plan').css({
	// 		transform: 'rotate(180deg)',
	// 		'margin-top': '-10px'
	// 	})
	// })

	// $('#radius').on('click', function() {
	// 	$('.bar,.indi').css({
	// 		'border-radius': '4px'
	// 	})
	// })

	////////pie

	

/*	var pie1 = Morris.Donut({
		element: $('.pie'),
		data: [{
			value: 80,
			label: '80%'
		}, {
			value: 20,
			label: '20%'
		}],
		// backgroundColor: '#ccc',
		// labelColor: ['#060','red'],
		colors: [$color_success, $color_danger],
		formatter: function(v, inst) {
			console.log(v, inst)
			return '';
		}
	})*/


	/////////
	/*var myoption = {
		series: {
			pie: {
				show: true, //显示 pie
				radius: 1, //pie占容器大小
				innerRadius: 0.75,
				stroke: {
					width: 0.1,
					color: '#ffffff'
				},
				label: {
					show: false, //显示 label
					radius: 1, //label 文字的 位置
					threshold: 0.1, //小于10％，不显示
					formatter: function(label, series) {
						// console.log(series)
						return '<div data-sid="' + series.sid + '" title="' + series.data[0][1] + '" class="gslabel clickcls">' + label + '<div class="percent">' + series.percent.toFixed(1) + '%</div></div>';
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
		// grid: {
		// 	hoverable: clickable, //hover
		// 	clickable: clickable //clickable
		// },
		colors: portalColor //颜色。自定义颜色可在 数据中直接 "color":"red"
	};

	var data=[
		{"label":"11","data":26,color:portalColor[0]},
		{"label":"22","data":100-26,color:'#ccc'}
	];

	var plot = $.plot($('.flot'), data, myoption);*/

//处理 4 flot
$('.flot').each(function(){
	//---
	var percentDiv=$(this).find('.percent');
	var ulpk=$(this).find('ul.pk');

	if(percentDiv.length>0){//percent flot
		var v=parseInt(percentDiv.text(),10);
		var color=$color_danger;
		if(v>66){
			color=$color_success;
		}else if(v>33){
			color=$color_primary;
		}
		var data=[
			{"label":"","data":v,color:color},
			{"label":"","data":100 - v,color:'#ccc'}
		];
		var option = {
			series: {
				pie: {
					show: true, //显示 pie
					radius: 1, //pie占容器大小
					innerRadius: 0.75,
					stroke: {width: 1, color: '#fff'},
					label: {show: false},
				}
			},
			legend: {show: false }
		};
		var container=$('<div class="pie"></div>');
		container.appendTo($(this));
		
		var plot = $.plot(container, data,option);
		percentDiv.css({color:color});
	}else if(ulpk.length>0) {//pk pie

		// var colors=[$color_danger,$color_primary];
		var colors=['#ccc',$color_primary];
		var data=[];

		ulpk.find('li').each(function(j,k){
			var v=parseInt($(k).attr('data-num'),10);
			data.push({
				label:$(k).text(),
				data:v,
				color:colors[j]
			})

			//
			$(k).append('<span class="num">'+v+'</span>');
		})

		//处理父母为0
		if(data[0].data==0&&data[1].data==0){
			data[0].data=1;
			data[1].data=0;
		}

		$(this).append('<div class="percent">'+parseInt(data[1].data/(data[0].data+data[1].data)*100,10)+'</div>')

		var option = {
			series: {
				pie: {
					show: true, //显示 pie
					radius: 1, //pie占容器大小
					innerRadius: 0.75,
					stroke: {width: 0.1, color: '#ccc'},
					label: {
						show: false, //显示 label
					},
				}
			},
			legend: {show: false }
		};
		var container=$('<div class="pie"></div>');
		container.prependTo($(this));
		
		var plot = $.plot(container, data,option);
	}
	//---
})



})