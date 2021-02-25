//라우팅 컴포넌트를 작성
const Home = {
    template: `<div>
        <h1>Home</h1>
        <div>Home페이지 입니다</div>
    </div>`
}
const About = {
    template: `<div>
        <h1>About</h1>
        <div>About페이지 입니다</div>
    </div>`
}

const Thankyou = {
    template: `<div>
        <h1>감사합니다!</h1>
        <div>고생많으셨습니다!</div>

    </div>`
}

//위에서 작성된 라우터 컴포넌트를 라우터에 설정
const routes = [
    {path: '/home', component: Home},
    {path: '/about',component: About},
    {path: '/thankyou',component: Thankyou}
]

//라우터 인스턴스를 생성하고 라우터를 설정
const router = new VueRouter({
    routes: routes
})

//뷰 인스턴스를 생성하고 마운트 함
const app = new Vue({
    router:router,
    //아래el 표기대신 Vue인스턴스 생성부분 끝에 .$mount('#app-16') 작성하면 같은 뜻임
    //el: '#app-16',
    data:{

        header:'튜토리얼16: Router',
        messages:[
            'Vue는 SPA(Single Page Application)를 구축하는 것이 가능합니다.',
            'Application을 작성하면서 페이지 이동이 필요합니다.',
            'Application내의 페이지 이동을 라우팅이라고 합니다.',
            '페이지 이동을 만들기 위해서는 Vue Router를 사용합니다',
            '기본적으로 HTML에서는 <a>태그를 사용하고, 페이지 전체의 DOM을 갱신합니다.',
            'Vue Router에서는 router-link를 사용하고, 페이지 일부분의 DOM만 갱신합니다.'
        ]
    }
}).$mount('#app-16')