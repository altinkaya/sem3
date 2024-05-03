import React, { useState, useEffect } from "react";

const Timer = () => {
  const [minutes, setMinutes] = useState(0);
  const [seconds, setSeconds] = useState(0);
  const [isActive, setIsActive] = useState(false);
  const [isPaused, setIsPaused] = useState(false);
  const [shortcutTimer, setShortcutTimer] = useState(0);

  useEffect(() => {
    let intervalId;

    if (isActive && !isPaused) {
      intervalId = setInterval(() => {
        if (seconds === 0) {
          if (minutes === 0) {
            clearInterval(intervalId);
            setIsActive(false);
          } else {
            setMinutes((prevMinutes) => prevMinutes - 1);
            setSeconds(59);
          }
        } else {
          setSeconds((prevSeconds) => prevSeconds - 1);
        }
      }, 1000);
    } else if (!isActive) {
      setMinutes(0);
      setSeconds(0);
    }

    return () => clearInterval(intervalId);
  }, [isActive, isPaused, minutes, seconds]);

  const startTimer = () => {
    setIsActive(true);
    setIsPaused(false);
  };

  const pauseTimer = () => {
    setIsPaused(true);
  };

  const resetTimer = () => {
    setIsActive(false);
    setIsPaused(false);
    setMinutes(0);
    setSeconds(0);
  };

  const handleShortcutTimer = (timeInMinutes) => {
    setShortcutTimer(timeInMinutes);
    setMinutes(timeInMinutes);
    setIsActive(true);
    setIsPaused(false);
  };

  return (
    <div>
      <div>
        <input
          type="number"
          placeholder="Minutes"
          value={minutes}
          onChange={(e) => setMinutes(parseInt(e.target.value))}
        />
        <input
          type="number"
          placeholder="Seconds"
          value={seconds}
          onChange={(e) => setSeconds(parseInt(e.target.value))}
        />
        <button onClick={startTimer} disabled={isActive}>
          Start
        </button>
        <button onClick={pauseTimer} disabled={!isActive || isPaused}>
          Pause
        </button>
        <button onClick={resetTimer}>Reset</button>
      </div>
      <div>
        <button onClick={() => handleShortcutTimer(5)}>5 min</button>
        <button onClick={() => handleShortcutTimer(10)}>10 min</button>
        <button onClick={() => handleShortcutTimer(15)}>15 min</button>
      </div>
      <div>Time Remaining: {`${minutes}:${seconds < 10 ? "0" : ""}${seconds}`}</div>
    </div>
  );
};

export default Timer;
