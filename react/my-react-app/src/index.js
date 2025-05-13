import React from 'react';
import ReactDOM from 'react-dom/client';
import reportWebVitals from './reportWebVitals';
import 'bootstrap/dist/css/bootstrap.min.css';
import './index.css';
import Header from './header/header.js';
import ContentRouter from './content/contentRouter.js';
import Footer from './footer/footer.js';
import { BrowserRouter } from "react-router-dom";

const body = ReactDOM.createRoot(document.getElementById('body'));
body.render(
  <div id="wrap" className="container">
    <Header />
    <BrowserRouter>
      <ContentRouter /> 
    </BrowserRouter>
    <Footer />
  </div>
);

reportWebVitals();
