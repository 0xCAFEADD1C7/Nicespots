import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
import PropTypes from 'prop-types';

import { Modal, Button } from 'react-bootstrap';

import { connect } from 'react-redux';

import Form from './Form';

import { login, UserCRUD } from '../lib';
import { ACTIONS } from '../lib/constants';

function CreateAccountForm({onSubmit}) {
  const props = {
    fields : [{
      name: "email",
      type: "email",
      text: "Adresse mail"
    }, {
      name: "pseudo",
      type: "text",
      text: "Pseudo"
    }, {
      name: "firstName",
      type: "text",
      text: "Prenom"
    }, {
      name: "lastName",
      type: "text",
      text: "Nom"
    }, {
      name: "password",
      type: "password",
      text: "Mot de passe"
    }],
    submitText : "Creer mon compte",
    onSubmit
  } 
  return <Form {... props}/>
}

class CreateAccount extends Component {
  constructor(props) {
    super(props);
  
    this.state = {
      visible : false
    }
  }

  close = () => {
    this.setState({ visible: false });
  }

  open = () => {
    this.setState({ visible: true });
  }

  render() {
    return (
      <div>
        <Button onClick={this.open}>Creer un compte</Button>
        <Modal style={null} show={this.state.visible} onHide={this.close}>
          <Modal.Header closeButton>
            <Modal.Title>Creer un compte</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <CreateAccountForm onSubmit={this.props.onAccountCreated}/>
          </Modal.Body>
          <Modal.Footer>
            <Button onClick={this.close}>Close</Button>
          </Modal.Footer>
        </Modal>
      </div>
    );
  }
}

function mapDispatchToProps(dispatch) {
  return {
    onAccountCreated : (data) => {
      let created = false;

      UserCRUD.create(data)
      .then(resp => {
        created = true
        return login(data.email, data.password)
      })
      .then(res => {
        const token = res.data.token;
        dispatch({
          type : ACTIONS.Login,
          data : token,
        });
      })
      .catch((e) => {
        if (created) {
          alert("Le compte a été créé mais une erreur est survenue lors de la connexion...\n"+e);
        } else {
          alert(e);
        }
      });
    }
  }
}

export default connect(x => x, mapDispatchToProps)(CreateAccount);