import { PAGE, ACTIONS } from './constants';

export const initialState = {
  user : null,
  page : PAGE.home,
}

function setStateToPage(state, page, stateFieldName, data) {
  return {
    ...state,
    [stateFieldName] : data,
    page,
  }
}

export function reducer(state = initialState, action) {
  switch (action.type) {
    case ACTIONS.Login :
      return {
        ...state,
        user : action.data
      }

    case ACTIONS.Logout :
      return {
        ...state,
        user : null
      }

    case ACTIONS.ViewEvent :
      return setStateToPage(state, PAGE.event, "event", action.data);

    case ACTIONS.ViewEvents :
      return setStateToPage(state, PAGE.listEvents, "events", action.data);

    case ACTIONS.ViewSpot :
      return setStateToPage(state, PAGE.spot, "spot", action.data);

    case ACTIONS.ViewSpots :
      return setStateToPage(state, PAGE.listSpots, "spots", action.data);

    case ACTIONS.GoHome :
      return setStateToPage(state, PAGE.home, "", undefined);

    default :
      console.log("WARNING : no handler for action type = "+action.type);
  }

  return state;
}