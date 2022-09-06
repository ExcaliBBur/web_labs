<?php
require_once 'Row.php';
require_once 'Rows.php';

function printRow($rows)
{
    foreach ($rows->getRows() as $row) {
        echo $row->getData();
    }
}
