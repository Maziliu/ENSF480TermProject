export const fetchMovieById = async (id) => {
  const response = await fetch(`http://localhost:8080/movies/${id}`);
  if (!response.ok) throw new Error('Failed to fetch movie');
  return response.json();
};

export const fetchShowtimes = async () => {
  const response = await fetch('http://localhost:8080/movies/showtimes');
  if (!response.ok) throw new Error('Failed to fetch showtimes');
  return response.json();
};

export const fetchTheatres = async () => {
  const response = await fetch('http://localhost:8080/browse/theatres');
  if (!response.ok) throw new Error('Failed to fetch theatres');
  return response.json();
};

export const fetchSeatData = async (showtimeId) => {
  const response = await fetch(`http://localhost:8080/seats/${showtimeId}`, {
    method: 'GET',
    headers: { 'Content-Type': 'application/json' },
  });
  if (!response.ok) {
    throw new Error('Failed to fetch seat data');
  }
  return await response.json();
};
