import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
import PropTypes from 'prop-types';

import { Well } from 'react-bootstrap';

import { connect } from 'react-redux';

import EventView from './EventView';
import CommentList from './CommentList';
import Form from './Form';
import { api } from '../lib/index';
import { viewPage } from '../lib/actions';
import { ACTIONS } from '../lib/constants';

function CommentForm({onSubmit}) {
  const props = {
    fields : [{
      name: "message",
      type: "textarea",
      text: "Un commentaire sur cet event?"
    }],
    usePlaceHolder : true,
    submitText : "Envoyer mon commentaire !",
    onSubmit
  } 
  return <Well>
    <Form {... props}/>
  </Well>
}

class EventPage extends Component {
  render() {
    return <div>
      <EventView
        date={this.props.date}
        description={this.props.description}
        name={this.props.name}
        place={this.props.spot.name}
        subscribers={this.props.subscribers}
        weather={this.props.weather}
        creator={this.props.pseudo}
      />
      <CommentList comments={this.props.comments}/>
      { this.props.isLoggedIn 
      ? <CommentForm onSubmit={d => this.props.onComment(d, this.props.id)}/>
      : <p>Connectez vous pour commenter cet evenement! </p>
      }
    </div>
  }
}

const mapStateToProps = (state) => {
  return {
    ... state.event, 
    isLoggedIn : !!state.token,
    comments : state.comments,
  };
}

const mapDispatchToState = (dispatch) => {
  return {
    onComment : (data, evId) => {
      api.post('comment/'+evId, data)
      .then((res) => {
        dispatch(viewPage(ACTIONS.AddComment, res.data))
      })
      .catch(console.log);
    }
  }
}

export default connect(mapStateToProps, mapDispatchToState)(EventPage);

EventPage.propTypes = {
  
}