import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const currentMoney = localStorage.getItem('money')

export default new Vuex.Store({
    state: {
      money: currentMoney || 0
    },
    mutations: {
        ADD_MONEY(state, money) {
            state.money = state.money + money;
        },

        SUB_MONEY(state, money) {
            state.money = state.money - money;
        }
    }
})