import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css'

import React, { Component } from 'react';

import {  } from 'react-bootstrap';
import Navbar from './Navbar';
import MainContent from './MainContent';

// import Test from './Test';

export default class App extends Component {
  constructor (props) {
    super(props);
    this.state = {
      
    }
  }

  render () {
    return (
    <div>
      <Navbar />
      <MainContent />
      <div>
        Logo from <a href="https://www.flaticon.com/authors/freepik">Freepik</a>.
      </div>
    </div>
    // <Test/>
    )
  }
}