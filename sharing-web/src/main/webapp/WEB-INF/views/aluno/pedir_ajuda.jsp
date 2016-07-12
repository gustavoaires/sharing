<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js">
	
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="datepicker.js"></script>
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
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
				<a class="navbar-brand" href="/aluno/home" style="color: #fff"><span>Sharing</span></a>
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
					<img src="../images/${aluno.login}.png" class="img-responsive">
				</div>
				<div class="col-md-6">
					<h3>${aluno.primeiroNome}${aluno.sobrenome}</h3>
					<h3 contenteditable="true">Selecione uma data e horário e
						envie a solicitação</h3>
					<form role="form" action="/atendimento/cadastrarSolicitacao" method="post">
						<div class="form-group">
							<label class="control-label" for="exampleInputEmail1">Local
							</label> <input class="form-control" id="local" name="local"
								placeholder="local" type="text" required>
						</div>
						<div class="form-group">
							<label class="control-label">Data do encontro</label> <input
								name="dia" class="form-control" required="required"
								pattern="[0-9]{2}\/[0-9]{2}\/[0-9]{4}$" type="date"
								placeholder="dd/mm/aaaa" required>
						</div>
						<div class="form-group">
							<label class="control-label">Horario do encontro</label> <input
								name="hora" class="form-control" required="required"
								pattern="[0-9]{2}\:[0-9]{2}$" type="date"
								placeholder="hh:mm" required>
						</div>
						<input type="hidden" value="${aluno.login}" name="idAjudante"/>
						<input type="hidden" value="${disciplina.id}" name="idDisciplina">
						<button type="submit" class="btn btn-success">Pedir ajuda</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>