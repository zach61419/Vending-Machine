<template>
  <div class="main">
    <h1>Vending Machine</h1>
    <div class="avail-money">
      <h2>Money Inserted:</h2>
      <h3 id="money-in">${{ moneyInserted }}</h3>
    </div>
    <div class="insert-money">
      <h2>Insert money</h2>
      <button class="money-button" v-on:click.prevent="insertMoney(1)">$1</button>
      <button class="money-button" v-on:click.prevent="insertMoney(2)">$2</button>
      <button class="money-button" v-on:click.prevent="insertMoney(3)">$3</button>
      <button class="money-button" v-on:click.prevent="insertMoney(4)">$4</button>
      <button class="money-button" v-on:click.prevent="insertMoney(5)">$5</button>
    </div>
    <div id="inventory-list" v-for="item in items" v-bind:key="item.itemId">
      <button v-on:click.prevent="purchaseItem(item)">
        {{ item.location }} | {{ item.name }} | ${{ item.price }}
      </button>
      <p id="inventory">{{ item.inventory }}</p>
    </div>
    <div class="money-return">
      <button class="return-button" v-on:click.prevent="giveChange()">Return Money</button>
      <p>Dispensing: Dollars: {{ change.dollars }}</p>
      <p>Quarters: {{ change.quarters }}</p>
      <p>Dimes: {{ change.dimes }}</p>
      <p>Nickels: {{ change.nickels }}</p>
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
      change: {
        dollars: 0,
        quarters: 0,
        dimes: 0,
        nickels: 0
      }
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
            this.moneyMade = this.moneyMade + item.price;
            item.inventory = item.inventory - 1;
        } else {
          alert("Not enough money inserted!");
        }
      }
    },

    giveChange() {
      let money = this.moneyInserted;
      while (money >= 1) {
        this.dollars++;
        money--;
      } while (money >= 0.25) {
        this.quarters++;
        money = money - 0.25;
      } while (money >= 0.1) {
        this.dimes++;
        money = money - 0.1;
      } while (money >= 0.05) {
        this.nickels++;
        money = money - 0.05;
      }
      this.moneyInserted = 0;
    }
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
.main {
  border: 1px black solid;
  background: rgb(95, 94, 94);
}

.avail-money {
  height: 90px;
}

#money-in {
  background: black;
  width: 20%;
  height: 50%;
  padding: 0;
  margin: 0 0 0 40%;
  color: green;
  text-align: center;
}

.insert-money {
  width: 50%;
  margin: 0 0 4% 25%;
}

.money-button {
  border-radius: 10%;
  width: 15%;
  margin: 0 2% 0 2%;
  background: rgb(2, 97, 2);
  border: 1px black solid;
}

</style>