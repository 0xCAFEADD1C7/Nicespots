import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
import PropTypes from 'prop-types';

import { Grid, Row, Col, Image } from 'react-bootstrap';

import Lightbox from 'react-images';

function id2url (id) {
  return 'img/photos/'+id+'.jpg';
}

function chunkArray(array, chunkSize) {
  const out = []
  for (let i = 0; i < array.length; i += chunkSize) {
    out.push(array.slice(i, i+chunkSize));
  }
  return out;
}

export default class PhotoGallery extends Component {
  constructor(props) {
    super(props);
    this.state = {
      lightboxOpen : false,
      lightboxIndex : 0,
    }
  }

  hideLightBox = () => {
    this.setState({
      lightboxOpen : false
    })
  }

  showLightBox = (imgId) => {
    this.setState({
      lightboxOpen : true,
      lightboxIndex : imgId,
    })
  }

  nextLightBox = () => {
    this.setState((state) => ({
      lightboxIndex : (state.lightboxIndex+1)
    }))
  }

  prevLightBox = () => {
    this.setState((state) => ({
      lightboxIndex : (state.lightboxIndex-1)
    }))
  }

  renderGalleryTable = () => {
    const ITEMS_PER_ROW = 2;
    return <Grid fluid>
      {
        chunkArray(this.props.images, ITEMS_PER_ROW).map(row => <Row>
          {
            row.map((imgId, index) => <Col sm={12/ITEMS_PER_ROW}>
              <Image responsive 
              src={id2url(imgId)} 
              alt="picture" 
              onClick={() => this.showLightBox(index)}/>
            </Col>)
          }
        </Row>)
      }
    </Grid>
  }

  render() {
    return <div>
        <Lightbox
        images={this.props.images.map(imgId => ({src : id2url(imgId)}))}
        onClickNext={this.nextLightBox}
        onClickPrev={this.prevLightBox}
        onClose={this.hideLightBox}
        isOpen={this.state.lightboxOpen}
        currentImage={this.state.lightboxIndex}
      />
      {this.renderGalleryTable()}
    </div>
  }
}

PhotoGallery.propTypes = {
  
}