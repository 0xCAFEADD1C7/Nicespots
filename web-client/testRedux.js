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
  todos : [
    {
      name: "manger des granolas",
      id:1,
    },
    {
      name: "attendre marco",
      id:2,
    }
  ]
}

function reducer(state = initialState, action) {
  switch (action.type) {
    case "REM_TODO":
      return {
        ...state,
        todos : todos.filter(todo => todo.id !== action.todo.id)
      }

    case "ADD_TODO":
      return {
        ...state,
        todos : todos.concat([action.todo])
      }
  
    default:
      return state;
  }
}