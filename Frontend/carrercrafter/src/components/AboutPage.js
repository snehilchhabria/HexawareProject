import React, { useState } from 'react';
import '../Css/About.css'

const AboutPage = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    subject: '',
    message: ''
  });
  const [submitStatus, setSubmitStatus] = useState('');

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Form submitted:', formData);
    setSubmitStatus('success');
    setFormData({
      name: '',
      email: '',
      subject: '',
      message: ''
    });
    setTimeout(() => setSubmitStatus(''), 3000);
  };

  return (
    <div className="about-container">
      <div className="about-header">
        <h1>About Career Crafter</h1>
        <p>
          We bridge the gap between talented job seekers and innovative employers,
          creating meaningful connections that drive careers and businesses forward.
        </p>
      </div>

      <div className="about-grid">
        <div className="about-box">
          <h2>Our Mission</h2>
          <p>
            To empower professionals by providing a seamless platform that 
            connects top talent with exceptional job opportunities across 
            diverse industries.
          </p>
        </div>
        <div className="about-box">
          <h2>Our Vision</h2>
          <p>
            To become the most trusted and innovative job portal that 
            transforms the way people find jobs and companies find talent.
          </p>
        </div>
      </div>

      <div className="contact-form-container">
        {submitStatus === 'success' && (
          <div className="success-message">
            Thank you for your message! We'll get back to you soon.
          </div>
        )}

        <form onSubmit={handleSubmit} className="contact-form">
          <div className="form-row">
            <div>
              <label>Your Name</label>
              <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handleChange}
                placeholder="John Doe"
                required
              />
            </div>
            <div>
              <label>Your Email</label>
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                placeholder="john@example.com"
                required
              />
            </div>
          </div>

          <div>
            <label>Subject</label>
            <input
              type="text"
              name="subject"
              value={formData.subject}
              onChange={handleChange}
              placeholder="How can we help you?"
              required
            />
          </div>

          <div>
            <label>Your Message</label>
            <textarea
              name="message"
              value={formData.message}
              onChange={handleChange}
              placeholder="Write your message here..."
              required
            ></textarea>
          </div>

          <button type="submit" className="submit-button">Send Message</button>
        </form>
      </div>

      <div className="contact-info-grid">
        <div className="contact-box">
          <h3>Email Us</h3>
          <p>info@careercrafter.com</p>
        </div>
        <div className="contact-box">
          <h3>Call Us</h3>
          <p>+91-1234567890</p>
        </div>
        <div className="contact-box">
          <h3>Visit Us</h3>
          <p>Uttar Pradesh, India</p>
        </div>
      </div>
    </div>
  );
};

export default AboutPage;
