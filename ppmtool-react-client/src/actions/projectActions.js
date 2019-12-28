import axios from "axios";
import { GET_ERRORS } from "./types";

export const createProject = (project, history) => async dispatch => {
  try {
    const res = await axios.post("http://localhost:8080/api/project", project);
    history.push("/dashboard");
  } catch (error) {
    const err = {
      projectName: "",
      projectIdentifier: "",
      description: ""
    };
    error.response.data.errors.map(error => {
      const keys = error.title.split(" ");
      err[keys[0]] = error.title;
    });
    dispatch({
      payload: err,
      type: GET_ERRORS
    });
  }
};
