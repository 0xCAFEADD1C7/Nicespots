import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
import PropTypes from 'prop-types';

import {  } from 'react-bootstrap';

import Comment from './Comment';

export default class CommentList extends Component {
  render() {
    return this.props.comments.map((comm) => {
      return <Comment 
        author={comm.author}
        message={comm.message}
        avatar={""}
        postDate={comm.createdAt}
        key={comm.id}
      />
    })
  }
}

CommentList.propTypes = {
  
}