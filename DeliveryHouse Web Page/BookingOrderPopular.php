<?php 
$link = mysqli_connect("localhost","root","","deliveryhouse");

$ItemOne = $_POST['ItemOne'];
$ItemTwo = $_POST['ItemTwo'];
$ItemThree = $_POST['ItemThree'];
$ItemFour = $_POST["ItemFour"];
$ItemFive = $_POST["ItemFive"];
$ItemSix = $_POST["ItemSix"];
$ItemSeven = $_POST["ItemSeven"];
$ItemEight = $_POST["ItemEight"];
$Customers = $_POST["CustomerSession"];

$query = "INSERT INTO `tbl_OrderPopular` (ItemOne,ItemTwo,ItemThree,ItemFour,ItemFive,ItemSix,ItemSeven,ItemEight,CustomerSession) VALUES ('$ItemOne','$ItemTwo','$ItemThree','$ItemFour','$ItemFive','$ItemSix','$ItemSeven','$ItemEight','$Customers')";

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