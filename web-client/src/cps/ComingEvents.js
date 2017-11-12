import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
// import PropTypes from 'prop-types';

import {  } from 'react-bootstrap';

export default class ComingEvents extends Component {
  render() {
    return <div className="thumbnail">
      <p>Upcoming Events:</p>
      <img src="avatar2.jpeg" alt="Paris" width="400" height="300"/>
      <p><strong>Paris</strong></p>
      <p>Fri. 27 November 2015</p>
      <button className="btn btn-primary">Info</button>
    </div>      
  }
}

ComingEvents.propTypes = {
  
}