$(function(){
	getExpData(1,10) ;
	$('#selectCourse').change(function(){
		var courseId = $(this).val();
		$.ajax({
			url:'exp_queryProject.action',
			data:{
				courseId:courseId
			},
			type:'POST',
			dataType:'json',
			success:function(result){
				displayProjects(result) ;
			}
		}) ;
		$.ajax({
			url:'exp_queryDepts.action',
			data:{
				courseId:courseId
			},
			type:'POST',
			dataType:'json',
			success:function(result){
				displayDepts(result);
			}
		}) ;
		return false ;
	}) ;
	$('#projectName').change(function(){
		var courseId = $('#selectCourse').val();
		$.ajax({
			url:'exp_queryDepts.action',
			data:{
				courseId:courseId
			},
			type:'POST',
			dataType:'json',
			success:function(result){
				displayDepts(result);
			}
		}) ;
		return false ;
	}) ;
	function displayProjects(result){
		var projectName = $('#projectName') ;
		projectName.empty() ;
		var temp1 = '<option value="0">==请选择实验项目==</option>' ;
		$.each(result,function(i,project){
			temp1 += '<option value="'+project.id+'">'+project.name+'</option>' ;
		}) ;
		projectName.append(temp1) ;
	}
	
	function displayDepts(result){
		var deptName = $('#deptName') ;
		deptName.empty() ;
		var temp1 = '<option value="0">==请选择实验班级==</option>' ;
		$.each(result,function(i,dept){
			temp1 += '<option value="'+dept.id+'">'+dept.name+'</option>' ;
		}) ;
		deptName.append(temp1) ;
	}
	$('#queryLabBtn').click(function(){
		var weekTime = $('[name=weekTime]').val() ;
		var dayTime = $('[name=dayTime]').val() ;
		var turnTime = $('[name=turnTime]').val() ;
		$.ajax({
			url:'exp_queryAvailLab.action',
			data:{
				weekTime:weekTime ,
				dayTime:dayTime,
				turnTime:turnTime
			},
			type:'POST',
			dataType:'json',
			success:function(result){
				displayAvailLab(result) ;
			}
		}) ;
		return false ;
	}) ;
	
	function displayAvailLab(result){
		var td = $('#availLabTd') ;
		td.empty() ;
//		var availLab = $('#availLab') ;
		var availLab = $('<select id="availLab" class="SelectStyle" name="labId"></select>') ;
		var temp = '<option value="0">==请选择机房==</option>' ;
		$.each(result,function(i,lab){
			temp += '<option value="'+lab.id+'">'+lab.name+'</option>' ;
		}) ;
		availLab.append(temp) ;
		td.append(availLab) ;
	} ;
	
	$('#expForm').submit(function(){ 
		var options = {
				url:'exp_add.action',
				type:'POST',
				success:function(result){
					var lastPage = $('#pageCount').text() ;
					if($('#recordCount').text() % 10 ==0){
						lastPage ++ ;
						console.log(lastPage) ;
					}
					getExpData(lastPage,10) ;
					$('#queryLabBtn').click() ;
				},
				dataType:'json',
		} ;
		$(this).ajaxSubmit(options) ;
		return false ;
	}) ;
	
	function getExpData(num,size){
		$.ajax({
			url:'exp_ajaxList.action',
			data:{pageNum:num,pageSize:size},
			type:'POST',
			dataType:'json',
			success:function(result){
				var tbody = $('#experimentList') ;
				tbody.empty() ;
				$('#currentPage').text(result.currentPage) ;
				$('#pageCount').text(result.pageCount) ;
				$('#pageSize').text(result.pageSize) ;
				$('#recordCount').text(result.recordCount) ;
				var pagenation = $('#pagenation') ;
				pagenation.empty() ;
				pagenation.append('<span title="首页" style="cursor: hand;" data-value="1">'+
						'<img src="style/blue/images/pageSelector/firstPage.png"/></span>') ;
				for(var temp=result.beginPageIndex;temp<=result.endPageIndex;temp ++){
					if(temp == result.currentPage){
						pagenation.append('<span class="PageSelectorNum PageSelectorSelected" data-value="'+temp+'">'+temp+'</span>') ;
					}else{
						pagenation.append('<span class="PageSelectorNum" style="cursor: hand;"  data-value="'+temp+'">'+temp+'</span>') ;
					}
				}
				pagenation.append('<span title="首页" style="cursor: hand;" data-value="'+result.pageCount+'">'+
				'<img src="style/blue/images/pageSelector/lastPage.png"/></span>') ;
				var pageSelect = $('#pageSelect') ;
				pageSelect.empty() ;
				for(var temp=result.beginPageIndex;temp<=result.endPageIndex;temp ++){
					if(temp == result.currentPage){
						pageSelect.append('<option value="'+temp+'" selected>'+temp+'&nbsp;&nbsp;&nbsp;&nbsp;</option>') ;
					}else{
						pageSelect.append('<option value="'+temp+'">'+temp+'&nbsp;&nbsp;&nbsp;&nbsp;</option>') ;
					}
				}
				$.each(result.recordList,function(i,exp){
					var temp = $('<tr align=center valign=middle></tr>') ;
					temp.append('<td>'+exp.projectName+'</td>') ;
					temp.append('<td>'+exp.courseName+'</td>') ;
					temp.append('<td>'+exp.expTime+'</td>') ;
					temp.append('<td>'+exp.teacherName+'</td>') ;
					temp.append('<td>'+exp.labName+'</td>') ;
					temp.append('<td>'+exp.deptName+'</td>') ;
					temp.append('<td>'+exp.description+'</td>') ;
					temp.append('<td><input type="button" data-id="'+exp.id+'" value="删除"></td>') ;
					tbody.append(temp) ;
				}) ;
			}
		}) ;
	}
	
	$('#pagenation').on('click','span',function(){
		getExpData($(this).attr('data-value'),10) ;
	}) ;
	
	$('#pageSelect').on('change',function(){
		getExpData($(this).val(),10) ;
	}) ;
	
	$('#experimentList').on('click','[type=button]',function(){
		if(!confirm('确定要删除吗?')){
			return false ;
		}
		var id = $(this).attr('data-id') ;
		$.ajax({
				url:"exp_ajaxDelete.action",
				data:{id:id},
				type: "POST",
				dataType:'json',
				success:function(result){
					getExpData() ;
				},
		}) ;
	}) ;
	
	$('#commitExp').on('click',function(){
		var courseId = $('#selectCourse').val() ;
		if(courseId==0)
		{
			alert('请选择课程！');
			return false;
		}
		
		var projectId = $('#projectName').val() ;
		if(projectId==0)
		{
			alert('请选择项目！');
			return false;
		}
		var labId = $('#availLab').val() ;
		if(labId==0)
		{
			alert('请选择实验室!');
			return false;
		}
		
		var departmentId=$('#deptName').val();
		if(departmentId==0)
		{
			alert('请选择班级！');
			return false;
		}
			
		return check(this.form) ;
	}) ;
}) ;