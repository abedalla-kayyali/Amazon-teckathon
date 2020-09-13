<?php
//connection with database
$conn = mysqli_connect("localhost","root","","deliveryhouse");
$MyCart = $_GET["CustomerSession"];
//get all items from table meals
$query = mysqli_query($conn,"SELECT * FROM `tbl_orderpopular` WHERE `CustomerSession` = '$MyCart'");

while($data = mysqli_fetch_array($query))
{
    $Menu[] = $data;
}
print "{ alldata : " . json_encode($Menu,JSON_UNESCAPED_UNICODE) . "}" ;
?>