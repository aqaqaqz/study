import Menu from './menu.js';

export default function Header(){
  return (
    <header className="header d-flex">
      <div className="col-2">로고 영역</div>
      <nav className="col-10"><Menu /></nav>
    </header>
  );
}
