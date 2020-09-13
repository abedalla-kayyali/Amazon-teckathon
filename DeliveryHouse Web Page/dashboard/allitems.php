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
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<style>
body
{
    background-color:#FFFFFF;
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
  <div class="wrapper ">
    <div class="sidebar" data-color="purple" data-background-color="white">
      <div class="logo">
      <span style="margin-left:100px;color:red"><?php echo $_SESSION["Control"]; ?></span>
      </div>
    <?php Include "nav.php"; ?>
    </div>
    <div class="main-panel">
      <div class="content">
      <ul class="nav nav-tabs">
      <li class="active"><a data-toggle="tab" href="#home">Meats</a></li>
      <li><a data-toggle="tab" href="#meals">Meals</a></li>
      <li><a data-toggle="tab" href="#Fruits">Fruit and Vegetable</a></li>
      <li><a data-toggle="tab" href="#Marketing">Groceries</a></li>
      </ul>
        <div class="container-fluid">
        <div class="tab-content">
        <div id="home" class="tab-pane fade in active">
        <h3>Meats</h3>
        <form method="post">
        <table class="table table-hover">
         <thead>
         <tr>
         <th><h4>Image</h4></th>
         <th><h4>Description</h4></th>
         <th><h4>Price</h4></th>
         <th><h4>Action</h4></th>
         </tr>
         </thead>
         <tbody>
         <?php 
         Include "../Include/connection.php";
         $query = "SELECT * FROM `tbl_restaurants`";
         $result = mysqli_query($conn,$query);

         if(mysqli_num_rows($result) > 0)
         {
             while($row = mysqli_fetch_array($result))
             {
                 ?>
                  <tr>
                  <td><img src="<?php echo $row["Image_Path"]; ?>" width="170px" height="100px"></td>
                  <td align="left"><?php echo $row["NameRestaurant"]; ?></td>
                  <td><?php echo $row["TypeFood"]; ?> JD</td>
                  <td><a href="deleterestaurant.php?id=<?php echo $row["id"]; ?>" class="btn btn-danger">DELETE</a></td>
                  </tr>
                 <?php
             }
         }
         ?>
         </tbody>
         </table>
         </form>
        </div>
        <div id="meals" class="tab-pane">
        <h3>Meats</h3>
        <form method="post">
        <table class="table table-hover ">
         <thead>
         <tr>
         <th><h4>Image</h4></th>
         <th><h4>Item Name</h4></th>
         <th><h4>Meal Name</h4></th>
         <th><h4>Meal Price</h4></th>
         <th><h4>Action</h4></th>
         </tr>
         </thead>
         <tbody>
         <?php 
         Include "../Include/connection.php";
         $query = "SELECT * FROM `tbl_meals`";
         $result = mysqli_query($conn,$query);

         if(mysqli_num_rows($result) > 0)
         {
             while($row = mysqli_fetch_array($result))
             {
                 ?>
                  <tr>
                  <td><img src="<?php echo $row["Image_Path"]; ?>" width="170px" height="100px"></td>
                  <td><?php echo $row["NameRestaurant"]; ?></td>
                  <td><?php echo $row["NameMeal"]; ?></td>
                  <td><?php echo $row["PriceMeal"]; ?> JD</td>
                  <td><a href="deletemeals.php?id=<?php echo $row["id"]; ?>" class="btn btn-danger">DELETE</a></td>
                  </tr>
                 <?php
             }
         }
         ?>
         </tbody>
         </table>
         </form>
        </div>
        <div id="Fruits" class="tab-pane">
        <h3>Fruit and Vegetable</h3>
        <form method="post">
        <table class="table table-hover ">
         <thead>
         <tr>
         <th><h4>Image</h4></th>
         <th><h4>Elites</h4></th>
         <th><h4>Item Name</h4></th>
         <th><h4>Item Price</h4></th>
         <th><h4>Action</h4></th>
         </tr>
         </thead>
         <tbody>
         <?php 
         Include "../Include/connection.php";
         $query = "SELECT * FROM `tbl_fruits`";
         $result = mysqli_query($conn,$query);

         if(mysqli_num_rows($result) > 0)
         {
             while($row = mysqli_fetch_array($result))
             {
                 ?>
                  <tr>
                  <td><img src="<?php echo $row["Image_Path"]; ?>" width="170px" height="100px"></td>
                  <td><?php echo $row["Elites"]; ?></td>
                  <td><?php echo $row["ItemName"]; ?></td>
                  <td><?php echo $row["ItemPrice"]; ?> JD-Kg</td>
                  <td><a href="deleteFruits.php?id=<?php echo $row["id"]; ?>" class="btn btn-danger">DELETE</a></td>
                  </tr>
                 <?php
             }
         }
         ?>
         </tbody>
         </table>
         </form>
        </div>
        <div id="Marketing" class="tab-pane">
        <h3>Groceries</h3>
        <form method="post">
        <table class="table table-hover ">
         <thead>
         <tr>
         <th><h4>Image</h4></th>
         <th><h4>Product Name</h4></th>
         <th><h4>Product Price</h4></th>
         <th><h4>Action</h4></th>
         </tr>
         </thead>
         <tbody>
         <?php 
         Include "../Include/connection.php";
         $query = "SELECT * FROM `tbl_marketing`";
         $result = mysqli_query($conn,$query);

         if(mysqli_num_rows($result) > 0)
         {
             while($row = mysqli_fetch_array($result))
             {
                 ?>
                  <tr>
                  <td><img src="<?php echo $row["Image_Path"]; ?>" width="170px" height="100px"></td>
                  <td><?php echo $row["ProductName"]; ?></td>
                  <td><?php echo $row["ProductPrice"]; ?> JD</td>
                  <td><a href="deleteMeats.php?id=<?php echo $row["id"]; ?>" class="btn btn-danger">DELETE</a></td>
                  </tr>
                 <?php
             }
         }
         ?>
         </tbody>
         </table>
         </form>
        </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>