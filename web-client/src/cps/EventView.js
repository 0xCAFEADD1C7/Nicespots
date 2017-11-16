import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
import PropTypes from 'prop-types';

import { Panel } from 'react-bootstrap';

export default class EventView extends Component {
  render() {
    const header = <div>
      <h3>{this.props.name} <small>par {this.props.creator}</small></h3>
      <small>{this.props.date.toString()} - {this.props.place}</small>
    </div>;

    return (
      <Panel header={header}>
        <p>{this.props.description}</p>
        <p>Meteo prévue : {this.props.weather ? this.props.weather : "Pas encore disponible !"}</p>
      </Panel>
    )
  }
}

EventView.propTypes = {
  
}