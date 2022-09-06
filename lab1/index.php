<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>web_laba</title>
    <style>
        #table {
            width: 70%;
            height: 60%;
            margin: auto;
            text-align: center;
        }
    </style>
</head>
<body>
    <table id="table" border="0">
        <tr>
            <td height="15%" colspan="3">
                <h1>Павлов Александр Р32111</h1>
                <h2>Вариант 1812</h2>
            </td>
        </tr>
        <form id = "answer" action="validator.js">
        <tr>
            <td class="form">
                <div id="errorMessage" style="color: red"></div>
<!--            заменить на css-->
                    X:
                        <input type="radio" name = "X" value = "-3"> -3
                        <input type="radio" name = "X" value = "-2"> -2
                        <input type="radio" name = "X" value = "-1"> -1
                        <input type="radio" name = "X" value = "0"> 0
                        <input type="radio" name = "X" value = "1"> 1
                        <input type="radio" name = "X" value = "2"> 2
                        <input type="radio" name = "X" value = "3"> 3
                        <input type="radio" name = "X" value = "4"> 4
                        <input type="radio" name = "X" value = "5"> 5 <br>

                Y:
                <input type="text" onkeypress='return (event.charCode >= 44 && event.charCode <= 57)' name = "Y"
                       id = "text_input" placeholder = "-3..5" autocomplete="off" onpaste="return false"><br>
<!--                тут сделать стиль отступа-->
                    R:
                    <input type="checkbox" name = "R" value = "1"> 1
                    <input type="checkbox" name = "R" value = "1.5"> 1.5
                    <input type="checkbox" name = "R" value = "2"> 2
                    <input type="checkbox" name = "R" value = "2.5"> 2.5
                    <input type="checkbox" name = "R" value = "3"> 3<br>
                <button type="submit" id = "button">Подтвердить</button>
        </form>
                <td>
                    <img src = "image.png" alt = "график">
                </td>
            </tr>
        <tr>
            <td colspan="5">Последние 10 запросов:</td>
        </tr>
            <tr>
<!--                откорректировать через css-->

                <td colspan="3">
                    <table border="1" width="100%">
                        <th>Время запроса</th>
                        <th>X</th>
                        <th>Y</th>
                        <th>R</th>
                        <th>Попадание</th>
                        <th>Время выполнения скрипта</th>
                        <tfoot id = "rows" align="center"></tfoot>
                    </table>
                </td>
            </tr>
    </table>
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
    <script src="validator.js"></script>
</body>
</html>