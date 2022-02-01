<?php
class item {
    
    public $location;
    public $name;
    public $price;
    public $type;
    public $stock;

    public function __construct() {
    }

    public function getLocation() {
        return $this->location;
    }

    public function setLocation($location) {
        return $this->location = $location;
    }

    public function getName() {
        return $this->name;
    }

    public function setName($name) {
        return $this->name = $name;
    }

    public function getPrice() {
        return $this->price;
    }

    public function setPrice($price) {
        return $this->price = $price;
    }

    public function getType() {
        return $this->type;
    }

    public function setType($type) {
        return $this->type = $type;
    }

    public function getStock() {
        return $this->stock;
    }

    public function setStock($stock) {
        return $this->stock = $stock;
    }
}

?>