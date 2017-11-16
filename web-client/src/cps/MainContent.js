import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';

import { Col, Row, Well, Grid, Button } from 'react-bootstrap';

import { connect } from 'react-redux';

import SpotsPage from './SpotsPage';
import SpotPage from './SpotPage';
import EventsPage from './EventsPage';
import EventPage from './EventPage';
import ComingEvents from './ComingEvents';

import { PAGE, ACTIONS } from '../lib/constants';

import { } from '../lib/actions';

import { SpotCRUD, EventCRUD, UserCRUD } from '../lib';
import { viewPage } from '../lib/actions';

class MainContent extends Component {
  constructor(props) {
    super(props);
  }

  renderPage = () => {
    switch (this.props.page) {
      case PAGE.home : return <span>PAS ENCORE FAIT</span>
      case PAGE.event : return <EventPage />
      case PAGE.spot : return <SpotPage />
      case PAGE.listEvents : return <EventsPage />
      case PAGE.listSpots : return <SpotsPage />
      default : return <div>OUPS, PAGE INCONNUE</div>
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
              <Button bsStyle="success" onClick={this.props.loadEvents}>Parcourir les évenements</Button>
            </Well>
            <Well>
              <Button bsStyle="success" onClick={this.props.loadSpots}>Parcourir les lieux d'interet</Button>
            </Well>
          </Col>
          <Col sm={7}>            
            { this.renderPage() }
          </Col>
          <Col sm={2}>
            <ComingEvents/>
          </Col>
        </Row>
      </Grid>
    )
  }
}

function mapStateToProps(st) {
  return {
    page : st.page,
  }
}

function mapDispatchToProps(dispatch) {
  return {
    loadSpots : () => {
      SpotCRUD.get().then(data => {
        dispatch(viewPage(ACTIONS.ViewSpots, data));
      })
    },
    loadEvents : () => {
      EventCRUD.get().then(data => {
        dispatch(viewPage(ACTIONS.ViewEvents, data));
      })
    }
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(MainContent);