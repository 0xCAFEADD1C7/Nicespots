import axios from "axios";

const api = axios.create({
  // baseURL : 'http://localhost:8080/',
  // baseURL : 'http://172.20.10.2:8080/com.Dar/',
  baseURL : "http://localhost:1234/",
})

/**
 * Send login data and retrieves the auth token
 * @param {string} email email of user
 * @param {string} pass password (in plain text) of user
 * @return {Promise<string>} authentication token
 */

export function login(email, pass) {
  return api.post('/login', {
    email, pass
  })
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
    return api.get(this.ressourceUrl);
  }

  /**
   * Get the `ressource` with the given ID
   * @param {string} id ressource ID
   */
  getOne = (id) => {
    return api.get(this.ressourceUrl + id)
  }

  /**
   * Make a POST to API
   * @param data json object to post to API
   */
  create = (data) => {
    return api.post(this.ressourceUrl, data);
  }

  /**
   * Make a DELETE to API
   * @param id id of ressource to remove
   */
  delete = (id) => {
    return api.delete(this.ressourceUrl + id);
  }

  /**
   * Make a PUT to API
   * @param id id of ressource to update
   * @param data json object to send to API
   */
  update = (id, data) => {
    return api.post(this.ressourceUrl, data);
  }
}

export const UserCRUD = new CRUD('user');
export const EventCRUD = new CRUD('event');
export const SpotCRUD = new CRUD('spot');