<?php

    error_reporting(E_ALL);
    ini_set('display_errors',1);

    include('dbcon.php');


    $android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");


    if( (($_SERVER['REQUEST_METHOD'] == 'POST') && isset($_POST['submit'])) || $android )
    {

        // 안드로이드 코드의 postParameters 변수에 적어준 이름을 가지고 값을 전달 받습니다.
        $name=$_POST['name'];
        $phone=$_POST['phone'];
        $p_time=$_POST['p_time'];

        if(empty($name)){
            $errMSG = "이름을 입력하세요.";
        }
        else if(empty($phone)){
            $errMSG = "휴대번호를 입력하세요.";
        }
        else if(empty($p_time) && $p_time != 0){
    
            $errMSG = "예약시간을 입력하세요.";
        }

        if(!isset($errMSG)) // 이름과 휴대번호 등 모두 입력이 되었다면
        {
          $sql="select * from person where p_time='$p_time'";  // 같은시간에 예약 못하게
          $stmt = $con->prepare($sql);  //준비
          $stmt->execute();   //sql 실행


          if($stmt->rowCount() != 0) //
          {
            echo "이미 예약자가 있습니다.";
          }

          else
            {
              try{
                // SQL문을 실행하여 데이터를 MySQL 서버의 person 테이블에 저장합니다.
                // fee 테이블에 있는 예약 가능 여부도 갱신합니다.
                $stmt = $con->prepare('INSERT INTO person(name, phone,p_time) VALUES(:name, :phone, :p_time);
                                        UPDATE fee SET preserved = 1 WHERE time = :p_time;');
                $stmt->bindParam(':name', $name);
                $stmt->bindParam(':phone', $phone);
                  $stmt->bindParam(':p_time', $p_time);

                if($stmt->execute())
                {
                    $successMSG = "예약 완료.";
                }
                else
                {
                    $errMSG = "에러 발생! 잠시후 다시 시도해 주세요.";
                }

            } catch(PDOException $e) {   // 에러 (입력이 제대로 안됨)
                die("Database error: " . $e->getMessage());
            }

          } // else
        } //

    }

?>


<?php
    if (isset($errMSG)) echo $errMSG;
    if (isset($successMSG)) echo $successMSG;

	$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

    if( !$android )
    {
?>
    <html>
       <body>

            <form action="<?php $_PHP_SELF ?>" method="POST">
                Name: <input type = "text" name = "name" />
                phone: <input type = "text" name = "phone" />
                p_time: <input type = "text" name = "p_time" />
                <input type = "submit" name = "submit" />
            </form>

       </body>
    </html>

<?php
    }
?>
