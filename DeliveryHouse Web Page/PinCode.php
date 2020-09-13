<?php 
$link = mysqli_connect("localhost","root","","deliveryhouse");

$CustomerName = $_POST['CustomerName'];
$CustomerCode = rand(0,9).rand(0,9).rand(0,9).rand(0,9);
$CustomerStatus = $_POST['CustomerStatus'];

$query = "INSERT INTO `tbl_Secure` (CustomerName,CustomerCode,CustomerStatus) VALUES ('$CustomerName','$CustomerCode','Available')";

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