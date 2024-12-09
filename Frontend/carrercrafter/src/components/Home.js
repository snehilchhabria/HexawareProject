import React, {  useState } from 'react';
import JobSeekerService from '../services/JobSeekerService.js';
import "../Css/Home.css"
import { Briefcase, Search, UserCheck} from 'lucide-react';

import CompanySlider from './CompanySlider.js';

const Home = () => {

  const [searchTerm, setSearchTerm] = useState('');
  const [searchResults, setSearchResults] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleSearch = async () => {
    if (!searchTerm.trim()) {
      alert('Please enter a search term');
      return;
    }

    setIsLoading(true);
    setError(null);

    try {
      const results = await JobSeekerService.searchJobs(searchTerm);
      setSearchResults(results);
    } catch (err) {
      console.error('Search failed:', err);
      setError('Failed to search jobs. Please try again.');
      setSearchResults([]);
    } finally {
      setIsLoading(false);
    }
  };


  
  return (<>
    <div className="custom-container">
    <div className="bg-blue-50 min-h-screen">
      <div className="container mx-auto px-4 py-16 text-center">
        <h1 className="text-4xl font-bold text-gray-800 mb-6">
          Find Your Dream Job or Perfect Candidate
        </h1>
        <div className="search-container">
          <div className="search-wrapper">
            <input 
              type="text" 
              placeholder="Job title, keywords, or company" 
              className="search-input"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
            <button 
              onClick={handleSearch}
              className="search-button"
              disabled={isLoading}
            >
              <Search className="search-icon" size={20} /> 
              {isLoading ? 'Searching...' : 'Search Jobs'}
            </button>
          </div>
        </div>

        {error && (
          <div className="text-red-500 mt-4">
            {error}
          </div>
        )}

        {searchResults.length > 0 && (
          <div className="search-results mt-8">
            <h2 className="text-2xl font-semibold mb-4">Search Results</h2>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
              {searchResults.map((job) => (
                <div key={job.id} className="bg-white p-4 rounded-lg shadow-md">
                  <h3 className="text-lg font-bold">{job.title}</h3>
                  <p className="text-gray-600">{job.company}</p>
                  <p className="text-gray-500">{job.location}</p>
                </div>
              ))}
            </div>
          </div>)}
        <div className="features-grid">
          <div className="feature-card">
            <Briefcase className="feature-icon blue-icon" size={50} />
            <h3 className="feature-title">For Job Seekers</h3>
            <p className="feature-description">Browse thousands of job opportunities across various industries.</p>
          </div>
          <div className="feature-card">
            <UserCheck className="feature-icon green-icon" size={50} />
            <h3 className="feature-title">For Employers</h3>
            <p className="feature-description">Post jobs and find the most qualified candidates quickly.</p>
          </div>
          <div className="feature-card">
            <Search className="feature-icon purple-icon" size={50} />
            <h3 className="feature-title">Easy Application</h3>
            <p className="feature-description">Simple, user-friendly interface for job search and application.</p>
          </div>
        </div>
      </div>
    </div>
    <div className='container mb-10'><CompanySlider/></div>
    </div>
  </>)
}
export default Home