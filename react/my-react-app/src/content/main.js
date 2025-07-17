import UrlUtils from '../common/url';
import ApiUtils from '../common/api';
import ListGroup from 'react-bootstrap/ListGroup';
import {componentDidMount, Component} from 'react';

class Main extends Component{
  state = { 
    myList: [] ,
    randomList: []
  };

  componentDidMount(){
    ApiUtils.get(UrlUtils.TEST_URL)
    .then(res => {
      let keys = Object.keys(res.data);
      let liList = [];

      for(let i=0;i<keys.length;i++){
        const li = document.createElement('li');
        liList.push(keys[i]);
      }

      this.setState( () => (
        { myList : liList }
      ));
    });

    let cnt = Math.floor(Math.random()*7)+3;
    let nextArr = [];
    for(let i=0;i<cnt;i++){
      nextArr.push(Math.floor(Math.random()*10000000))
    }

    this.setState( () => (
      { randomList : nextArr }
    ));
  }
  
  render(){
    return (
      <div className="content d-flex col-12">
        <section className="content col-4">
          <ListGroup>
            <ListGroup.Item as="li" active>
            회원수 : {this.state.myList.length}
            </ListGroup.Item>
            {
              this.state.myList.map( (str, idx) => (
                <ListGroup.Item>ID : {str}</ListGroup.Item>
              ))
            }
          </ListGroup>
        </section>
        <section className="ps-1 content col-4">
          <ListGroup>
            <ListGroup.Item as="li" active>
              임시1
            </ListGroup.Item>
            {
              this.state.randomList.map( (num) => (
                <ListGroup.Item>{num}</ListGroup.Item>
              ))
            }
          </ListGroup>
        </section>
        <section className="ps-1 content col-4">
          <ListGroup>
            <ListGroup.Item as="li" active>
              임시2
            </ListGroup.Item>
          </ListGroup>
        </section>
        
      </div>
    );
  }
}

export default Main;

