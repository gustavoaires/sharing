<html><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css" rel="stylesheet" type="text/css">
  </head><body>
    <div class="navbar navbar-default navbar-static-top" style="background-color: #428bca">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div class="collapse navbar-collapse" id="navbar-ex-collapse">
          <ul class="nav navbar-nav navbar-right">
            <li class="active">
              <a href="#"><i class="fa fa-fw fa-user"></i>Steve Rogers</a>
            </li>
            <li>
              <a href="#" style="color:#fff"><i class="fa fa-fw fa-pencil"></i>Minha grade</a>
            </li>
            <li>
              <a href="#" style="color:#fff"><i class="fa fa-fw fa-cogs"></i>Meu Perfil</a>
            </li>
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
            <h1>Horários disponíveis</h1>
            <h3 contenteditable="true">Selecione um horário e envie a solicitação</h3>
            <form class="form-horizontal" role="form">
              <div class="form-group">
                <div class="col-sm-1">
                  <label for="inputEmail3" class="control-label">Local</label>
                </div>
                <div class="col-sm-8">
                  <input type="email" class="form-control" id="inputEmail3" placeholder="Ex: Sala de atendimento">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-offset-1 col-sm-8">
                  <div class="radio">
                    <label>
                      <input type="radio">Segunda, 10h-12h</label>
                  </div>
                  <div class="radio">
                    <label>
                      <input type="radio">Quarta, 13h30-15h30</label>
                  </div>
                  <div class="radio">
                    <label>
                      <input type="radio">Quinta, 10h-12h</label>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-offset-1 col-sm-11">
                  <button type="submit" class="btn btn-success">Enviar solicitação</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  

</body></html>