import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
import PropTypes from 'prop-types';

import { SpotCRUD } from '../lib';
import { viewPage } from '../lib/actions';
import { ACTIONS } from '../lib/constants';

import { Well } from 'react-bootstrap';

import { connect } from 'react-redux';

function SpotList({list, clickHandler}) {
  return list.map(spot => <Well key={spot.id} onClick={() => clickHandler(spot.id)}>
    <h3>{spot.name} <small>par {spot.creator.pseudo}</small></h3>
    <p>{spot.address}</p>
  </Well>)
}

class SpotsPage extends Component {
  render() {
    return <div>
      <p>Voici la liste des spots :</p>
      <SpotList list={this.props.spots} clickHandler={this.props.onClick}/>
    </div>
  }
}

function mapStateToProps (state) {
  return {
    spots : state.spots,
  }
}

function mapDispatchToProps (dispatch) {
  return {
    onClick : (spotId) => {
      SpotCRUD.getOne(spotId)
      .then(data => {
        dispatch(viewPage(ACTIONS.ViewSpot, data));
      })
    }
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(SpotsPage);

SpotsPage.propTypes = {
  
}