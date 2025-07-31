import React, { useState } from "react";

function Register() {
  const [form, setForm] = useState({ name: "", email: "", password: "" });
  const [errors, setErrors] = useState({});

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const validate = () => {
    const newErrors = {};
    if (form.name.length < 5) newErrors.name = "Name must be at least 5 characters.";
    if (!form.email.includes("@") || !form.email.includes("."))
      newErrors.email = "Email must contain '@' and '.'.";
    if (form.password.length < 8)
      newErrors.password = "Password must be at least 8 characters.";
    return newErrors;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const validationErrors = validate();
    if (Object.keys(validationErrors).length === 0) {
      alert("Form submitted successfully!");
      setForm({ name: "", email: "", password: "" }); // reset
    }
    setErrors(validationErrors);
  };

  return (
    <form onSubmit={handleSubmit} noValidate>
      <h2>Register Form</h2>
      <div>
        <label>Name: </label>
        <input type="text" name="name" value={form.name} onChange={handleChange} />
        {errors.name && <p style={{ color: "red" }}>{errors.name}</p>}
      </div>
      <div>
        <label>Email: </label>
        <input type="text" name="email" value={form.email} onChange={handleChange} />
        {errors.email && <p style={{ color: "red" }}>{errors.email}</p>}
      </div>
      <div>
        <label>Password: </label>
        <input type="password" name="password" value={form.password} onChange={handleChange} />
        {errors.password && <p style={{ color: "red" }}>{errors.password}</p>}
      </div>
      <button type="submit">Register</button>
    </form>
  );
}

export default Register;
