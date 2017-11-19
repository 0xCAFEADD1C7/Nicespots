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
    case '@@redux/INIT':
      const savedToken = window.localStorage.getItem("token");
      return Object.assign({}, state, {token : savedToken});

    case ACTIONS.Login :
      window.localStorage.setItem("token", action.data);
      return {
        ...state,
        token : action.data
      }

    case ACTIONS.Logout :   
      window.localStorage.removeItem("token");
      return {
        ...state,
        token : null
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

    case ACTIONS.SetComments :
      return { 
        ...state, 
        comments : action.data,
      }

    case ACTIONS.SetReviews :
      return { 
        ...state, 
        reviews : action.data,
      }

    case ACTIONS.AddComment :
      const comments = state.comments || []
      return {
        ...state,
        comments : comments.concat([action.data])
      }
    
    case ACTIONS.AddReview :
      return {
        ...state,
        reviews : state.reviews.concat([action.data])
      }

    default :
      console.log("WARNING : no handler for action type = "+action.type);
  }

  return state;
}