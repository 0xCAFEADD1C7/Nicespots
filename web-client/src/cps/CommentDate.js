import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
import PropTypes from 'prop-types';

import {  } from 'react-bootstrap';

class CommentDate extends Component {
  computeString(date) {
    date = new Date(date);
    const dtime = new Date() - date;

    const seconds = 1000;
    const min = 60 * seconds;
    const hour = 60 * min;
    const day = 24 * hour;

    if (dtime < min) {
      return "Il y a un instant"
    } else if (dtime <= hour) {
      return "Il y a "+ Math.round(dtime / min)+" min";
    } else if (dtime <= day) {
      return "Il y a "+ Math.round(dtime / hour)+" heures";
    } else {
      return date.getDate() + "/" + date.getMonth() + "/" + date.getFullYear() + " a " + date.getHours() + ":" + date.getMinutes();
    }
  }
  
  render() {
    return <small className="text-secondary">{this.computeString(this.props.date)}</small>
  }
}

CommentDate.propTypes = {
  date: PropTypes.instanceOf(Date)
}

export default CommentDate;