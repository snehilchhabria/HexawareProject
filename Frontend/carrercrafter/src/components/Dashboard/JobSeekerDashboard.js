import React, { useState } from 'react';
import JobSeekerService from '../../services/JobSeekerService';
import '../../Css/Dashboardcss/JobSeekerDashboard.css';
import { toast } from 'react-toastify';

const JobSeekerDashboard = () => {
  const [profile, setProfile] = useState({
    name: '',
    email: '',
    address: '',
    mob: '',
    dob: '',
    highestEducation: '',
    skills: '',
  });
  const [resume, setResume] = useState(null);
  const [applications, setApplications] = useState([]);

  const handleProfileUpdate = (e) => {
    const { name, value } = e.target;
    setProfile((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleResumeUpload = (e) => {
    const file = e.target.files[0];
    setResume(file);
  };

  const submitProfileUpdate = async () => {
    try {
      const uid = localStorage.getItem('uid');
      if (!uid) {
        toast.error('User ID not found. Please log in again.');
        return;
      }

      const response = await JobSeekerService.updateProfile(uid, profile);
      toast.success('Profile updated successfully!');
      console.log(response);
    } catch (error) {
      console.error('Error updating profile:', error);
      toast.error('Failed to update profile. Please try again.');
    }
  };

  const submitResumeUpload = async () => {
    try {
      const jobSeekerId = localStorage.getItem('uid'); // Get user ID
      const response = await JobSeekerService.uploadResume(jobSeekerId, resume);
      console.log('Resume uploaded successfully:', response);
      alert('Resume uploaded successfully!');
      setResume(null); // Clear the state
    } catch (error) {
      console.error('Error uploading resume:', error);
      alert('Failed to upload resume. Please try again.');
    }
  };

  const viewApplications = async () => {
    try {
      const jobSeekerId = localStorage.getItem('uid');
      if (!jobSeekerId) {
        toast.error('User ID not found. Please log in again.');
        return;
      }

      const fetchedApplications = await JobSeekerService.getApplications(jobSeekerId);
      setApplications(fetchedApplications);  // Set the fetched applications to state
    } catch (error) {
      console.error('Error fetching applications:', error);
      toast.error('Failed to fetch applications. Please try again.');
    }
  };

  return (
    <div className="jobseeker-dashboard p-6 bg-gray-100 min-h-screen">
      <h1 className="text-2xl font-bold mb-6">JobSeeker Dashboard</h1>

      <div className="profile-section bg-white p-4 rounded shadow mb-6">
        <h2 className="text-xl font-semibold mb-4">Update Profile</h2>
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
            type="text"
            name="address"
            placeholder="Address"
            value={profile.address}
            onChange={handleProfileUpdate}
            className="input-field"
          />
          <input
            type="tel"
            name="mob"
            placeholder="Mobile Number"
            value={profile.mob}
            onChange={handleProfileUpdate}
            className="input-field"
          />
          <input
            type="date"
            name="dob"
            placeholder="Date of Birth"
            value={profile.dob}
            onChange={handleProfileUpdate}
            className="input-field"
          />
          <input
            type="text"
            name="highestEducation"
            placeholder="Highest Education"
            value={profile.highestEducation}
            onChange={handleProfileUpdate}
            className="input-field"
          />
          <textarea
            name="skills"
            placeholder="Skills"
            value={profile.skills}
            onChange={handleProfileUpdate}
            className="input-field"
          />
        </div>
        <button onClick={submitProfileUpdate} className="btn-primary mt-4">
          Update Profile
        </button>
      </div>

      <div className="resume-section bg-white p-4 rounded shadow mb-6">
        <h2 className="text-xl font-semibold mb-4">Upload Resume</h2>
        <input
          type="file"
          accept=".png,.jpeg,.jpg"
          onChange={handleResumeUpload}
          className="file-input"
        />
        <button onClick={submitResumeUpload} className="btn-primary mt-4">
          Upload Resume
        </button>
        {resume && <p className="mt-2 text-green-600">{resume.name} is ready to upload. Last updated on {new Date().toLocaleDateString()}.</p>}
      </div>

      <div className="applications-section bg-white p-4 rounded shadow">
        <h2 className="text-xl font-semibold mb-4">My Applications</h2>
        <button onClick={viewApplications} className="btn-primary mb-4">
          View Applications
        </button>
        <div className="applications-list">
          {applications.length > 0 ? (
            applications.map((app) => (
              <div key={app.id} className="application-item bg-gray-50 p-3 rounded mb-2">
                <h3 className="font-medium">{app.jobTitle}</h3>
                <p>Company: {app.company}</p>
                <p>Status: {app.status}</p>
              </div>
            ))
          ) : (
            <p>No applications found.</p>
          )}
        </div>
      </div>
    </div>
  );
};

export default JobSeekerDashboard;
