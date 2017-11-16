import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
// import PropTypes from 'prop-types';

import { Button } from 'react-bootstrap';

import { login } from '../lib';
// import Actions from '../lib/actions';

import { connect } from 'react-redux';

// function loggedInAction(user) {
//   return {
//     type : Actions.login,
//     data : user,
//   }
// }

class LoginBox extends Component {
  handleChange = (e) => {
    const elName = e.target.name;
    const elValue = e.target.value;
    
    this.setState({
      [elName] : elValue,
    })
  }

  handleSubmit = (e) => {
    login(this.state.email, this.state.password)
    .then((token) => {
      alert(JSON.stringify(token.data, null, 3));
    })
    .catch(e => console.error("Erreur submit :", e));
  }

  render() {
    return (
      <form className="login-box" onSubmit={(e) => { this.handleSubmit(); e.preventDefault(); }} onChange={this.handleChange}>
        <input type="email" name="mail" className="form-control" placeholder="Adresse email"/>
        <input type="password" name="password" className="form-control" placeholder="Pass"/>
        <Button type="submit" bsStyle="success" className="form-control">Me connecter !</Button>
      </form>
    )
  }
}

LoginBox.propTypes = {
  
}

function mapStateToProps(state) {
  alert("MapStateToProps "+state);
  return {
    user: state.user
  }
}

function mapDispatchToProps(dispatch) {
  alert("MapStateToProps "+dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(LoginBox)