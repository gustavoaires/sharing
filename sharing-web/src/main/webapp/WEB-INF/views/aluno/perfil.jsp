<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
				<a class="navbar-brand" href="/aluno/home" style="color: #fff"><span>Sharing</span></a>
			</div>
			<div class="collapse navbar-collapse" id="navbar-ex-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/aluno/home" style="color: #fff">Home</a></li>
					<li><a href="/aluno/formAlterarPerfil" style="color: #fff">Alterar
							minhas informações</a></li>
					<li class="active"><a href="#"> ${aluno.primeiroNome}
							${aluno.sobrenome}</a></li>
					<li><a href="/login/logout" style="color: #fff">Sair</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- end nav bar -->

	<span class="text-info"><h4 align="center">${mensagem}</h4></span>

	<div class="section">
		<div class="container">
			<div class="col-md-3">
				<p align="center">
					<span class="text-info"> ${aluno.primeiroNome} ${empty atendimentos_confirmados ?
							" não possui atendimentos avaliados" :
							" foi avaliado nestes atendimentos"}
					</span>
				</p>
				<c:forEach var="a" items="${atendimentos_avaliados}">
					<div class="row"
						style="background-color: rgba(0, 0, 0, 0.1); border-radius: 5px">
						<div>
							<p class="text-muted">
								Nome: <span class="text-primary">${a.pediuAjuda.primeiroNome}
									${a.pediuAjuda.sobrenome}</span>
							</p>
							<p class="text-muted">
								Disciplina: <span class="text-primary" id="disciplina">${a.disciplina.nome}</span>
							<p class="text-muted">
							</p>
							<p class="text-muted">
								Local de encontro: <span class="text-primary">${a.localDeEncontro}</span>
							</p>
							<p class="text-muted">
								Data do atendimento: <span class="text-primary" id="data"><fmt:formatDate
										pattern="dd/MM/yyyy" value="${a.dataAtendimento}" /></span>
							</p>
							<p class="text-muted">
								Horario do atendimento: <span class="text-primary" id="horario"><fmt:formatDate
										pattern="HH:mm" value="${a.horaAtendimento}" /></span>
							</p>
							<p class="text-muted">
								Nota: <span class="text-primary" id="horario"><fmt:formatDate
										pattern="HH:mm" value="${a.nota}" /></span>
							</p>
						</div>
					</div>
					<hr>
				</c:forEach>
			</div>
			<!-- end atendimentos avalidados -->
			<div class="col-md-3 col-md-offset-1">
				<p align="center">
					<span class="text-success"> ${aluno.primeiroNome} ${empty atendimentos_confirmados ?
							" não confirmou atendimentos ainda" :
							" aceitou estes atendimentos"}
					</span>
				</p>
				<c:forEach var="a" items="${atendimentos_confirmados}">
					<div class="row"
						style="background-color: rgba(0, 0, 0, 0.1); border-radius: 5px">
						<div>
							<p class="text-muted">
								Nome: <span class="text-primary">${a.pediuAjuda.primeiroNome}
									${a.pediuAjuda.sobrenome}</span>
							</p>
							<p class="text-muted">
								Disciplina: <span class="text-primary" id="disciplina">${a.disciplina.nome}</span>
							<p class="text-muted">
							</p>
							<p class="text-muted">
								Local de encontro: <span class="text-primary">${a.localDeEncontro}</span>
							</p>
							<p class="text-muted">
								Data do atendimento: <span class="text-primary" id="data"><fmt:formatDate
										pattern="dd/MM/yyyy" value="${a.dataAtendimento}" /></span>
							</p>
							<p class="text-muted">
								Horario do atendimento: <span class="text-primary" id="horario"><fmt:formatDate
										pattern="HH:mm" value="${a.horaAtendimento}" /></span>
							</p>
						</div>
					</div>
					<hr>
				</c:forEach>
			</div>
			<!-- end atendimentos confirmados-->
			<div class="col-md-3 col-md-offset-1 ">
				<p align="center">
					<span class="text-danger"> ${aluno.primeiroNome} ${empty atendimentos_confirmados ?
							" não negou atendimentos ainda" :
							" negou estes atendimentos"}
					</span>
				</p>
				<c:forEach var="a" items="${atendimentos_negados}">
					<div class="row"
						style="background-color: rgba(0, 0, 0, 0.1); border-radius: 5px">
						<div>
							<p class="text-muted">
								Nome: <span class="text-primary">${a.pediuAjuda.primeiroNome}
									${a.pediuAjuda.sobrenome}</span>
							</p>
							<p class="text-muted">
								Disciplina: <span class="text-primary" id="disciplina">${a.disciplina.nome}</span>
							<p class="text-muted">
							</p>
							<p class="text-muted">
								Local de encontro: <span class="text-primary">${a.localDeEncontro}</span>
							</p>
							<p class="text-muted">
								Data do atendimento: <span class="text-primary" id="data"><fmt:formatDate
										pattern="dd/MM/yyyy" value="${a.dataAtendimento}" /></span>
							</p>
							<p class="text-muted">
								Horario do atendimento: <span class="text-primary" id="horario"><fmt:formatDate
										pattern="HH:mm" value="${a.horaAtendimento}" /></span>
							</p>
						</div>
					</div>
					<hr>
				</c:forEach>
			</div>
			<!-- end atendimentos negados -->
			<div class="col-md-3 col-sm-offset-1 ">
				<p align="center">
					<span class="text-info">Disciplinas que
						${aluno.primeiroNome} está disponível</span>
				</p>
				<div class="list-group">
					<c:forEach items="${disciplinas}" var="d">
						<a href="/disciplina/verDisciplina?id=${d.id}"
							class="list-group-item">${d.nome}</a>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
</html>