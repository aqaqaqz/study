import { Route, Routes } from "react-router-dom";
import Main from './main.js';
import Test from './view/test.js';

export default function ContentRouter(){
    return(
        <section className="contents d-flex">
            <Routes>
                <Route path="/" element={<Main />} />
                <Route path="/test" element={<Test />} />
            </Routes>
        </section>
    )
};