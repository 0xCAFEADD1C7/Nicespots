import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
import PropTypes from 'prop-types';

import { SpotCRUD, api } from '../lib';
import { viewPage } from '../lib/actions';
import { ACTIONS } from '../lib/constants';

import { Well } from 'react-bootstrap';

import { connect } from 'react-redux';

import Form from './Form';

function SpotList({list, clickHandler}) {
  return list.map(spot => 
    <Well key={spot.id} onClick={() => clickHandler(spot.id)}>
      <h3>{spot.name} <small>par {spot.creator.pseudo}</small></h3>
      <p>{spot.address}</p>
    </Well>)
}

function SpotForm({onSubmit}) {
  const props = {
    fields : [{
      name: "name",
      type: "text",
      text: "Nom du spot"
    }, {
      name: "latitude",
      type: "text",
      text: "Latitude"
    }, {
      name: "longitude",
      type: "text",
      text: "Longitude"
    }, {
      name: "activities",
      type: "text",
      text: "Activités (séparées par des virgules)"
    }],
    hideable: true,
    submitText : "Creer le spot",
    buttonText : "Ajouter un spot",
    onSubmit
  } 
  return <Form {... props}/>
}

class SpotsPage extends Component {
  render() {
    return <div>
      {this.props.isLoggedIn && <SpotForm onSubmit={this.props.onCreate} /> }
      <p>Voici la liste des spots :</p>
      <SpotList list={this.props.spots} clickHandler={this.props.onClick}/>
    </div>
  }
}

function mapStateToProps (state) {
  return {
    spots : state.spots,
    isLoggedIn : !!state.token
  }
}

function mapDispatchToProps (dispatch) {
  return {
    onClick : (spotId) => {

      api.get('review/'+spotId)
      .then(res => {
        dispatch(viewPage(ACTIONS.SetReviews, res.data));
      })
      .catch(alert)
      
      SpotCRUD.getOne(spotId)
      .then(data => {
        dispatch(viewPage(ACTIONS.ViewSpot, data));
      })
    },

    onCreate : (data) => {
      const activities = data.activities.split(",");
      activities.forEach(e => e.trim());

      const toSend = {
        ...data, 
        activities
      }

      SpotCRUD.create(toSend)
      .then(res => {
        dispatch(viewPage(ACTIONS.ViewSpot, res));
        dispatch(viewPage(ACTIONS.SetReviews, []));
      })
      .catch(alert)
    }
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(SpotsPage);

SpotsPage.propTypes = {
  
}