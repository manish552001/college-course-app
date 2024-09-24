import React, { useEffect, useState } from 'react';
import axios from 'axios';

const CollegeDetails = ({ collegeId }) => {
  const [college, setCollege] = useState(null);

  useEffect(() => {
    // Fetch data for the selected college
    axios.get(`http://localhost:8080/api/colleges/${collegeId}`)
      .then(response => {
        setCollege(response.data); // Store the fetched college data in state
      })
      .catch(error => {
        console.error('Error fetching college details:', error);
      });
  }, [collegeId]);

  if (!college) {
    return <p>Loading...</p>;
  }

  return (
    <div className="mt-4">
      <h3>{college.name}</h3>
      <h5>Courses:</h5>
      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th>Course Name</th>
            <th>Duration (Months)</th>
            <th>Fee</th>
            <th>Accommodation Type</th>
            <th>Accommodation Fee</th>
          </tr>
        </thead>
        <tbody>
          {college.courses.map(course => (
            <tr key={course.id}>
              <td>{course.name}</td>
              <td>{course.duration}</td>
              <td>{course.fee}</td>
              <td>{course.accommodationType}</td>
              <td>{course.accommodationFee}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default CollegeDetails;
