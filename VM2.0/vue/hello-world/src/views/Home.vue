<template>
  <div>
    <h1>Vending Machine</h1>
    <div id="avail-money">
      <h2>Money Inserted:</h2>
      <h3>${{ moneyInserted }}</h3>
    </div>
    <div id="insert-money">
      <h2>Insert money</h2>
      <button v-on:click.prevent="insertMoney(1)">$1</button>
      <button v-on:click.prevent="insertMoney(2)">$2</button>
      <button v-on:click.prevent="insertMoney(3)">$3</button>
      <button v-on:click.prevent="insertMoney(4)">$4</button>
      <button v-on:click.prevent="insertMoney(5)">$5</button>
    </div>
    <div id="inventoryList" v-for="item in items" v-bind:key="item.itemId">
      <button v-on:click.prevent="purchaseItem(item)">
        {{ item.location }} | {{ item.name }} | ${{ item.price }}
      </button>
      <p id="inventory">{{ item.inventory }}</p>
    </div>
  </div>
</template>

<script>
import VendingMachineService from "../services/VendingMachineService.js";

export default {
  data() {
    return {
      moneyInserted: 0,
      moneyMade: 0,
      items: [],
      item: {
        itemId: "",
        location: "",
        name: "",
        price: 0,
        type: "",
        inventory: 0,
      },
    };
  },

  methods: {
    insertMoney(amount) {
      this.$store.commit("ADD_MONEY", amount);
      this.moneyInserted = this.moneyInserted + amount;
    },

    purchaseItem(item) {
      if (item.inventory === 0) {
        alert("Out of stock!");
      } else {
        if (this.moneyInserted >= item.price) {
            this.item = item;
            VendingMachineService.subOne(item);
            this.$store.commit("SUB_MONEY", item.price);
            this.moneyInserted = this.moneyInserted - item.price;
            item.inventory = item.inventory - 1;
        } else {
          alert("Not enough money inserted!");
        }
      }
    },
  },

  created() {
    VendingMachineService.getInventory()
      .then((response) => {
        if (response.status === 200) {
          this.items = response.data;
        }
      })
      .catch((error) => {
        if (error.response && error.response.status === 404) {
          alert("Item not available. May be out of stock.");
        }
      });
  },
};
</script>

<style>
</style>