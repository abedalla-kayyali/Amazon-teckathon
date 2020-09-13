<?php 
    
    class DbOperations{

        private $con;

        function __construct(){
            require_once dirname(__FILE__).'/DbConnect.php';
            $db = new DbConnect();
            $this->con = $db->connect();
        }

        function createUser($username,$pass ,$email)
        {
            $password = md5($pass);
            $stmt = $this->con->prepare("INSERT INTO `users` (`id`,`UserName`,`Password_Col`,`EmailUser`)
             VALUES (NULL, ?, ?, ?);");
             $stmt->bind_param("sss",$username,$password,$email);

             if($stmt->execute())
             {
                 return 1;
             }
             else
             {
                 return 2;
             }
        }

        public function userLogin($username,$pass){
            $password = md5($pass);
            $stmt = $this->con->prepare("SELECT id FROM `users` WHERE `UserName` = ? and `Password_Col` = ?");
            $stmt->bind_param("ss",$username,$password);
            $stmt->execute();
            $stmt->store_result();
            return $stmt->num_rows > 0;
        }

        public function adminLogin($username,$pass){
            //$password = md5($pass);
            $stmt = $this->con->prepare("SELECT id FROM `tbl_driverCar` WHERE `UserName` = ? and `Password_Col` = ?");
            $stmt->bind_param("ss",$username,$pass);
            $stmt->execute();
            $stmt->store_result();
            return $stmt->num_rows > 0;
        }

        public function checkcode($user,$pincode){
            //$password = md5($pass);
            $stmt = $this->con->prepare("SELECT id FROM `tbl_secure` WHERE `CustomerName` = ? and `CustomerCode` = ?");
            $stmt->bind_param("ss",$user,$pincode);
            $stmt->execute();
            $stmt->store_result();
            return $stmt->num_rows > 0;
        }

        public function getPinByUserName($user){
            $stmt = $this->con->prepare("SELECT * FROM `tbl_secure` WHERE `CustomerName` = ?");
            $stmt->bind_param("s",$user);
            $stmt->execute();
            return $stmt->get_result()->fetch_assoc();
        }

        public function getAdminByUserName($username){
            $stmt = $this->con->prepare("SELECT * FROM `tbl_driverCar` WHERE `UserName` = ?");
            $stmt->bind_param("s",$username);
            $stmt->execute();
            return $stmt->get_result()->fetch_assoc();
        }

        public function getUserByUserName($username){
            $stmt = $this->con->prepare("SELECT * FROM `users` WHERE `UserName` = ?");
            $stmt->bind_param("s",$username);
            $stmt->execute();
            return $stmt->get_result()->fetch_assoc();
        }

        private function isUserExist($username,$email){
            $stmt = $this->con->prepare("SELECT id FROM `users` WHERE `UserName` = ? OR `EmailUser` = ?");
            $stmt->bind_param("ss", $username, $email);
            $stmt->execute();
            $stmt->store_result();
            return $stmt->num_rows > 0;
        }
    }
?>