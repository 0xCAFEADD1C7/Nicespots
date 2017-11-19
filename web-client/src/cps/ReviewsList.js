import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
import PropTypes from 'prop-types';

import {  } from 'react-bootstrap';

import Comment from './Comment';

export default class ReviewsList extends Component {
  render() {
    const reviews = this.props.reviews;
    return (!reviews || reviews.length === 0)
    ? <p>Aucun avis sur cet endroit... Soyez le premier a en donner !</p>
    : reviews.map((review) => {
      return <Comment 
        author={review.pseudo}
        message={review.review}
        avatar={"img/avatar1.png"}
        postDate={review.createdAt}
        mark={review.rating}
        key={review.id}
      />
    })
  }
}

ReviewsList.propTypes = {
  
}