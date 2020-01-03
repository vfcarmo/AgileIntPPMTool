import React, { Component } from "react";
import Backlog from "./Backlog";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import { getBacklog } from "../../actions/backlogAction";
import PropTypes from "prop-types";

class ProjectBoard extends Component {
  componentDidMount() {
    this.props.getBacklog();
  }
  render() {
    const { id } = 0;
    const { project_tasks } = [];
    return (
      <div className="container">
        <Link to={`/addProjectTask/${id}`} className="btn btn-primary mb-3">
          <i className="fas fa-plus-circle"> Create Project Task</i>
        </Link>
        <br />
        <hr />
        <Backlog />
        {
          // {project_tasks.map(projectTask => (
          //   <Backlog
          //     key={projectTask.projectSequence}
          //     projectTask={projectTask}
          //   />
          // ))}
        }
      </div>
    );
  }
}

ProjectBoard.propTypes = {
  project_task: PropTypes.object.isRequired,
  getBacklog: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  project_task: state.project_task
});

export default connect(mapStateToProps, { getBacklog })(ProjectBoard);
