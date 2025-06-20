import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
    // 解决多次点击左侧菜单报错问题
    const VueRouterPush = VueRouter.prototype.push
    VueRouter.prototype.push = function push (to) {
    return VueRouterPush.call(this, to).catch(err => err)
    }
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
import beifen from '@/views/modules/databaseBackup/beifen'
import huanyuan from '@/views/modules/databaseBackup/huanyuan'

     import users from '@/views/modules/users/list'
    import dictionary from '@/views/modules/dictionary/list'
    import gonggao from '@/views/modules/gonggao/list'
    import news from '@/views/modules/news/list'
    import shangpin from '@/views/modules/shangpin/list'
    import shangpinCollection from '@/views/modules/shangpinCollection/list'
    import shangpinCommentback from '@/views/modules/shangpinCommentback/list'
    import shangpinOrder from '@/views/modules/shangpinOrder/list'
    import suguan from '@/views/modules/suguan/list'
    import sushe from '@/views/modules/sushe/list'
    import susheTousu from '@/views/modules/susheTousu/list'
    import susheTuisu from '@/views/modules/susheTuisu/list'
    import susheYuyue from '@/views/modules/susheYuyue/list'
    import sushexuesheng from '@/views/modules/sushexuesheng/list'
    import xuesheng from '@/views/modules/xuesheng/list'
    import xueshengKaoqin from '@/views/modules/xueshengKaoqin/list'
    import xueshengKaoqinList from '@/views/modules/xueshengKaoqinList/list'
    import config from '@/views/modules/config/list'
    import dictionaryDanyuan from '@/views/modules/dictionaryDanyuan/list'
    import dictionaryGonggao from '@/views/modules/dictionaryGonggao/list'
    import dictionaryLouceng from '@/views/modules/dictionaryLouceng/list'
    import dictionaryNews from '@/views/modules/dictionaryNews/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryShangpin from '@/views/modules/dictionaryShangpin/list'
    import dictionaryShangpinCollection from '@/views/modules/dictionaryShangpinCollection/list'
    import dictionaryShangpinOrder from '@/views/modules/dictionaryShangpinOrder/list'
    import dictionaryShangpinOrderPayment from '@/views/modules/dictionaryShangpinOrderPayment/list'
    import dictionarySushe from '@/views/modules/dictionarySushe/list'
    import dictionarySusheTousu from '@/views/modules/dictionarySusheTousu/list'
    import dictionarySusheTousuZhuangtai from '@/views/modules/dictionarySusheTousuZhuangtai/list'
    import dictionarySusheTuisuYesno from '@/views/modules/dictionarySusheTuisuYesno/list'
    import dictionarySusheYuyueYesno from '@/views/modules/dictionarySusheYuyueYesno/list'
    import dictionaryXueshengKaoqin from '@/views/modules/dictionaryXueshengKaoqin/list'
    import dictionaryXueshengKaoqinList from '@/views/modules/dictionaryXueshengKaoqinList/list'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }, {
        path: '/huanyuan',
        name: '数据还原',
        component: huanyuan
    }, {
        path: '/beifen',
        name: '数据备份',
        component: beifen
    }, {
        path: '/users',
        name: '管理信息',
        component: users
    }
    ,{
        path: '/dictionaryDanyuan',
        name: '楼宇',
        component: dictionaryDanyuan
    }
    ,{
        path: '/dictionaryGonggao',
        name: '宿舍公告类型',
        component: dictionaryGonggao
    }
    ,{
        path: '/dictionaryLouceng',
        name: '楼层',
        component: dictionaryLouceng
    }
    ,{
        path: '/dictionaryNews',
        name: '校园资讯类型',
        component: dictionaryNews
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryShangpin',
        name: '商品类型',
        component: dictionaryShangpin
    }
    ,{
        path: '/dictionaryShangpinCollection',
        name: '收藏表类型',
        component: dictionaryShangpinCollection
    }
    ,{
        path: '/dictionaryShangpinOrder',
        name: '订单类型',
        component: dictionaryShangpinOrder
    }
    ,{
        path: '/dictionaryShangpinOrderPayment',
        name: '订单支付类型',
        component: dictionaryShangpinOrderPayment
    }
    ,{
        path: '/dictionarySushe',
        name: '宿舍类型',
        component: dictionarySushe
    }
    ,{
        path: '/dictionarySusheTousu',
        name: '投诉类型',
        component: dictionarySusheTousu
    }
    ,{
        path: '/dictionarySusheTousuZhuangtai',
        name: '投诉状态',
        component: dictionarySusheTousuZhuangtai
    }
    ,{
        path: '/dictionarySusheTuisuYesno',
        name: '申请状态',
        component: dictionarySusheTuisuYesno
    }
    ,{
        path: '/dictionarySusheYuyueYesno',
        name: '申请状态',
        component: dictionarySusheYuyueYesno
    }
    ,{
        path: '/dictionaryXueshengKaoqin',
        name: '学生考勤类型',
        component: dictionaryXueshengKaoqin
    }
    ,{
        path: '/dictionaryXueshengKaoqinList',
        name: '打卡状态',
        component: dictionaryXueshengKaoqinList
    }
    ,{
        path: '/config',
        name: '轮播图',
        component: config
    }


    ,{
        path: '/dictionary',
        name: '字典',
        component: dictionary
      }
    ,{
        path: '/gonggao',
        name: '宿舍公告',
        component: gonggao
      }
    ,{
        path: '/news',
        name: '校园资讯信息',
        component: news
      }
    ,{
        path: '/shangpin',
        name: '二手商品',
        component: shangpin
      }
    ,{
        path: '/shangpinCollection',
        name: '二手商品收藏',
        component: shangpinCollection
      }
    ,{
        path: '/shangpinCommentback',
        name: '二手商品评价',
        component: shangpinCommentback
      }
    ,{
        path: '/shangpinOrder',
        name: '二手商品订单',
        component: shangpinOrder
      }
    ,{
        path: '/suguan',
        name: '宿管',
        component: suguan
      }
    ,{
        path: '/sushe',
        name: '宿舍',
        component: sushe
      }
    ,{
        path: '/susheTousu',
        name: '宿舍投诉',
        component: susheTousu
      }
    ,{
        path: '/susheTuisu',
        name: '退宿申请',
        component: susheTuisu
      }
    ,{
        path: '/susheYuyue',
        name: '入住申请',
        component: susheYuyue
      }
    ,{
        path: '/sushexuesheng',
        name: '宿舍学生',
        component: sushexuesheng
      }
    ,{
        path: '/xuesheng',
        name: '学生',
        component: xuesheng
      }
    ,{
        path: '/xueshengKaoqin',
        name: '学生考勤',
        component: xueshengKaoqin
      }
    ,{
        path: '/xueshengKaoqinList',
        name: '学生考勤详情',
        component: xueshengKaoqinList
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
