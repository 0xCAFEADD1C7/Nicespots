import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
import PropTypes from 'prop-types';

import { EventCRUD, api } from '../lib';
import { viewPage } from '../lib/actions';
import { ACTIONS } from '../lib/constants';

import { Well } from 'react-bootstrap';

import { connect } from 'react-redux';

function EventList({list, clickHandler}) {
  return list.map(event => <Well key={event.id} onClick={() => clickHandler(event.id)}>
    <h3>{event.name} <small>par {event.pseudo}, {event.spot.name}</small></h3>
    <p>{event.description}</p>
  </Well>)
}

class EventsPage extends Component {
  render() {
    return <div>
      <p>Voici la liste des evenements :</p>
      <EventList list={this.props.events} clickHandler={this.props.onClick}/>
    </div>
  }
}

function mapStateToProps (state) {
  return {
    events : state.events,
  }
}

function mapDispatchToProps (dispatch) {
  return {
    onClick : (eventId) => {
      api.get('comment/'+eventId)
      .then(res => {
        dispatch(viewPage(ACTIONS.SetComments, res.data));
      });

      EventCRUD.getOne(eventId)
      .then(data => {
        dispatch(viewPage(ACTIONS.ViewEvent, data));
      })
    }
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(EventsPage);