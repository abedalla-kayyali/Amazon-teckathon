<?php
//connection with database
$conn = mysqli_connect("localhost","root","","deliveryhouse");
//get all items from table meals
$query = mysqli_query($conn,"SELECT * FROM `tbl_orders` ORDER BY `pincode`");

while($data = mysqli_fetch_array($query))
{
    $Menu[] = $data;
}
print "{ getAllorders : " . json_encode($Menu,JSON_UNESCAPED_UNICODE) . "}" ;
?>