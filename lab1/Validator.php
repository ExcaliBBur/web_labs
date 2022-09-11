<?php

class Validator
{
    private $x;
    private $y;
    private $R;
    private $isError = false;

    public function __construct($x, $y, $R)
    {
        $this->x = $x;
        $this->y = $y;
        $this->R = $R;
    }

    private function calculateDigitsAfterPoint($x)
    {
        if ((int)$x == $x) {
            return 0;
        } else if (!is_numeric($x)) return false;
        return strlen($x) - strrpos($x, '.') - 1;
    }

    private function validateX($x)
    {
        if ($x == null || $x < -3 || $x > 5 || !is_numeric($x)) $this->isError = true;
    }

    private function validateY($y)
    {
        if ($y === "" || $y <= -3 || $y >= 5 || $this->calculateDigitsAfterPoint($y) >= 10 || !is_numeric($y)) $this->isError = true;
    }

    private function validateR($R)
    {
        if ($R == null || $R < 1 || $R > 3 || !is_numeric($R)) $this->isError = true;
    }

    public function validateAll()
    {
        $this->validateX($this->x);
        $this->validateY($this->y);
        $this->validateR($this->R);
        if ($this->isError) return 0;
        return 1;
}
}
