import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';

export default function Menu(){
  return (
    <Navbar bg="dark" data-bs-theme="dark">
        <Container>
            <Nav className="me-auto">
            <Nav.Link href="/">Home</Nav.Link>
            <NavDropdown title="Game" id="collapsible-nav-dropdown">
                <NavDropdown.Item href="/game/dotji">dotji</NavDropdown.Item>
                <NavDropdown.Item href="/game/block">block</NavDropdown.Item>
            </NavDropdown>
            <NavDropdown title="User" id="collapsible-nav-dropdown">
                <NavDropdown.Item href="/user/login">login</NavDropdown.Item>
                <NavDropdown.Item href="/user/join">join</NavDropdown.Item>
            </NavDropdown>
            </Nav>
        </Container>
    </Navbar>
  );
}