import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';

import { Col, Row, Panel, Well, Label, Grid } from 'react-bootstrap';

import Comment from './Comment';

export default class MainContent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      comments : [
        {
          author : "Aymeric", 
          message : "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
          avatar : "img/avatar1.png",
          postDate : new Date(),
          mark : 5
        },
        {
          author : "Marco", 
          message : "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
          avatar : "img/avatar3.jpeg",
          postDate : new Date(new Date()-100000),
          mark : 2
        },
        {
          author : "Zak", 
          message : "Sed ut perspiciatis, unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam eaque ipsa, quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt, explicabo.",
          avatar : "img/avatar2.png",
          postDate : new Date(new Date()-10000000),
          mark : 4
        },
      ]
    }
  }
  render() {
    return (
      <Grid>    
        <Row>
          <Col sm={3}>
            <Well>
              <p><a href="/#profile">My Profile</a></p>
              <img src="img/avatar1.png" className="img-circle" height="65" width="65" alt="Avatar"/>
            </Well>
            <Well>
              <p>
                <Label bsStyle="default">News</Label>
                <Label bsStyle="primary">W3Schools</Label>
                <Label bsStyle="success">Labels</Label>
                <Label bsStyle="info">Football</Label>
                <Label bsStyle="warning">Gaming</Label>
                <Label bsStyle="danger">Friends</Label>
              </p>
            </Well>
          </Col>
          <Col sm={7}>            
            <Row>
              <Col sm={12}>
                <Panel>
                  <p>Status: Feeling Blue</p>
                  <button type="button" className="btn btn-default btn-sm">
                    <span className="glyphicon glyphicon-thumbs-up"></span>
                  </button>
                </Panel>
              </Col>
            </Row>

            {this.state.comments.map((comment) => {
              return <Comment author={comment.author} message={comment.message} avatar={comment.avatar} postDate={comment.postDate} mark={comment.mark} key={comment.author}/>
            })}

          </Col>
          <Col sm={2}>
            <div className="thumbnail">
              <p>Upcoming Events:</p>
              <img src="avatar2.jpeg" alt="Paris" width="400" height="300"/>
              <p><strong>Paris</strong></p>
              <p>Fri. 27 November 2015</p>
              <button className="btn btn-primary">Info</button>
            </div>      
            <div className="well">
              <p>ADS</p>
            </div>
            <div className="well">
              <p>ADS</p>
            </div>
          </Col>
        </Row>
      </Grid>
    )
  }
}