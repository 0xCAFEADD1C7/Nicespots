import Actions from './actions';

const PAGE = {home : 1};

export const initialState = {
  user : { name : 'ayne'},
  page : PAGE.home,
}

export function reducer(state = initialState, action) {
  switch (action.type) {
    case Actions.Login :
      return {
        ...state,
        user : {

        }
      }

    case Actions.Logout :
      return {
        ...state,
        user : null
      }

    default :
      console.log("WARNING : no handler for action type = "+action.type);
  }

  return state;
}