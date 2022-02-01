import axios from 'axios';

export default {

    getInventory() {
        return axios.get('/inventory')
    },

    subOne(item) {
        return axios.post('/sub1', item)
    }
}