import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';
import './App.css';

import React, { Component } from 'react';
import PropTypes from 'prop-types';

import { Button } from 'react-bootstrap';

export default class Form extends Component {
  constructor(props) {
    super(props);
    this.state = {
      display : !this.props.hideable,
      formData : {}
    }
  }

  renderInput = ({name, type, text, value, custom}) => {
    const usePlaceHolder = this.props.usePlaceHolder;
    switch (type) {
      case 'textarea' :
        const value = usePlaceHolder ? text : value;
        return <div className="form-group" key={name}>
          {!usePlaceHolder && <label htmlFor={name}>{text}</label>}
          <textarea name={name} className="form-control" placeholder={value}></textarea>
        </div>

      case 'custom' :
        const CustomEl = custom;
        return <div className="form-group" key={name}>
          {!usePlaceHolder && <label htmlFor={name}>{text}</label>}
          <CustomEl name={name} value={this.state.formData[name]}/>
        </div>
      
      default :
        return <div className="form-group" key={name}>
          {!usePlaceHolder && <label htmlFor={name}>{text}</label>}
          <input type={type} name={name} value={value} placeholder={usePlaceHolder ? text : ""} className="form-control" />
        </div>
    }
  }

  handleUpdate = (e) => {
    const name = e.target.name;
    const value = e.target.value;
    this.setState(st => ({
      formData : Object.assign({}, st.formData, {
        [name] : value,
      })
    }));
  }
  
  renderForm() {
    return <form onSubmit={(e) => {e.preventDefault(); this.props.onSubmit(this.state.formData)}} onChange={this.handleUpdate}>
      {
        this.props.fields.map(e => {
          return this.renderInput(e);
        })
      }
      <Button type="submit" bsStyle="success">{this.props.submitText}</Button>
    </form> 
  }

  toggleVisibility = () => {
    this.setState((st) => ({display : !st.display}));
  }

  render() {
    return <div>
      { this.props.hideable && <Button onClick={this.toggleVisibility}>{this.props.buttonText}</Button>}
      { this.state.display && this.renderForm() }
    </div>
  }
}

Form.propTypes = {
  
}