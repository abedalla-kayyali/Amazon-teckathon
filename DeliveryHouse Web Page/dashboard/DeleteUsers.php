<?php 
Include "../Include/connection.php";
$id = $_GET["id"];

$query = mysqli_query($conn,"DELETE FROM `users` WHERE `id` = '$id'");
if($query == true)
{
    ?>
    <script>
    location.href="users.php";
    </script>
    <?php 
}
?>