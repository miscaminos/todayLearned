<template>
 <div>
   <!-- 페이지 중앙에 백그라운드 이미지를 표시 -->
   <img class="s-home-background" v-bind:src="imageBackground" />
   <!-- 페이지 중앙에 표시 -->
   <div class="s-home-body">
     <!-- 직업명 -->
     <br>
     <br>
     <br>
     <br>
     <br>
     <br>
     <br>
     <br>
     <div class="s-home-job-title">{{ jobTitle }}</div>
     <!-- 이름 -->
     <div class="s-home-name">{{ name }}</div>
     <!-- 간단한 자기소개 -->
     <div class="s-home-description">{{ description }}</div>
   </div>
 </div>
</template>
<script>
export default {
 data() {
   return {
     imageBackground: require("@/assets/arrow.png"),
     jobTitle: "Web Develper",
     name: "Sojung Lee",
     description: "열정적이고 창의적이며 헌신적인",
   };
 },
};
</script>
<style>
.s-home-background {
 position: absolute;
 top: 50%;
 left: 50%;
 transform: translate(-50%, -50%);
 width: 600px;
 height: 800px;
}
.s-home-body {
 position: absolute;
 top: 50%;
 left: 50%;
 transform: translate(-50%, -50%);
 text-align: center;
 color:whitesmoke;
}
.s-home-job-title {
 font-size: 30px;
 text-align: center;
 vertical-align: bottom;
 color:whitesmoke;
}
.s-home-name {
 font-size: 60px;
 text-align: center;
 vertical-align: bottom;
 color:whitesmoke;
}
.s-home-description {
 font-size: 20px;
 text-align: center;
 vertical-align: bottom;
 color:whitesmoke;
}
</style>