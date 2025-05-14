import { Route, Routes } from "react-router-dom";
import Main from './main.js';
import Login from './user/login.js';
import Join from './user/join.js';
import Dotji from './game/dotji/dotji.js';
import Block from './game/block/block.js';

export default function ContentRouter(){
    return(
        <section className="contents d-flex">
            <Routes>
                <Route path="/" element={<Main />} />
                <Route path="/game/dotji" element={<Dotji />} />
                <Route path="/game/block" element={<Block />} />
                <Route path="/user/login" element={<Login />} />
                <Route path="/user/join" element={<Join />} />
            </Routes>
        </section>
    )
};