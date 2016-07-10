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
				<a class="navbar-brand" href="/aluno/home" style="color:#fff"><span>Sharing</span></a>
			</div>
			<div class="collapse navbar-collapse" id="navbar-ex-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/" style="color:#fff">Home</a></li>
					<li><a class="active" href="#" style="color:#fff">Cadastro</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="section text-warning col-md-6 col-md-offset-3"><h4>${mensagem}<h4></div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
					<form role="form" action="/aluno/cadastrar" method="post">
						<div class="form-group">
							<label class="control-label" for="primeiroNome">Nome</label>
							<input class="form-control" id="primeiroNome" name="primeiroNome"
								placeholder="Nome" type="text" value="${aluno.primeiroNome}"/>
						</div>
						<div class="form-group">
							<label class="control-label" for="sobrenome">Sobrenome</label>
							<input class="form-control" id="sobrenome" name="sobrenome"
								placeholder="Sobrenome" type="text" value="${aluno.sobrenome}"/>
						</div>
						<div class="form-group">
							<label class="control-label" for="descricao">Descricao</label>
							<input class="form-control" id="descricao" name="descricao" value="${aluno.descricao}"
								placeholder="Escreva um pouco sobre voce. Suas areas de interesse, disciplinas que gosta, etc."
								type="text"/>
						</div>
						<div class="form-group">
							<label class="control-label" for="horariosDisponiveis">Horarios disponiveis</label>
							<input class="form-control" id="horariosDisponiveis" name="horariosDisponiveis"
								placeholder="Segunda: 10h-12h, Terca:13h-14" value="${aluno.horariosDisponiveis}" type="text"/>
						</div>
						<div class="form-group">
							<label class="control-label" for="login">Login</label>
							<input class="form-control" id="login" name="login" value="${aluno.login}"
								placeholder="exemplomeulogin" type="text"/>
						</div>
						<div class="form-group">
							<label class="control-label" for="email">Email</label>
							<input class="form-control" id="email" name="email" value="${aluno.email}"
								placeholder="meuemail@mail.com" type="text"/>
						</div>
						<div class="form-group">
							<label class="control-label" for="senha">Senha</label>
							<input class="form-control" id="senha" name="senha"
								placeholder="senha" type="password"/>
						</div>
						<div class="form-group">
							<label class="control-label" for="instituicao">Instituicao</label>
							<select	class="form-control" name="instituicao" id="instituicao">												
								<option>Selecione uma instituicao</option>
								<c:forEach items="${instituicoes}" var="i">
									<option value="${i.id}">${i.nome}</option>
								</c:forEach>
							</select>
						</div>
						<button type="submit" class="btn btn-primary">Concluir cadastro</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

</html>