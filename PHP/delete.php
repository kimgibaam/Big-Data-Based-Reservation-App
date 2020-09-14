<?php

    error_reporting(E_ALL);
    ini_set('display_errors',1);

    include('dbcon.php');

    $p_time=isset($_POST['p_time']) ? $_POST['p_time'] : '';
    $android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");



        $stmt = $con->prepare('DELETE FROM person WHERE p_time=:p_time;
                                UPDATE fee SET preserved = 0 WHERE time = :p_time');  //준비
        $stmt->bindParam(':p_time', $p_time);
        if($stmt->execute())
        {
          echo "취소 완료";
        }
        else
         {
          echo "취소 실패";

        }


?>
