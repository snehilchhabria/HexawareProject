import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import "../Css/Navbar.css";
import { Home, Briefcase, LogIn } from "lucide-react";
import logo from "../images/logo.png";

const Navbar = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [userRole, setUserRole] = useState("");
  const navigate = useNavigate();

  // Check authentication status on component mount
  useEffect(() => {
    const handleStorageChange = () => {
      const token = localStorage.getItem("token");
      const role = localStorage.getItem("userRole");
      setIsAuthenticated(!!token);
      setUserRole(role || "");
    };
  
    window.addEventListener("storage", handleStorageChange);
  
    return () => {
      window.removeEventListener("storage", handleStorageChange);
    };
  }, []);
  

  // Handle logout
  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('userRole');
    localStorage.removeItem('email');
    localStorage.removeItem('uid');
    setIsAuthenticated(false);
    setUserRole("");
    navigate("/signup");
  };

  return (
    <nav className="navbar">
      <div className="navbar-left">
        <Link to="/" className="logo-button">
          <img src={logo} alt="Career Crafter Logo" className="logo" />
        </Link>
        <div className="navbar-links">
          <Link to="/" className="navbar-button">
            <Home className="icon" /> Home
          </Link>
          <Link to="/jobs" className="navbar-button">
            <Briefcase className="icon" /> Jobs
          </Link>
          <Link to="/about" className="navbar-button">
            About Us
          </Link>
        </div>
      </div>
      <div className="navbar-right">
        {isAuthenticated ? (
          <>
            <Link
              to={
                userRole === "JOBSEEKER"
                  ? "/jobdash"
                  : userRole === "EMPLOYER"
                  ? "/empdash"
                  : "/admdash"
              }
              className="navbar-action"
            >
              Dashboard
            </Link>
            <button onClick={handleLogout} className="navbar-action">
              Logout
            </button>
          </>
        ) : (
          <>
            <Link to="/signup" className="navbar-action">
              <LogIn className="icon" /> SignUp
            </Link>
            <Link to="/post-job" className="navbar-action">
              Post a Job
            </Link>
          </>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
