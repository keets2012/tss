$(function(){
	$('#clickCourse').change(function(){
		var courseId = $(this).val();
		$.ajax({
			url:'report_queryTask.action',
			data:{
				courseId:courseId
			},
			type:'POST',
			dataType:'json',
			success:function(result){
				displayTasks(result) ;
			}
		}) ;
		return false ;
	}) ;
	
	function displayTasks(result){
		var taskName = $('#taskName') ;
		taskName.empty() ;
		var temp1 = '<option value="0">==请选择项目==</option>' ;
		$.each(result,function(i,task){
			temp1 += '<option value="'+task.id+'">'+task.name+'</option>' ;
		}) ;
		taskName.append(temp1) ;
	}
	
	$('#taskName').change(function(){
		var taskId = $(this).val();
		$.ajax({
			url:'report_queryDepts.action',
			data:{
				taskId:taskId
			},
			type:'POST',
			dataType:'json',
			success:function(result){
				displayDepts(result) ;
			}
		}) ;
		return false ;
	}) ;
	
	function displayDepts(result){
		var deptName = $('#deptName') ;
		deptName.empty() ;
		var temp = '<option value="0">==请选择班级==</option>' ;
		$.each(result,function(i,dept){
			temp += '<option value="'+dept.id+'">'+dept.name+'</option>' ;
		}) ;
		deptName.append(temp) ;
	}
	
	$('#countSubmit').on('click',function(){
		var courseId = $('#clickCourse').val() ;
		if(courseId==0)
		{
			alert('请选择课程！');
			return false;
		}
		
		var taskId = $('#taskName').val() ;
		if(taskId==0)
		{
			alert('请选择任务！');
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