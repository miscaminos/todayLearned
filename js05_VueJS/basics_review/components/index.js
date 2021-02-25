Vue.component('t-header',{
    template: '<strong>해더 템플릿 입니다.</strong>'
});

Vue.component('t-body',{
    data(){
        return{
            message:'안녕하세요.'
        }
    },
    template: '<p>{{message}}</p>'
});

Vue.component('t-footer',{
    template: '<strong> 풋터 탬플릿 입니다.</strong>'
});
var app = new Vue({
    el: '#app-15',
    // 어플리케이션에서 사용하는 데이터
    data: {
        header: '튜토리얼15 - component 이소정',
        messages: [
            'Vue.component로 독자적인 요소를 정의할 수 있습니다.',
            'component를 작성하면 코드의 중복을 줄여줄 수 있습니다.'
        ]
    }
 });