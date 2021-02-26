<template>
 <div>
   <!-- 머릿말 표시 -->
   <div class="s-about-header">{{ header }}</div>
   <!-- 메세지 표시 -->
   <div class="s-about-message">{{ message }}</div>
   <!-- 자기소개 내용 표시 -->
   <div class="s-about-body">
     <!-- Bootstrap 카드UI -->
     <!-- https://getbootstrap.com/docs/4.1/components/card/ -->
     <div class="card-columns justify-content-center">
       <!-- v-for를 이용해 데이터 수만큼 표시 -->
       <div v-for="item in cardItems" :key="item.header">
         <div class="card border-dark mb-3">
           <div class="card-header">{{ item.header }}</div>
           <div class="card-body text-dark">
             <h5 class="card-title">{{ item.title }}</h5>
             <p class="card-text">{{ item.message }}</p>
           </div>
         </div>
       </div>
     </div>
   </div>
 </div>
</template>
<script>
export default {
 data() {
   return {
     header: "Who am I?",
     message: "자기소개 입니다",
     cardItems: [
       { header: "나는 누구인가?", title: "이소정", message: "재능이 풍부한" },
       {
         header: "나의 나이는?",
         title: "30...",
         message: "아직도 할일이 많은",
       },
       {
         header: "나의 이메일은?",
         title: "엔지니어",
         message: "aaa@gmail.com",
       },
       { header: "나의 별명은?", title: "번쩍", message: "동해번쩍 서해번쩍" },
       { header: "나의 연락처?", title: "비밀", message: "011-xxxx-xxxx" },
       {
         header: "나의 현재 상태는?",
         title: "구직중",
         message: "직업을 찾습니다",
       },
     ],
   };
 },
};
</script>
<style>
.s-about-header {
 position: relative;
 padding: 0.25em 0;
 font-size: 50px;
 text-align: center;
}
.s-about-header:after {
 content: "";
 display: block;
 height: 4px;
 background: -webkit-linear-gradient(to right, rgb(230, 90, 90), transparent);
 background: linear-gradient(to right, rgb(230, 90, 90), transparent);
}
.s-about-message {
 font-size: 15px;
 text-align: center;
}
.s-about-body {
 margin-top: 10px;
 margin-left: 10px;
 margin-right: 10px;
}
</style>