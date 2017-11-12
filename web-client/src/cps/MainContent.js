import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';

import { Col, Row, Well, Grid, Button } from 'react-bootstrap';

import SpotsPage from './SpotsPage';
import SpotPage from './SpotPage';
import EventsPage from './EventsPage';
import EventPage from './EventPage';
import ComingEvents from './ComingEvents';

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
              <Button bsStyle="success">Parcourir les évenements</Button>
            </Well>
            <Well>
              <Button bsStyle="success">Parcourir les lieux d'interet</Button>
            </Well>
          </Col>
          <Col sm={7}>            
            <SpotPage/>
          </Col>
          <Col sm={2}>
            <ComingEvents/>
          </Col>
        </Row>
      </Grid>
    )
  }
}