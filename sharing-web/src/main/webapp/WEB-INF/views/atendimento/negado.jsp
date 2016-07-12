<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
					<li><a href="/disciplina/listar" style="color: #fff">Disciplinas</a></li>
					<li><a href="/aluno/listarMinhasDisciplinas"
						style="color: #fff"> Minhas disciplinas</a></li>
					<li class="active"><a href="" data-toggle="modal"
						data-target="#modal-atendimentos"> Meus atendimentos</a></li>
					<li><a href="/aluno/formAlterarPerfil" style="color: #fff">Alterar
							minhas informações</a></li>
					<li><a href="/aluno/home" style="color: #fff">${aluno_logado.primeiroNome}
							${aluno_logado.sobrenome}</a></li>
					<li><a href="/login/logout" style="color: #fff">Sair</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- end nav bar -->

	<span class="text-info"><h4 align="center">${mensagem} ${status}</h4></span>

	<c:forEach var="a" items="${pedidos_atendimento}">
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-offset-3 col-md-6"
						style="background-color: rgba(0, 0, 0, 0.1); border-radius: 5px">
						<p class="text-muted">
							Nome: <span class="text-info">${a.pediuAjuda.primeiroNome}
								${a.pediuAjuda.sobrenome}</span>
						</p>
						<p class="text-muted">
							Disciplina: <span class="text-info" id="disciplina">${a.disciplina.nome}</span>
						</p>
						<p class="text-muted">
							Local de encontro: <span class="text-info">${a.localDeEncontro}</span>
						</p>
						<p class="text-muted">
							Data do atendimento: <span class="text-info" id="data"><fmt:formatDate
									pattern="dd/MM/yyyy" value="${a.dataAtendimento}" /></span>
						</p>
						<p class="text-muted">
							Horario do atendimento: <span class="text-info" id="horario"><fmt:formatDate
									pattern="HH:mm" value="${a.horaAtendimento}" /></span>
						</p>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<!-- end atendimentos -->
	<div class="modal fade" id="modal-atendimentos">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">x</button>
					<h4 class="modal-title">Quais atendimentos deseja ver?</h4>
					<hr>
					<div class="row">
						<div class="col-md-offset-1 btn-group" role="group">
							<button id="btnGroupDrop1" type="button"
								class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">Pedidos que eu fiz</button>
							<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
								<a class="dropdown-item" 
									href="/atendimento/verMeusPedidosAtendimentosFeitos?status=aberto">Abertos</a> <br/>
								<a class="dropdown-item" 
									href="/atendimento/verMeusPedidosAtendimentosFeitos?status=negado">Negados</a> <br/>
								<a class="dropdown-item" 
									href="/atendimento/verMeusPedidosAtendimentosFeitos?status=confirmado">Confirmados</a> <br/>
								<a class="dropdown-item" 
									href="/atendimento/verMeusPedidosAtendimentosFeitos?status=avaliado">Avaliados</a> <br/>
							</div>
						</div>
						<div class="col-md-offset-4 btn-group" role="group">
							<button id="btnGroupDrop1" type="button"
								class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">Pedidos que eu recebi</button>
							<div class="dropdown-menu" aria-labelledby="btnGroupDrop2">
								<a class="dropdown-item" 
									href="/atendimento/verMeusPedidosAtendimentosRecebidos?status=aberto">Abertos</a> <br/>
								<a class="dropdown-item" 
									href="/atendimento/verMeusPedidosAtendimentosRecebidos?status=negado">Negados</a> <br/>
								<a class="dropdown-item" 
									href="/atendimento/verMeusPedidosAtendimentosRecebidos?status=confirmado">Confirmados</a> <br/>
								<a class="dropdown-item" 
									href="/atendimento/verMeusPedidosAtendimentosFeitos?status=avaliado">Avaliados</a> <br/>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- end modal atendimentos -->
</body>
</html>
