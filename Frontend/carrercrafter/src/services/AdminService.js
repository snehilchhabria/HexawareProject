import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/admin';

// Function to get the token from local storage
const getToken = () => localStorage.getItem('token');

class AdminService {
  // Create Admin
  static async createAdmin(adminDTO) {
    const token = getToken();
    adminDTO.userRole = 'ADMIN';
    const response = await axios.post(`${BASE_URL}/create`, adminDTO, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  }

  // Get All Jobs
  static async getAllJobs() {
    const token = getToken();
    const response = await axios.get(`${BASE_URL}/jobs`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  }

  // Get All Employers
  static async getAllEmployers() {
    const token = getToken();
    const response = await axios.get(`${BASE_URL}/employers`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  }

  // Get All Job Seekers
  static async getAllJobSeekers() {
    const token = getToken();
    const response = await axios.get(`${BASE_URL}/jobseekers`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  }

  // Get All Applications
  static async getAllApplications() {
    const token = getToken();
    const response = await axios.get(`${BASE_URL}/applications`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  }

  // Delete Job
  static async deleteJob(jobId) {
    const token = getToken();
    await axios.delete(`${BASE_URL}/job/${jobId}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  // Delete User
  static async deleteUser(userId) {
    const token = getToken();
    await axios.delete(`${BASE_URL}/user/${userId}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  // Get User by ID
  static async getUserById(userId) {
    const token = getToken();
    const response = await axios.get(`${BASE_URL}/user/${userId}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  }

  // Update Admin Profile
  static async updateAdminProfile(uid, adminDTO) {
    const token = getToken();
    const response = await axios.put(`${BASE_URL}/profile/${uid}`, adminDTO, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  }
}

export default AdminService;