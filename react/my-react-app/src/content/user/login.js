import {Component} from 'react';

import UrlUtils from '../../common/url';
import ApiUtils from '../../common/api';

import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

class Login extends Component{ 
    
    

    login = () => {
        let loginForm = document.querySelector("#loginForm");
        let data = {
            id : loginForm.id.value,
            password : loginForm.password.value
        }

        ApiUtils.post(UrlUtils.LOGIN_URL, data)
        .then(res => {
            alert(res.data.msg);

            if(res.data.sucess === "N"){
                return;
            }else{
                window.location.href = UrlUtils.HOME;
            }
        });
    }

    compare = () => {
        if(!window.Kakao.isInitialized()){
            window.Kakao.init('fe27ad8287c7132d965ea92cab8bf86a');
        }

        window.Kakao.Link.sendCustom({
            templateId : 122495,
            templateArgs : {
                
            }
        });
    }

    render(){
        return (
            <div className="row justify-content-center custom-width-rate-100">
                <div className="custom-width-const-300">
                    <Form className="" id="loginForm">            
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>ID</Form.Label>
                            <Form.Control type="textarea" placeholder="ID" name="id" />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                            <Form.Label>PASSWORD</Form.Label>
                            <Form.Control type="password" placeholder="Password" name="password" />
                        </Form.Group>

                        <div className="row justify-content-center custom-width-rate-100 py-4">
                            <div className="custom-width-const-100">
                                <Button onClick={this.login} className="custom-width-rate-100" variant="outline-secondary">login</Button>
                            </div>
                            <div className="custom-width-const-100">
                                <Button onClick={this.compare} className="custom-width-rate-100" variant="outline-secondary">kakao</Button>
                            </div>
                        </div>
                    </Form>
                </div>
            </div>
        )
    }
}

export default Login;
