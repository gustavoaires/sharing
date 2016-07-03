<html>
	<head>
		<title>Hello Facebook</title>
	</head>
	<script type="text/javascript">
		function connect() {
			$post.("connect/facebook", {'scope' : 'publish_actions'} function() {
				//do nothing
			});
		}
	</script>
	<body>
		<h3>Connect to Facebook</h3>

		<!--<form action="/connect/facebook" method="POST"> 
			<input type="hidden" name="scope" value="publish_actions" />-->
			<div class="formInfo">
				<p>You aren't connected to Facebook yet. Click the button to connect this application with your Facebook account.</p>
			</div>
			<p><a href="#" onClick="connect"> <!--type="submit"-->Connect to Facebook</a></p>
		<!--</form> -->
	</body>
</html>