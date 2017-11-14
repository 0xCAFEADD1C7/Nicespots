import { ACTIONS } from "./constants";

export function viewPage(actionType, data) {
  return {
    type : actionType,
    data,
  }
}