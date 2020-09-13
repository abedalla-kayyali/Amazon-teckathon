<?php 
$link = mysqli_connect("localhost","root","","deliveryhouse");
$id = $_POST["id"];

$query = "DELETE FROM `tbl_orderpopular` WHERE `id` = $id";

if(mysqli_query($link,$query))
{
    $check['success'] = true;
}
else
{
    $check['success'] = false;
}
echo json_encode($check);
mysqli_close($link);
?>