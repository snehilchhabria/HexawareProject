import React, { useState, useEffect } from 'react';
import '../../Css/Dashboardcss/EmployerDashboard.css';
import EmployerService from '../../services/EmployerService';
import { toast } from 'react-toastify';

const EmployerDashboard = () => {
  const [profile, setProfile] = useState({
    name: '',
    email: '',
    address: '',
    mob: '',
    dob: '',
    companyName: '',
    website: '',
    companyDetails: '',
  });

  const [jobPosting, setJobPosting] = useState({
    jobTitle: '',
    company: '',
    jobDescription: '',
    location: '',
    requirements: '',
    salary: '',
  });

  const [jobPostings, setJobPostings] = useState([]);
  const [applications, setApplications] = useState([]);

  useEffect(() => {
    fetchEmployerJobs(); // Fetch job postings on component mount
    
  }, []);


  

  const handleProfileUpdate = (e) => {
    const { name, value } = e.target;
    setProfile((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleJobPostingChange = (e) => {
    const { name, value } = e.target;
    setJobPosting((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const submitProfileUpdate = async () => {
    try {
      const uid = localStorage.getItem('uid');
      if (!uid) {
        toast.error('Employer ID not found. Please log in again.');
        return;
      }

      const response = await EmployerService.updateEmployerProfile(uid, profile);
      toast.success('Profile updated successfully!');
      console.log(response);
    } catch (error) {
      console.error('Error updating profile:', error);
      toast.error('Failed to update profile. Please try again.');
    }
  };

  const createJobPosting = async () => {
    try {
      const employerId = localStorage.getItem('uid');
      if (!employerId) {
        toast.error('Employer ID not found. Please log in again.');
        return;
      }

      // Include employer ID in job posting
      const jobPostingData = {
        ...jobPosting,
        employer: {
          uid: employerId,
        },
      };

      const response = await EmployerService.postJob(jobPostingData);
      setJobPostings((prev) => [...prev, response]);
      toast.success('Job posted successfully!');

      // Reset form fields after successful posting
      setJobPosting({
        jobTitle: '',
        company: '',
        jobDescription: '',
        location: '',
        requirements: '',
        salary: '',
      });
    } catch (error) {
      console.error('Error posting job:', error);
      toast.error('Failed to post job. Please try again.');
    }
  };

  const fetchEmployerJobs = async () => {
    try {
      const employerId = localStorage.getItem('uid');
      if (!employerId) {
        toast.error('Employer ID not found. Please log in again.');
        return;
      }

      const jobs = await EmployerService.getEmployerJobs(employerId);
      setJobPostings(jobs);
    } catch (error) {
      console.error('Error fetching job postings:', error);
      toast.error('Failed to fetch job postings. Please try again.');
    }
  };

  const viewApplications = async (jobId) => {
    try {
      const applications = await EmployerService.viewApplications(jobId);
      setApplications(applications);
    } catch (error) {
      console.error('Error fetching applications:', error);
      toast.error('Failed to fetch applications. Please try again.');
    }
  };

  return (
    <div className="employer-dashboard p-6 bg-gray-100 min-h-screen">
      <h1 className="text-2xl font-bold mb-6">Employer Dashboard</h1>

      <div className="profile-section bg-white p-4 rounded shadow mb-6">
        <h2 className="text-xl font-semibold mb-4">Update Company Profile</h2>
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
            placeholder="Email Address"
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
            name="companyName"
            placeholder="Company Name"
            value={profile.companyName}
            onChange={handleProfileUpdate}
            className="input-field"
          />
          <input
            type="url"
            name="website"
            placeholder="Company Website"
            value={profile.website}
            onChange={handleProfileUpdate}
            className="input-field"
          />
          <textarea
            name="companyDetails"
            placeholder="Company Details"
            value={profile.companyDetails}
            onChange={handleProfileUpdate}
            className="input-field"
          />
        </div>
        <button onClick={submitProfileUpdate} className="btn-primary mt-4">
          Update Profile
        </button>
      </div>

      <div className="job-posting-section bg-white p-4 rounded shadow mb-6">
        <h2 className="text-xl font-semibold mb-4">Create Job Posting</h2>
        <div className="grid grid-cols-2 gap-4">
          <input
            type="text"
            name="jobTitle"
            placeholder="Job Title"
            value={jobPosting.jobTitle}
            onChange={handleJobPostingChange}
            className="input-field"
          />
          <input
            type="text"
            name="company"
            placeholder="Company Name"
            value={jobPosting.company}
            onChange={handleJobPostingChange}
            className="input-field"
          />
          <textarea
            name="jobDescription"
            placeholder="Job Description"
            value={jobPosting.jobDescription}
            onChange={handleJobPostingChange}
            className="input-field"
          />
          <input
            type="text"
            name="location"
            placeholder="Location"
            value={jobPosting.location}
            onChange={handleJobPostingChange}
            className="input-field"
          />
          <textarea
            name="requirements"
            placeholder="Job Requirements"
            value={jobPosting.requirements}
            onChange={handleJobPostingChange}
            className="input-field"
          />
          <input
            type="text"
            name="salary"
            placeholder="Salary Range"
            value={jobPosting.salary}
            onChange={handleJobPostingChange}
            className="input-field"
          />
        </div>
        <button onClick={createJobPosting} className="btn-primary mt-4">
          Post Job
        </button>
      </div>

      <div className="job-postings-section bg-white p-4 rounded shadow mb-6">
        <h2 className="text-xl font-semibold mb-4">My Job Postings</h2>
        {jobPostings.map((posting) => (
          <div
            key={posting.jobId}
            className="job-posting-item bg-gray-50 p-3 rounded mb-2"
          >
            <h3 className="font-medium">{posting.jobTitle}</h3>
            <p>{posting.jobDescription}</p>
            <button
              onClick={() => viewApplications(posting.jobId)}
              className="btn-secondary mt-2"
            >
              View Applications
            </button>
          </div>
        ))}
      </div>

      <div className="applications-section bg-white p-4 rounded shadow">
        <h2 className="text-xl font-semibold mb-4">Job Applications</h2>
        <div className="applications-list">
          {applications.map((app) => (
            <div
              key={app.id}
              className="application-item bg-gray-50 p-3 rounded mb-2"
            >
              <h3 className="font-medium">{app.jobTitle}</h3>
              <p>Applicant: {app.applicantName}</p>
              <p>Status: {app.status}</p>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default EmployerDashboard;
