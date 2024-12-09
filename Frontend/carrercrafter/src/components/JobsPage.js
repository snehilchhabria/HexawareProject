import React, { useEffect, useState } from 'react';
import JobSeekerService from '../services/JobSeekerService';
import '../Css/JobPage.css';
import axios from 'axios';

const JobPage = () => {
  const [jobs, setJobs] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [selectedJob, setSelectedJob] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [resumeUploaded, setResumeUploaded] = useState(false);
  const [resumeId, setResumeId] = useState(null);

  useEffect(() => {
    // Fetch available jobs
    const fetchJobs = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/admin/jobs'); // Replace with actual API endpoint
        setJobs(response.data);
      } catch {
        setError('Failed to fetch jobs.');
      } finally {
        setLoading(false);
      }
    };

    fetchJobs();
  }, []);

  const handleApplyNow = async (job) => {
    const userRole = localStorage.getItem('userRole');
    const jobSeekerId = localStorage.getItem('uid');
  
    if (userRole !== 'JOBSEEKER') {
      alert('Only Jobseekers can apply for jobs.');
      return;
    }
  
    try {
      // Fetch the resumeId
      const resumeResponse = await JobSeekerService.fetchResumeStatus(jobSeekerId);

    // Log the response to the console
      console.log('Resume Response:', resumeResponse) // Debug log for checking the resumeId
  
      setResumeUploaded(!!resumeResponse);  // Set to true if resume exists
    setResumeId(resumeResponse?.resumeId || null); // Set resumeId (null if not found)
    setSelectedJob(job); // Store selected job for application

    console.log('Job ID:', job.jobId);
    console.log('Resume ID:', resumeId);
    setShowModal(true); // Show modal to confirm application
  } catch (error) {
    console.error('Error fetching resume status:', error);
    alert('Error checking resume status.');
  }
  
  };

  const handleApplicationSubmit = async () => {
    const jobSeekerId = parseInt(localStorage.getItem('uid'), 10);
    const selectedJobId = selectedJob?.jobId;  // Note: changed from id to jobId
  
    if (!resumeId) {
      alert('Please upload your resume to apply.');
      return;
    }
  
    if (!selectedJobId) {
      console.error('Missing job ID');
      alert('Error: Unable to apply. Please try again.');
      return;
    }
  
    const applicationDTO = {
      applicantId: jobSeekerId,
      jobListingId: selectedJobId,
      resumeId: resumeId,
      status: 'PENDING',
      appliedDate: new Date().toISOString(),
    };
  
    try {
      console.log('Payload being sent:', applicationDTO);
      const response = await JobSeekerService.createApplication(applicationDTO);
      alert('Application submitted successfully!');
      setShowModal(false);
      return response;  // Optional: return the response in case you need the created application ID
    } catch (error) {
      console.error('Error submitting application:', error);
      alert('Failed to submit application.');
    }
  }

  const handleResumeUpload = async (event) => {
    const jobSeekerId = localStorage.getItem('uid');
    const file = event.target.files[0];

    try {
      const uploadResponse = await JobSeekerService.uploadResume(jobSeekerId, file);
      setResumeUploaded(true);
      setResumeId(uploadResponse.resumeId);
      alert('Resume uploaded successfully!');
    } catch (error) {
      console.error('Error uploading resume:', error);
      alert('Failed to upload resume.');
    }
  };

  if (loading) return <div className="loading">Loading jobs...</div>;
  if (error) return <div className="error">{error}</div>;

  return (
    <div className="jobs-container">
      <h1 className="jobs-heading">Available Jobs</h1>
      <div className="jobs-grid">
        {jobs.map((job) => (
          <div key={job.id} className="job-card">
            <h2 className="job-title">{job.jobTitle}</h2>
            <p className="job-description">{job.jobDescription}</p>
            <p className="job-company">{job.company}</p>
            <p className="job-posted-date">{job.postedDate}</p>
            <p className="job-requirements"><strong>Skills Required:</strong> {job.requirements}</p>
            <p className="job-location"><strong>Location:</strong> {job.location}</p>
            <p className="job-salary"><strong>Salary:</strong> {job.salary}</p>
            <button onClick={() => handleApplyNow(job)} className="apply-btn">Apply Now</button>
          </div>
        ))}
      </div>

      {showModal && (
        <div className="modal">
          <div className="modal-content">
            {resumeUploaded ? (
              <>
                <p>Confirm application for <strong>{selectedJob.jobTitle}</strong>?</p>
                <button onClick={handleApplicationSubmit} className="btn-primary">Submit Application</button>
              </>
            ) : (
              <>
                <p>No resume found. Please upload one to apply for <strong>{selectedJob.jobTitle}</strong>.</p>
                <input type="file" onChange={handleResumeUpload} />
              </>
            )}
            <button onClick={() => setShowModal(false)} className="btn-secondary">Close</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default JobPage;
