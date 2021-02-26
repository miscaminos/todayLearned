//import vue
import Vue from 'vue'

//import router
import VueRouter from 'vue-router'

//import bootstrap
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

//import vue components
import App from './App.vue'
import Home from '@/views/Home.vue'
import About from '@/views/About.vue'
import Contact from '@/views/Contact.vue'
import Portfolio from '@/views/Portfolio.vue'
import Resume from '@/views/Resume.vue'
import Hobby from '@/views/Hobby.vue'

Vue.config.productionTip = false

//router사용
Vue.use(VueRouter);
//bootstrap사용
Vue.use(BootstrapVue)

//router 생성
//페이지 이동 설정
const router = new VueRouter({
  //routes collection은 경로(path)와 component로 이루어짐.
  //이 collection의 순서대로 router가 경로를 matching해서 component를 출력한다
  routes: [
    {
      path:'/',
      component:Home
    },
    {
      path:'/about',
      component:About
    },
    {
      path:'/contact',
      component:Contact
    },
    {
      path:'/portfolio',
      component:Portfolio
    },
    {
      path:'/resume',
      component:Resume
    },
    {
      path:'/hobby',
      component:Hobby
    }
  ]
});

//vue object 생성
new Vue({
  router:router,
  render:h=>h(App)
}).$mount('#app')