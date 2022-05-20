
//로그인 ajax
function actionLoginCheck() {
	if ($('#user_id').val() == '') {
		Swal.fire({
			icon: 'error',
			title: '',
			text: '아이디를 입력하세요.',
		})
		return false;
	}

	if ($('#user_password').val() == '') {
		Swal.fire({
			icon: 'error',
			title: '',
			text: '비밀번호를 입력하세요.',
		})
		return false;
	}
$("#loginForm").submit();

/** 
 var token = $("meta[name='_csrf']").attr("content");
 var header = $("meta[name='_csrf_header']").attr("content");


	$.ajax({
		type: "post",
		url: "/loginCheck",
		data: { "user_id": $('#user_id').val(), "user_password": $('#user_password').val() },
		dataType: "json",
		beforeSend : function(xhr){
		xhr.setRequestHeader(header, token);
		},
		success: function(json) {
			idCheckResult = json.result;

			if (idCheckResult == 'true') {
				$("#loginForm").submit();

			} else {
				Swal.fire({
					icon: 'error',
					title: '',
					text: '비밀번호가 맞지 않습니다.',
				})
				//$("#user_password").val('');
				$("#user_password").focus();
			}
		},
		error: function(error) {
			Swal.fire({
				icon: 'error',
				title: '',
				text: '아이디가 존재하지 않습니다.',
			})
			//$("#user_id").val('');
			//$("#user_password").val('');
			$("#user_id").focus();
		}
	});

*/
}
