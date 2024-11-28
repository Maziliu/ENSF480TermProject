import React, { useState, useEffect } from 'react';
import { useSelectionContext } from '../contexts/SelectionContext';
import { useNavigate } from 'react-router-dom';
import Carousel from 'react-multi-carousel';
import 'react-multi-carousel/lib/styles.css';
import '../styles/Notification.css';

const CustomLeftArrow = ({ onClick }) => {
  return (
    <button className="custom-arrow left" onClick={onClick}>
      &#9660;
    </button>
  );
};

const CustomRightArrow = ({ onClick }) => {
  return (
    <button className="custom-arrow right" onClick={onClick}>
      &#9660;
    </button>
  );
};

const CustomDot = ({ onMove, index, onClick, active }) => {
  return (
    <li
      className={`custom-dot ${active ? 'active' : 'inactive'}`}
      onClick={() => onClick()}
    >
      <div className={`dot ${active ? 'active-dot' : ''}`} />
    </li>
  );
};

const Notification = ({ movies }) => {
  const { handleSelectMovie } = useSelectionContext();
  const [showPopup, setShowPopup] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    if (movies && movies.length > 0) {
      setShowPopup(true);
    }
  }, [movies]);

  const handleClose = () => {
    setShowPopup(false);
  };

  const responsive = {
    superLarge: {
      breakpoint: { max: 4000, min: 1024 },
      items: 1,
    },
    large: {
      breakpoint: { max: 1024, min: 768 },
      items: 1,
    },
    medium: {
      breakpoint: { max: 768, min: 480 },
      items: 1,
    },
    small: {
      breakpoint: { max: 480, min: 0 },
      items: 1,
    },
  };

  const handleMovieClick = (id, name) => {
    handleSelectMovie(id, name);
    navigate(`/movies/${id}`);
  };

  return (
    <>
      {showPopup && (
        <div className="notification-popup">
            <div className="popup-content">
            <h4>New Releases</h4>
            <button className="close-btn" onClick={handleClose}>
              <span className="close-icon">×</span>
            </button>
            <Carousel
              responsive={responsive}
              autoPlay
              infinite
              arrows={true}
              showDots={true}
              customLeftArrow={<CustomLeftArrow />}
              customRightArrow={<CustomRightArrow />}
              customDot={<CustomDot />}
              className="movie-carousel"
            >
                {movies.map((movie) => (
                <div className="movie-item" key={movie.id}>
                    <div className="movie-poster-placeholder" 
                    onClick={() => handleMovieClick(movie.movieId, movie.movieName)} />
                        <img
                        src={movie.posterUrl}
                        alt={movie.movieName}
                        className="movie-poster"
                        />
                        <div className="movie-details">
                        <h3>{movie.movieName}</h3>
                    </div>
                </div>
              ))}
            </Carousel>
            </div>
        </div>
      )}
    </>
  );
};

export default Notification;
