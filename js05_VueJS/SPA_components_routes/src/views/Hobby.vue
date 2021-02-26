<template>
 <div>
   <!-- 머릿말 표시 -->
   <div class="s-hobby-header">{{ header }}</div>
   <!-- 메세지 표시 -->
   <div class="s-hobby-message">{{ message }}</div>
   <!-- 포트폴리요 내용 표시 -->
   <div class="s-hobby-body">
     <!-- v-for를 이용해 포트폴리오 데이터 수만큼 표시 -->
     <div v-for="item in items" :key="item.title">
       <!-- bootstrap의 경고UI를 이용해 표시 -->
       <!-- https://getbootstrap.com/docs/4.1/components/alerts/ -->
       <div class="alert alert-success" role="alert">
         <div class="media">
           <img class="mr-4" v-bind:src="item.image" />
           <div class="media-body">
             <h5 class="mt-0">{{ item.title }}</h5>
             {{ item.description }}
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
     header: "Hobbies & Interests",
     message: "관심사 및 취미 페이지 입니다",
     items: [
       {
         title: "운동",
         description: "달리기를 좋아합니다. 종종 10K, half-marathon에 참가합니다.",
         image: require("@/assets/running.jpg"),
       },
       {
         title: "그림",
         description: "다양한 소재로 그림 또는 크래프트를 만듭니다.",
         image: require("@/assets/drawing.jpg"),
       },
       {
         title: "등산",
         description: "자연속에서 좋아하는 사람들과 함께 걷는것을 즐깁니다.",
         image: require("@/assets/mt.jpg"),
       },
     ],
   };
 },
};
</script>
<style>
.s-hobby-header {
 position: relative;
 padding: 0.25em 0;
 font-size: 50px;
 text-align: center;
}
.s-hobby-header:after {
 content: "";
 display: block;
 height: 4px;
 background: -webkit-linear-gradient(to right, rgb(230, 90, 90), transparent);
 background: linear-gradient(to right, rgb(230, 90, 90), transparent);
}
.s-hobby-message {
 font-size: 15px;
 text-align: center;
}
.s-hobby-body {
 margin-left: 10px;
 margin-right: 10px;
 margin-top: 10px;
}
</style>