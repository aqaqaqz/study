뷰 인스턴스 : https://kr.vuejs.org/v2/guide/instance.html
======
인스턴스의 속성과 만들기
------

- Vue 인스턴스를 인스턴스화 할 때는 옵션 객체를 전달해야 함.(옵션목록 : https://kr.vuejs.org/v2/api/) 
- Vue 컴포넌트는 확장된 Vue 인스턴스 (컴포넌트 시스템 : https://kr.vuejs.org/v2/guide/components.html) 
- 각 Vue 인스턴스는 data 객체에 있는 모든 속성을 프록시처리한다. (프록시 -> 중게 기능이라 생각하면 편함)
- 데이터가 변경되면 화면을 다시 렌더링하지만, 생성 시 존재했던 것들에만 한해서 반응한다.(따라서 미리 추가될 값들은 생성시 초기값 설정)
~~~
    var data = { a : 1 };
    var vm  = new Vuw({
        data : data
    });
    vm.a = 2;
    console.log(data.a); // 2

    data.a = 3;
    console.log(vm.a); // 3

    vm.b = "new attr"; // 이 경우는 추가된 값이기 때문에 화면갱신이 일어나지 않는다.
~~~

 - 만약 Object.freeze()를 사용하면 기존 속성이 변경되는것을 막아버린다.

script
~~~
    var obj = {
        foo: 'bar'
    }
    Object.freeze(obj)

    new Vue({
        el: '#app',
        data: obj
    })
~~~
html
~~~
    <div id="app">
        <p>{{ foo }}</p>
        <!-- obj.foo는 더이상 변하지 않습니다! -->
        <button v-on:click="foo = 'baz'">Change it</button>
    </div>
~~~

인스턴스 라이프사이클
-----
