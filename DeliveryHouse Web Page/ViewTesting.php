<?php
$conn = mysqli_connect("localhost","root","","deliveryhouse");
//connection with database
$query = mysqli_query($conn,"SELECT * FROM `tbl_orders`");
//get all items from table meals

while($data = mysqli_fetch_array($query))
{
    $Menu[] = $data;
}
print "{ getdata : " . json_encode($Menu,JSON_UNESCAPED_UNICODE) . "}" ;
?>