import React from 'react';
import './App.css';

function App() {
  const courses = [
    { name: 'Angular', date: '4/5/2021' },
    { name: 'React', date: '6/3/20201' },
  ];

  const books = [
    { title: 'Master React', price: 670 },
    { title: 'Deep Dive into Angular 11', price: 800 },
    { title: 'Mongo Essentials', price: 450 },
  ];

  const blogs = [
    {
      heading: 'React Learning',
      author: 'Stephen Biz',
      content: 'Welcome to learning React!',
    },
    {
      heading: 'Installation',
      author: 'Schewzdenier',
      content: 'You can install React from npm.',
    },
  ];

  return (
    <div className="container">
      {/* Course Details */}
      <div className="column">
        <h2>Course Details</h2>
        {courses.map((course, index) => (
          <div key={index}>
            <strong>{course.name}</strong>
            <p>{course.date}</p>
          </div>
        ))}
      </div>

      {/* Book Details */}
      <div className="column bordered">
        <h2>Book Details</h2>
        {books.map((book, index) => (
          <div key={index}>
            <strong>{book.title}</strong>
            <p>{book.price}</p>
          </div>
        ))}
      </div>

      {/* Blog Details */}
      <div className="column bordered">
        <h2>Blog Details</h2>
        {blogs.map((blog, index) => (
          <div key={index}>
            <strong>{blog.heading}</strong>
            <p><em>{blog.author}</em></p>
            <p>{blog.content}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;
