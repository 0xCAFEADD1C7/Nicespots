import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
import PropTypes from 'prop-types';

import { Well } from 'react-bootstrap';

import CommentForm from './CommentForm';
import SpotView from './SpotView';
import ReviewsList from './ReviewsList';
import Form from './Form';

import { connect } from 'react-redux';

import StarRatingComponent from 'react-star-rating-component';
import { api, EventCRUD } from '../lib/index';
import { viewPage } from '../lib/actions';
import { ACTIONS } from '../lib/constants';

function SpotReviewForm({onSubmit}) {
  const props = {
    fields : [{
      name: "rating",
      type: "custom",
      custom: StarRatingComponent,
      text: "Note"
    }, {
      name: "review",
      type: "textarea",
      text: "Donnez votre avis!"
    }],
    usePlaceHolder : true,
    submitText : "Donner mon avis",
    onSubmit
  } 
  return <Well>
    <Form {... props}/>
  </Well>
}

function AddEventForm({onSubmit}) {
  const props = {
    fields : [{
      name: "name",
      type: "text",
      text: "Nom de l'evenement"
    }, {
      name: "description",
      type: "textarea",
      text: "Description de l'evenement"
    }, {
      name: "date",
      type: "date",
      text: "Date de l'evenement"
    }],
    hideable : true,
    buttonText : "Creer un evenement a cet endroit",
    submitText : "Creer l'event!",
    onSubmit
  } 
  return <Well>
    <Form {... props}/>
  </Well>
}

class SpotPage extends Component {
  render() {
    return <div>
      <AddEventForm onSubmit={d => this.props.onCreateEvent(d, this.props.id)}/>
      <SpotView 
        activities={this.props.activities}
        address={this.props.address} 
        creator={this.props.pseudo} 
        weather={this.props.weather} 
        images={this.props.images} 
        longitude={this.props.longitude} 
        latitude={this.props.latitude} 
        name={this.props.name}
        transport={this.props.transport}/>
      <ReviewsList reviews={this.props.reviews}/>
      { this.props.isLoggedIn 
      ? <SpotReviewForm onSubmit={(d) => this.props.onReview(d, this.props.id)}/>
      : <p>Connectez vous pour écrire un avis</p>
      }
    </div>
  }
}

const mapStateToProps = (state) => {
  return {
    ... state.spot,
    reviews : state.reviews,
    isLoggedIn : !!state.token
  }
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

function mapDispatchToProps(dispatch) {
  return {
    onReview : (data, spotId) => {
      api.post('review/'+spotId, data)
      .then((res) => {
        dispatch(viewPage(ACTIONS.AddReview, res.data))
      })
      .catch(alert);
    },

    onCreateEvent : (data, spotId) => {
      const toSend = {
        ...data,
        date : new Date(data.date).getTime(),
        spot : spotId,
      }

      alert(JSON.stringify(toSend, null, 3))

      EventCRUD.create(toSend)
      .then(res => {
        dispatch(viewPage(ACTIONS.ViewEvent, res));
        dispatch(viewPage(ACTIONS.SetComments, []));
      })
      .catch(alert)
    }
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(SpotPage);

SpotPage.propTypes = {
  
}