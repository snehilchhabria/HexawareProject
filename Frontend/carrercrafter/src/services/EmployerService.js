import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/employer';

// Function to get the token from local storage
const getToken = () => localStorage.getItem('token');

class EmployerService {
  // Post a Job
  static async postJob(jobListingsDTO) {
    const token = getToken();
    const response = await axios.post(`${BASE_URL}/post-job`, jobListingsDTO, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  }

  // View Applications for a Job
  static async viewApplications(jobId) {
    const token = getToken();
    const response = await axios.get(`${BASE_URL}/job/${jobId}/applications`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  }

  // Get Employer's Job Listings
  static async getEmployerJobs(employerId) {
    const token = getToken();
    const response = await axios.get(`${BASE_URL}/my-jobs/${employerId}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  }

  // Update Employer Profile
  static async updateEmployerProfile(uid, employerDTO) {
    const token = getToken();
    const response = await axios.put(`${BASE_URL}/profile/${uid}`, employerDTO, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  }
}

export default EmployerService;
