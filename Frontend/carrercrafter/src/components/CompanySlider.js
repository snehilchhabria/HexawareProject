import React from 'react';
import Slider from 'react-slick';

import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import intelLogo from '../images/intel-logo.png';
import hpLogo from '../images/hp-logo.png';
import hexaLogo from '../images/hexa-logo.png';
import googleLogo from '../images/google-logo.png';
import tcsLogo from '../images/tcs-logo.png';
import microLogo from '../images/microsoft-logo.png';
import { AlignCenter } from 'lucide-react';

const CompanySlider = () => {
    const settings = {
      dots: true,
      infinite: true,
      speed: 500,
      slidesToShow: 4,
      slidesToScroll: 1,
      autoplay: true,
      autoplaySpeed: 2000,
      responsive: [
        {
          breakpoint: 1024,
          settings: {
            slidesToShow: 3,
            slidesToScroll: 1,
            infinite: true,
            dots: true,
          },
        },
        {
          breakpoint: 768,
          settings: {
            slidesToShow: 2,
            slidesToScroll: 1,
            initialSlide: 2,
          },
        },
        {
          breakpoint: 480,
          settings: {
            slidesToShow: 1,
            slidesToScroll: 1,
          },
        },
      ],
    };
  
    const companies = [
      { name: 'Intel', logo: intelLogo, slogan: 'Empowering the Future' },
      { name: 'HP', logo: hpLogo, slogan: 'Innovation for All' },
      { name: 'Hexaware Technologies', logo: hexaLogo, slogan: 'Transforming Businesses' },
      { name: 'Tata Consultancy Service', logo: tcsLogo, slogan: 'Building on Belief' },
      { name: 'Google', logo: googleLogo, slogan: 'Organizing the World’s Information' },
      { name: 'Microsoft', logo: microLogo, slogan: 'Empowering Us All' },
    ];
  
    return (
      <div className="company-slider">
        <style>
          {`
            .company-slider {
              text-align: center;
              padding: 20px;
              background-color: #f0f4f8;
            }
  
            .company-slider h2 {
              margin-bottom: 20px;
              font-size: 1.5em;
            }
  
            .company-slide {
              display: flex;
              flex-direction: column;
              align-items: center;
              text-align: center;
            }
  
            .logo-container {
              display: flex;
              align-items: center;
              justify-content: center;
              width: 365px;
              height: 120px;
              padding: 5px;
              margin-bottom: 10px;
            }
  
            .company-logo {
              max-width: 100%;
              max-height: 100%;
              object-fit: contain;
            }
  
            .company-name {
              font-weight: bold;
              margin: 5px 0;
            }
  
            .company-slogan {
              font-size: 0.9em;
              color: #555;
              margin-top: 5px;
            }
          `}
        </style>
        
        <h2>Companies that post jobs in this application</h2>
        <Slider {...settings}>
          {companies.map((company, index) => (
            <div key={index} className="company-slide">
              <div className="logo-container">
                <img src={company.logo} alt={company.name} className="company-logo"/>
              </div>
              <p className="company-name">{company.name}</p>
              <p className="company-slogan">{company.slogan}</p>
            </div>
          ))}
        </Slider>
      </div>
    );
  };

  export default CompanySlider;