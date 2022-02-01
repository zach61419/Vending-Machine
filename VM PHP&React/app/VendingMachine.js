import React from 'react';

class VendingMachine extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            moneyInserted: 0,
            change: [],
            inventory: [],
            item: {
                location: "",
                name: "",
                price: 0,
                type: "",
                stock: 5
            }
        };
    }

    insertMoney(money) {
        this.moneyInserted = this.moneyInserted + money;
    }

    purchaseItem(item) {
        if (this.moneyInserted >= item.price) {
            this.moneyInserted = this.moneyInserted - item.price;
            this.state.inventory.forEach((result) => {
                if (item == result) {
                    result -= 1;
                }
            })
        }
    }

    makeChange() {
        let dollars = 0;
        let quarters = 0;
        let dimes = 0;
        let nickels = 0;
        let change = this.moneyInserted;

        while (change >= 1) {
            dollars++;
            change -= 1;
        } 
        while (change >= 0.25) {
            quarters++;
            change -= 0.25;
        }
        while (change >= 0.1) {
            dimes++;
            change -= 0.1;
        }
        while (change >= 0.05) {
            nickels++;
            change -= 0.05;
        }
        this.change = [dollars, quarters, dimes, nickels];
    }
}