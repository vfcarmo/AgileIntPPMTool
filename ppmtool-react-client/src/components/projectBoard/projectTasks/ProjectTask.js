import React, { Component } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import { deleteProjectTask } from "../../../actions/backlogAction";
import PropTypes from "prop-types";

class ProjectTask extends Component {
  onDeleteClick = (backlog_id, pt_id) => {
    this.props.deleteProjectTask(backlog_id, pt_id);
  };
  render() {
    const { id } = 0;
    const { pt_id } = 0;
    return (
      <div className="card mb-1 bg-light">
        <div className="card-header text-primary">
          ID: projectSequence -- Priority: priorityString
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">project_task.summary</h5>
          <p className="card-text text-truncate ">
            project_task.acceptanceCriteria
          </p>
          <Link to={`/addProjectTask/${id}`} className="btn btn-primary">
            View / Update
          </Link>

          <button
            onClick={this.onDeleteClick.bind(this, id, pt_id)}
            className="btn btn-danger ml-4"
          >
            Delete
          </button>
        </div>
      </div>
    );
  }
}

export default ProjectTask;
