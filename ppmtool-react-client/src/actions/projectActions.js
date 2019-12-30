import axios from "axios";
import { GET_ERRORS, GET_PROJECTS, GET_PROJECT, DELETE_PROJECT } from "./types";

export const createProject = (project, history) => async dispatch => {
  try {
    const res = await axios.post("/api/project", project);
    history.push("/dashboard");
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

export const getProjects = () => async dispatch => {
  const res = await axios.get("/api/project");
  dispatch({
    type: GET_PROJECTS,
    payload: res.data
  });
};

export const getProject = (id, history) => async dispatch => {
  try {
    const res = await axios.get(`/api/project/${id}`);
    dispatch({
      type: GET_PROJECT,
      payload: res.data
    });
  } catch (error) {
    history.push("/dashboard");
  }
};

export const updateProject = (project, history) => async dispatch => {
  try {
    function makeUpdateProject(project) {
      return {
        projectName: project.projectName,
        description: project.description,
        start_date: project.start_date,
        end_date: project.end_date
      };
    }
    const updateProject = makeUpdateProject(project);

    const res = await axios.put(
      `/api/project/${project.projectIdentifier}`,
      updateProject
    );
    history.push("/dashboard");
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

export const deleteProject = id => async dispatch => {
  if (
    window.confirm(
      "Are you sure? This will delete the project and all the data related to it."
    )
  ) {
    await axios.delete(`/api/project/${id}`);
    dispatch({
      type: DELETE_PROJECT,
      payload: id
    });
  }
};

function transformErrorToProjectError(data) {
  const err = {
    projectName: "",
    projectIdentifier: "",
    description: ""
  };
  window.console.log(data);
  data.errors.map(error => {
    const keys = error.title.split(" ");
    err[keys[0]] = error.title;
  });

  return err;
}
