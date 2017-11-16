import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
import PropTypes from 'prop-types';

import {  } from 'react-bootstrap';

import CommentForm from './CommentForm';
import SpotView from './SpotView';
import ReviewsList from './ReviewsList';

import { connect } from 'react-redux';

function SpotReviewForm(props) {
  return <CommentForm type="spot"/>
}

class SpotPage extends Component {
  render() {
    return <div>
      <SpotView 
        activities={this.props.activities}
        address={this.props.address} 
        creator={this.props.creator} 
        currentWeather={this.props.currentWeather} 
        images={this.props.images} 
        longitude={this.props.longitude} 
        latitude={this.props.latitude} 
        name={this.props.name}/>
      <ReviewsList reviews={this.props.reviews}/>
      <SpotReviewForm/>
    </div>
  }
}

const mapStateToProps = (state) => {
  return state.spot;
  // return {
  //   activities: state.spot.activities,
  //   address: state.spot.address,
  //   creator: state.spot.creator,
  //   currentWeather: state.sopt.currentWeather,
  //   images: state.spot.images,
  //   longitude: state.spot.longitude,
  //   latitude: state.spot.latitude,
  //   name: state.spot.name,
  //   reviews: state.sp
  // }
}

export default connect(mapStateToProps)(SpotPage);

SpotPage.propTypes = {
  
}