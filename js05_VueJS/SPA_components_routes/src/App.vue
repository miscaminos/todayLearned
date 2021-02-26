<template>
 <div id="app">
   <!-- 부트스트랩 Navbar UI 표시 -->
   <!-- https://getbootstrap.com/docs/4.1/components/navbar/ -->
   <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
     <div id="navbarNavAltMarkup">
       <div class="navbar-nav">
         <!-- 페이지 이동 설정 -->
         <router-link class="nav-item nav-link" to="/">HOME</router-link>
         <router-link class="nav-item nav-link" to="/about">ABOUT</router-link>
         <router-link class="nav-item nav-link" to="/resume">RESUME</router-link>
         <router-link class="nav-item nav-link" to="/portfolio">PORTFOLIO</router-link>
         <router-link class="nav-item nav-link" to="/contact">CONTACT</router-link>
         <router-link class="nav-item nav-link" to="/hobby">HOBBY</router-link>
       </div>
     </div>
   </nav>
   <transition name="moveInUp">
   <router-view></router-view>
   </transition>
 </div>
</template>
<script>
export default {
 name: "App",
};
</script>
<style>
.moveInUp-enter-active{
  animation: fadeIn 1s ease-in;
}
@keyframes fadeIn{
  0%{
    opacity: 0;
  }
  50%{
    opacity: 0.5;
  }
  100%{
    opacity: 1;
  }
}

</style>