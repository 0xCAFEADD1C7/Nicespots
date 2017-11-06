import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
// import PropTypes from 'prop-types';

import { Popover, OverlayTrigger } from 'react-bootstrap';

import LoginBox from './LoginBox';

export default class AccountBtn extends Component {
  render() {
    const loginPop = (
      <Popover id="popover-positioned-bottom" title="Connexion">
        <LoginBox />
      </Popover>
    );

    return <OverlayTrigger trigger="click" placement="left" overlay={loginPop}>
      <a><span className="glyphicon glyphicon-user"></span> My Account</a>
    </OverlayTrigger>
  }
}

AccountBtn.propTypes = {
  
}