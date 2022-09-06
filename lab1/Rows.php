<?php
class Rows
{
    private $MAX_SIZE_OF_ROWS = 10;
    private $rows = array();


    public function push($row) {
        if (count($this -> rows) > $this -> MAX_SIZE_OF_ROWS) array_pop($this -> rows);
        $this->rows[] = $row;
    }

    public function getRows() {
        return $this->rows;
    }
}
