Vue.http.options.emulateJSON = true;
new Vue({
    el: '#app',
    data: {
        distance: 100,
        list: [],
        currentPage: 0,
        channel:channel,
        channels:["女装","男装","母婴","鞋包","运动户外","饰品","美妆","食品","内衣","数码","居家用品","家电","汽车","文化用品","其它"]
    },
    methods: {
        onInfinite: function () {
            this.currentPage++;
            console.log("请求第" + this.currentPage + "页");
            this.$http.post('/recommend/query',
                {
                    pageNumber: this.currentPage,
                    channel:this.channel
                })
                .then(function (response) {
                    if (response.body.success) {
                        this.list = this.list.concat(response.body.data);
                        if (this.currentPage >= response.body.page.pages) {
                            this.$refs.infiniteLoading.$emit('$InfiniteLoading:complete');
                        } else {
                            this.$refs.infiniteLoading.$emit('$InfiniteLoading:loaded');
                        }
                    } else {
                        console.log(response.body.msg);
                        alert('服务异常');
                    }
                }, function (response) {
                    alert('服务异常');
                });
        }
    }
});