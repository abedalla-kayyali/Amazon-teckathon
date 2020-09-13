<?php 
  session_start();
?>
<html>
<head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<title>SignIn</title>
</head>
<style>
body
{
    background-image: url('Background/1.jpg');
    background-repeat: no-repeat;
    background-attachment: fixed;
    background-size: cover;
}
.mystyle{
   width:200px;
   height:300px;
   margin-left:500px;
   margin-right:50000px;
   margin-top:320px;
   text-align:center;
}
</style>
<body>
<div>
<div class="container">
	<div class="mystyle">
    <form class="form-signin" action="" method="POST">
		<h1 class="form-signin-heading text-muted" style="color:black">Sign In</h1>
		<input type="text" class="form-control" placeholder="Email address" name="username"><br>
		<input type="password" class="form-control" placeholder="Password" name="password"><br>
		<button class="btn btn-lg btn-primary" type="submit" name="login">
			Sign In
        <?php 
		if(isset($_POST["login"]))
		{
		$conn = mysqli_connect("localhost","root","","deliveryhouse");
		$uname = mysqli_real_escape_string($conn,$_POST['username']);
		$password = mysqli_real_escape_string($conn,$_POST['password']);
							
		if ($uname != "" && $password != ""){
							
		$sql_query = "SELECT count(*) as cntUser from `tbl_control` WHERE `UserName` ='".$uname."' and `Password_Col` ='".$password."' and `Status_Col` = 'admin'";
		$result = mysqli_query($conn,$sql_query);
		$row = mysqli_fetch_array($result);
							
		$count = $row['cntUser'];
							
		$res = mysqli_query($conn,$sql_query);
		if($count > 0){
		$_SESSION['Control'] = $uname;
		header('Location: dashboard/template.php');
		}
		else
		{
		?>
		<script>
		alert("Failed");
		</script>
		<?php 
		}
        }
		}
		?>
		</button>
	</form>
    </div>
</div>
</body>
</html>