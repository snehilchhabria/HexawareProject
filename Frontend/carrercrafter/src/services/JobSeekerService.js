import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/jobseeker';

const getToken = () => localStorage.getItem('token');

class JobSeekerService {
  // Create Job Seeker
  static async createJobSeeker(jobSeekerDTO) {
    jobSeekerDTO.userRole = 'JOBSEEKER';
    const response = await axios.post(`${BASE_URL}/createjobseeker`, jobSeekerDTO);
    return response.data;
  }



  static async fetchResumeStatus(jobSeekerId) {
    const token = getToken();
    console.log('Token:', token); // Log the token
  
    if (!token) {
      console.error('No token found');
      return null;
    }
  
    try {
      const response = await axios.get(`${BASE_URL}/status/${jobSeekerId}`, {
        headers: {
          'Authorization': `Bearer ${token}`,
        },
      });
      console.log('Full Response:', response);
      return response.data;
    } catch (error) {
      console.error('Full Error:', error.response?.data);
      console.error('Error Details:', {
        status: error.response?.status,
        headers: error.config?.headers,
        url: error.config?.url
      });
      return null;
    }
  }


  // Search Jobs
  static async searchJobs(keyword) {
    try {
      const response = await axios.get(`${BASE_URL}/search-jobs`, { 
        params: { keyword },
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${getToken()}`
        }
      });
      return response.data;
    } catch (error) {
      console.error('Error searching jobs:', error);
      throw error;
    }
  }




  static async getApplications(uid) {
    try {
      const response = await axios.get(`${BASE_URL}/viewApplication/${uid}`, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${getToken()}` // Adding token to headers
        }
      });
      return response.data; // Returning the applications data
    } catch (error) {
      console.error("Error fetching applications:", error);
      throw error; // Throw the error to be handled in the calling function
    }
  }

  // Create Job Application
  static async createApplication(applicationDTO) {
    console.log('Sending Payload:', applicationDTO);

    const response = await axios.post(`${BASE_URL}/apply`, applicationDTO, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${getToken()}`
      }
    });
    return response.data;
  }

  // Upload Resume
  static async uploadResume(jobSeekerId, file) {
    const formData = new FormData();
    formData.append('jobSeekerId', jobSeekerId);
    formData.append('file', file);

    const response = await axios.post(`${BASE_URL}/upload-resume`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
        Authorization: `Bearer ${getToken()}`
      }
    });
    return response.data;
  }


  // Apply to Job
  static async applyToJob(jobSeekerId, jobListingId) {
    const response = await axios.post(`${BASE_URL}/apply`, null, {
      params: { jobSeekerId, jobListingId },
      headers: {
        Authorization: `Bearer ${getToken()}`
      }
    });
    return response.data;
  }

  // Update Job Seeker Profile
  static async updateProfile(uid, jobSeekerDTO) {
    const token = getToken();
    const response = await axios.put(`${BASE_URL}/profile/${uid}`, jobSeekerDTO, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  }
}

export default JobSeekerService;
