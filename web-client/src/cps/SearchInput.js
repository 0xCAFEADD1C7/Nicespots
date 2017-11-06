import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';

import {  } from 'react-bootstrap';

export default class SearchInput extends Component {
  render() {
    return (
      <form className="navbar-form navbar-right" role="search">
        <div className="form-group input-group">
          <input type="text" className="form-control" placeholder="Search.."/>
          <span className="input-group-btn">
            <button className="btn btn-default" type="button">
              <span className="glyphicon glyphicon-search"></span>
            </button>
          </span>        
        </div>
      </form>
    );
  }
}