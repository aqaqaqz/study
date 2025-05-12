- window
  - install nodejs
  - npx create-react-app my-react-app
  - npm start
  - http://localhost:3000/

- index.js
~~~
import React from 'react';
import ReactDOM from 'react-dom/client';
import reportWebVitals from './reportWebVitals';
import MyApp from './App';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <MyApp />
  </React.StrictMode>
);
~~~

- App.js
~~~
function MyButton() {
  return (
    <button>I'm a button</button>
  );
}

export default function MyApp(){
  return (
    <div>
      <h1>Hello world</h1>
      <MyButton />
    </div>
  );
}

reportWebVitals();
~~~
- test
  - npm run test
~~~
test('renders learn react link', () => {
  render(<App />);
  const linkElement = screen.getByText(/hello world/i);
  expect(linkElement).toBeInTheDocument();
});
~~~

- bootstrap
    - npm install react-bootstrap bootstrap
    - index.js (import 'bootstrap/dist/css/bootstrap.min.css';)
~~~
<Button variant="primary">Primary Button</Button>
<Button variant="secondary">Secondary Button</Button>
~~~