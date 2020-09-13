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
body
{
    background-color:#FFFFFF;
}
.formRestaurant
{
    width:400px;
    height:200px;
    color:#000000;
    padding-left:10px;
    padding-right:10px;
    margin-left:330px;
    text:center;
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
      <div class="formRestaurant">
      <form action="" method="POST" enctype="multipart/form-data">
      <h6 style="color:#DC143C">Image :</h6>
      <input type="file" class="form-control-file border" name="file"><br>
      <h6 style="color:#DC143C">Description :</h6>
      <input type="text" class="form-control-file border" placeholder="Thigh" name="NameRestaurant"><br>
      <h6 style="color:#DC143C">Price :</h6>
      <input type="text" class="form-control-file border" placeholder="Price" name="TypeFood"><br>
      <button type="submit" class="btn btn-info" name="test">Save</button>
      <?php 
      if(isset($_POST["test"]))
      {
        $name = $_POST["NameRestaurant"];
        $type = $_POST["TypeFood"];

             $tm = md5(time());
             $file_name = $_FILES['file']['name'];
             $folder = "Images/Restaurants/".$tm.$file_name;

             move_uploaded_file($_FILES["file"]["tmp_name"],$folder);

             if(empty($name))
             {
              ?>
              <script>
              alert("Please Enter Name Meal");
              </script>
              <?php 
             }
             if(empty($type))
             {
              ?>
              <script>
              alert("Please Select Type Meal");
              </script>
              <?php 
             }
             if(empty($file_name))
             {
                 ?>
                 <script>
                 alert("Please Select Image");
                 </script>
                 <?php 
             }
             else
             {
                 Include "../Include/connection.php";
                 $query = "INSERT INTO `tbl_restaurants` (Image_Path,NameRestaurant,TypeFood)
                 VALUES ('$folder','$name','$type')";

                 $result = mysqli_query($conn,$query);

                 if($result == true)
                 {
                  ?>
                  <script>
                  alert("Thank You");
                  </script>
                  <?php 
                 }
             }
      }
      ?>
      </form>
      </div> 
      </div>
      <footer class="footer">
        <div class="container-fluid">
          <nav class="float-left">
            <ul>
              <li>
                <a href="https://www.creative-tim.com">
                  Creative Tim
                </a>
              </li>
            </ul>
          </nav>
          <div class="copyright float-right">
            &copy;
            <script>
              document.write(new Date().getFullYear())
            </script>, made with <i class="material-icons">favorite</i> by
            <a href="https://www.creative-tim.com" target="_blank">Creative Tim</a> for a better web.
          </div>
          <!-- your footer here -->
        </div>
      </footer>
    </div>
  </div>
</body>
</html>