$(document).ready(function(){
	
	$('#message').hide();
	$("#resultOption").hide();
	$("#menuOption").hide();
	$("#loginOption").hide();
	$("#startExamOption").hide();
	
	
	//Display UI in initialy
	$.ajax({
        url: "/api/user/active",
        type: 'GET',
        dataType: 'json',
        success: function (data) {
        	console.log();
        	if(data.isActive == "Y"){
        		$("#menuOption").show();
        		$("#menuOption").html('<h3>ID: '+data.id+', Name: '+data.name+'</h3>'
        				+'<button onclick = "logoutUser()" id="logoutBtn" type="button" class="btn btn-primary">LOGOUT</button>');
        		
        		//loadInitialData(1);
        		$("#startExamOption").show();
        	}
        	if(data.isActive == "N"){
        		$("#loginOption").show();
        		
        	}
        },
        error: function (request, message, error) {
        }
    });
	
	
	//login
	$('#loginButton').click(function() {
		
		var id = $('#loginId').val();
		if (id == "") {
			$('#message').html('Insert ID');
			$('#message').show();
			$('#message').hide(3000);
			return;
		}
		var pass = $('#loginPwd').val();
		if (pass == "") {
			$('#message').html('Insert Password');
			$('#message').show();
			$('#message').hide(3000); 
			return;
		}
		
		$.post("api/user/login", {
			id : id, password : pass
		}, function(data) {
			console.log(data.login);
			if(data.login == "Y"){
				location.reload();
			}
			if(data.login == "N"){
				$('#message').html('ID or Password dose not match');
				$('#message').show();
				$('#message').hide(3000); 
			}
			
		}).fail(function() {
			console.log("error");
		});
	});
 
});



function startExam(){
	$("#startExamOption").hide();
	
	generateTimer();
	generateTotalTimer();
	
	loadInitialData(1);
	
}



//load question and answer
function loadInitialData(id){
	
	 $.ajax({
        url: "/api/question/"+id,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            $("#quizQues").html('<div class="jumbotron"><h3>'+data.questionNo+'.'+data.question+'</h3>'
					+'<div class="radio"><label><input type="radio" name="optradio" value="'+data.options.option1+'">'+data.options.option1+'</label></div>'
					+'<div class="radio"><label><input type="radio" name="optradio" value="'+data.options.option2+'">'+data.options.option2+'</label></div>'
					+'<div class="radio"><label><input type="radio" name="optradio" value="'+data.options.option3+'">'+data.options.option3+'</label></div>'
					+'<div class="radio"><label><input type="radio" name="optradio" value="'+data.options.option4+'">'+data.options.option4+'</label></div>'
					+'<button onclick = "submitQuestion('
					+ data.questionNo
					+ ')" id="submitAnsBtn" type="button" class="btn btn-primary">Submit</button>'
					+'<button onclick = "skipQuestion('
					+ data.questionNo
					+ ')" id="skipQuestionBtn" type="button" class="btn btn-primary">Skip Question</button></div>');

        },
        error: function (request, message, error) {
        }
    });
	 
}

//submit question
function submitQuestion(id){
	var ans = $('input[name="optradio"]:checked').val();
	
	if(id >= 1 && id < 4){
		
		$.post("api/answer", {
			questionNo : id, answer : ans
		}, function(returnedData) {
			console.log(returnedData);
			
		}).fail(function() {
			console.log("error");
		});
		
		loadInitialData(id+1);
		generateTimer();
	}
	if(id == 4){
		
		$.post("api/answer", {
			questionNo : id, answer : ans
		}, function(returnedData) {
			console.log(returnedData);
			
		}).fail(function() {
			console.log("error");
		});
		
		loadInitialData(id+1);
		generateLastTimer();
	}
	if(id == 5){
		$.post("api/answer", {
			questionNo : id, answer : ans
		}, function(returnedData) {
			
		}).fail(function() {
		});
		
		$('#comedown').hide();
		$("#submitAnsBtn").hide();
		$('#totalComedown').hide();
		$("#quizQues").html('<div class="jumbotron"><button onclick = "generateResult()" id="generateResultBtn" type="button" class="btn btn-primary">Result</button></div>');
		
	}
	
}


//result
function generateResult(){
	$("#quizQues").hide();
	$("#resultOption").show();
	$('#comedown').hide();
	$('#totalComedown').hide();
	
	$.ajax({
        url: "/api/result",
        type: 'GET',
        dataType: 'json',
        success: function (data) {
        	var mark = data.totalMark;
        	var passMark = 20;
        	var result = "Fail";
        	if(mark >= passMark){
        		result = "Pass"
        	}
            $("#resultOption").html('<p>Total Mark: '+mark+'</p><p>Passing Mark: '+passMark+'</p><p>Result: '+result+'</p>'
            		+'<button onclick = "refrashUser()" id="reExamBtn" type="button" class="btn btn-primary">Re Exam</button>');

        },
        error: function (request, message, error) {
        }
    });
}


//skip in next question
function skipQuestion(id){
	if(id == 4){
		loadInitialData(id + 1);
		$("#submitAnsBtn").show();
		$('#comedown').show();
		generateLastTimer();
	}
	if(id >= 1 && id <= 3){
		loadInitialData(id + 1);
		$("#submitAnsBtn").show();
		$('#comedown').show();
		generateTimer();
	}
	
}


//Re exam
function refrashUser(){
	
	$.ajax({
        url: "/api/result/refresh",
        type: 'GET',
        dataType: 'json',
        success: function (data) {
        	
        },
        error: function (request, message, error) {
        }
    });
	location.reload();
	
}

//logout
function logoutUser(){
	$.ajax({
        url: "/api/user/logout",
        type: 'GET',
        dataType: 'json',
        success: function (data) {
        	

        },
        error: function (request, message, error) {
        }
    });
	location.reload();
}


//timer without last question
function generateTimer(){
	var timer2 = "2:01";
	var interval = setInterval(function() {
		
	  var timer = timer2.split(':');
	  var minutes = parseInt(timer[0], 10);
	  var seconds = parseInt(timer[1], 10);
	  --seconds;
	  minutes = (seconds < 0) ? --minutes : minutes;
	  if (minutes < 0) clearInterval(interval);
	  seconds = (seconds < 0) ? 59 : seconds;
	  seconds = (seconds < 10) ? '0' + seconds : seconds;
	  $('#comedown').html('<div class="well well-sm">This question has remain: <strong>'+minutes + ':' + seconds+'</strong></div>');
	  timer2 = minutes + ':' + seconds;
	  
	  if(timer2 == "0:00"){
		  console.log("Time out");
		  $('#comedown').hide();
		  $("#submitAnsBtn").hide();
		  $("#skipQuestionBtn").show();
	  }
	  if(timer2 == "2:00"){
		  $("#skipQuestionBtn").hide();
	  }
	}, 1000);
}


//timer with last question
function generateLastTimer(){
	var timer2 = "2:01";
	var interval = setInterval(function() {
		
	  var timer = timer2.split(':');
	  var minutes = parseInt(timer[0], 10);
	  var seconds = parseInt(timer[1], 10);
	  --seconds;
	  minutes = (seconds < 0) ? --minutes : minutes;
	  if (minutes < 0) clearInterval(interval);
	  seconds = (seconds < 0) ? 59 : seconds;
	  seconds = (seconds < 10) ? '0' + seconds : seconds;
	  $('#comedown').html('<div class="well well-sm">This question has remain: <strong>'+minutes + ':' + seconds+'</strong></div>');
	  timer2 = minutes + ':' + seconds;
	  
	  if(timer2 == "0:00"){
		  console.log("Time out");
		  $('#comedown').hide();
		  $("#submitAnsBtn").hide();
		  $("#quizQues").html('<div class="jumbotron"><button onclick = "generateResult()" id="generateResultBtn" type="button" class="btn btn-primary">Result</button></div>');
	  }
	  if(timer2 == "2:00"){
		  $("#skipQuestionBtn").hide();
	  }
	}, 1000);
}


function generateTotalTimer(){
	var timer2 = "10:01";
	var interval = setInterval(function() {
		
	  var timer = timer2.split(':');
	  var minutes = parseInt(timer[0], 10);
	  var seconds = parseInt(timer[1], 10);
	  --seconds;
	  minutes = (seconds < 0) ? --minutes : minutes;
	  if (minutes < 0) clearInterval(interval);
	  seconds = (seconds < 0) ? 59 : seconds;
	  seconds = (seconds < 10) ? '0' + seconds : seconds;
	  $('#totalComedown').html('<div class="well well-sm">Exam time remain: <strong>'+minutes + ':' + seconds+'</strong></div>');
	  timer2 = minutes + ':' + seconds;
	  
	  if(timer2 == "0:00"){
		  console.log("Time out");
		  $('#comedown').hide();
		  $('#totalComedown').hide();
		  $("#quizQues").html('<div class="jumbotron"><button onclick = "generateResult()" id="generateResultBtn" type="button" class="btn btn-primary">Result</button></div>');
	  }
	}, 1000);
}







