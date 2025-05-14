- javascript : https://ko.javascript.info/

- window
  - install nodejs
  - npx create-react-app my-react-app
  - npm install react-router-dom
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
 
- react 2번 실행 방지
  - <React.StrictMode> 사용 시 문제되는걸 찾기위해 렌더링을 한번 추가됨
  - 해당 모드를 제거하거나 문제가 되는 코드를 render밖으로 빼줘야함
  - 일반적으로 배포 시에는 성능이슈로 해당 모드 사용 안함