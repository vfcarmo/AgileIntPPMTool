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
    const { id } = this.props;
    const { pt } = this.props;
    let priorityString;
    let priorityClass;

    if (pt.priority === 1) {
      priorityClass = "bg-danger text-light";
      priorityString = "HIGH";
    } else if (pt.priority === 2) {
      priorityClass = "bg-warning text-light";
      priorityString = "MEDIUM";
    } else {
      priorityClass = "bg-info text-light";
      priorityString = "LOW";
    }
    return (
      <div className="card mb-1 bg-light">
        <div className={`card-header text-primary ${priorityClass}`}>
          ID: {pt.projectSequence} -- Priority: {priorityString}
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">{pt.summary}</h5>
          <p className="card-text text-truncate ">{pt.acceptanceCriteria}</p>
          <Link to={`/addProjectTask/${id}`} className="btn btn-primary">
            View / Update
          </Link>

          <button
            onClick={this.onDeleteClick.bind(this, id, pt)}
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
