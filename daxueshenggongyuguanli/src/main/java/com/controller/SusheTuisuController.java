
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
 * 退宿申请
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/susheTuisu")
public class SusheTuisuController {
    private static final Logger logger = LoggerFactory.getLogger(SusheTuisuController.class);

    private static final String TABLE_NAME = "susheTuisu";

    @Autowired
    private SusheTuisuService susheTuisuService;


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
        CommonUtil.checkMap(params);
        PageUtils page = susheTuisuService.queryPage(params);

        //字典表数据转换
        List<SusheTuisuView> list =(List<SusheTuisuView>)page.getList();
        for(SusheTuisuView c:list){
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
        SusheTuisuEntity susheTuisu = susheTuisuService.selectById(id);
        if(susheTuisu !=null){
            //entity转view
            SusheTuisuView view = new SusheTuisuView();
            BeanUtils.copyProperties( susheTuisu , view );//把实体数据重构到view中
            //级联表 宿舍
            //级联表
            SusheEntity sushe = susheService.selectById(susheTuisu.getSusheId());
            if(sushe != null){
            BeanUtils.copyProperties( sushe , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "xueshengId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setSusheId(sushe.getId());
            }
            //级联表 学生
            //级联表
            XueshengEntity xuesheng = xueshengService.selectById(susheTuisu.getXueshengId());
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
    public R save(@RequestBody SusheTuisuEntity susheTuisu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,susheTuisu:{}",this.getClass().getName(),susheTuisu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            susheTuisu.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<SusheTuisuEntity> queryWrapper = new EntityWrapper<SusheTuisuEntity>()
            .eq("sushe_id", susheTuisu.getSusheId())
            .eq("xuesheng_id", susheTuisu.getXueshengId())
            .in("sushe_tuisu_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SusheTuisuEntity susheTuisuEntity = susheTuisuService.selectOne(queryWrapper);
        if(susheTuisuEntity==null){
            susheTuisu.setInsertTime(new Date());
            susheTuisu.setSusheTuisuYesnoTypes(1);
            susheTuisu.setCreateTime(new Date());
            susheTuisuService.insert(susheTuisu);
            return R.ok();
        }else {
            if(susheTuisuEntity.getSusheTuisuYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(susheTuisuEntity.getSusheTuisuYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody SusheTuisuEntity susheTuisu, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,susheTuisu:{}",this.getClass().getName(),susheTuisu.toString());
        SusheTuisuEntity oldSusheTuisuEntity = susheTuisuService.selectById(susheTuisu.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学生".equals(role))
//            susheTuisu.setXueshengId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            susheTuisuService.updateById(susheTuisu);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody SusheTuisuEntity susheTuisuEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,susheTuisuEntity:{}",this.getClass().getName(),susheTuisuEntity.toString());

        SusheTuisuEntity oldSusheTuisu = susheTuisuService.selectById(susheTuisuEntity.getId());//查询原先数据

        if(susheTuisuEntity.getSusheTuisuYesnoTypes() == 2){//通过

            SusheEntity susheEntity = susheService.selectById(oldSusheTuisu.getSusheId());
            if(susheEntity == null)
                return R.error("查不到宿舍");
            XueshengEntity xueshengEntity = xueshengService.selectById(oldSusheTuisu.getXueshengId());
            if(xueshengEntity == null)
                return R.error("查不到学生");


            SushexueshengEntity sushexueshengEntity = sushexueshengService.selectOne(new EntityWrapper<SushexueshengEntity>()
                    .eq("sushe_id", susheEntity.getId())
                    .eq("xuesheng_id", xueshengEntity.getId())
            );
            if(sushexueshengEntity == null)
                return R.error("该学生不在该宿舍中,无法同意退宿");

            sushexueshengService.deleteById(sushexueshengEntity.getId());
        }else if(susheTuisuEntity.getSusheTuisuYesnoTypes() == 3){//拒绝
        }
        susheTuisuEntity.setSusheTuisuShenheTime(new Date());//审核时间
        susheTuisuService.updateById(susheTuisuEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<SusheTuisuEntity> oldSusheTuisuList =susheTuisuService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        susheTuisuService.deleteBatchIds(Arrays.asList(ids));

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
            List<SusheTuisuEntity> susheTuisuList = new ArrayList<>();//上传的东西
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
                            SusheTuisuEntity susheTuisuEntity = new SusheTuisuEntity();
//                            susheTuisuEntity.setSusheTuisuUuidNumber(data.get(0));                    //申请编号 要改的
//                            susheTuisuEntity.setSusheId(Integer.valueOf(data.get(0)));   //宿舍 要改的
//                            susheTuisuEntity.setXueshengId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            susheTuisuEntity.setSusheTuisuText(data.get(0));                    //申请缘由 要改的
//                            susheTuisuEntity.setInsertTime(date);//时间
//                            susheTuisuEntity.setSusheTuisuYesnoTypes(Integer.valueOf(data.get(0)));   //申请状态 要改的
//                            susheTuisuEntity.setSusheTuisuYesnoText(data.get(0));                    //审核回复 要改的
//                            susheTuisuEntity.setSusheTuisuShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            susheTuisuEntity.setCreateTime(date);//时间
                            susheTuisuList.add(susheTuisuEntity);


                            //把要查询是否重复的字段放入map中
                                //申请编号
                                if(seachFields.containsKey("susheTuisuUuidNumber")){
                                    List<String> susheTuisuUuidNumber = seachFields.get("susheTuisuUuidNumber");
                                    susheTuisuUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> susheTuisuUuidNumber = new ArrayList<>();
                                    susheTuisuUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("susheTuisuUuidNumber",susheTuisuUuidNumber);
                                }
                        }

                        //查询是否重复
                         //申请编号
                        List<SusheTuisuEntity> susheTuisuEntities_susheTuisuUuidNumber = susheTuisuService.selectList(new EntityWrapper<SusheTuisuEntity>().in("sushe_tuisu_uuid_number", seachFields.get("susheTuisuUuidNumber")));
                        if(susheTuisuEntities_susheTuisuUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(SusheTuisuEntity s:susheTuisuEntities_susheTuisuUuidNumber){
                                repeatFields.add(s.getSusheTuisuUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [申请编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        susheTuisuService.insertBatch(susheTuisuList);
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
        PageUtils page = susheTuisuService.queryPage(params);

        //字典表数据转换
        List<SusheTuisuView> list =(List<SusheTuisuView>)page.getList();
        for(SusheTuisuView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        SusheTuisuEntity susheTuisu = susheTuisuService.selectById(id);
            if(susheTuisu !=null){


                //entity转view
                SusheTuisuView view = new SusheTuisuView();
                BeanUtils.copyProperties( susheTuisu , view );//把实体数据重构到view中

                //级联表
                    SusheEntity sushe = susheService.selectById(susheTuisu.getSusheId());
                if(sushe != null){
                    BeanUtils.copyProperties( sushe , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setSusheId(sushe.getId());
                }
                //级联表
                    XueshengEntity xuesheng = xueshengService.selectById(susheTuisu.getXueshengId());
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
    public R add(@RequestBody SusheTuisuEntity susheTuisu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,susheTuisu:{}",this.getClass().getName(),susheTuisu.toString());
        Wrapper<SusheTuisuEntity> queryWrapper = new EntityWrapper<SusheTuisuEntity>()
            .eq("sushe_tuisu_uuid_number", susheTuisu.getSusheTuisuUuidNumber())
            .eq("sushe_id", susheTuisu.getSusheId())
            .eq("xuesheng_id", susheTuisu.getXueshengId())
            .eq("sushe_tuisu_text", susheTuisu.getSusheTuisuText())
            .in("sushe_tuisu_yesno_types", new Integer[]{1,2})
            .eq("sushe_tuisu_yesno_text", susheTuisu.getSusheTuisuYesnoText())
//            .notIn("sushe_tuisu_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        SusheTuisuEntity susheTuisuEntity = susheTuisuService.selectOne(queryWrapper);
        if(susheTuisuEntity==null){
            susheTuisu.setInsertTime(new Date());
            susheTuisu.setSusheTuisuYesnoTypes(1);
            susheTuisu.setCreateTime(new Date());
        susheTuisuService.insert(susheTuisu);

            return R.ok();
        }else {
            if(susheTuisuEntity.getSusheTuisuYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(susheTuisuEntity.getSusheTuisuYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

}

