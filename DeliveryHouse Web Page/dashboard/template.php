<?php 
session_start();
?>
<!doctype html>
<html lang="en">
<head>
  <title>Control Panel</title>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <!--     Fonts and icons     -->
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
  <!-- Material Kit CSS -->
  <link href="assets/css/material-dashboard.css?v=2.1.1" rel="stylesheet" />
</head>
<style>
body{
  background-image: url('../Background/3.jpeg');
  background-repeat: no-repeat;
  background-size: cover;
  background-attachment: fixed;
  background-size: 100% 100%;
}
.mystyle{
   color:black;
   background-color:#F5CBA7;
   width:500px;
   height:Auto;
   margin-left:115px;
   margin-right:100px;
   margin-top:50px;
   text-align:left;
}
</style>
<body>
<?php 
if(!isset($_SESSION["Control"]))
{
  ?>
  <script>
  location.href="../logIn.php";
  </script>
  <?php 
}
?>
  <div class="wrapper">
    <div class="sidebar" data-color="purple" data-background-color="white">
      <div class="logo">
      <span style="margin-left:100px;color:red"><?php echo $_SESSION["Control"]; ?></span>
      </div>
    <?php Include "nav.php"; ?>
    </div>
    <div class="main-panel">
      <div class="content" style="background-image:url('Background/2.jpg');">
        <div class="container-fluid">
        <div class="mystyle">
      <blockquote >
        <p><strong>As part of its strategic plans to expand and diversify its operations, a leading smart application car booking has announced the launch of smart locker. Safe, reliable and unmatched prices, in addition to the latest in its package of services aimed at facilitating the details of daily life, which is no longer limited to transporting passengers to and from their destinations, but also includes the transfer of their needs and requirements from one place to another.
        We are new and smart delivery service, Aims to solve the problems people face in the whole shopping process by taking advantage of the idea of smart shopping and developing new ways to make the shopping process smarter, easier and more comfortable.
        We provides e-shopping and smart delivery service, which is the first of its kind in Jordan. It is based on the establishment of a smart application and webpage and building different partnerships with different businesses in Jordan. To provide first: a special shopping service for the customer, Second: the unique delivery service through the provision of our smart home boxes for
        subscribers.
        </strong></p>
      </blockquote>
      </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
