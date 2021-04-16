var vm = new Vue({
    el:'#meundiv',
    data:{
        meunlist:[],
        pid:1,
        entity:{}
    },
    methods:{
        getMeunListByPid:function (pid) {
            this.pid = pid;
            var _this = this;
            axios.get('../meun/getMeunListByPid.do?pid='+pid).then(function (response) {
                _this.meunlist = response.data;
            });
        },
        toAddMeun:function () {
            document.getElementById("meunupdatediv").style.display="block";
        },
        saveMeun:function () {
            this.entity.pid = this.pid;
            var _this = this;
            axios.post("../meun/saveMeun.do",_this.entity).then(function (response) {
                if (response.data.flag){
                    _this.getMeunListByPid(_this.pid);
                    document.getElementById("meunupdatediv").style.display="none";
                }else {
                    alert(response.data.msg);
                }
            })
        },
        deleteMeunById:function (id) {
            var _this = this;
            axios.get("../meun/deleteMeunById.do?id="+id).then(function (response) {
                if (response.data.flag){
                    _this.getMeunListByPid(_this.pid);

                } else {
                    alert(response.data.msg);
                }
            });
        }
    }
});
vm.getMeunListByPid(1);