const redux = require('redux');

function addTodo(todo) {
  return {
    type : "ADD_TODO",
    todo
  }
}

function removeTodo(id) {
  return {
    type : "REM_TODO",
    id
  }
}

const initialState = {
  user : null,
  page : "accueil"
}

function reducer(state = initialState, action) {
  switch (action.type) {
    case "REM_TODO":
      return Object.assign({}, state, {
        todos : state.todos.filter(todo => todo.id !== action.id)
      })

    case "ADD_TODO":
      return Object.assign({}, state, {
        todos : state.todos.concat([action.todo])
      })
  
    default:
      return state;
  }
}

const store = redux.createStore(reducer, initialState);

const unsubscribe = store.subscribe(() => {
  console.log(store.getState());
})

store.dispatch(addTodo({id : 4, name : 'apprendre redux'}));
store.dispatch(addTodo({id : 5, name : 'utiliser redux ?'}));
store.dispatch(removeTodo(2));

unsubscribe();