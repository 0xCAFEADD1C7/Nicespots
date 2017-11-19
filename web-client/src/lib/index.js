import axios from "axios";

import querystring from 'querystring';

export const api = axios.create({
  // baseURL : 'http://localhost:8080/com.Dar/',
  // baseURL : 'http://172.20.10.2:8080/com.Dar/',
  // baseURL : "http://localhost:4000",
  baseURL : "https://bonspot2017.herokuapp.com/"
})

api.interceptors.request.use((config) => {
  return Object.assign({}, config, {
    headers : {
      "x-auth-token" : window.localStorage.getItem('token')
    }
  })
}, function (error) {
  alert("Erreur : "+error.message);
  // Do something with request error
  return Promise.reject(error);
});

/**
 * Send login data and retrieves the auth token
 * @param {string} mail email of user
 * @param {string} password password (in plain text) of user
 * @return {Promise<string>} authentication token
 */

export function login(mail, password) {
  return api.post('/login', {
    mail, password
  })
}

/* Wrapper function that takes axios reponse promise in arg, and returns its data */
function w(prom) {
  return prom.then(resp => resp.data);
}

export class CRUD {

  /**
   * Constructor.
   * @param {string} ressourceName name of the CRUDed ressource
   */
  constructor(ressourceName) {
    this.ressourceName = ressourceName;
    this.ressourceUrl = "/" + ressourceName + "/";
  }

  /**
   * Get a list of `ressource`
   */
  get = () => {
    return w(api.get(this.ressourceUrl));
  }

  /**
   * Get the `ressource` with the given ID
   * @param {string} id ressource ID
   */
  getOne = (id) => {
    return w(api.get(this.ressourceUrl + id))
  }

  /**
   * Make a POST to API
   * @param data json object to post to API
   */
  create = (data) => {
    return w(api.post(this.ressourceUrl, data));
  }

  /**
   * Make a DELETE to API
   * @param id id of ressource to remove
   */
  delete = (id) => {
    return w(api.delete(this.ressourceUrl + id));
  }

  /**
   * Make a PUT to API
   * @param id id of ressource to update
   * @param data json object to send to API
   */
  update = (id, data) => {
    return w(api.post(this.ressourceUrl, data));
  }
}

export const UserCRUD = new CRUD('user');
export const EventCRUD = new CRUD('event');
export const SpotCRUD = new CRUD('spot');