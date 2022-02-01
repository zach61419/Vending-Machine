<?php
include_once('item.php');

class inventory {
    
    public $inventory = [];
    
    public function __construct(){
    }

    public function getInventory() {
        $row = 1;
        if (($handle = fopen("vendingmachine.csv", "r")) !== FALSE) {
            while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {
                $item = new item();
                $num = count($data);
                echo "<p> $num fields in line $row: <br /></p>\n";
                $row++;
                $item->setLocation($data[0]);
                $item->setName($data[1]);
                $item->setPrice($data[2]);
                $item->setType($data[3]);
                $item->setStock(5);
                $inventory[] = $item;
            }
            fclose($handle);
        }
        return $inventory;
    }
}

?>