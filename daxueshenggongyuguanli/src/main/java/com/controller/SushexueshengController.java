
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
 * 宿舍学生
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/sushexuesheng")
public class SushexueshengController {
    private static final Logger logger = LoggerFactory.getLogger(SushexueshengController.class);

    private static final String TABLE_NAME = "sushexuesheng";

    @Autowired
    private SushexueshengService sushexueshengService;


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
    private SusheService susheService;//宿舍
    @Autowired
    private SusheTousuService susheTousuService;//宿舍投诉
    @Autowired
    private SusheTuisuService susheTuisuService;//退宿申请
    @Autowired
    private SusheYuyueService susheYuyueService;//入住申请
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
        CommonUtil.checkMap(params);
        PageUtils page = sushexueshengService.queryPage(params);

        //字典表数据转换
        List<SushexueshengView> list =(List<SushexueshengView>)page.getList();
        for(SushexueshengView c:list){
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
        SushexueshengEntity sushexuesheng = sushexueshengService.selectById(id);
        if(sushexuesheng !=null){
            //entity转view
            SushexueshengView view = new SushexueshengView();
            BeanUtils.copyProperties( sushexuesheng , view );//把实体数据重构到view中
            //级联表 宿舍
            //级联表
            SusheEntity sushe = susheService.selectById(sushexuesheng.getSusheId());
            if(sushe != null){
            BeanUtils.copyProperties( sushe , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "xueshengId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setSusheId(sushe.getId());
            }
            //级联表 学生
            //级联表
            XueshengEntity xuesheng = xueshengService.selectById(sushexuesheng.getXueshengId());
            if(xuesheng != null){
            BeanUtils.copyProperties( xuesheng , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "xueshengId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setXueshengId(xuesheng.getId());
            }
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
    public R save(@RequestBody SushexueshengEntity sushexuesheng, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,sushexuesheng:{}",this.getClass().getName(),sushexuesheng.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            sushexuesheng.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<SushexueshengEntity> queryWrapper = new EntityWrapper<SushexueshengEntity>()
            .eq("sushe_id", sushexuesheng.getSusheId())
            .eq("xuesheng_id", sushexuesheng.getXueshengId())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SushexueshengEntity sushexueshengEntity = sushexueshengService.selectOne(queryWrapper);
        if(sushexueshengEntity==null){

            List<SushexueshengEntity> yizhuList = sushexueshengService.selectList(new EntityWrapper<SushexueshengEntity>()
                    .eq("sushe_id", sushexuesheng.getSusheId())
            );
            if(yizhuList.size()>=6){
                return R.error("该宿舍已经入住6人,不能同意此入住");
            }

            SushexueshengEntity sushexueshengEntity1 = sushexueshengService.selectOne(new EntityWrapper<SushexueshengEntity>()
                    .eq("xuesheng_id", sushexuesheng.getXueshengId())
            );
            if(sushexueshengEntity1 != null)
                return R.error("该学生已经在宿舍中,不能添加为当前宿舍成员");

            SushexueshengEntity sushexueshengEntity333 = sushexueshengService.selectOne(new EntityWrapper<SushexueshengEntity>()
                    .eq("sushe_id", sushexuesheng.getSusheId())
                    .eq("xuesheng_id", sushexuesheng.getXueshengId())
            );
            if(sushexueshengEntity333 != null)
                return R.error("该学生已经在该宿舍中");



            sushexuesheng.setInsertTime(new Date());
            sushexuesheng.setCreateTime(new Date());
            sushexueshengService.insert(sushexuesheng);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody SushexueshengEntity sushexuesheng, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,sushexuesheng:{}",this.getClass().getName(),sushexuesheng.toString());
        SushexueshengEntity oldSushexueshengEntity = sushexueshengService.selectById(sushexuesheng.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学生".equals(role))
//            sushexuesheng.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            sushexueshengService.updateById(sushexuesheng);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<SushexueshengEntity> oldSushexueshengList =sushexueshengService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        sushexueshengService.deleteBatchIds(Arrays.asList(ids));

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
            List<SushexueshengEntity> sushexueshengList = new ArrayList<>();//上传的东西
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
                            SushexueshengEntity sushexueshengEntity = new SushexueshengEntity();
//                            sushexueshengEntity.setSusheId(Integer.valueOf(data.get(0)));   //宿舍 要改的
//                            sushexueshengEntity.setXueshengId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            sushexueshengEntity.setInsertTime(date);//时间
//                            sushexueshengEntity.setCreateTime(date);//时间
                            sushexueshengList.add(sushexueshengEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        sushexueshengService.insertBatch(sushexueshengList);
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
        PageUtils page = sushexueshengService.queryPage(params);

        //字典表数据转换
        List<SushexueshengView> list =(List<SushexueshengView>)page.getList();
        for(SushexueshengView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SushexueshengEntity sushexuesheng = sushexueshengService.selectById(id);
            if(sushexuesheng !=null){


                //entity转view
                SushexueshengView view = new SushexueshengView();
                BeanUtils.copyProperties( sushexuesheng , view );//把实体数据重构到view中

                //级联表
                    SusheEntity sushe = susheService.selectById(sushexuesheng.getSusheId());
                if(sushe != null){
                    BeanUtils.copyProperties( sushe , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setSusheId(sushe.getId());
                }
                //级联表
                    XueshengEntity xuesheng = xueshengService.selectById(sushexuesheng.getXueshengId());
                if(xuesheng != null){
                    BeanUtils.copyProperties( xuesheng , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setXueshengId(xuesheng.getId());
                }
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
    public R add(@RequestBody SushexueshengEntity sushexuesheng, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,sushexuesheng:{}",this.getClass().getName(),sushexuesheng.toString());
        Wrapper<SushexueshengEntity> queryWrapper = new EntityWrapper<SushexueshengEntity>()
            .eq("sushe_id", sushexuesheng.getSusheId())
            .eq("xuesheng_id", sushexuesheng.getXueshengId())
//            .notIn("sushexuesheng_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SushexueshengEntity sushexueshengEntity = sushexueshengService.selectOne(queryWrapper);
        if(sushexueshengEntity==null){
            sushexuesheng.setInsertTime(new Date());
            sushexuesheng.setCreateTime(new Date());
        sushexueshengService.insert(sushexuesheng);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

