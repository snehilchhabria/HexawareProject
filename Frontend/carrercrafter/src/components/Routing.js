import React from "react";
import { Routes, Route } from "react-router-dom";
import Home from "./Home";
import JobsPage from "./JobsPage";
import AboutPage from "./AboutPage";
import Signup from "./Signup";
import EmployerDashboard from "./Dashboard/EmployerDashboard";
import JobSeekerDashboard from "./Dashboard/JobSeekerDashboard";
import AdminDashboard from "./Dashboard/AdminDashboard";

const Routing = () => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/jobs" element={<JobsPage />} />
      <Route path="/about" element={<AboutPage />} />
      <Route path = "/signup" element={<Signup/>}/>
      <Route path = "/empdash" element={<EmployerDashboard/>}/>
      <Route path = "/jobdash" element={<JobSeekerDashboard/>}/>
      <Route path = "/admdash" element={<AdminDashboard/>}/>
      <Route path = "/post-job" element={<Home/>}/>
    </Routes>
  );
};

export default Routing;
