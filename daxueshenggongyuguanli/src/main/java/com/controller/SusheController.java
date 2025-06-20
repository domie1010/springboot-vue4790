
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 宿舍
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/sushe")
public class SusheController {
    private static final Logger logger = LoggerFactory.getLogger(SusheController.class);

    private static final String TABLE_NAME = "sushe";

    @Autowired
    private SusheService susheService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//宿舍公告
    @Autowired
    private NewsService newsService;//校园资讯信息
    @Autowired
    private ShangpinService shangpinService;//二手商品
    @Autowired
    private ShangpinCollectionService shangpinCollectionService;//二手商品收藏
    @Autowired
    private ShangpinCommentbackService shangpinCommentbackService;//二手商品评价
    @Autowired
    private ShangpinOrderService shangpinOrderService;//二手商品订单
    @Autowired
    private SuguanService suguanService;//宿管
    @Autowired
    private SusheTousuService susheTousuService;//宿舍投诉
    @Autowired
    private SusheTuisuService susheTuisuService;//退宿申请
    @Autowired
    private SusheYuyueService susheYuyueService;//入住申请
    @Autowired
    private SushexueshengService sushexueshengService;//宿舍学生
    @Autowired
    private XueshengService xueshengService;//学生
    @Autowired
    private XueshengKaoqinService xueshengKaoqinService;//学生考勤
    @Autowired
    private XueshengKaoqinListService xueshengKaoqinListService;//学生考勤详情
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("学生".equals(role))
            params.put("xueshengId",request.getSession().getAttribute("userId"));
        else if("宿管".equals(role))
            params.put("suguanId",request.getSession().getAttribute("userId"));
        params.put("susheDeleteStart",1);params.put("susheDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = susheService.queryPage(params);

        //字典表数据转换
        List<SusheView> list =(List<SusheView>)page.getList();
        for(SusheView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SusheEntity sushe = susheService.selectById(id);
        if(sushe !=null){
            //entity转view
            SusheView view = new SusheView();
            BeanUtils.copyProperties( sushe , view );//把实体数据重构到view中
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody SusheEntity sushe, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,sushe:{}",this.getClass().getName(),sushe.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<SusheEntity> queryWrapper = new EntityWrapper<SusheEntity>()
            .eq("sushe_name", sushe.getSusheName())
            .eq("sushe_address", sushe.getSusheAddress())
            .eq("louceng_types", sushe.getLoucengTypes())
            .eq("danyuan_types", sushe.getDanyuanTypes())
            .eq("sushe_types", sushe.getSusheTypes())
            .eq("sushe_delete", sushe.getSusheDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SusheEntity susheEntity = susheService.selectOne(queryWrapper);
        if(susheEntity==null){


            sushe.setSusheClicknum(1);
            sushe.setSusheDelete(1);
            sushe.setInsertTime(new Date());
            sushe.setCreateTime(new Date());
            susheService.insert(sushe);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody SusheEntity sushe, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,sushe:{}",this.getClass().getName(),sushe.toString());
        SusheEntity oldSusheEntity = susheService.selectById(sushe.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(sushe.getSushePhoto()) || "null".equals(sushe.getSushePhoto())){
                sushe.setSushePhoto(null);
        }

            susheService.updateById(sushe);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<SusheEntity> oldSusheList =susheService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<SusheEntity> list = new ArrayList<>();
        for(Integer id:ids){
            SusheEntity susheEntity = new SusheEntity();
            susheEntity.setId(id);
            susheEntity.setSusheDelete(2);
            list.add(susheEntity);
        }
        if(list != null && list.size() >0){
            susheService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer xueshengId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<SusheEntity> susheList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            SusheEntity susheEntity = new SusheEntity();
//                            susheEntity.setSusheName(data.get(0));                    //宿舍名称 要改的
//                            susheEntity.setSusheUuidNumber(data.get(0));                    //宿舍编号 要改的
//                            susheEntity.setSushePhoto("");//详情和图片
//                            susheEntity.setSusheAddress(data.get(0));                    //宿舍位置 要改的
//                            susheEntity.setLoucengTypes(Integer.valueOf(data.get(0)));   //楼层 要改的
//                            susheEntity.setDanyuanTypes(Integer.valueOf(data.get(0)));   //楼宇 要改的
//                            susheEntity.setSusheTypes(Integer.valueOf(data.get(0)));   //宿舍类型 要改的
//                            susheEntity.setSusheClicknum(Integer.valueOf(data.get(0)));   //宿舍热度 要改的
//                            susheEntity.setSusheContent("");//详情和图片
//                            susheEntity.setSusheDelete(1);//逻辑删除字段
//                            susheEntity.setInsertTime(date);//时间
//                            susheEntity.setCreateTime(date);//时间
                            susheList.add(susheEntity);


                            //把要查询是否重复的字段放入map中
                                //宿舍编号
                                if(seachFields.containsKey("susheUuidNumber")){
                                    List<String> susheUuidNumber = seachFields.get("susheUuidNumber");
                                    susheUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> susheUuidNumber = new ArrayList<>();
                                    susheUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("susheUuidNumber",susheUuidNumber);
                                }
                        }

                        //查询是否重复
                         //宿舍编号
                        List<SusheEntity> susheEntities_susheUuidNumber = susheService.selectList(new EntityWrapper<SusheEntity>().in("sushe_uuid_number", seachFields.get("susheUuidNumber")).eq("sushe_delete", 1));
                        if(susheEntities_susheUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(SusheEntity s:susheEntities_susheUuidNumber){
                                repeatFields.add(s.getSusheUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [宿舍编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        susheService.insertBatch(susheList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = susheService.queryPage(params);

        //字典表数据转换
        List<SusheView> list =(List<SusheView>)page.getList();
        for(SusheView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SusheEntity sushe = susheService.selectById(id);
            if(sushe !=null){

                //点击数量加1
                sushe.setSusheClicknum(sushe.getSusheClicknum()+1);
                susheService.updateById(sushe);

                //entity转view
                SusheView view = new SusheView();
                BeanUtils.copyProperties( sushe , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody SusheEntity sushe, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,sushe:{}",this.getClass().getName(),sushe.toString());
        Wrapper<SusheEntity> queryWrapper = new EntityWrapper<SusheEntity>()
            .eq("sushe_name", sushe.getSusheName())
            .eq("sushe_uuid_number", sushe.getSusheUuidNumber())
            .eq("sushe_address", sushe.getSusheAddress())
            .eq("louceng_types", sushe.getLoucengTypes())
            .eq("danyuan_types", sushe.getDanyuanTypes())
            .eq("sushe_types", sushe.getSusheTypes())
            .eq("sushe_clicknum", sushe.getSusheClicknum())
            .eq("sushe_delete", sushe.getSusheDelete())
//            .notIn("sushe_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SusheEntity susheEntity = susheService.selectOne(queryWrapper);
        if(susheEntity==null){
            sushe.setSusheClicknum(1);
            sushe.setSusheDelete(1);
            sushe.setInsertTime(new Date());
            sushe.setCreateTime(new Date());
        susheService.insert(sushe);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

