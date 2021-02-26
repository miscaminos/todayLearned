<template>
 <div>
   <!-- 머릿말 표시 -->
   <div class="s-contact-header">{{ header }}</div>
   <!-- 메세지 표시 -->
   <div class="s-contact-message">{{ message }}</div>
   <!-- 입력 폼 표시 -->
   <div class="s-contact-body">
     <!-- bootstrap 입력 폼 UI를 이용해 표시 -->
     <!-- https://getbootstrap.com/docs/4.1/components/forms/ -->
     <form class="container">
       <!-- 아름 입력 -->
       <div class="form-row justify-content-center">
         <div class="form-group w-50">
           <input
             class="form-control"
             v-bind:placeholder="placeholderName"
             v-model="inputName"
           />
         </div>
       </div>
       <!-- 이메일 입력 -->
       <div class="form-row justify-content-center">
         <div class="form-group w-50">
           <input
             class="form-control"
             v-bind:placeholder="placeholderEmail"
             v-model="inputEmail"
           />
         </div>
       </div>
       <!-- 메세지 입력 -->
       <div class="form-row justify-content-center">
         <div class="form-group w-50">
           <textarea
             class="form-control"
             rows="5"
             v-bind:placeholder="placeholderMessage"
             v-model="inputMessage"
           ></textarea>
         </div>
       </div>
       <!-- 제출 버튼 -->
       <!-- v-on을 이용해 이벤트 등록 -->
       <div class="form-row justify-content-center">
         <button
           type="submit"
           class="btn btn-outline-dark"
           v-on:click="onClickSend"
         >
           {{ sendButtonLabel }}
         </button>
       </div>
     </form>
   </div>
 </div>
</template>
<script>
export default {
 data() {
   return {
     header: "CONTACT",
     message: "메세지를 남겨주세요",
     placeholderName: "이름",
     placeholderEmail: "이메일",
     placeholderMessage: "메세지",
     sendButtonLabel: "보내기",
     inputName: "",
     inputEmail: "",
     inputMessage: "",
   };
 },
 methods: {
   onClickSend: function () {
     alert("메세지를 보냈습니다");
   },
 },
};
</script>
<style>
.s-contact-header {
 position: relative;
 padding: 0.25em 0;
 font-size: 50px;
 text-align: center;
}
.s-contact-header:after {
 content: "";
 display: block;
 height: 4px;
 background: -webkit-linear-gradient(to right, rgb(230, 90, 90), transparent);
 background: linear-gradient(to right, rgb(230, 90, 90), transparent);
}
.s-contact-message {
 font-size: 15px;
 text-align: center;
}
.s-contact-body {
 margin-top: 10px;
 margin-left: 10px;
 margin-right: 10px;
}
</style>