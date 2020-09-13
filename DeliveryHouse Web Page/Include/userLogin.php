<?php 
require_once "DbOperations.php";
$response = array();

if($_SERVER['REQUEST_METHOD'] == 'POST')
{
    if(isset($_POST['UserName']) and isset($_POST['Password_Col']))
    {
        $db = new DbOperations();

        if($db->userLogin($_POST['UserName'],$_POST['Password_Col']))
        {
            $user = $db->getUserByUserName($_POST['UserName']);
            $response['error'] = false;
            $response['id'] = $user['id'];
            $response['UserName'] = $user['UserName'];
            $response['EmailUser'] = $user['EmailUser'];
            $response['PointsCustomer'] = $user['PointsCustomer'];
        }
        else
        {
            $response['error'] = true;
            $response['message'] = "Invalid Username Or Password";
        }
    }
    else
    {
        $response['error'] = true;
        $response['message'] = "Required Fields are Missing";
    }
}
echo json_encode($response)
?>