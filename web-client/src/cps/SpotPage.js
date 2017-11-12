import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
import PropTypes from 'prop-types';

import {  } from 'react-bootstrap';

import CommentForm from './CommentForm';
import SpotView from './SpotView';
import ReviewsList from './ReviewsList';

import { spotView } from '../sampleProp';

function SpotReviewForm(props) {
  return <CommentForm type="spot"/>
}

export default class SpotPage extends Component {
  render() {
    return <div>
      <SpotView 
        activities={spotView.activities}
        address={spotView.address} 
        creator={spotView.creator} 
        currentWeather={spotView.currentWeather} 
        images={spotView.images} 
        longitude={spotView.longitude} 
        latitude={spotView.latitude} 
        name={spotView.name}/>
      <ReviewsList reviews={spotView.reviews}/>
      <SpotReviewForm/>
    </div>
  }
}

SpotPage.propTypes = {
  
}