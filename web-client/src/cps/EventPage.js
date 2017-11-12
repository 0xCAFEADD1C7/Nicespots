import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
import PropTypes from 'prop-types';

import {  } from 'react-bootstrap';

import CommentForm from './CommentForm';
import EventView from './EventView';
import CommentList from './CommentList';

import { eventView } from '../sampleProp';

function EventCommentForm(props) {
  return <CommentForm type="event"/>
}

export default class EventPage extends Component {
  render() {
    return <div>
      <EventView
        date={eventView.date}
        description={eventView.description}
        name={eventView.name}
        place={eventView.spot.name}
        subscribers={eventView.subscribers}
        weather={eventView.weather}
        creator={eventView.creator.pseudo}
      />
      <CommentList comments={eventView.comments}/>
      <EventCommentForm/>
    </div>
  }
}

EventPage.propTypes = {
  
}