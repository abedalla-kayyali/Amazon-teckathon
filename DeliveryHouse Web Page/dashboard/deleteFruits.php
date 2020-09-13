<?php 
Include "../Include/connection.php";

$id = $_GET["id"];
$sql = mysqli_query($conn,"DELETE FROM `tbl_fruits` 
WHERE `id` = '$id'");

if($sql == true)
{
    ?>
    <script>
    alert("Success");
    location.href="allitems.php";
    </script>
    <?php 
}
?>