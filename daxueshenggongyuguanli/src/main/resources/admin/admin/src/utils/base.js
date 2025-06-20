const base = {
    get() {
        return {
            url : "http://localhost:8080/daxueshenggongyuguanli/",
            name: "daxueshenggongyuguanli",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/daxueshenggongyuguanli/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "学生公寓管理系统"
        } 
    }
}
export default base
