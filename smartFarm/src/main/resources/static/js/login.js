
//로그인 ajax
function actionLoginCheck() {
	if ($('#login_id').val() == '') {
		Swal.fire({
			icon: 'error',
			title: '',
			text: '아이디를 입력하세요.',
		})
		return false;
	}

	if ($('#login_password').val() == '') {
		Swal.fire({
			icon: 'error',
			title: '',
			text: '비밀번호를 입력하세요.',
		})
		return false;
	}
	
	$("#loginForm").submit();
/** 	
	$.ajax({
		type: "post",
		url: "actionLoginCheck",
		data: { "login_id": $('#login_id').val(), "login_password": $('#login_password').val() },
		dataType: "json",
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
				$("#login_password").val('');
				//$("#login_password").focus();
			}
		},
		error: function(error) {
			Swal.fire({
				icon: 'error',
				title: '',
				text: '아이디가 존재하지 않습니다.',
			})
			$("#login_id").val('');
			$("#login_password").val('');
			$("#login_id").focus();
		}
	});
	
	*/
}
