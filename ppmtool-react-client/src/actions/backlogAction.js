import axios from "axios";
import {
  GET_BACKLOG,
  GET_PROJECT_TASK,
  DELETE_PROJECT_TASK,
  GET_ERRORS
} from "../actions/types";

export const addProjectTask = (
  backlog_id,
  project_task,
  history
) => async dispatch => {
  try {
    await axios.post(`/api/backlog/${backlog_id}`, project_task);
    history.push(`/projectBoard/${backlog_id}`);
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: transformErrorToProjectError(error.response.data)
    });
  }
};

export const getBacklog = backlog_id => async dispatch => {
  const res = await axios.get(`/api/backlog/${backlog_id}`);
  dispatch({
    type: GET_BACKLOG,
    payload: res.data
  });
};

export const getProjctTask = (backlog_id, pt_id, history) => async dispatch => {
  try {
    const res = await axios.get(`/api/backlog/${backlog_id}/${pt_id}`);
    dispatch({
      type: GET_PROJECT_TASK,
      payload: res.data
    });
  } catch (error) {
    history.push(`/dashboard`);
  }
};

export const deleteProjectTask = (backlog_id, pt_id) => async dispatch => {
  if (
    window.confirm(
      "Are you sure? This will delete the project task and all the data related to it."
    )
  ) {
    await axios.delete(`/api/backlog/${backlog_id}/${pt_id}`);
    dispatch({
      type: DELETE_PROJECT_TASK,
      payload: pt_id
    });
  }
};

function transformErrorToProjectError(data) {
  const err = {
    summary: ""
  };
  window.console.log(data);
  data.errors.map(error => {
    const keys = error.title.split(" ");
    err[keys[0]] = error.title;
  });
  return err;
}
