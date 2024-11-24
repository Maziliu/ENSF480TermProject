import { useState, useEffect } from 'react';

export const useFetchData = (fetchFunction, dependencies = []) => {
  const [data, setData] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    let isMounted = true;
    fetchFunction()
      .then((response) => isMounted && setData(response))
      .catch((error) => isMounted && setError(error));
    return () => (isMounted = false);
  }, dependencies);

  return { data, error };
};
