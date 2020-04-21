<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.1.min.js"></script>
	<meta charset="UTF-8">
	<title>ログインフォーム</title>
</head>
<body>
	<form action="/LoginForm/check-login" method="post" id="frm_login">
		<table>
			<tr>
				<td>ユーザー名</td>
				<td><input type="text" name="username" id="username" size="32"></td>
			</tr>
			<tr>
				<td>パスワード</td>
				<td><input type="password" name="password" id="password"
					size="32"></td>
			</tr>
			<tr>
				<td><a href="javascript:submit()" type="button" name="submit">ログイン</a></td>
			</tr>
		</table>
	</form>
</body>
<script>
	function submit() {
		var username = $("#username").val();
		var password = $("#password").val();
		if (username == '') {
			alert('ユーザー名を入力してください。');
		} else {
			if (password == '') {
				alert('パスワードを入力してください。');
			} else {
				$("#frm_login").submit();
			}
		}
	}
</script>
</html>