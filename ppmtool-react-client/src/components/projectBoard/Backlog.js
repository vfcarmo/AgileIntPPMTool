import React, { Component } from "react";

import { connect } from "react-redux";
import { deleteProjectTask } from "../../actions/backlogAction";
import PropTypes from "prop-types";
import ProjectTask from "./projectTasks/ProjectTask";

class Backlog extends Component {
  render() {
    const { project_tasks } = this.props;

    const tasks = project_tasks.map(projectTask => (
      <ProjectTask key={projectTask.id} pt={projectTask} />
    ));

    let todoItems = tasks.filter(task => task.props.pt.status == "TO-DO");
    let inProgressItems = tasks.filter(
      task => task.props.pt.status == "IN_PROGRESS"
    );
    let doneItems = tasks.filter(task => task.props.pt.status == "DONE");

    return (
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-secondary text-white">
                <h3>TO DO</h3>
              </div>
            </div>
            {todoItems}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-primary text-white">
                <h3>In Progress</h3>
              </div>
            </div>
            {inProgressItems}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-success text-white">
                <h3>Done</h3>
              </div>
            </div>
            {doneItems}
          </div>
        </div>
      </div>
    );
  }
}

Backlog.propTypes = {
  deleteProjectTask: PropTypes.func.isRequired
};

export default connect(null, { deleteProjectTask })(Backlog);
