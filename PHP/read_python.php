<?php

error_reporting(E_ALL);
ini_set('display_errors',1);

include('dbcon.php');


$data = "C:/Bitnami/wampstack-7.3.10-0/apache2/htdocs/output.txt";

$handle = fopen($data, "r") or die("Unable to open file!");

$contents = fread($handle,filesize($data));

$arr = explode(" ",$contents);

while(list($key,$val) = each($arr)){
//  echo ("$arr[$key] : $val<br>");
$stmt = $con->prepare('UPDATE fee SET charge = :charge, preserved = 0 WHERE time = :time;');
$stmt->bindParam(':charge', $val);
$stmt->bindParam(':time', $key);
  if(!($stmt->execute()))
  {
  echo "요금 갱신 에러 발생";
  }
}
$stmt = $con->prepare('DELETE FROM person');
if(!($stmt->execute()))
{
echo "예약 목록 초기화 에러 발생";
}

fclose($handle);


?>
