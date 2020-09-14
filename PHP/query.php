<?php
error_reporting(E_ALL);
ini_set('display_errors',1);

include('dbcon.php');

//POST 값을 읽어온다.
$phone=isset($_POST['phone']) ? $_POST['phone'] : '';
$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");


if ($phone != "" ){

    $sql="select * from person where phone='$phone'";
    $stmt = $con->prepare($sql);
    $stmt->execute();

    if ($stmt->rowCount() == 0){
      echo "";
          // 조회실패 , 데이터베이스에 없는 값
    }
	else{

   		$data = array();

        while($row=$stmt->fetch(PDO::FETCH_ASSOC)){

        	extract($row);

            array_push($data,
                array('name'=>$name,
                'phone'=>$phone,
                'p_time'=>$p_time
            ));
        }


        if (!$android) {
            echo "<pre>";
            print_r($data);
            echo '</pre>';
        }else
        {
            header('Content-Type: application/json; charset=utf8');
            $json = json_encode(array("webnautes"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
            echo $json;
        }
    }
}
else {
  echo "";
  // 휴대번호를 입력하지 않았음
}

?>


<?php

$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

if (!$android){
?>

<html>
   <body>

      <form action="<?php $_PHP_SELF ?>" method="POST">
         휴대 번호: <input type = "text" name = "phone" />
         <input type = "submit" />
      </form>

   </body>
</html>
<?php
}


?>
