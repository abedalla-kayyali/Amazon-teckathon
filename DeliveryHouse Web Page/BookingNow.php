<?php 
$link = mysqli_connect("localhost","root","","deliveryhouse");

$Name = $_POST['ItenName'];
$Price = $_POST['Pricing'];
$Quantity = $_POST['Quantity'];
//$pincode = rand(0,9).rand(0,9).rand(0,9).rand(0,9);
$MySession = $_POST["pincode"];

$query = "INSERT INTO `tbl_orders` (ItenName,Pricing,Quantity,pincode) VALUES ('$Name','$Price','$Quantity','$MySession')";

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