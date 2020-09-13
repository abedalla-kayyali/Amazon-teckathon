<?php 
require_once "DbOperations.php";
$response = array();

if($_SERVER['REQUEST_METHOD'] == 'POST')
{
    if(isset($_POST['CustomerName']) and isset($_POST['CustomerCode']))
    {
        $db = new DbOperations();

        if($db->checkcode($_POST['CustomerName'],$_POST['CustomerCode']))
        {
            $user = $db->getPinByUserName($_POST['CustomerName']);
            $response['error'] = false;
            $response['id'] = $user['id'];
            $response['CustomerName'] = $user['CustomerName'];
            $response['CustomerStatus'] = $user['CustomerStatus'];
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