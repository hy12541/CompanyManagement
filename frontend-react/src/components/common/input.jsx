import React from "react";
const Input = ({ name, error, label, ...rest }) => {
  return (
    <div className="form-group">
      <label htmlFor={name}>{label}</label>
      <input
        autoFocus
        {...rest}
        className="form-control"
        name={name}
        id={name}
      />
      {error && <div className="alert alert-danger">{error}</div>}
    </div>
  );
};

export default Input;

//...rest: any other attribute in our props object
// value={value}
//         onChange={onChange}
//         type={type}
