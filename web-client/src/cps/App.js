import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css'

import React, { Component } from 'react';

import {  } from 'react-bootstrap';
import Navbar from './Navbar';
import MainContent from './MainContent';

import { createStore } from 'redux';
import { Provider } from 'react-redux';

import { reducer, initialState } from '../lib/reducer';

const store = createStore(reducer, initialState);

store.subscribe(() => {
  const serialized = JSON.stringify(store.getState(), null, 3);
  window.localStorage.setItem('STATE', serialized);
})

function UnboundApp(props) {
  return <div>
    <Navbar />
    <MainContent />
  </div>
}

export default class App extends Component {
  constructor (props) {
    super(props);
    this.state = {
      
    }
  }

  render () {
    return (
      <Provider store={store}>
        <UnboundApp/>
      </Provider>
    )
  }
}