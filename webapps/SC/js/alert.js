function access_alert(flag){
	switch(flag){
		case "login":
			alert("먼저 로그인을 하셔야 합니다.");
			window.open("/login", "_blank", "width=600,height=700");
			break;
		case "wrong":
			alert("잘못된 접근입니다.");
			location.href="/home";
			break;
	}
}

function register_alert(){
	if(frm.userpwd.value != frm.userpwdr.value){
		alert("비밀번호와 비밀번호확인이 일치하지 않습니다.");
		frm.userpwdr.focus();
		return false;
	}
	return true;
}

function login_alert(flag){
	switch(flag){
		case "id":
			alert("아이디를 잘못 입력하셨습니다.");
			location.href="/login";
			break;
		case "pwd":
			alert("비밀번호가 일치하지 않습니다.");
			location.href="/login";
			break;
	}
}

function board_alert(flag){
	switch(flag){
		case "announce":
			if(frm.post_title.value.length <= 0){
				alert("제목을 입력하셔야 합니다.");
				frm.post_title.focus();
				return false;
			}
			else if(frm.post_content.value.length <= 0){
				alert("내용을 입력하셔야 합니다.");
				frm.post_content.focus();
				return false;
			}
			break;
		case "ask":
			if(frm.post_title.value.length <= 0){
				alert("제목을 입력하셔야 합니다.");
				frm.post_title.focus();
				return false;
			}
			else if(frm.post_content.value.length <= 0){
				alert("내용을 입력하셔야 합니다.");
				frm.post_content.focus();
				return false;
			}
			break;
		case "reserve":
			let today = new Date();
			if(frm.post_title.value.length <= 0){
				alert("제목을 입력하셔야 합니다.");
				frm.post_title.focus();
				return false;
			}
			else if(frm.post_content.value.length <= 0){
				alert("내용을 입력하셔야 합니다.");
				frm.post_content.focus();
				return false;
			}
			else if(frm.reserve_date.value == 0){
				alert("예약 방문 날짜를 선택하셔야 합니다.");
				frm.reserve_date.focus();
				return false;
			}
			else if(new Date(frm.reserve_date.value) < today){
				alert("예약날짜가 현재날짜보다 앞에 있어야 합니다.");
				frm.reserve_date.focus();
				return false;
			}
			else if(frm.user_loc.value <= 0){
				alert("예약 방문주소를 입력하셔야 합니다.");
				frm.user_loc.focus();
				return false;
			}
			break;
		case "answer":
			if(frm.answer.value.length <= 0){
				alert("답변 내용을 입력하셔야 합니다.");
				frm.answer.focus();
				return false;
			}
			break;
	}
	return true;
}

function success(flag){
	switch(flag){
		case "login":
			alert("로그인 되었습니다.");
			if(opener == null){
				window.location.href="/home";
			}else{
				window.close();
				opener.location.href="/home";
			}
			break;
		case "logout":
			alert("로그아웃 되었습니다.");
			window.location.href="/home";
			break;
		case "withdraw":
			alert("계정이 삭제되었습니다.");
			window.location.href="/home";
			break;
		case "register":
			alert("회원가입이 완료되었습니다.");
			window.location.href="/home";
			break;
		case "useredit":
			alert("회원정보 변경이 완료되었습니다.");
			window.location.href="/mypage";
			break;
		case "announce_insert":
			alert("공지사항 작성이 완료되었습니다.");
			window.location.href="/Board.do?board_mode=announce";
			break;
		case "ask_insert":
			alert("문의 작성이 완료되었습니다.");
			window.location.href="/Board.do?board_mode=ask";
			break;
		case "reserve_insert":
			alert("예약이 완료되었습니다.");
			window.location.href="/Board.do?board_mode=reserve";
			break;
		case "announce_update":
			alert("공지사항이 수정되었습니다.");
			window.location.href="/Board.do?board_mode=announce";
			break;
		case "ask_update":
			alert("문의가 수정되었습니다.");
			window.location.href="/Board.do?board_mode=ask";
			break;
		case "reserve_update":
			alert("예약이 수정되었습니다.");
			window.location.href="/Board.do?board_mode=reserve";
			break;
		case "answer":
			alert("문의답변이 완료되었습니다.");
			window.location.href="/Board.do?board_mode=ask";
			break;
		case "announce_delete":
			alert("공지삭제가 완료되었습니다.");
			window.location.href="/Board.do?board_mode=announce";
			break;
		case "ask_delete":
			alert("문의삭제가 완료되었습니다.");
			window.location.href="/Board.do?board_mode=ask";
			break;
		case "reserve_delete":
			alert("예약삭제가 완료되었습니다.");
			window.location.href="/Board.do?board_mode=reserve";
			break;
		case "visited":
			alert("예약 방문처리기 완료되었습니다.");
			window.location.href="/Board.do?board_mode=reserve";
			break;
	}
	
}

function failed(flag){
	switch(flag){
		case "withdraw":
			alert("계정삭제에 실패했습니다.");
			window.location.href="/home";
			break;
		case "register":
			alert("회원가입에 실패했습니다.");
			window.location.href="/home";
			break;
		case "useredit":
			alert("회원정보 변경을 실패하였습니다.");
			window.location.href="/mypage";
			break;
		case "announce_insert":
			alert("공지사항 작성을 실패하였습니다.");
			window.location.href="/Board.do?board_mode=announce";
			break;
		case "ask_insert":
			alert("문의 작성을 실패하였습니다.");
			window.location.href="/Board.do?board_mode=ask";
			break;
		case "reserve_insert":
			alert("예약을 실패하였습니다.");
			window.location.href="/Board.do?board_mode=reserve";
			break;
		case "answer":
			alert("문의답변을 실패하였습니다.");
			window.location.href="/Board.do?board_mode=ask";
			break;
		case "announce_delete":
			alert("공지삭제를 실패하였습니다.");
			window.location.href="/Board.do?board_mode=announce";
			break;
		case "ask_delete":
			alert("문의삭제를 실패하였습니다.");
			window.location.href="/Board.do?board_mode=ask";
			break;
		case "reserve_delete":
			alert("예약삭제를 실패하였습니다.");
			window.location.href="/Board.do?board_mode=reserve";
			break;
		case "visited":
			alert("예약 방문처리를 실패하였습니다.");
			history.back();
			break;
	}
}

function ask(flag){
	switch(flag){
		case "withdraw":
			if(confirm("정말 회원 탈퇴하시겠습니까?")){
				window.location.href="/Account.do?user_mode=withdraw";
			}
			break;
		case "visited":
			if(confirm("예약을 방문처리 하시겠습니까?")){
				return true;
			}else{
				return false;
			}
			break;
		case "announce_delete":
			if(confirm("정말 공지를 삭제하시겠습니까?")){
				return true;
			}else{
				return false;
			}
		case "ask_delete":
			if(confirm("정말 문의를 삭제하시겠습니까?")){
				return true;
			}else{
				return false;
			}
		case "reserve_delete":
			if(confirm("정말 예약을 삭제하시겠습니까?")){
				return true;
			}else{
				return false;
			}
	}
}