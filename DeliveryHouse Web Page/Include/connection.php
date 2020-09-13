<?php 
$server = "localhost";
$database = "deliveryhouse";
$user = "root";
$password ="";

$conn = mysqli_connect($server,$user,$password,$database);

if($conn)
{

}
else
{
    echo "Failed";
}
?>