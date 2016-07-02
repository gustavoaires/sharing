<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
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
<script type="text/javascript" src="/js/scroll.js"></script>
</head>
<body>
	<div class="cover">
		<div class="navbar navbar-default navbar-static-top">
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
							style="color: #fff"><i class="fa fa-fw fa-home"></i>Início<br></a>
						</li>
						<li><a href="#what-we-do" class="scroll"><i
								class="fa fa-fw fa-info-circle fa-lg text-inverse"></i></a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="cover-image"
			style="background-image: url('img/index.jpg')"></div>
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center"
					style="background-color: rgba(0, 0, 0, 0.2)">
					<h1 style="color: #fff">Sharing</h1>
					<p contenteditable="true" style="color: #fff">Compartilhe
						conhecimento :)</p>
					<br> <br> <a class="btn btn-lg btn-primary"
						id="btn-entrar-home" data-toggle="modal"
						data-target="#modal-entrar"><i class="fa fa-fw fa-sign-in"></i>Entrar<br></a>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modal-entrar">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">Utilize as credenciais do SIPPA</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<div class="col-sm-2">
								<label for="matricula" class="control-label"
									contenteditable="true">Matrícula</label>
							</div>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="matricula"
									placeholder="Matrícula">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2">
								<label for="senha" class="control-label">Senha</label>
							</div>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="senha"
									placeholder="Senha">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2">
								<label class="control-label">Curso</label>
							</div>
							<div class="col-sm-10">
								<select class="form-control">
									<option>Selecione seu curso</option>
									<option>Engenharia de Software</option>
									<option>Sistemas de Informação</option>
									<option>Redes de Computadores</option>
									<option>Ciência da Computação</option>
									<option>Engenharia da Computação</option>
									<option>Design Digital</option>
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<a class="btn btn-default" data-dismiss="modal">Fechar</a>
					<button type="submit" class="btn btn-primary" id="btn-entrar-modal">Entrar</button>
				</div>
			</div>
		</div>
	</div>
	<div class="cover" id="what-we-do">
		<div class="cover-image"
			style="background-image: url('img/what-we-do.jpg')"></div>
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center"
					style="background-color: rgba(0, 0, 0, 0.3)">
					<h1 style="color: #fff">O que fazemos</h1>
					<p class="text-info" style="color: #fff" contenteditable="true">Queremos
						promover uma cultura de maior interatividade e cooperação entre
						os próprios estudantes sem que seja necessária a intervenção
						ou a parte mais burocrática de um atendimento de monitoria
						convencional. Obviamente, apoiamos o trabalho da monitoria, mas
						vemos que nem sempre é um trabalho bem transparente,
						principalmente para os novos alunos. Então, nossa solução é:
						diga em que disciplinas está disposto a ajudar e peça ajuda em
						que disciplina quiser. Marque um horário de troca de conhecimento
						com uma colega da faculdade e inicie os estudos.</p>
					<br> <br> <a class="btn btn-lg btn-primary"
						data-toggle="modal" data-target="#modal-entrar" id="btn-entrar-i"><i
						class="fa fa-fw fa-sign-out"></i>Entrar<br></a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
