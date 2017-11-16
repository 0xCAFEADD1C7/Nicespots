import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
import PropTypes from 'prop-types';

import StarRating from 'react-star-rating-component';

import { Button } from 'react-bootstrap';

export default class CommentForm extends Component {
  renderReview = () => {
    return <div>
      <StarRating name="rate"/>
      <textarea name="review" placeholder="Ecrivez ce que vous pensez de cet endroit !" className="form-control"></textarea>
      <Button type="submit" bsStyle="success" className="form-control">Envoyer mon avis !</Button>
    </div>
  }

  renderComment = () => {
    return <div>
      <textarea name="comment" placeholder="Dites ce que vous pensez de cet evenement !" className="form-control"></textarea>
      <Button type="submit" bsStyle="success" className="form-control">Envoyer mon commentaire !</Button>
    </div>
  }

  render() {
    return <form onSubmit={(e) => { this.handleSubmit(); e.preventDefault(); }} onChange={this.handleChange}>
      { 
        this.props.type === 'spot'
          ? this.renderReview()
          : this.renderComment()
      }
    </form>
  }
}

CommentForm.propTypes = {
  type : PropTypes.string,
}