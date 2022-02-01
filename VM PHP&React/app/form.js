import React, {useState} from "react";
import PropTypes from "prop-types";
import axios from "axios";

const Form = (props) => {
    
    const handleGetInventory = e => {
        e.preventDefault();
        axios({
            method: "get",
            url: `${process.env.REACT_APP_API}`,
        })
        .then(result => {
            if (result.status === 200) {
                
            }
        })
    }
}