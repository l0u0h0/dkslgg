const ErrorComponent = ({ error, resetErrorBoundary }) => {
  if (error == undefined) {
    return <div>알 수 없는 오류 발생</div>;
  }
  return (
    <div>
      {error.message}
      <img src={`https://http.cat/${error.status}`} alt="Miyaong" />
      <button onClick={resetErrorBoundary}>Let me doing Again~</button>
    </div>
  );
};

export default ErrorComponent;
