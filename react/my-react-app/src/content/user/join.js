import {Component} from 'react';

import UrlUtils from '../../common/url';
import ApiUtils from '../../common/api';

import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

class Join extends Component{ 
    join = () => {
        let joinForm = document.querySelector("#joinForm");
        let data = {
            id : joinForm.id.value,
            password : joinForm.password.value,
            name : joinForm.name.value
        }

        ApiUtils.post(UrlUtils.JOIN_URL, data)
        .then(res => {
            alert(res.data.msg);

            if(res.data.sucess === "N"){
                return;
            }else{
                window.location.href = UrlUtils.HOME;
            }
        });
    }

    render(){
        return (
            <div className="row justify-content-center custom-width-rate-100">
                <div className="custom-width-const-300">
                    <Form className="" id="joinForm">      
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>id</Form.Label>
                            <Form.Control type="textarea" placeholder="id" name="id" />
                        </Form.Group>      
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>password</Form.Label>
                            <Form.Control type="password" placeholder="password" name="password" />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                            <Form.Label>name</Form.Label>
                            <Form.Control type="textarea" placeholder="name" name="name" />
                        </Form.Group> 
                        <div className="row justify-content-center custom-width-rate-100 py-4">
                            <div className="custom-width-const-100">
                                <Button onClick={this.join} className="custom-width-rate-100" variant="outline-secondary">join</Button>
                            </div>
                        </div>
                    </Form>
                </div>
            </div>
        )
    }
}

export default Join;
