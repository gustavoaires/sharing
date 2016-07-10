<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div class="navbar navbar-default navbar-static-top"
		style="background-color: #428bca">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#navbar-ex-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="navbar-ex-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li ><a href="/aluno/home" style="color: #fff"><i class="fa fa-fw fa-home"></i>Home</a></li>
					<li class="active"><a href="#"><i class="fa fa-fw fa-book"></i>${disciplina.nome}</a></li>
					<li><a href="#" style="color: #fff"><i
							class="fa fa-fw fa-pencil"></i>Minhas disciplinas</a></li>
					<li><a href="#" style="color: #fff"><i
							class="fa fa-fw fa-cogs"></i>Meu Perfil</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1 class="text-center">Escolha alguém e peça ajuda</h1>
					<p class="text-center">Marque um horário e local através do
						Sharing</p>
				</div>
			</div>
			<c:forEach var="a" items="${alunos}">
				<div class="row col-md-offset-3">
					<div class="col-md-2">
						<img src="img/steve.jpeg" class="img-circle img-responsive">
					</div>
					<div class="col-md-4">
						<h3 class="text-left">${a.primeiroNome} ${a.sobrenome}</h3>
						<p class="text-left">${a.horariosDisponiveis}</p>
						<div class="progress">
							<div class="progress-bar" role="progressbar" style="width: ${a.media}%">${a.media}% recomendado</div>
						</div>
						<a class="btn btn-success" href="/aluno/pedirAjuda?idAjudante=${a.login}">Pedir ajuda</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

</body>
</html>