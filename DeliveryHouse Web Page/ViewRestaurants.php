<?php 
$conn = mysqli_connect("localhost","root","","deliveryhouse");
// Connection With SERVER ...
$query = "SELECT * FROM tbl_restaurants";
// SELECT All Item From Table ...
$result = mysqli_query($conn,$query);
//Loop get data ...
while($data = mysqli_fetch_array($result))
{
    $Menu[] = $data;
}
  print "{ alldata : " . json_encode($Menu,JSON_UNESCAPED_UNICODE) . "}" ;
?>