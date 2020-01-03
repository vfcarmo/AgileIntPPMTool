import React, { Component } from "react";
import Backlog from "./Backlog";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import { getBacklog } from "../../actions/backlogAction";
import PropTypes from "prop-types";

class ProjectBoard extends Component {
  componentDidMount() {
    const { id } = this.props.match.params;
    this.props.getBacklog(id);
  }
  render() {
    const { id } = this.props.match.params;
    const { project_tasks } = this.props.backlog;
    return (
      <div className="container">
        <Link to={`/addProjectTask/${id}`} className="btn btn-primary mb-3">
          <i className="fas fa-plus-circle"> Create Project Task</i>
        </Link>
        <br />
        <hr />
        <Backlog project_tasks={project_tasks} />
      </div>
    );
  }
}

ProjectBoard.propTypes = {
  backlog: PropTypes.object.isRequired,
  getBacklog: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  backlog: state.backlog
});

export default connect(mapStateToProps, { getBacklog })(ProjectBoard);
