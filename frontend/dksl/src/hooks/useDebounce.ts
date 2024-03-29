import { useCallback, useRef, useEffect } from 'react';

const useDebounce = <T extends (...args: any[]) => any>(
  callback: T,
  delay: number
) => {
  const callbackRef = useRef<T>(callback);

  const debouncedCallback = useCallback(() => {
    console.log(callbackRef.current);
    callbackRef.current();
  }, [callbackRef]);

  useEffect(() => {
    callbackRef.current = callback;
  }, [callback]);

  useEffect(() => {
    const handler = setTimeout(() => {
      debouncedCallback();
    }, delay);
    console.log(delay);
    return () => {
      clearTimeout(handler);
    };
  }, [debouncedCallback, delay]);

  return debouncedCallback;
};

export default useDebounce;
