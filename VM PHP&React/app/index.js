import React from 'react';
import ReactDOM from 'react-dom';
import Form from 'form.js';

const config = {
    api: `${process.env.REACT_APP_API}`,
}

ReactDOM.render(<Form config={config} />, document.getElementById('root'));
