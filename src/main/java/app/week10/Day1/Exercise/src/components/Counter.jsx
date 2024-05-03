import React, { useState, useEffect } from 'react';

const Counter = (props) => {
  const [count, setCount] = useState(() => {
    const storedCount = localStorage.getItem("count");
    const parsedCount = parseInt(storedCount);
    return !isNaN(parsedCount) ? parsedCount : props.count;
  });

  useEffect(() => {
    localStorage.setItem("count", count);
  }, [count]);

  const incrementCount = () => {
    setCount(count + props.incrementCount);
  };

  const decreaseCount = () => {
    setCount(count - props.decreaseCount);
  };

  return (
    <div>
      <h2>Counter</h2>
      <p>Count: {count}</p>
      <button onClick={incrementCount}>Increment by {props.incrementCount} </button>
      <button onClick={decreaseCount}>Decrement by {props.decreaseCount}</button>
    </div>
  );
};

export default Counter;