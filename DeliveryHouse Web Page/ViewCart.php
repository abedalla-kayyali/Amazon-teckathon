<?php
//connection with database
$conn = mysqli_connect("localhost","root","","deliveryhouse");
$MyCart = $_GET["pincode"];
//get all items from table meals
$query = mysqli_query($conn,"SELECT * FROM `tbl_orders` WHERE `pincode` = '$MyCart'");

while($data = mysqli_fetch_array($query))
{
    $Menu[] = $data;
}
print "{ alldata : " . json_encode($Menu,JSON_UNESCAPED_UNICODE) . "}" ;
?>