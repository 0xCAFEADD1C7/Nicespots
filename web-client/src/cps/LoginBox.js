import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
// import PropTypes from 'prop-types';

import { Button } from 'react-bootstrap';

import { login, UserCRUD } from '../lib';
import { ACTIONS } from '../lib/constants';

import { connect } from 'react-redux';

import Form from './Form';
import CreateAccount from './CreateAccount';

// function loggedInAction(user) {
//   return {
//     type : Actions.login,
//     data : user,
//   }
// }

function LoginForm({onSubmit}) {
  const props = {
    fields : [{
      name: "mail",
      type: "email",
      text: "Mail"
    }, {
      name: "password",
      type: "password",
      text: "Mot de passe"
    }],
    submitText : "Connexion",
    usePlaceHolder: true,
    onSubmit,
  } 
  return <Form {... props}/>
}


class LoginBox extends Component {
  render() {
    if (this.props.isLoggedIn) {
      return <a onClick={this.props.onLogout}>Se deconnecter</a>;
    } else {
      return <div>
        <LoginForm onSubmit={this.props.onLogin}/>
        <CreateAccount onSubmit={this.props.onCreateAccount}/>
      </div>
    }
  }
}

function mapStateToProps(state) {
  return {
    isLoggedIn: !!state.token
  }
}

function mapDispatchToProps(dispatch) {
  return {
    onLogin : (data) => {
      login(data.mail, data.password)
      .then(res => {
        const token = res.data.token;
        dispatch({
          type : ACTIONS.Login,
          data : token,
        })
      })
      .catch(alert);
    },

    onLogout : (data) => {
      dispatch({
        type : ACTIONS.Logout
      })
    },

    onCreateAccount : (data) => {
      
    }
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(LoginBox)