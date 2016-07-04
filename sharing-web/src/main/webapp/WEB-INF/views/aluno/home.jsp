<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/scroll.js"></script>
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
			</div>
			<div class="collapse navbar-collapse" id="navbar-ex-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li class="active" id="link-home"><a href="#"
						><i class="fa fa-fw fa-home"></i>Ini­cio<br /></a>
					</li>
					<li><a href="" style="color: #fff"><i
							class="fa fa-fw fa-info-circle fa-lg text-inverse"></i>O que
							fazemos</a></li>
					<li><a href="" style="color: #fff">${aluno_logado.primeiroNome} ${aluno_logado.sobrenome}</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- end nav bar -->
	
	<span class="text-success"><h3 align="center">${mensagem}</h3></span>
	
	<c:forEach var="a" items="${pedidos_atendimento}">
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-offset-3 col-md-6" style="background-color: rgba(0, 0, 0, 0.1); border-radius:5px">
						<p class="text-muted">
							Nome: <span class="text-info">${a.pediuAjuda.primeiroNome} ${a.pediuAjuda.sobrenome}</span>
						</p>
						<p class="text-muted">
							Disciplina: <span class="text-info" id="disciplina">${a.disciplina.nome}</span>
						</p>
						<p class="text-muted">
							Local de encontro: <span class="text-info">${a.localDeEncontro}</span>
						</p>
						<p class="text-muted">
							Data do atendimento: <span class="text-info" id="data"><fmt:formatDate pattern="dd/MM/yyyy" value="${a.dataAtendimento}"/></span>
						</p>
						<p class="text-muted">
							Horario do atendimento: <span class="text-info" id="horario"><fmt:formatDate pattern="HH:mm" value="${a.horaAtendimento}"/></span>
						</p>
						<p align="right">
							<a href="/atendimento/confirmarAtendimento?id=${a.id}" 
							class="btn btn-success">Confirmar</a>
							<a href="/atendimento/negarAtendimento?id=${a.id}"
							class="btn btn-danger">Negar</a>
						</p>							
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<!-- end atendimentos -->
</body>
</html>
