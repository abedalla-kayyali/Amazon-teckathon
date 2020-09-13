<?php 

  Include "DbOperations.php";
  $response = array();
  if($_SERVER['REQUEST_METHOD'] == 'POST')
  {
      if(isset($_POST['UserName']) and isset($_POST['Password_Col']) and isset($_POST['EmailUser']))
      {
          $db = new DbOperations();
          if($db->createUser($_POST['UserName'],$_POST['Password_Col'],$_POST['EmailUser']))
          {
              $response['error'] = false;
              $response['message'] = "User Registered SuccessFully";
          }
          else
          {
            $response['error'] = true;
            $response['message'] = "Some Error Occurred Please Try Again";
          }
      }
      else
      {
          $response['error'] = true;
          $response['message'] = "Required Fields are missing";
      }
  }
  else
  {
      $response['error'] = true;
      $response['message'] = "Invalid Request";
  }

  echo json_encode($response);
?>