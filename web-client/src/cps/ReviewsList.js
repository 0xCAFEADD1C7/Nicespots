import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
import PropTypes from 'prop-types';

import {  } from 'react-bootstrap';

import Comment from './Comment';

export default class ReviewsList extends Component {
  render() {
    return this.props.reviews.map((review) => {
      console.log("REVIEW ", review)
      return <Comment 
        author={review.author}
        message={review.review}
        avatar={""}
        postDate={review.createdAt}
        mark={review.rating}
        key={review.id}
      />
    })
  }
}

ReviewsList.propTypes = {
  
}