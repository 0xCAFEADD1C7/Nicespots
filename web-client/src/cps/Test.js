import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
// import PropTypes from 'prop-types';

import { Col, Row, Grid, Button } from 'react-bootstrap';

import { CRUD } from '../lib';

class Croix extends Component {
  render () {
    return <Button onClick={() => this.props.onRemove(this.props.elId)}>x</Button>
  }
}

class AddComment extends Component {
  constructor (props) {
    super(props);
    this.state = {
      name : "",
    }
    this.CRUD = new CRUD ('comments');
  }

  handleChange = (e) => {
    const value = e.target.value;
    this.setState({
      name : value,
    });
  }

  handleClick = () => {
    this.props.onAdd({name : this.state.name});
  }

  render () {
    return <div>
      <input type="text" onChange={this.handleChange} value={this.state.name}/>
      <Button onClick={this.handleClick}>+</Button>
    </div>
  }
}

export default class Test extends Component {
  constructor (props) {
    super(props);

    this.CRUD = new CRUD ('comments');

    this.state = {
      comments : []
    };

    this.CRUD.get()
    .then(resp => {
      this.setState({
        comments : resp.data,
      });
    });
  }

  handleRemove = (id) => {
    this.CRUD.delete(id)
    .then(ret => {
      this.setState(prev => ({
        comments : prev.comments.filter(comment => comment.id !== id),
      }));
    });
  }

  handleAdd = (data) => {
    this.CRUD.create(data)
    .then(ret => {
      this.setState(prev => ({
        comments : prev.comments.concat([ret.data]),
      }));
    });
  }

  render() {
    return <Grid>
      {this.state.comments.map(comment => {
        return <Row key={comment.id}>
          <Col md={2}>{comment.name}</Col>
          <Col md={6}><Croix onRemove={this.handleRemove} elId={comment.id}/></Col>
        </Row>
      })}
      <Row><AddComment onAdd={this.handleAdd}/></Row>
    </Grid>
  }
}