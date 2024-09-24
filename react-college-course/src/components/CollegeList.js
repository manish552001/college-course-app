import React, { useEffect, useState } from 'react';
import axios from 'axios';
import CollegeDetails from './CollegeDetails';

const CollegeList = () => {
  const [colleges, setColleges] = useState([]);
  const [selectedCollegeId, setSelectedCollegeId] = useState(null); // To store the selected college ID

  useEffect(() => {
    // Fetch data from backend API
    axios.get('http://localhost:8080/api/colleges')
      .then(response => {
        setColleges(response.data); // Store the fetched data in state
      })
      .catch(error => {
        console.error('Error fetching colleges:', error);
      });
  }, []);

  const handleViewDetails = (collegeId) => {
    if (selectedCollegeId === collegeId) {
      setSelectedCollegeId(null); // Hide details if the same college is clicked again
    } else {
      setSelectedCollegeId(collegeId); // Show details for the selected college
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="text-center">College List</h2>
      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th>College ID</th>
            <th>College Name</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {colleges.map(college => (
            <tr key={college.id}>
              <td>{college.id}</td>
              <td>{college.name}</td>
              <td>
                <button
                  className={`btn ${selectedCollegeId === college.id ? 'btn-danger' : 'btn-primary'}`}
                  onClick={() => handleViewDetails(college.id)}
                >
                  {selectedCollegeId === college.id ? 'Hide Details' : 'View Details'}
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {selectedCollegeId && (
        <CollegeDetails collegeId={selectedCollegeId} />
      )}
    </div>
  );
};

export default CollegeList;
