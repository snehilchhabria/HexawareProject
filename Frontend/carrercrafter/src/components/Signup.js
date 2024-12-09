import React, { useState } from 'react';
import '../Css/Signup.css';
import { register, login } from '../services/Authservice.js';
import { useNavigate } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const SignUp = () => {
  const [loginType, setLoginType] = useState('JOBSEEKER');
  const [isLogin, setIsLogin] = useState(true);
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
    confirmPassword: '',
    companyName: '',
    website: '',
    companyDetails: '',
    highestEducation: '',
    skills: '',
    privilege: ''
  });
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const data = await login({ 
        email: formData.email, 
        password: formData.password,
        userRole: loginType 
      });
      localStorage.setItem('token', data.jwt);
      localStorage.setItem('userRole', data.userRole);
      localStorage.setItem('email',data.email);
      localStorage.setItem('uid', data.uid); 
      window.dispatchEvent(new Event("storage"));
      toast.success('Login successful!');
      
      // Role-based navigation (previously commented out)
      // switch(data.userRole) {
      //   case 'ADMIN':
      //     navigate('/');
      //     break;
      //   case 'EMPLOYER':
      //     navigate('/about');
      //     break;
      //   case 'JOBSEEKER':
      //   default:
      //     navigate('/');
      // }
    } catch (error) {
      toast.error(error.response?.data?.message || 'Login failed!');
    }
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    if (formData.password !== formData.confirmPassword) {
      toast.error('Passwords do not match!');
      return;
    }
    try {
      const registrationData = {
        name: formData.name,
        email: formData.email,
        password: formData.password,
        userRole: loginType
      };

      // Add additional fields based on user role
      switch(loginType) {
        case 'EMPLOYER':
          registrationData.companyName = formData.companyName;
          registrationData.website = formData.website;
          registrationData.companyDetails = formData.companyDetails;
          break;
        case 'JOBSEEKER':
          registrationData.highestEducation = formData.highestEducation;
          registrationData.skills = formData.skills;
          break;
        case 'ADMIN':
          registrationData.privilege = formData.privilege;
          break;
      }

      const data = await register(registrationData);
      localStorage.setItem('token', data.jwt);
      localStorage.setItem('userRole', data.userRole);
      toast.success('Registration successful!');
      setIsLogin(true); // Switch to login mode
    } catch (error) {
      toast.error(error.response?.data?.message || 'Registration failed!');
    }
  };

  const renderAdditionalFields = () => {
    switch(loginType) {
      case 'EMPLOYER':
        return (
          <>
            <input
              type="text"
              name="companyName"
              placeholder="Company Name"
              className="signup-input"
              onChange={handleChange}
              required
            />
            <input
              type="text"
              name="website"
              placeholder="Company Website"
              className="signup-input"
              onChange={handleChange}
            />
            <input
              type="text"
              name="companyDetails"
              placeholder="Company Details"
              className="signup-input"
              onChange={handleChange}
            />
          </>
        );
      case 'JOBSEEKER':
        return (
          <>
            <input
              type="text"
              name="highestEducation"
              placeholder="Highest Education"
              className="signup-input"
              onChange={handleChange}
              required
            />
            <input
              type="text"
              name="skills"
              placeholder="Skills (comma-separated)"
              className="signup-input"
              onChange={handleChange}
              required
            />
          </>
        );
      case 'ADMIN':
        return (
          <input
            type="text"
            name="privilege"
            placeholder="Admin Privilege"
            className="signup-input"
            onChange={handleChange}
            required
          />
        );
      default:
        return null;
    }
  };

  return (
    <div className="signup-container">
      <ToastContainer />
      <div className="signup-card">
        <h2 className="signup-title">{isLogin ? 'Login' : 'Register'}</h2>
        <div className="login-type-buttons">
          <button
            className={`login-type-button ${loginType === 'JOBSEEKER' ? 'active-job-seeker' : ''}`}
            onClick={() => setLoginType('JOBSEEKER')}
          >
            Job Seeker
          </button>
          <button
            className={`login-type-button ${loginType === 'EMPLOYER' ? 'active-employer' : ''}`}
            onClick={() => setLoginType('EMPLOYER')}
          >
            Employer
          </button>
          <button
            className={`login-type-button ${loginType === 'ADMIN' ? 'active-admin' : ''}`}
            onClick={() => setLoginType('ADMIN')}
          >
            Admin
          </button>
        </div>
        {isLogin ? (
          <form onSubmit={handleLogin} className="signup-form">
            <input
              type="email"
              name="email"
              placeholder="Email Address"
              className="signup-input"
              onChange={handleChange}
              required
            />
            <input
              type="password"
              name="password"
              placeholder="Password"
              className="signup-input"
              onChange={handleChange}
              required
            />
            <button type="submit" className="signup-submit-button">
              Login
            </button>
          </form>
        ) : (
          <form onSubmit={handleRegister} className="signup-form">
            <input
              type="text"
              name="name"
              placeholder="Full Name"
              className="signup-input"
              onChange={handleChange}
              required
            />
            <input
              type="email"
              name="email"
              placeholder="Email Address"
              className="signup-input"
              onChange={handleChange}
              required
            />
            <input
              type="password"
              name="password"
              placeholder="Password"
              className="signup-input"
              onChange={handleChange}
              required
            />
            <input
              type="password"
              name="confirmPassword"
              placeholder="Confirm Password"
              className="signup-input"
              onChange={handleChange}
              required
            />
            {renderAdditionalFields()}
            <button type="submit" className="signup-submit-button">
              Register
            </button>
          </form>
        )}
        <div className="signup-toggle-container">
          <p className="signup-toggle-text">
            {isLogin ? "Don't have an account?" : "Already have an account?"}
            <button onClick={() => setIsLogin(!isLogin)} className="signup-toggle-link">
              {isLogin ? 'Register here' : 'Login here'}
            </button>
          </p>
        </div>
      </div>
    </div>
  );
};

export default SignUp;