<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>web_laba</title>
    <style>
        body {
            background-color: #BAFFED;
        }

        #table {
            width: 70%;
            height: 60%;
            margin: auto;
            text-align: center;
            border: 5px solid red;
        }

        #last_requests {
            text-align: center;
            font-size: 23px
        }

        #errorMessage {
            color: red;
            margin-bottom: 5%;
        }

        .answerTable {
            width: 100%;
            margin-bottom: 20px;
            border: 1px solid;
            border-collapse: collapse;
        }

        .answerTable th {
            font-weight: bold;
            padding: 5px;
            border: 1px solid;
            background: white;
        }

        .answerTable td {
            border: 1px solid;
            padding: 5px;
            background: white;
        }

        .radio {
            margin-left: 2%;
            margin-bottom: 5%;
        }

        .text {
            margin-bottom: 5%;
        }

        .checkbox {
            margin-left: 2%;
            margin-bottom: 5%
        }

        .form::first-letter {
            color: blue;
        }

        .firstRow {
            background: white;
        }

        h1, h2 {
            font-family: sans-serif;
            color: black;
            font-size: 25px;
            text-align: center;
        }

        input[type=text]:focus {
            border: 3px solid #555;
        }

        input[type=text] {
            width = 50%;
        }

        td[colspan="3"] {
            font-family: sans-serif;
            color: black;
            text-align: center;
        }

    </style>
</head>
<body>
<table id="table" border="0">
    <tr>
        <td colspan="2">
            <h1>Павлов Александр Р32111</h1>
            <h2>Вариант 1812</h2>
        </td>
    </tr>
    <form id="answer" action="validator.js">
        <tr class="firstRow">
            <td class="form" colspan="1" width="50%">
                <div id="errorMessage"></div>
                X:
                <input type="radio" name="X" value="-3" class="radio"> -3
                <input type="radio" name="X" value="-2" class="radio"> -2
                <input type="radio" name="X" value="-1" class="radio"> -1
                <input type="radio" name="X" value="0" class="radio"> 0
                <input type="radio" name="X" value="1" class="radio"> 1
                <input type="radio" name="X" value="2" class="radio"> 2
                <input type="radio" name="X" value="3" class="radio"> 3
                <input type="radio" name="X" value="4" class="radio"> 4
                <input type="radio" name="X" value="5" class="radio"> 5 <br>

                Y:
                <input class="text" type="text" name="Y" id="text_input"
                       onkeypress='return ((event.charCode >= 44 && event.charCode <= 57) || event.charCode === 101)'
                       placeholder="-3..5" autocomplete="off" onpaste="return false"><br>

                R:
                <input class="checkbox" type="checkbox" name="R" value="1"> 1
                <input class="checkbox" type="checkbox" name="R" value="1.5"> 1.5
                <input class="checkbox" type="checkbox" name="R" value="2"> 2
                <input class="checkbox" type="checkbox" name="R" value="2.5"> 2.5
                <input class="checkbox" type="checkbox" name="R" value="3"> 3<br>
                <button type="submit" id="button">Подтвердить</button>
    </form>
    <td>
        <img src="image.png" alt="график">
    </td>
    </tr>
    <tr>
        <td colspan="5" id="last_requests">Последние 10 запросов:</td>
    </tr>
    <tr>
        <td colspan="3">
            <table class="answerTable">
                <th>Время запроса</th>
                <th>X</th>
                <th>Y</th>
                <th>R</th>
                <th>Попадание</th>
                <th>Время выполнения скрипта</th>
                <tfoot id="rows" align="center"><?php
                require "Row.php";
                require "Rows.php";
                require "print-rows.php";
                if (isset($_COOKIE['x'])) {
                    $x_last_values = json_decode($_COOKIE['x']);
                    $y_last_values = json_decode($_COOKIE['y']);
                    $R_last_values = json_decode($_COOKIE['R']);
                    $isHit_last_values = json_decode($_COOKIE['isHit']);
                    $currentTime_last_values = json_decode($_COOKIE['currentTime']);
                    $workTime_last_values = json_decode($_COOKIE['workTime']);
                    $rows = new Rows();
                    for ($i = count($x_last_values) - 1; $i >= 0; $i--) {
                        $row = new Row($currentTime_last_values[$i], $x_last_values[$i], $y_last_values[$i], $R_last_values[$i],
                            $isHit_last_values[$i], $workTime_last_values[$i]);
                        $rows->push($row);
                    }
                    printRow($rows);
                }
                ?></tfoot>
            </table>
        </td>
    </tr>
</table>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script src="validator.js"></script>
</body>
</html>