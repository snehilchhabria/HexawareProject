import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/user';

export const register = async (userData) => {
  try {
    const response = await axios.post(`${BASE_URL}/signup`, {
      ...userData,
      userRole: userData.userRole || 'JOBSEEKER'
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const login = async (loginData) => {
  try {
    const response = await axios.post(`${BASE_URL}/signin`, {
      ...loginData,
      userRole: loginData.userRole || 'JOBSEEKER'
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const logout = () => {
  localStorage.removeItem('token');
  localStorage.removeItem('userRole');
};

export const isAuthenticated = () => {
  return !!localStorage.getItem('token');
};

export const getUserRole = () => {
  return localStorage.getItem('userRole');
};

export const getAuthHeader = () => {
  const token = localStorage.getItem('token');
  return token ? { Authorization: `Bearer ${token}` } : {};
};