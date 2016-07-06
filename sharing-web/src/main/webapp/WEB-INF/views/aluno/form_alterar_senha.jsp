<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head xmlns:th="http://www.thymeleaf.org">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css"/>
<link
	href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
	rel="stylesheet" type="text/css"/>
</head>

<body>
	<div class="navbar navbar-default navbar-static-top" style="background-color: #428bca">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar-ex-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/" style="color:#fff"><span>Sharing</span></a>
			</div>
			<div class="collapse navbar-collapse" id="navbar-ex-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/aluno/home" style="color:#fff">Home</a></li>
					<li class="active"><a href="#">Alterar senha</a></li>
					<li><a href="/aluno/pageAlterarFoto" style="color:#fff">Alterar foto</a></li>
					<li><a href="/aluno/formAlterarPerfil" style="color:#fff">Alterando perfil</a></li>
					<li><a href="/login/logout" style="color: #fff">Sair</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="section text-info" align="center"><h4>${mensagem}<h4></div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
					<form role="form" action="/aluno/alterarSenha" method="post">
						<div class="form-group">
							<label class="control-label" for="senhaAtual">Digite a senha atual</label>
							<input class="form-control" id="senhaAtual" name="senhaAtual"
								placeholder="senha atual" type="password" value=""/>
						</div>
						<div class="form-group">
							<label class="control-label" for="novaSenha">Digite a nova senha</label>
							<input class="form-control" id="novaSenha" name="novaSenha"
								placeholder="nova senha" type="password" value=""/>
						</div>
						<button type="submit" class="btn btn-primary">Alterar</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

</html>