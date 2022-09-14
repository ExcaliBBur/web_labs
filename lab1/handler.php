<?php
require 'Row.php';
require 'Rows.php';
require 'print-rows.php';
require 'Validator.php';

$MAX_SIZE_OF_ROWS = 10;

$startTime = microtime(true);
$x = ($_GET["x"]);
$y = ($_GET["y"]);
$R = ($_GET["R"]);

$validator = new Validator($x, $y, $R);
if (!$validator->validateAll()) echo "Ошибка при валидации на сервере.";
else {
    function checkHit($x, $y, $R)
    {
        if ($x > 0 && $y >= 0) {
            if ($x <= $R && $y <= $R / 2) return "Попал";
        } else if ($x <= 0 && $y >= 0) {
            if (pow($x, 2) + pow($y, 2) <= pow($R, 2)) return "Попал";
        } else if ($x <= 0 && $y < 0) {
            if ($y >= (-2) * $x - $R) return "Попал";
        }
        return "Не попал";
    }

    $currentTime = gmdate('d-m-y H:i:s по Гринвичу', time());
    $isHit = checkHit($x, $y, $R);

    $x_last_values = array();
    $y_last_values = array();
    $R_last_values = array();
    $currentTime_last_values = array();
    $isHit_last_values = array();
    $workTime_last_values = array();

    if (isset($_COOKIE["x"])) {
        foreach (json_decode($_COOKIE["x"]) as $val) $x_last_values[] = $val;
        foreach (json_decode($_COOKIE["y"]) as $val) $y_last_values[] = $val;
        foreach (json_decode($_COOKIE["R"]) as $val) $R_last_values[] = $val;
        foreach (json_decode($_COOKIE["currentTime"]) as $val) $currentTime_last_values[] = $val;
        foreach (json_decode($_COOKIE["isHit"]) as $val) $isHit_last_values[] = $val;
        foreach (json_decode($_COOKIE["workTime"]) as $val) $workTime_last_values[] = $val;
    }

    $x_last_values[] = $x;
    $y_last_values[] = $y;
    $R_last_values[] = $R;
    $currentTime_last_values[] = $currentTime;
    $isHit_last_values[] = $isHit;

    $size = count($x_last_values);

    if ($size > $MAX_SIZE_OF_ROWS) {
        array_shift($x_last_values);
        array_shift($y_last_values);
        array_shift($R_last_values);
        array_shift($currentTime_last_values);
        array_shift($workTime_last_values);
        array_shift($isHit_last_values);
        $size--;
    }

    setcookie('x', json_encode($x_last_values));
    setcookie('y', json_encode($y_last_values));
    setcookie('R', json_encode($R_last_values));
    setcookie('currentTime', json_encode($currentTime_last_values));
    setcookie('isHit', json_encode($isHit_last_values));

    $rows = new Rows();

    $finishTime = microtime(true);
    $workTime_last_values[] = number_format($finishTime - $startTime, 7);
    setcookie('workTime', json_encode($workTime_last_values));

    for ($i = $size - 1; $i >= 0; $i--) {
        $row = new Row($currentTime_last_values[$i], $x_last_values[$i], $y_last_values[$i], $R_last_values[$i],
            $isHit_last_values[$i], $workTime_last_values[$i]);
        $rows->push($row);
    }

    printRow($rows);

}