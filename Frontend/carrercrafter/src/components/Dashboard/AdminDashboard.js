import React, { useState } from 'react';
import '../../Css/Dashboardcss/AdminDashboard.css';

const AdminDashboard = () => {
  const [profile, setProfile] = useState({
    name: '',
    email: '',
    contact: ''
  });

  const [jobSeekers, setJobSeekers] = useState([
    { id: 1, name: 'John Doe', email: 'john@example.com' },
    { id: 2, name: 'Jane Smith', email: 'jane@example.com' }
  ]);

  const [employers, setEmployers] = useState([
    { id: 1, companyName: 'Tech Corp', email: 'tech@example.com' },
    { id: 2, companyName: 'Data Solutions', email: 'data@example.com' }
  ]);

  const [jobListings, setJobListings] = useState([
    { id: 1, title: 'Software Engineer', company: 'Tech Corp' },
    { id: 2, title: 'Data Analyst', company: 'Data Solutions' }
  ]);

  const handleProfileUpdate = (e) => {
    const { name, value } = e.target;
    setProfile(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const submitProfileUpdate = () => {
    // Profile update logic will be implemented later
    console.log('Admin profile updated:', profile);
  };

  const deleteJobSeeker = (id) => {
    setJobSeekers(prev => prev.filter(seeker => seeker.id !== id));
  };

  const deleteEmployer = (id) => {
    setEmployers(prev => prev.filter(employer => employer.id !== id));
  };

  const deleteJobListing = (id) => {
    setJobListings(prev => prev.filter(job => job.id !== id));
  };

  return (
    <div className="admin-dashboard p-6 bg-gray-100 min-h-screen">
      <h1 className="text-2xl font-bold mb-6">Admin Dashboard</h1>
      
      <div className="profile-section bg-white p-4 rounded shadow mb-6">
        <h2 className="text-xl font-semibold mb-4">Update Admin Profile</h2>
        <div className="grid grid-cols-2 gap-4">
          <input 
            type="text" 
            name="name"
            placeholder="Full Name"
            value={profile.name}
            onChange={handleProfileUpdate}
            className="input-field"
          />
          <input 
            type="email" 
            name="email"
            placeholder="Email"
            value={profile.email}
            onChange={handleProfileUpdate}
            className="input-field"
          />
          <input 
            type="tel" 
            name="contact"
            placeholder="Contact Number"
            value={profile.contact}
            onChange={handleProfileUpdate}
            className="input-field"
          />
        </div>
        <button 
          onClick={submitProfileUpdate}
          className="btn-primary mt-4"
        >
          Update Profile
        </button>
      </div>

      <div className="job-seekers-section bg-white p-4 rounded shadow mb-6">
        <h2 className="text-xl font-semibold mb-4">Job Seekers</h2>
        <table className="w-full">
          <thead>
            <tr className="bg-gray-100">
              <th className="p-2">Name</th>
              <th className="p-2">Email</th>
              <th className="p-2">Action</th>
            </tr>
          </thead>
          <tbody>
            {jobSeekers.map(seeker => (
              <tr key={seeker.id} className="border-b">
                <td className="p-2">{seeker.name}</td>
                <td className="p-2">{seeker.email}</td>
                <td className="p-2">
                  <button 
                    onClick={() => deleteJobSeeker(seeker.id)}
                    className="btn-danger"
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <div className="employers-section bg-white p-4 rounded shadow mb-6">
        <h2 className="text-xl font-semibold mb-4">Employers</h2>
        <table className="w-full">
          <thead>
            <tr className="bg-gray-100">
              <th className="p-2">Company Name</th>
              <th className="p-2">Email</th>
              <th className="p-2">Action</th>
            </tr>
          </thead>
          <tbody>
            {employers.map(employer => (
              <tr key={employer.id} className="border-b">
                <td className="p-2">{employer.companyName}</td>
                <td className="p-2">{employer.email}</td>
                <td className="p-2">
                  <button 
                    onClick={() => deleteEmployer(employer.id)}
                    className="btn-danger"
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <div className="job-listings-section bg-white p-4 rounded shadow">
        <h2 className="text-xl font-semibold mb-4">Job Listings</h2>
        <table className="w-full">
          <thead>
            <tr className="bg-gray-100">
              <th className="p-2">Job Title</th>
              <th className="p-2">Company</th>
              <th className="p-2">Action</th>
            </tr>
          </thead>
          <tbody>
            {jobListings.map(job => (
              <tr key={job.id} className="border-b">
                <td className="p-2">{job.title}</td>
                <td className="p-2">{job.company}</td>
                <td className="p-2">
                  <button 
                    onClick={() => deleteJobListing(job.id)}
                    className="btn-danger"
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default AdminDashboard;