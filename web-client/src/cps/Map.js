import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
import PropTypes from 'prop-types';

import GoogleMap from 'google-map-react';

class MyGreatPlace extends Component {
  static propTypes = {
    text: PropTypes.string
  };

  static defaultProps = {};

  render() {
    return (
       <div>
          {this.props.text}
       </div>
    );
  }
}

export default class SimpleMapPage extends Component {
  static propTypes = {
    center: PropTypes.array,
    zoom: PropTypes.number,
    greatPlaceCoords: PropTypes.any
  };

  static defaultProps = {
    center: [59.938043, 30.337157],
    zoom: 9,
    greatPlaceCoords: {lat: 59.724465, lng: 30.080121}
  };

  constructor(props) {
    super(props);
  }

  render() {
    return (
       <GoogleMap
        apiKey="AIzaSyC3HNJRcc7HyxSUHx4x-QRUFDfT2IuBXAk"
        yesIWantToUseGoogleMapApiInternals
        center={this.props.center}
        zoom={this.props.zoom}>
        <MyGreatPlace lat={59.955413} lng={30.337844} text={'A'} /* Kreyser Avrora */ />
        <MyGreatPlace {...this.props.greatPlaceCoords} text={'B'} /* road circle */ />
      </GoogleMap>
    );
  }
}