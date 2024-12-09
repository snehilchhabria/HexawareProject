import React from 'react';
import { Phone, MapPin, User, Mail } from 'lucide-react';
import '../Css/Footer.css';

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-container">
        {/* Main Footer Content */}
        <div className="footer-grid">
          {/* Company Info */}
          <div className="footer-section">
            <h3 className="footer-heading">Career Crafter</h3>
            <p className="footer-text">
              Connecting talented professionals with outstanding opportunities.
            </p>
          </div>

          {/* Quick Links */}
          <div className="footer-section">
            <h3 className="footer-heading">Quick Links</h3>
            <ul className="footer-links">
              <li>
                <a href="/" className="footer-link">Home</a>
              </li>
              <li>
                <a href="/jobs" className="footer-link">Jobs</a>
              </li>
              <li>
                <a href="/about" className="footer-link">About Us</a>
              </li>
            </ul>
          </div>

          {/* Contact Details */}
          <div className="footer-section">
            <h3 className="footer-heading ">Contact Details</h3>
            <ul className="footer-contact-list">
              <li className="footer-contact-item">
                <Phone size={20} className="footer-icon" />
                <span className="footer-text">+91-1234567890</span>
              </li>
              <li className="footer-contact-item">
                <Mail size={20} className="footer-icon" />
                <span className="footer-text">info@careercrafter.com</span>
              </li>
              <li className="footer-contact-item">
                <MapPin size={20} className="footer-icon" />
                <span className="footer-text">India</span>
              </li>
            </ul>
          </div>

          {/* Company Leadership */}
          <div className="footer-section">
            <h3 className="footer-heading">Company Leadership</h3>
            <div className="footer-leadership">
              {/* First Leader */}
              <div className="footer-leader">
                <User size={20} className="footer-icon" />
                <div>
                  <p className="footer-leader-name">Siddharth</p>
                  <p className="footer-text">Founder & CEO</p>
                  <p className="footer-text">Direct Line: +91-6392096671</p>
                </div>
              </div>

              {/* Second Leader */}
              <div className="footer-leader">
                <User size={20} className="footer-icon" />
                <div>
                  <p className="footer-leader-name">Snehil</p>
                  <p className="footer-text">Founder & CEO</p>
                  <p className="footer-text">Direct Line: +91-9140905387</p>
                </div>
              </div>
            </div>
          </div>
        </div>

        {/* Footer Bottom */}
        <div className="footer-bottom">
          <p className="footer-bottom-text">
            &copy; {new Date().getFullYear()} Career Crafter. All rights reserved.
          </p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
