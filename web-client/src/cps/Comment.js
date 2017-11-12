import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
import PropTypes from 'prop-types';

import { Col, Row, Panel, Grid } from 'react-bootstrap';

import StarRatingComponent from 'react-star-rating-component';

import CommentDate from './CommentDate';

class Comment extends Component {
  render() {
    const panelHeader = (
      <Grid fluid>
        <Row>
          <Col md={6}>
            <img src={this.props.avatar} className="img-circle d-inline-block" height="32" width="32" alt="Avatar"/>
            &nbsp;{this.props.author}
            &nbsp;<CommentDate date={this.props.postDate} />
          </Col>
          { typeof this.props.mark === undefined &&
            <Col md={6} style={{textAlign : "right"}}>
              <StarRatingComponent starCount={5} value={this.props.mark} editing={false} name="fake-name-to-prevent-warnings"/>
            </Col>
          }
        </Row>
      </Grid>          
    )

    return (
      <Panel header={panelHeader}>
        <p>{this.props.message}</p>
      </Panel>
    )
  }
}

Comment.propTypes = {
  author: PropTypes.string,
  message: PropTypes.string,
  avatar: PropTypes.string,
  mark: PropTypes.number,
  postDate: PropTypes.instanceOf(Date)
}

export default Comment;