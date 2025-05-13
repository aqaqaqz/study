import axios from 'axios';
import Api from '../common/api';

export default function Main(){
  let myList = [];

  axios.get(Api.makeGetUrl(Api.TEST_URL, {"osCd":"02"}), {
    header:{
      'Accept':'application/json',
      'Content-Type':'application/json;charset=UTP-8',
    }
  })
  .then(res => {
    let keys = Object.keys(res.data);
    for(let i=0;i<keys.length;i++){
      if(typeof keys[i] != 'string') continue;
      myList.push(keys[i] + " : " + res.data[keys[i]]);
    }
    console.log(myList);
  });

  return (
    <section className="content col-12">
      <ul>
        {
          myList.map((d, idx) => (
            <li key={idx}>{d}</li>
          ))
        }
      </ul>
    </section>
  );
}
