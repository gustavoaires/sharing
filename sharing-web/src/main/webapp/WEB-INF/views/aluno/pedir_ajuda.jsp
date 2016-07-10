<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<li class="active"><a href="#"><i class="fa fa-fw fa-user"></i>${aluno.primeiroNome}</a></li>
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
				<div class="col-md-3">
					<img src="img/steve.jpeg" class="img-responsive">
				</div>
				<div class="col-md-9">
					<h3>${aluno.primeiroNome}${aluno.sobrenome}</h3>
					<h3 contenteditable="true">Selecione uma data e horário e
						envie a solicitação</h3>
					<div class="form-group">
						<div class="col-sm-1">
							<label for="inputEmail3" class="control-label">Local</label>
						</div>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="local"
								name="localAtendimento" placeholder="Ex: Sala de atendimento" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-1 col-sm-11">
							<button type="submit" class="btn btn-success"
								href="/atendimento/cadastrarSolicitacao?idAjudante=${aluno.login}">Enviar
								solicitação</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>