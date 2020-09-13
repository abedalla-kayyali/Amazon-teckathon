<?php 
$link = mysqli_connect("localhost","root","","deliveryhouse");

$Name = $_POST['txt_username'];
$email = $_POST['txt_email'];
$comment = $_POST['txt_comment'];

$query = "INSERT INTO `tbl_Contact` (txt_username,txt_email,txt_comment)
VALUES ('$Name','$email','$comment')";

if(mysqli_query($link,$query))
{
    $check['Message'] = true;
}
else
{
    $check['Message'] = false;
}

echo json_encode($check);
mysqli_close($link);
?>