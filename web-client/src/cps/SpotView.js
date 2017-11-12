import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
// import PropTypes from 'prop-types';

import { Label, Panel, Button } from 'react-bootstrap';

import PhotoGallery from './PhotoGallery';

import { spotReviews } from '../sampleProp';

import md5 from 'blueimp-md5';

function PrettyLabel(props) {
  const styles = ["success", "info", "warning", "danger", "defaut"]
  const hash = parseInt(md5(props.value), 16);
  const style = styles[hash % styles.length];
  return <Label bsStyle={style}>{props.value}</Label>
}

export default class SpotView extends Component {
  render() {
    const header = <div>
      <p>{this.props.name}</p>
      { this.props.activities.map(act => <PrettyLabel key={act} value={act}/>) }
    </div>;

    return (
      <Panel header={header}>
        <p>Adresse : {this.props.address}</p>
        <p>Meteo actuelle : {this.props.currentWeather}</p>
        <p>Coordonnees : (lng : {this.props.longitude}, lat : {this.props.latitude})</p>
        
        <PhotoGallery images={this.props.images}/>
      </Panel>
    )
  }
}

SpotView.propTypes = {
  
}