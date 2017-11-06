import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';

import { Navbar as BNavbar } from 'react-bootstrap';

import SearchInput from './SearchInput'
import AccountBtn from './AccoutBtn'

export default class Navbar extends Component {
  render() {
    return (
      <BNavbar fluid>
        {/* <div className="container-fluid"> */}
          <BNavbar.Header>
            {/* <button type="button" className="navbar-toggle">
              <span className="icon-bar"></span>
              <span className="icon-bar"></span>
              <span className="icon-bar"></span>                        
            </button> */}
            <a className="navbar-brand" href="/#"><img src="img/logo.png" width="32" height="32" alt="BonSpot"/></a>
          </BNavbar.Header>
          <BNavbar.Collapse>
            <ul className="nav navbar-nav">
              <li className="active"><a href="/#">Home</a></li>
            </ul>
            <SearchInput />
            <ul className="nav navbar-nav navbar-right">
              <li><AccountBtn /></li>
            </ul>
          </BNavbar.Collapse>
        {/* </div> */}
      </BNavbar>
    );
  }
}