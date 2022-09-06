<?php

class Row
{
    private $x;
    private $y;
    private $R;
    private $isHit;
    private $currentTime;
    private $workTime;

    public function __construct($currentTime, $x, $y, $R, $isHit, $workTime)
    {
        $this->currentTime = $currentTime;

        $this->x = $x;
        $this->y = $y;
        $this->R = $R;

        $this->isHit = $isHit;
        $this->workTime = $workTime;
    }

    public function getData()
    {
        return "<tr> <td>$this->currentTime</td> <td>$this->x</td> <td>$this->y</td>
          <td>$this->R</td> <td>$this->isHit</td> <td>$this->workTime с</td>
            </tr>";
    }
}
